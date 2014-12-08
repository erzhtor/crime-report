package com.erzhan.crimereport.API;

import com.erzhan.crimereport.R;

import java.util.Calendar;

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

    public static String COMMENTOR_NAME = "commentor_name";
    public static String COMMENT_TEXT = "comment";


//    public static final String crime_controller_url = "http://sfw.auca.kg:8080/~zufar/cr_my/Controller/Crime_controller.php";
//    public static final String comment_controller_url = "http://sfw.auca.kg:8080/~zufar/cr_my/Controller/Comment_controller.php";

    public static final String crime_controller_url = "http://sfw.auca.kg/~zufar/cr_my/Controller/Crime_controller.php";
    public static final String comment_controller_url = "http://sfw.auca.kg/~zufar/cr_my/Controller/Comment_controller.php";

    public static final String CrimeJsonObject = "CrimeJsonObject";
    private Constants() {
    }

    public static int getCrimeCategoryStringID(int type)
    {
        switch (type)
        {
            case 1:
                return R.string.crime_category_1;
            case 2:
                return R.string.crime_category_2;
            case 3:
                return R.string.crime_category_3;
            case 4:
                return R.string.crime_category_4;
            case 5:
                return R.string.crime_category_5;
            case 6:
                return R.string.crime_category_6;
            default:
                return R.string.crime_category_7;
        }
    }
    public static String normalizeDateAndTime(String date, String time)
    {
        String res = "";
        String[] d = date.split("-");
        String[] t = time.split(":");

//        Calendar.getInstance().get
        return res;
    }
}
