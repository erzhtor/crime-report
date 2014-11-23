package com.erzhan.crimereport.API;

/**
 * Created by Erzhan on 22-Nov 14.
 */
public class Constants {
    private static Constants ourInstance = new Constants();

    public static Constants getInstance() {
        return ourInstance;
    }
    public static String CRIME_ID = "crime_id";
    public static String DESCRIPTION = "description";
    public static String CATEGORY = "category";
    public static String DATE = "date";
    public static String TIME = "time";
    public static String POLICE_REPORT = "police_report";
    public static String LATITUDE = "latitude";
    public static String LONGITUDE = "longitude";

    public static final String URL = "http://sfw.auca.kg/~zufar/cr_my/Controller/Crime_controller.php";

    private Constants() {
    }
}
