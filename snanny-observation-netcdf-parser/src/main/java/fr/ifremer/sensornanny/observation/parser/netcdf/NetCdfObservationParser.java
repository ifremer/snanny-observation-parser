package fr.ifremer.sensornanny.observation.parser.netcdf;

import java.io.InputStream;
import java.util.Date;
import java.util.function.Consumer;

import fr.ifremer.sensornanny.observation.parser.ObservationData;
import fr.ifremer.sensornanny.observation.parser.TimePosition;
import fr.ifremer.sensornanny.observation.parser.util.IOUtils;
import ucar.ma2.Array;
import ucar.nc2.NetcdfFile;

/**
 * Concrete parser for momar observation file
 * 
 * @author athorel
 *
 */
public class NetCdfObservationParser implements fr.ifremer.sensornanny.observation.parser.IObservationParser {

    private static final String DEPTH_SECTION = "depth";

    private static final String TIME_SECTION = "time";

    private static final String LAT_SECTION = "lat";

    private static final String LONG_SECTION = "long";

    private static final String ACCEPTED_FORMAT = "application/netcdf";

    private static final double START_TIME_NETCDF = 2209165199011d;
    private static final int HOURS_IN_MILLIS = 24 * 60 * 60 * 1000;

    @Override
    public void read(ObservationData data, InputStream stream, Consumer<TimePosition> consumer) {
        NetcdfFile cdfFile = null;
        try {
            cdfFile = NetcdfFile.openInMemory(data.getFileName(), ucar.nc2.util.IO.readContentsToByteArray(stream));
            Array longitudeSection = cdfFile.readSection(LONG_SECTION);
            Array latitudeSection = cdfFile.readSection(LAT_SECTION);
            Array timeSection = cdfFile.readSection(TIME_SECTION);
            Array depthSection = cdfFile.readSection(DEPTH_SECTION);

            long size = longitudeSection.getSize();
            for (int i = 0; i < size; i++) {
                TimePosition timePosition = new TimePosition();
                timePosition.setRecordNumber(Long.valueOf(i));
                timePosition.setLongitude(longitudeSection.getFloat(i));
                timePosition.setLatitude(latitudeSection.getFloat(i));
                timePosition.setDepth(depthSection.getFloat(i));
                timePosition.setDate(getDate(timeSection.getDouble(i)));
                consumer.accept(timePosition);
            }

        } catch (Exception e) {
            // Nothing to do
        } finally {
            IOUtils.closeQuietly(cdfFile);
        }
    }

    @Override
    public boolean accept(ObservationData data) {
        return ACCEPTED_FORMAT.equalsIgnoreCase(data.getMimeType());
    }

    public static Date getDate(Double daysSinceStart) {
        return new Date((long) (daysSinceStart * HOURS_IN_MILLIS - START_TIME_NETCDF));
    }
}
