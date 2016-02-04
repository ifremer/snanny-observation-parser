package fr.ifremer.sensornanny.observation.parser.momar;

import java.io.InputStream;
import java.util.function.Consumer;

import org.junit.Assert;
import org.junit.Test;

import fr.ifremer.sensornanny.observation.parser.ObservationData;
import fr.ifremer.sensornanny.observation.parser.TimePosition;

public class MomarObservationParserTest {

    private MomarObservationParser parser = new MomarObservationParser();

    @Test
    public void testParseMomarFile() {
        InputStream resource = getClass().getClassLoader().getResourceAsStream("momar/obs/optode2011.csv");
        parser.read(ObservationData.of("optode2011.csv", "txt/csv"), resource, new Consumer<TimePosition>() {

            @Override
            public void accept(TimePosition t) {
                Assert.assertNotNull(t);
            }
        });
    }
}
