package chooeat.Utils;
import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

public class CustomTimeSerializer implements JsonSerializer<Date> {
    @Override
    public JsonElement serialize(Date time, Type type, JsonSerializationContext context) {
        SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
        return new JsonPrimitive(format.format(time));
    }
}
