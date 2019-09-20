package parser;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import parser.index.Index;

import java.io.IOException;

import static org.junit.Assert.*;

public class PollutionInfoInternetTest {

    private PollutionInfo pollutionInfo;

    @Before
    public void setUp() throws IOException {
        pollutionInfo = new PollutionInfoInternet();
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

}