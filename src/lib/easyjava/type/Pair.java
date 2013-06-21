package lib.easyjava.type;

/**
 * A simple Pair implementation
 * 
 * @author Rob Rua (rrua@andrew.cmu.edu)
 * 
 * @param <T1>
 *            the type of the left item in the pair
 * @param <T2>
 *            the type of the right item in the pair
 */
public class Pair<T1, T2> {
    private T1 left;
    private T2 right;

    /**
     * @param left
     *            the left item in the pair
     * @param right
     *            the right item in the pair
     */
    public Pair(T1 left, T2 right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj) {
            return true;
        }
        if(obj == null) {
            return false;
        }
        if(!(obj instanceof Pair)) {
            return false;
        }

        @SuppressWarnings("rawtypes")
        final Pair other = (Pair) obj;

        if(left == null) {
            if(other.left != null) {
                return false;
            }
        }
        else if(!left.equals(other.left)) {
            return false;
        }
        if(right == null) {
            if(other.right != null) {
                return false;
            }
        }
        else if(!right.equals(other.right)) {
            return false;
        }
        return true;
    }

    /**
     * Gets the left item in the pair
     * 
     * @return the left item in the pair
     */
    public T1 getLeft() {
        return left;
    }

    /**
     * Gets the right item in the pair
     * 
     * @return the right item in the pair
     */
    public T2 getRight() {
        return right;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (left == null ? 0 : left.hashCode());
        result = prime * result + (right == null ? 0 : right.hashCode());
        return result;
    }

    /**
     * Sets the left item in the pair
     * 
     * @param left
     *            the new left item for the pair
     */
    public void setLeft(T1 left) {
        this.left = left;
    }

    /**
     * Sets the right item in the pair
     * 
     * @param right
     *            the new right item for the pair
     */
    public void setRight(T2 right) {
        this.right = right;
    }

    @Override
    public String toString() {
        return "(" + left.toString() + ", " + right.toString() + ")";
    }
}
