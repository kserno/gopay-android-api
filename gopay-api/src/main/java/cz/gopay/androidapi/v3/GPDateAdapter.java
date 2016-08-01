package cz.gopay.androidapi.v3;

import com.google.gson.JsonSyntaxException;
import com.google.gson.TypeAdapter;
import com.google.gson.internal.bind.util.ISO8601Utils;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;

public class GPDateAdapter extends TypeAdapter<Date> {

    private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    private SimpleDateFormat timestampFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");

    @Override
    public void write(JsonWriter out, Date value) throws IOException {
        if (value == null) {
            out.nullValue();
            return;
        }
        String dateFormatAsString = dateFormat.format(value);
        out.value(dateFormatAsString);
    }

    @Override
    public Date read(JsonReader in) throws IOException {
        String date = in.nextString();
        try {
            return dateFormat.parse(date);
        } catch (ParseException ex) {
            try {
                return timestampFormat.parse(date);
            } catch (ParseException eex) {
                throw new JsonSyntaxException("Can't parse dateformat - " + date, eex);
            }
        }
    }

}
