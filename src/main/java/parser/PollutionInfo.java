package parser;

import parser.index.Index;
import parser.values.SensorData;

import java.io.IOException;

public interface PollutionInfo {
    String getStationNameByNumber(int stationNumber);
    Index getIndex(int stationNumber) throws IOException;
    SensorData getSensorMeasurements(int stationNumber, String parameter) throws IOException;
}
