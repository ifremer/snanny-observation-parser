package fr.ifremer.sensornanny.observation.parser.netcdf;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.util.function.Consumer;

import org.junit.Assert;
import org.junit.Test;

import fr.ifremer.sensornanny.observation.parser.ObservationData;
import fr.ifremer.sensornanny.observation.parser.TimePosition;

public class NetCDFObservationParserTest {

    private NetCdfObservationParser parser = new NetCdfObservationParser();

    @Test
    public void testOpenFile() throws MalformedURLException, IOException {
        String file = "netcdf/201304010045-shipnav-TL_CINNA.nav";
        InputStream stream = load(file);
        String fileName = "201304010045-shipnav-TL_CINNA.nav";
        ObservationData of = ObservationData.of(fileName, "application/netcdf");
        Assert.assertTrue(parser.accept(of));
        parser.read(of, stream, new Consumer<TimePosition>() {

            @Override
            public void accept(TimePosition t) {
                Assert.assertNotNull(t);
            }
        });
    }

    @Test
    public void testOpenFileNotNav() throws MalformedURLException, IOException {

        InputStream stream = load("sensorML/atalante_sensorML.xml");
        String fileName = "atalante_sensorML.xml";
        ObservationData of = ObservationData.of(fileName, "text/xml");
        Assert.assertFalse(parser.accept(of));
        parser.read(of, stream, new Consumer<TimePosition>() {

            @Override
            public void accept(TimePosition t) {
                Assert.fail("Shouldn't passed here");
            }
        });
    }

    private InputStream load(String file) {
        return getClass().getClassLoader().getResourceAsStream(file);
    }
}
