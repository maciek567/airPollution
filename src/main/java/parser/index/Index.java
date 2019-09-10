package parser.index;

/**
 * parser.values.Value of current main air pollution index and indexes of specified parameters
 */
public class Index {

    private int id;

    private String stCalcDate;
    private IndexLevel stIndexLevel;
    private String stSourceDataDate;
    private Boolean stIndexStatus;
    private String stIndexCrParam;

    private String so2CalcDate;
    private IndexLevel so2IndexLevel;
    private String so2SourceDataDate;

    private String no2CalcDate;
    private IndexLevel no2IndexLevel;
    private String no2SourceDataDate;

    private String coCalcDate;
    private IndexLevel coIndexLevel;
    private String coSourceDataDate;

    private String pm10CalcDate;
    private IndexLevel pm10IndexLevel;
    private String pm10SourceDataDate;

    private String pm25CalcDate;
    private IndexLevel pm25IndexLevel;
    private String pm25SourceDataDate;

    private String o3CalcDate;
    private IndexLevel o3IndexLevel;
    private String o3SourceDataDate;

    private String c6h6CalcDate;
    private IndexLevel c6h6IndexLevel;
    private String c6h6SourceDataDate;

    public IndexLevel getStIndexLevel() {
        return stIndexLevel;
    }

    public IndexLevel getSo2IndexLevel() {
        return so2IndexLevel;
    }

    public IndexLevel getNo2IndexLevel() {
        return no2IndexLevel;
    }

    public IndexLevel getCoIndexLevel() {
        return coIndexLevel;
    }

    public IndexLevel getPm10IndexLevel() {
        return pm10IndexLevel;
    }

    public IndexLevel getPm25IndexLevel() {
        return pm25IndexLevel;
    }

    public IndexLevel getO3IndexLevel() {
        return o3IndexLevel;
    }

    public IndexLevel getC6h6IndexLevel() {
        return c6h6IndexLevel;
    }

    @Override
    public String toString() {
        return "id: " + this.id +
                "\nstIndexLevel:\n" + this.stIndexLevel + "\nstSourceDataDate: " + this.stSourceDataDate + "\nstCalcDate: " + this.stCalcDate +
                "\nstIndexStatus: " + this.stIndexStatus + "\nstIndexCrParam: " + this.stIndexCrParam +

                "\n\nso2IndexLevel:\n" + this.so2IndexLevel + "\nso2SourceDataDate: " +
                this.so2SourceDataDate + "\nso2CalcDate: " + this.so2CalcDate +

                "\n\nno2IndexLevel:\n" + this.no2IndexLevel + "\nno2SourceDataDate: " +
                this.no2SourceDataDate + "\nno2CalcDate: " + this.no2CalcDate +

                "\n\ncoIndexLevel:\n" + this.coIndexLevel + "\ncoSourceDataDate: " +
                this.coSourceDataDate + "\ncoCalcDate: " + this.coCalcDate +

                "\n\npm10IndexLevel:\n" + this.pm10IndexLevel + "\npm10SourceDataDate: " +
                this.pm10SourceDataDate + "\npm10CalcDate: " + this.pm10CalcDate +

                "\n\npm25IndexLevel:\n" + this.pm25IndexLevel + "\npm25SourceDataDate: " +
                this.pm25SourceDataDate + "\npm25CalcDate: " + this.pm25CalcDate +

                "\n\no3IndexLevel:\n" + this.o3IndexLevel + "\no3SourceDataDate: " +
                this.o3SourceDataDate + "\no3CalcDate: " + this.o3CalcDate +

                "\n\nc6h6IndexLevel:\n" + this.c6h6IndexLevel + "\nc6h6SourceDataDate: " +
                this.c6h6SourceDataDate + "\nc6h6CalcDate: " + this.c6h6CalcDate;
    }
}
