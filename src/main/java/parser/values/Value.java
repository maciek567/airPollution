package parser.values;

import java.util.Date;

/**
 * Class with objects that stores a single record: date and value of measure
 */
public class Value {

    private Date date;
    private Double value;

    public Double getValue() {
        return value;
    }

    public Date getDate() { return date; }

    @Override
    public String toString() {
        return "\tDate: " + this.date + "\n\tValue: " + this.value + '\n';
    }


}
