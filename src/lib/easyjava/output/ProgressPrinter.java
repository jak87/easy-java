package lib.easyjava.output;

/**
 * Prints a bunch of dots to stdout to report the progress of some operation in
 * your command line application
 * 
 * @author Rob Rua (rrua@andrew.cmu.edu)
 */
public class ProgressPrinter {
    private long count;
    private final long dotsPerLine;
    private long lineSoFar;
    private double nextSegment;
    private final long numTicks;
    private final String postfix;
    private final String prefix;
    private double segment;

    /**
     * @param numTicks
     *            the number of operations that your application is completing
     *            while printing dots
     * @param numDots
     *            the number of dots to print to the console
     * @param dotsPerLine
     *            the number of dots per line
     */
    public ProgressPrinter(long numTicks, long numDots, long dotsPerLine) {
        this(numTicks, numDots, dotsPerLine, "", "");
    }

    /**
     * @param numTicks
     *            the number of operations that your application is completing
     *            while printing dots
     * @param numDots
     *            the number of dots to print to the console
     * @param dotsPerLine
     *            the number of dots per line
     * @param prefix
     *            a string to print before every line of dots
     */
    public ProgressPrinter(long numTicks, long numDots, long dotsPerLine,
            String prefix) {
        this(numTicks, numDots, dotsPerLine, prefix, "");
    }

    /**
     * @param numTicks
     *            the number of operations that your application is completing
     *            while printing dots
     * @param numDots
     *            the number of dots to print to the console
     * @param dotsPerLine
     *            the number of dots per line
     * @param prefix
     *            a string to print before every line of dots
     * @param postfix
     *            a string to print after every line of dots
     */
    public ProgressPrinter(long numTicks, long numDots, long dotsPerLine,
            String prefix, String postfix) {
        this.prefix = prefix;
        this.postfix = postfix;
        this.numTicks = numTicks;
        this.dotsPerLine = dotsPerLine;
        segment = (double) numTicks / (double) numDots;

        if(segment == 0) {
            segment = 1;
        }

        count = 0;
        lineSoFar = 0;
        nextSegment = segment;

        System.out.print(prefix);
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj) {
            return true;
        }
        if(obj == null) {
            return false;
        }
        if(!(obj instanceof ProgressPrinter)) {
            return false;
        }
        final ProgressPrinter other = (ProgressPrinter) obj;
        if(count != other.count) {
            return false;
        }
        if(dotsPerLine != other.dotsPerLine) {
            return false;
        }
        if(lineSoFar != other.lineSoFar) {
            return false;
        }
        if(Double.doubleToLongBits(nextSegment) != Double
                .doubleToLongBits(other.nextSegment)) {
            return false;
        }
        if(numTicks != other.numTicks) {
            return false;
        }
        if(postfix == null) {
            if(other.postfix != null) {
                return false;
            }
        }
        else if(!postfix.equals(other.postfix)) {
            return false;
        }
        if(prefix == null) {
            if(other.prefix != null) {
                return false;
            }
        }
        else if(!prefix.equals(other.prefix)) {
            return false;
        }
        if(Double.doubleToLongBits(segment) != Double
                .doubleToLongBits(other.segment)) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (int) (count ^ count >>> 32);
        result = prime * result + (int) (dotsPerLine ^ dotsPerLine >>> 32);
        result = prime * result + (int) (lineSoFar ^ lineSoFar >>> 32);
        long temp;
        temp = Double.doubleToLongBits(nextSegment);
        result = prime * result + (int) (temp ^ temp >>> 32);
        result = prime * result + (int) (numTicks ^ numTicks >>> 32);
        result = prime * result + (postfix == null ? 0 : postfix.hashCode());
        result = prime * result + (prefix == null ? 0 : prefix.hashCode());
        temp = Double.doubleToLongBits(segment);
        result = prime * result + (int) (temp ^ temp >>> 32);
        return result;
    }

    /**
     * Informs the printer that you've completed one of the operations the user
     * is waiting on. Prints dots when appropriate.
     */
    public void tick() {
        count++;

        while(count >= nextSegment) {
            System.out.print(".");
            nextSegment += segment;
            lineSoFar++;

            if(lineSoFar == dotsPerLine) {
                System.out.println(postfix);
                if(count < numTicks) {
                    System.out.print(prefix);
                }
                lineSoFar = 0;
            }
        }

        // Compensates for lack of precision in doubles which would cause the
        // last dot not to show in many cases
        if(count == numTicks && lineSoFar > 0) {
            System.out.println(".");
        }
    }

    @Override
    public String toString() {
        return "ProgressPrinter(" + count + "/" + numTicks + ")";
    }
}
