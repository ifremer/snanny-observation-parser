package fr.ifremer.sensornanny.observation.parser;

import java.io.InputStream;
import java.util.function.Consumer;

/**
 * Interface of observation parser
 * 
 * @author athorel
 */
public interface IObservationParser {

    /**
     * This method allow to read element
     * 
     * @param data informations about file and inputstream to read
     * @param stream of the file
     * @param consumer Consumer of parser
     * 
     */
    void read(ObservationData data, InputStream stream, Consumer<TimePosition> consumer);

    /**
     * This method verify if a parser accept the file
     * 
     * @param data informations about file and inputstream to read
     * @return <code>true</code> if the file is accepted by the parser otherwise <code>false</code>
     */
    boolean accept(ObservationData data);
}
