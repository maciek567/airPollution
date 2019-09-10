package parser.index;

/**
 * Class with object storing air pollution index and its name
 */
public class IndexLevel {

    private int id;
    private String indexLevelName;

    public int getId() {
        return id;
    }

    public String getIndexLevelName() {
        return indexLevelName;
    }

    @Override
    public String toString() {
        return "\tid: " + this.id + "\n\tindexLevelName: " + this.indexLevelName;
    }
}
