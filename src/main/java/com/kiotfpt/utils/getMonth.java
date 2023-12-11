package com.kiotfpt.utils;
import java.text.SimpleDateFormat;
import java.util.Date;

public class getMonth {
    public int getMonthFromDateTime(Date date) {
        int month = 0;

        try {
            // Create a date format to convert Date to String
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            
            // Convert Date to String
            String dateTimeString = dateFormat.format(date);

            // Extract the month
            if (dateTimeString != null) {
                // Split the date and time components
                String[] dateTimeParts = dateTimeString.split(" ");
                
                // Split the date into year, month, and day
                String[] dateParts = dateTimeParts[0].split("-");
                
                // Extract the month
                if (dateParts.length >= 2) {
                    month = Integer.parseInt(dateParts[1]);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return month;
    }
}
