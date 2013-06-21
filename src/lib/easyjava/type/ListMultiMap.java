package lib.easyjava.type;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

/**
 * A multimap back by a list, allowing duplicate values under the same key
 * 
 * @author Rob Rua (rrua@andrew.cmu.edu)
 * 
 * @param <K>
 *            the type of the map's keys
 * @param <V>
 *            the type of the map's values
 */
public class ListMultiMap<K, V> implements MultiMap<K, V> {
    private final Map<K, List<V>> map;

    public ListMultiMap() {
        map = new HashMap<K, List<V>>();
    }

    @Override
    public void clear() {
        map.clear();
    }

    @Override
    public boolean containsKey(K key) {
        return map.containsKey(key);
    }

    @Override
    public boolean containsValue(V value) {
        for(final K key : map.keySet()) {
            if(map.get(key).contains(value)) {
                return true;
            }
        }

        return false;
    }

    @Override
    public Collection<Entry<K, V>> entries() {
        final Collection<Entry<K, V>> entries = new LinkedList<Entry<K, V>>();
        for(final K key : map.keySet()) {
            for(final V val : map.get(key)) {
                entries.add(new AbstractMap.SimpleImmutableEntry<K, V>(key, val));
            }
        }

        return entries;
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj) {
            return true;
        }
        if(obj == null) {
            return false;
        }
        if(!(obj instanceof ListMultiMap)) {
            return false;
        }

        @SuppressWarnings("rawtypes")
        final ListMultiMap other = (ListMultiMap) obj;

        if(map == null) {
            if(other.map != null) {
                return false;
            }
        }
        else if(!map.equals(other.map)) {
            return false;
        }
        return true;
    }

    @Override
    public List<V> get(K key) {
        return map.get(key);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (map == null ? 0 : map.hashCode());
        return result;
    }

    @Override
    public boolean isEmpty() {
        return map.isEmpty();
    }

    @Override
    public Set<K> keySet() {
        return map.keySet();
    }

    @Override
    public void put(K key, V value) {
        if(!map.containsKey(key)) {
            final ArrayList<V> val = new ArrayList<V>();
            map.put(key, val);
        }
        map.get(key).add(value);
    }

    @Override
    public void putAll(Map<? extends K, ? extends V> map) {
        for(final Entry<? extends K, ? extends V> entry : map.entrySet()) {
            put(entry.getKey(), entry.getValue());
        }
    }

    @Override
    public void putAll(MultiMap<? extends K, ? extends V> map) {
        for(final Entry<? extends K, ? extends V> entry : map.entries()) {
            put(entry.getKey(), entry.getValue());
        }
    }

    @Override
    public List<V> remove(K key) {
        return map.remove(key);
    }

    @Override
    public int size() {
        int count = 0;
        for(final K key : map.keySet()) {
            count += map.get(key).size();
        }

        return count;
    }

    @Override
    public String toString() {
        String result = "{";
        for(final K key : map.keySet()) {
            result += "\n\t" + key.toString() + ": ";

            final String vals = "";
            for(final V val : map.get(key)) {
                result += ", " + val.toString();
            }

            result += vals.substring(2);
        }
        result += "\n}";

        return result;
    }

    @Override
    public Collection<V> values() {
        final Collection<V> values = new LinkedList<V>();
        for(final K key : map.keySet()) {
            values.addAll(map.get(key));
        }

        return values;
    }
}
