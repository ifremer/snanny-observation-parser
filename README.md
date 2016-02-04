# Snanny Parsers 
This project handle observation parsers used by elastic-sync project,  

Available parsers :
- snanny-observation-momar-parser : 
    -   CSV based file representing an observation       
- netcdf parser
    -   Navigation file with .nav extension 

## Documentation

Each parser must add dependency of concrete parser using this 
```xml
	<dependency>
		<groupId>fr.ifremer.sensornanny</groupId> 
		<artifactId>snanny-observation-parser</artifactId> 
		<version>VERSION</version> 
	</dependency> 
```

and implements interface ObservationParser

```java
/**
 * Interface of an observation parser
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
```

Parser will return data transfert object describe as a TimePosition 
```java
/**
* Information about observation using date, coordinates and depth
*/
public class TimePosition {

    /** Number of the record in the file */
    private Long recordNumber;

    /** Date of the observation */
    private Date date;

    /** Latitude of the observation */
    private Float latitude;

    /** Longitude of the observation */
    private Float longitude;

    /** Depth of the observation */
    private Float depth;
```

### Concrete parser exemple 

```java
/**
 * Simple text observation file parser
 * @author athorel
 */
public class SimpleTextParser implements fr.ifremer.sensornanny.observation.parser.IObservationParser {
    /**Accepted format*/
    private static final String ACCEPTED_FORMAT = "plain/text";

    @Override
    public void read(ObservationData data, InputStream stream, Consumer<TimePosition> consumer) {
        //Do parsing readlines
        int recordNumber = 0;
        while(String line = readLine()){
            //Foreach line call
            timePosition = new TimePosition();
            timePosition.setRecordNumber(recordNumber);
            timePosition.setLongitude(...);
            timePosition.setLatitude(...);
            timePosition.setDepth(...);
            timePosition.setDate(...);
            
            consumer.accept(timePosition);
            i++;
       }
    }

    @Override
    public boolean accept(ObservationData data) {
        return ACCEPTED_FORMAT.equalsIgnoreCase(data.getMimeType());
    }
```

