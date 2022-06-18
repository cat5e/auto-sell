package autosell.utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public final class DateUtil {
    public static final String DATE_FORMAT = "dd/MM/yyyy";
    public static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern(DATE_FORMAT);
    
    public static String getDateFormated(LocalDate date) {
        if(date == null)
            return "";
        
        return date.format(DATE_FORMATTER);
    }
    
    public static LocalDate parseLocalDate(String date){
        try {
            return LocalDate.parse(date, DATE_FORMATTER);
        } catch (Exception e) {
            return LocalDate.MIN;
        }
    }
}
