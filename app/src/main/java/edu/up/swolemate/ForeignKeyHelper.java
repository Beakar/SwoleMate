package edu.up.swolemate;

/**
 * Created by Nathan on 3/3/2015.
 */
public class ForeignKeyHelper {

    public String fieldName;
    public String homeTable;
    public String homeProperty;

    public ForeignKeyHelper(String fieldName, String homeTable, String homeProperty) {
        this.fieldName = fieldName;
        this.homeTable = homeTable;
        this.homeProperty = homeProperty;
    }

}
