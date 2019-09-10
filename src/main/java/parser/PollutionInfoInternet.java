package parser;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import parser.index.Index;
import parser.sensor.SensorInfo;
import parser.station.Station;
import parser.values.SensorData;

import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Collection;
import java.util.List;

/**
 * class that allows to download pollution level from web api (http://api.gios.gov.pl) and analyse that data
 */
public class PollutionInfoInternet implements PollutionInfo{

    /**
     * a gson object that allows to deserialize JSONs download from http://api.gios.gov.pl site
     */
   private Gson gson;

    /**
     * a list with all defined stations
     */
   private List<Station> stations;

    /**
     * Constructor that create new gson object and load list of all possible stations
     * @throws IOException - if connection with api is impossible or required data does not exist at given address
     */
   public PollutionInfoInternet() throws IOException {
       gson = new GsonBuilder().setDateFormat("yyyy-MM-dd kk:mm:ss").create();
       stations = gson.fromJson(new FileReader("src/main/resources/json/findAll.json"), new TypeToken<Collection<Station>>(){}.getType());
   }


    /**
     * search for station name corresponding to its unique number
     * @param stationNumber - number of station
     * @return stationName - name (string) of station
     */
    public String getStationNameByNumber(int stationNumber) {
        for(Station station: stations) {
            if(station.getId() == stationNumber) {
                return station.getStationName();
            }
        }
        return null;
    }


    /**
     * get pollution index by station number
     * @param stationNumber - number of station
     * @return index - basic information about pollution of every available pollution type at given station
     * @throws IOException - if connection with api is impossible or required data does not exist at given address
     */
    @Override
    public Index getIndex(int stationNumber) throws IOException {
        URL indexPathURL = new URL("http://api.gios.gov.pl/pjp-api/rest/aqindex/getIndex/" + stationNumber);
        InputStreamReader reader = new InputStreamReader(indexPathURL.openStream());
        return gson.fromJson(reader, Index.class);
    }


    /**
     * get detailed measurements from given sensor of given station (stationNumber)
     * @param stationNumber - number of station
     * @param parameter - name of considering pollution
     * @return sensorData - detailed information about considered pollution during last 48 hours at given station
     * @throws IOException - if connection with api is impossible or required data does not exist at given address
     */
    @Override
    public SensorData getSensorMeasurements(int stationNumber, String parameter) throws IOException {
        // find proper file with description of available sensors in given station
        URL sensorsDescriptionURL = new URL("http://api.gios.gov.pl/pjp-api/rest/station/sensors/" + stationNumber);
        InputStreamReader reader = new InputStreamReader(sensorsDescriptionURL.openStream());
        List <SensorInfo> sensors = gson.fromJson(reader, new TypeToken<Collection<SensorInfo>>(){}.getType());

        // find index of sensor with given parameter
        int sensorIndex = -1;
        for(SensorInfo sensor : sensors) {
            if(sensor.getParam().getParamFormula().equals(parameter)) {
                sensorIndex = sensor.getId();
                break;
            }
        }
        // load file with statistics of given sensor
        URL sensorDataURL = new URL("http://api.gios.gov.pl/pjp-api/rest/data/getData/" + sensorIndex);
        InputStreamReader reader2 = new InputStreamReader(sensorDataURL.openStream());
        return gson.fromJson(reader2, SensorData.class);
    }



    /*   okHttp prototype:

    @Override
    public Index getIndex(int stationNumber) throws IOException {

        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("http://api.gios.gov.pl/pjp-api/rest/aqindex/getIndex" + stationNumber)
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                System.out.println(e.getMessage());
            }
            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                String json = Objects.requireNonNull(response.body()).string();
                return new Index(gson.fromJson(json, Index.class));
            }
        });
    }

    @Override
    public SensorData getSensorMeasurements(int stationNumber, String parameter) throws IOException {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("http://api.gios.gov.pl/pjp-api/rest/station/sensors/" + stationNumber)
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                System.out.println(e.getMessage());
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                String json = Objects.requireNonNull(response.body()).string();
                Type type = new TypeToken<Collection<SensorInfo>>() {
                }.getType();
                List<SensorInfo> sensors = gson.fromJson(json, type);

                // find index of sensor with given parameter
                int sensorIndex = -1;
                for (SensorInfo sensor : sensors) {
                    if (sensor.getParam().getParamFormula().equals(parameter)) {
                        sensorIndex = sensor.getId();
                        break;
                    }
                }
                // load file with statistics of given sensor
                URL sensorDataURL = new URL("http://api.gios.gov.pl/pjp-api/rest/data/getData/" + sensorIndex);
                InputStreamReader reader2 = new InputStreamReader(sensorDataURL.openStream());
                return gson.fromJson(reader2, SensorData.class);
            }
        });
    }

    */


}



