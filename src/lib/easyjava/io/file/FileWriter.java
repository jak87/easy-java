package lib.easyjava.io.file;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;

/**
 * Simplifies the interface for writing to files
 * 
 * @author Rob Rua (rrua@andrew.cmu.edu)
 */
public class FileWriter {
    /**
     * Writes to the given file with only the content given
     * 
     * @param targetFile
     *            the file to write to
     * @param content
     *            the content to put in the file
     * @throws IOException
     */
    public static void writeFile(File targetFile, String content)
            throws IOException {
        final FileWriter writer = new FileWriter(targetFile);
        writer.write(content);
        writer.close();
    }

    /**
     * Writes to the given file with only the content given
     * 
     * @param targetFilePath
     *            the path of the file to write to
     * @param content
     *            the content to put in the file
     * @throws IOException
     */
    public static void writeFile(String targetFilePath, String content)
            throws IOException {
        writeFile(new File(targetFilePath), content);
    }

    private File file;
    protected BufferedWriter writer;

    /**
     * @param file
     *            the file to write to
     * @throws IOException
     */
    public FileWriter(File file) throws IOException {
        writer = new BufferedWriter(new java.io.FileWriter(file));
    }

    /**
     * @param filePath
     *            the path of the file to write to
     * @throws IOException
     */
    public FileWriter(String filePath) throws IOException {
        this(new File(filePath));
    }

    /**
     * Closes the writer. Call this when you're done with it.
     * 
     * @throws IOException
     */
    public void close() throws IOException {
        writer.close();
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj) {
            return true;
        }
        if(obj == null) {
            return false;
        }
        if(!(obj instanceof FileWriter)) {
            return false;
        }
        final FileWriter other = (FileWriter) obj;
        if(file == null) {
            if(other.file != null) {
                return false;
            }
        }
        else if(!file.equals(other.file)) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (file == null ? 0 : file.hashCode());
        return result;
    }

    /**
     * Resets the current location in the file to the beginning
     * 
     * @throws IOException
     */
    public void reopen() throws IOException {
        writer.close();
        writer = new BufferedWriter(new java.io.FileWriter(file));
    }

    @Override
    public String toString() {
        return "FileWriter(" + file.getAbsolutePath() + ")";
    }

    /**
     * Writes the given string into the file
     * 
     * @param text
     *            the text to write to the file
     * @throws IOException
     */
    public void write(String text) throws IOException {
        writer.write(text);
        writer.flush();
    }
}
