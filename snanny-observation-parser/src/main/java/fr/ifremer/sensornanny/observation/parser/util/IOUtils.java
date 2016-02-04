package fr.ifremer.sensornanny.observation.parser.util;

import java.io.Closeable;
import java.io.IOException;

/**
 * Util class to close a closeable item
 * 
 * @author athorel
 *
 */
public final class IOUtils {

    /**
     * close quietly an item
     * 
     * @param closeable closeable item
     */
    public static void closeQuietly(Closeable closeable) {
        // Try if the item isn't null
        if (closeable != null) {
            try {
                closeable.close();
            } catch (IOException e) {
                // Nothing todo
            }
        }
    }

    public static void closeQuietly(AutoCloseable closeable) {
        // Try if the item isn't null
        if (closeable != null) {
            try {
                closeable.close();
            } catch (Exception e) {
                // Nothing todo
            }
        }
    }

}
