package parser;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import parser.index.Index;
import parser.sensor.SensorInfo;
import parser.station.Station;
import parser.values.SensorData;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Collection;
import java.util.List;

/**
 * Class that operates on local files  and analyse that data
 */
public class PollutionInfoLocal implements PollutionInfo{
    private Gson gson;
    private List<Station> stations;

    public PollutionInfoLocal() throws FileNotFoundException {
        gson = new GsonBuilder().setDateFormat("yyyy-MM-dd kk:mm:ss").create();
        stations = gson.fromJson(new FileReader("src/main/resources/json/findAll.json"), new TypeToken<Collection<Station>>(){}.getType());
    }



    public String getStationNameByNumber(int stationNumber) {

        // find station name with given number
        for(Station station: stations) {
            if(station.getId() == stationNumber) {
                return station.getStationName();
            }
        }
        return null;
    }


    // get pollution index by station number
    public Index getIndex(int stationNumber) throws FileNotFoundException {

        // find proper file and air pollution index
        String indexPath = "src/main/resources/json/index/" + stationNumber + ".json";
        return gson.fromJson(new FileReader(indexPath), Index.class);
    }


    // get detailed measurements from given sensor of given station (stationNumber)
    public SensorData getSensorMeasurements(int stationNumber, String parameter) throws FileNotFoundException {
        // find proper file with description of available sensors in given station
        String sensorsDescriptionPath = "src/main/resources/json/sensorInfo/" + stationNumber + ".json";
        List <SensorInfo> sensors = gson.fromJson(new FileReader(sensorsDescriptionPath), new TypeToken<Collection<SensorInfo>>(){}.getType());

        // find index of sensor with given parameter
        int sensorIndex = -1;
        for(SensorInfo sensor : sensors) {
            if(sensor.getParam().getParamFormula().equals(parameter)) {
                sensorIndex = sensor.getId();
                break;
            }
        }
        // load file with statistics of given sensor
        String sensorDataPath = "src/main/resources/json/sensorData/" + sensorIndex + ".json";
        return gson.fromJson(new FileReader(sensorDataPath), SensorData.class);
    }

}
