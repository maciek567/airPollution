package parser.sensor;

import parser.sensor.Param;

/**
 * Information about sensor like its id, station id and parameter formula
 */
public class SensorInfo {

    private int id;
    private int stationId;
    private Param param;

    public int getId() {
        return id;
    }

    public int getStationId() {
        return stationId;
    }

    public Param getParam() {
        return param;
    }

    @Override
    public String toString() {
        return "id: " + String.valueOf(this.id) + '\n' +
                "parser.station.Station id: " + String.valueOf(this.stationId) + '\n' +
                "Parameter code:" + '\n' + this.param;
    }
}
