/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package groupon;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 *
 * @author ramans4447
 */
public class DateTime {
    public static final String DATE_FORMAT = "EEE, d MMM yyyy HH:mm:ss"; //EEE -> 3 letters for month, d -> min 1 digit for day of month
    
    public static String DateandTime(){
        Calendar cal=Calendar.getInstance();    //To get system date and time
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);   //To return system date and time as a string
        return sdf.format(cal.getTime());
    }
}
