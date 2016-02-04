package fr.ifremer.sensornanny.observation.parser.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * That class allow to parse date using known pattern
 * 
 * @author athorel
 *
 */
public final class DateParserUtil {

    private static final String EMPTY = "";
    private static final String ISO_DATETIME_8601 = "yyyy-MM-dd'T'HH:mm:ss";
    private static final String ISO_DATE_8601 = "yyyy-MM-dd";
    private static final String ISO_DATETIME = "yyyy-MM-dd HH:mm:ss";

    /**
     * Declaration of threadlocal to ensure threadsafe usage
     */
    private static ThreadLocal<Map<String, SimpleDateFormat>> formatter = new ThreadLocal<Map<String, SimpleDateFormat>>() {

        protected Map<String, SimpleDateFormat> initialValue() {
            Map<String, SimpleDateFormat> map = new HashMap<String, SimpleDateFormat>();
            map.put(ISO_DATETIME_8601, new SimpleDateFormat(ISO_DATETIME_8601));
            map.put(ISO_DATE_8601, new SimpleDateFormat(ISO_DATE_8601));
            map.put(ISO_DATETIME, new SimpleDateFormat(ISO_DATETIME));
            return map;
        }

    };

    /**
     * Safe parse with specific date in known format
     * 
     * @param date date in string format
     * @return date object if parser match otherwise <code>null</code>
     */
    public static Date parse(String date) {
        if (date != null && !EMPTY.equals(date)) {
            Date ret = null;
            Collection<SimpleDateFormat> list = formatter.get().values();
            for (SimpleDateFormat simpleDateFormat : list) {
                ret = safeParse(date, simpleDateFormat);
                if (ret != null) {
                    return ret;
                }
            }
        }
        return null;
    }

    /**
     * Safe parse date with a simple format
     * 
     * @param date date to parse
     * @param date formatting
     * @return date date object if pattern match string otherwise <code>null</code>
     */
    private static Date safeParse(String date, SimpleDateFormat format) {
        try {
            return format.parse(date);
        } catch (ParseException e) {
            return null;
        }
    }

}
