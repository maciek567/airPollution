package parser.station;

/**
 * Detailed information about city where a station exists
 */
public class Commune {

    private String communeName;
    private String districtName;
    private String provinceName;

    @Override
    public String toString() {
        return "\t\tparser.station.Commune name: " + this.communeName + '\n' +
                "\t\tDistrict name: " +this.districtName + '\n' +
                "\t\tProvince name: " + this.provinceName;
    }
}
