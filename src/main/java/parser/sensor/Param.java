package parser.sensor;

/**
 * Specified parameter name, its formula and code
 */
public class Param {

    private String paramName;
    private String paramFormula;
    private String paramCode;
    private int idParam;

    public String getParamName() {
        return paramName;
    }

    public String getParamFormula() {
        return paramFormula;
    }

    public String getParamCode() {
        return paramCode;
    }

    public int getIdParam() {
        return idParam;
    }

    @Override
    public String toString() {
        return "\tParameter name: " + this.paramName + '\n' +
                "\tParameter formula: " +this.paramFormula + '\n' +
                "\tParameter code: "  + this.paramCode + '\n' +
                "\tParameter id: " + String.valueOf(this.idParam) + '\n';
    }
}
