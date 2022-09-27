package live.cilicili.util;

import lombok.extern.slf4j.Slf4j;
import java.util.Map;

/**
 * 字符串工具类
 * @author payne
 * @since 2022-09-01
 */
@Slf4j
public class StringUtils extends org.apache.commons.lang3.StringUtils{

    public static final char SEPARATOR = '_';
    public static final String SEMICOLON = ";";
    private static final String ENCODE_UTF = "UTF-8";
    public  static final String POINT = ".";

    public static String toString(Object o) {
        if (o == null) {
            return StringUtils.EMPTY;
        }
        return o.toString().trim();
    }

    public static String getString(String key, Map<String, Object> o) {
        return toString(o.get(key));
    }


    public static boolean isBank(Object o) {
        return StringUtils.isBlank(toString(o));
    }

    public static boolean isNotBank(Object o) {
        return StringUtils.isNotBlank(toString(o));
    }
}
