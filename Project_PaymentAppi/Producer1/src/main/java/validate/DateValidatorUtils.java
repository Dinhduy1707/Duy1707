package validate;

import lombok.Getter;
import lombok.Setter;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

@Getter
@Setter
public class DateValidatorUtils {
    private String dateFormat;

    private DateValidatorUtils() {

    }

    public static boolean checkFormat(String dateStr, String dateFormat) {
        DateFormat format = new SimpleDateFormat(dateFormat);
        format.setLenient(false);
        try {
            format.parse(dateStr);
        } catch (ParseException e) {
            return false;
        }
        return true;
    }
}
