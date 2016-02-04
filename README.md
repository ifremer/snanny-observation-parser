# snanny-observation-parser
Interface for parsers of observation

## Build and deploy
tested with java 1.8 and maven 3.0.4 or above
```sh
mvn clean install
```	

Deploy new version 
Edit POM and execute
```sh
mvn clean deploy
```

## Documentation

This module must be added has dependency of concrete parser using this 
```xml
	<dependency>
		<groupId>fr.ifremer.sensornanny</groupId> 
		<artifactId>snanny-observation-parser</artifactId> 
		<version>VERSION</version> 
	</dependency> 
```

The concrete parser must implements interface ObservationParser

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

Exemple 

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