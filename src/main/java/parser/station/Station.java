package parser.station;

import parser.station.City;

/**
 * Class with objects representing a single station
 */
public class Station {

    private int id;
    private String stationName;
    private double gegrLat;
    private double gegrLon;
    private City city;
    private String addressStreet;

    public int getId() {
        return id;
    }

    public String getStationName() {
        return stationName;
    }

    public double getGegrLat() {
        return gegrLat;
    }

    public double getGegrLon() {
        return gegrLon;
    }

    public City getCity() {
        return city;
    }

    public String getAddressStreet() {
        return addressStreet;
    }

    @Override
    public String toString() {
        return ("id: " + this.id + '\n' + "parser.station.Station name: " + this.stationName + '\n' +
                "Geographical latitude: " + this.gegrLat + '\n' + "Geographical longitude: " +  this.gegrLon + '\n' +
                "parser.station.City:" + '\n' + this.city + '\n' + "Address street: " + this.addressStreet+ '\n');
    }
}
