package fr.ifremer.sensornanny.observation.parser.momar;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.function.Consumer;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import fr.ifremer.sensornanny.observation.parser.IObservationParser;
import fr.ifremer.sensornanny.observation.parser.ObservationData;
import fr.ifremer.sensornanny.observation.parser.TimePosition;
import fr.ifremer.sensornanny.observation.parser.util.DateParserUtil;
import fr.ifremer.sensornanny.observation.parser.util.IOUtils;
import fr.ifremer.sensornanny.observation.parser.util.NumberParserUtil;

/**
 * Concrete parser for momar observation file
 * 
 * @author athorel
 *
 */
public class MomarObservationParser implements IObservationParser {

    private static final List<String> ACCEPTED_FORMAT = Arrays.asList("txt/csv", "text/csv");
    private static final int LON_INDEX = 3;
    private static final int LAT_INDEX = 2;
    private static final int DATE_INDEX = 1;

    public void read(ObservationData data, InputStream stream, final Consumer<TimePosition> consumer) {
        CSVParser parser = null;
        try {
            parser = new CSVParser(new InputStreamReader(stream), CSVFormat.DEFAULT);
            Iterator<CSVRecord> iterator = parser.iterator();

            if (iterator.hasNext()) {
                // skip header
                iterator.next();
            }

            // ReadData
            iterator.forEachRemaining(new Consumer<CSVRecord>() {

                public void accept(CSVRecord t) {

                    TimePosition timePosition = new TimePosition();
                    timePosition.setDate(DateParserUtil.parse(t.get(DATE_INDEX)));
                    timePosition.setLatitude(NumberParserUtil.safeFloatValue(t.get(LAT_INDEX)));
                    timePosition.setLongitude(NumberParserUtil.safeFloatValue(t.get(LON_INDEX)));
                    timePosition.setRecordNumber(t.getRecordNumber());

                    consumer.accept(timePosition);
                }

            });

        } catch (Exception e) {
            System.out.println("Exception lors du chargement du fichier  " + data.getFileName());
            e.printStackTrace();
        } finally {
            IOUtils.closeQuietly(parser);
        }
    }

    public boolean accept(ObservationData data) {
        if (data.getMimeType() != null) {
            return ACCEPTED_FORMAT.contains(data.getMimeType().toLowerCase());
        }
        return false;
    }
}
