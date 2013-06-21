package lib.easyjava.type;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

/**
 * An interface for a multimap roughly conforming to Java's map specifications
 * 
 * @author Rob Rua (rrua@andrew.cmu.edu)
 * 
 * @param <K>
 *            the type of the map's keys
 * @param <V>
 *            the type of the map's values
 */
public interface MultiMap<K, V> {
    /**
     * Empties the map of all previously added keys and values
     */
    public void clear();

    /**
     * Checks if the map contains the given key
     * 
     * @param key
     *            the key to look for
     * @return whether the map contains the key
     */
    public boolean containsKey(K key);

    /**
     * Checks if the map contains the given value
     * 
     * @param value
     *            the value to look for
     * @return whether the map contains the value
     */
    public boolean containsValue(V value);

    /**
     * Gets a collection of all the entries in the map
     * 
     * @return the entries of the map
     */
    public Collection<java.util.Map.Entry<K, V>> entries();

    /**
     * Gets all of the values stored for a key
     * 
     * @param key
     *            the key to get values for
     * @return all the values stored for the key or null if there are none
     */
    public Collection<V> get(K key);

    /**
     * Checks if the map is empty or not
     * 
     * @return whether the map is empty
     */
    public boolean isEmpty();

    /**
     * Gets a set of all the keys in the map
     * 
     * @return the key set
     */
    public Set<K> keySet();

    /**
     * Adds a key/value pair to the map
     * 
     * @param key
     *            the key to add
     * @param value
     *            the value to add
     */
    public void put(K key, V value);

    /**
     * Adds all of the key/value pairs from the given map into this map
     * 
     * @param map
     *            the map to add
     */
    public void putAll(Map<? extends K, ? extends V> map);

    /**
     * Adds all of the key/value pairs from the given map into this map
     * 
     * @param map
     *            the map to add
     */
    public void putAll(MultiMap<? extends K, ? extends V> map);

    /**
     * Removes a key from the map
     * 
     * @param key
     *            the key to remove
     * @return the values mapped to that key before it was removed
     */
    public Collection<V> remove(K key);

    /**
     * Gets the number of entries in the map (number of values)
     * 
     * @return the number of entries
     */
    public int size();

    /**
     * Gets all the values in the map
     * 
     * @return the values
     */
    public Collection<V> values();
}
