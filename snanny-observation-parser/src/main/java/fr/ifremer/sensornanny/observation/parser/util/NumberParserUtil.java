package fr.ifremer.sensornanny.observation.parser.util;

/**
 * Number util parser allow to parse String to number with safe usage
 * 
 * @author athorel
 *
 */
public final class NumberParserUtil {

    /**
     * Safe parse float
     * 
     * @param value value to parse
     * @return float value from string otherwise <code>null</code>
     */
    public static Float safeFloatValue(String value) {
        if (value != null) {
            try {
                return Float.valueOf(value);
            } catch (NumberFormatException e) {
                return null;
            }
        }
        return null;
    }

    /**
     * Safe parse integer
     * 
     * @param value value to parse
     * @return integer value from string otherwise <code>null</code>
     */
    public static Integer safeIntegerValue(String value) {
        if (value != null) {
            try {
                return Integer.parseInt(value);
            } catch (NumberFormatException e) {
                return null;
            }
        }
        return null;
    }

    /**
     * Safe parse double
     * 
     * @param value value to parse
     * @return integer value from string otherwise <code>null</code>
     */
    public static Double safeDoubleValue(String value) {
        if (value != null) {
            try {
                return Double.parseDouble(value);
            } catch (NumberFormatException e) {
                return null;
            }
        }
        return null;
    }
}
