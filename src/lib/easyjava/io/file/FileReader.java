package lib.easyjava.io.file;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Simplifies the interface for reading from files
 * 
 * @author Rob Rua (rrua@andrew.cmu.edu)
 */
public class FileReader {
    /**
     * Reads the entire file in as a String
     * 
     * @param file
     *            the file to read
     * @return String containing the contents of the file
     * @throws IOException
     */
    public static String readFile(File file) throws IOException {
        final FileReader reader = new FileReader(file);
        final String text = reader.readFile();
        reader.close();

        return text;
    }

    /**
     * Reads the entire file in as a String
     * 
     * @param filePath
     *            the path of the file to read
     * @return String containing the contents of the file
     * @throws IOException
     */
    public static String readFile(String filePath) throws IOException {
        return readFile(new File(filePath));
    }

    private boolean atStart = true;
    private final File file;

    protected BufferedReader reader;

    /**
     * @param file
     *            the file to read with this reader
     * @throws FileNotFoundException
     */
    public FileReader(File file) throws FileNotFoundException {
        this.file = file;
        reader = new BufferedReader(new java.io.FileReader(file));
    }

    /**
     * @param filePath
     *            the path of the file to read with this reader
     * @throws FileNotFoundException
     */
    public FileReader(String filePath) throws FileNotFoundException {
        this(new File(filePath));
    }

    /**
     * Closes the reader. Call this when you're done with it.
     * 
     * @throws IOException
     */
    public void close() throws IOException {
        reader.close();
    }

    /**
     * Reads the entire file into a String
     * 
     * @return String containing the contents of the file
     * @throws IOException
     */
    public String readFile() throws IOException {
        if(!atStart) {
            reopen();
        }

        return readRestOfFile();
    }

    /**
     * Reads a single line of the file
     * 
     * @return String containing the next line in the file
     * @throws IOException
     */
    public String readLine() throws IOException {
        atStart = false;
        return reader.readLine();
    }

    /**
     * Reads the rest of the file into a String
     * 
     * @return String containing the remaining contents of the file
     * @throws IOException
     */
    public String readRestOfFile() throws IOException {
        String file = "";
        String line;
        while((line = readLine()) != null) {
            file += line + "\n";
        }

        return file;
    }

    /**
     * Reopens the file, returning the the beginning
     * 
     * @throws IOException
     */
    public void reopen() throws IOException {
        reader.close();
        reader = new BufferedReader(new java.io.FileReader(file));
    }
}
