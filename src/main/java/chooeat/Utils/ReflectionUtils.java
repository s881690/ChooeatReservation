package chooeat.Utils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ReflectionUtils {
    public static Map<String, Object> getObjectValues(Object obj) {
        Map<String, Object> valuesMap = new HashMap<>();

        Class<?> clazz = obj.getClass();
        Field[] fields = clazz.getDeclaredFields();

        for (Field field : fields) {
            field.setAccessible(true);
            String fieldName = field.getName();
            Object fieldValue;

            try {
                fieldValue = field.get(obj);
                if (valuesMap.containsKey(fieldName)) {
                    Object existingValue = valuesMap.get(fieldName);
                    if (existingValue instanceof List<?>) {
                        // 如果屬性已經是 List，將新值加入到 List 中
                        List<Object> valueList = (List<Object>) existingValue;
                        valueList.add(fieldValue);
                    } else {
                        // 如果屬性只有單一值，創建新的 List，並加入先前的值和新值
                        List<Object> valueList = new ArrayList<>();
                        valueList.add(existingValue);
                        valueList.add(fieldValue);
                        valuesMap.put(fieldName, valueList);
                    }
                } else {
                    // 如果屬性只有單一值，直接加入到 Map 中
                    valuesMap.put(fieldName, fieldValue);
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }

        return valuesMap;
    }
}
