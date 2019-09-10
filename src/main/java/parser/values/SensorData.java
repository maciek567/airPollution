package parser.values;

import parser.values.Value;

import java.util.ArrayList;

/**
 * Class with defined parameter formula and list of records with date and value of measure
 */
public class SensorData {

    private String key;
    private ArrayList<Value> values;

    public String getKey() { return key; }
    public void setKey(String key) { this.key = key; }
    public ArrayList<Value> getValues() { return values; }
    public void setValues(ArrayList<Value> values) { this.values = values; }

    @Override
    public String toString() {
        String toReturn = "Key: " + this.key + "\n" + "Values: \n";
        for (int i=0; i<values.size(); i++) {
            toReturn += (this.values.get(i));
        }
        return toReturn;
    }

}
