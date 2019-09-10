package parser.station;

/**
 * parser.station.City where a station exists
 */
public class City {

    private int id;
    private String name;
    private Commune commune;

    @Override
    public String toString() {
        return "\tid: " + String.valueOf(this.id) + '\n' +
                "\tname: " +this.name + '\n' +
                "\tparser.station.Commune:" + '\n' + this.commune;
    }
}
