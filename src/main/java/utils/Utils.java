package utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Utils {
    public static Integer getCurrentDate() {
        DateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy");
        Date date = new Date();

        return Integer.valueOf(dateFormat.format(date).split("-")[1].split("-")[0]);
    }
}
