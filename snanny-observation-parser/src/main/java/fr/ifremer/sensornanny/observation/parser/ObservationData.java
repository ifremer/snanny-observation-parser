package fr.ifremer.sensornanny.observation.parser;

/**
 * Data of observation file
 * 
 * @author athorel
 *
 */
public class ObservationData {

    /**
     * file name with extension
     */
    private String fileName;
    /**
     * mimetype of the file
     */
    private String mimeType;

    /**
     * Factory method to allow to create simple observation data
     * 
     * @param fileName name of the file
     * @param mimeType mime-type of the file
     * @return observation data with specified filename and mimetype
     */
    public static ObservationData of(String fileName, String mimeType) {
        ObservationData observationData = new ObservationData();
        observationData.setFileName(fileName);
        observationData.setMimeType(mimeType);
        return observationData;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getMimeType() {
        return mimeType;
    }

    public void setMimeType(String mimeType) {
        this.mimeType = mimeType;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((fileName == null) ? 0 : fileName.hashCode());
        result = prime * result + ((mimeType == null) ? 0 : mimeType.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        ObservationData other = (ObservationData) obj;
        if (fileName == null) {
            if (other.fileName != null)
                return false;
        } else if (!fileName.equals(other.fileName))
            return false;
        if (mimeType == null) {
            if (other.mimeType != null)
                return false;
        } else if (!mimeType.equals(other.mimeType))
            return false;
        return true;
    }

}
