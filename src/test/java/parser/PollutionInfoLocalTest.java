package parser;

import org.junit.Before;
import org.junit.Test;
import parser.index.Index;
import parser.values.SensorData;
import parser.values.Value;

import java.io.IOException;
import java.util.ArrayList;

import static org.junit.Assert.*;

public class PollutionInfoLocalTest {

    private PollutionInfo pollutionInfo;

    @Before
    public void setUp() throws Exception {
        pollutionInfo = new PollutionInfoLocal();
    }

    @Test
    public void getStationNameByNumberWroclaw() throws IOException {
        assertEquals("Wroc\u0142aw - Bartnicza", pollutionInfo.getStationNameByNumber(114));
    }
    @Test
    public void getStationNameByNumberKrakow() throws IOException {
        assertEquals("Krak\u00F3w, Aleja Krasi\u0144skiego", pollutionInfo.getStationNameByNumber(400));
    }
    @Test
    public void getStationNameByNumberWarsaw() throws IOException {
        assertEquals("Warszawa-To\u0142stoja", pollutionInfo.getStationNameByNumber(538));
    }


    @Test
    public void getIndexKrasinskiegoStation() throws IOException {
        Index index = pollutionInfo.getIndex(400); // "Kraków, Aleja Krasińskiego"
        assertEquals(1, index.getNo2IndexLevel().getId());
        assertEquals(0, index.getCoIndexLevel().getId());
    }

    @Test
    public void getIndexBulwarowaStation() throws IOException {
        Index index = pollutionInfo.getIndex(402); // "Kraków, ul. Bulwarowa"
        assertEquals(0, index.getPm10IndexLevel().getId());
        assertEquals(0, index.getC6h6IndexLevel().getId());
    }

    @Test
    public void getIndexPiastowStation() throws IOException {
        Index index = pollutionInfo.getIndex(10139); // "Kraków, os. Piastów"
        assertEquals(-1, index.getStIndexLevel().getId());
        assertEquals(0, index.getPm10IndexLevel().getId());
    }


    @Test
    public void getSensorMeasurementsBujakaStationCoSensor() throws IOException { //"Kraków, ul. Bulwarowa, PM10 - 2770"
        SensorData sensorData = pollutionInfo.getSensorMeasurements(401, "PM10");
        ArrayList<Value> values = sensorData.getValues();

        assertEquals(27.5874, values.get(1).getValue(), 0.0001);
        assertEquals(18.2006, values.get(20).getValue(), 0.0001);
        assertEquals(20.2374, values.get(61).getValue(), 0.0001);
    }

    @Test
    public void getSensorMeasurementsBujakaStationPm25Sensor() throws IOException { //"Kraków, ul. Bulwarowa, PM25 - 2772"
        SensorData sensorData = pollutionInfo.getSensorMeasurements(401, "PM2.5");
        ArrayList<Value> values = sensorData.getValues();

        assertEquals(18.9456, values.get(5).getValue(), 0.0001);
        assertEquals(7.36171, values.get(40).getValue(), 0.0001);
        assertEquals(14.2976, values.get(60).getValue(), 0.0001);

    }
}