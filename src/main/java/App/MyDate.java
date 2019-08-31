package App;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MyDate extends Date {

    public static final String datePattern = "MM/dd/yyyy HH:mm:ss";

    @Override
    public String toString(){
        DateFormat df = new SimpleDateFormat(datePattern);
        return df.format(this);
    }
}
