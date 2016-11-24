package be.howest.junglewars.data.util;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class EnumStringConverter {

    public static <E extends Enum<E>> E[] stringToEnumArray(Class<E> enumType, String enumStrings) {
        String[] stringAsArray = enumStrings.split(",");
        for (int i = 0; i < stringAsArray.length; i++) {
            stringAsArray[i] = stringAsArray[i].trim().toUpperCase();
        }

        ArrayList<E> stringAsEnumArray = new ArrayList<E>();
        E[] allEnumsOfType = enumType.getEnumConstants();

        List<String> allEnumsOfTypeAsString = new ArrayList<>();
        for (E value : allEnumsOfType) {
            allEnumsOfTypeAsString.add(value.name());
        }

        for (String string : stringAsArray) {
            if (allEnumsOfTypeAsString.contains(string)) {
                stringAsEnumArray.add(E.valueOf(enumType, string));
            }
        }

        E[] stringAsEnums = (E[]) Array.newInstance(enumType, stringAsEnumArray.size());
        return stringAsEnumArray.toArray(stringAsEnums);
    }

    public static <E extends Enum<E>> String enumArrayToString(E[] enumArray) {
        ArrayList<String> stringArray = new ArrayList<>();
        for (E e : enumArray) {
            stringArray.add(e.name());
        }
        return String.join(",", stringArray);
    }

}
