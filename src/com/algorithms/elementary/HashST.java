package com.algorithms.elementary;

import java.util.AbstractMap;
import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
/**
 * separateChainingHashST 
 * @author Administrator
 *
 * @param <K>
 * @param <V>
 */
public class HashST<K, V> extends AbstractMap<K,V> implements Map<K, V>{
	
	public static void main(String[] args) {
		HashST<String, Integer> map = new HashST<>();
		map.put("1", 1);
		map.put("2", 2);
	}
	
	
	private static final int DEFAULT_INITIAL_CAPACITY = 1 << 4; // aka 16
	private static final float DEFAULT_LOAD_FACTOR = 0.75f;
	private int size;
	private Node<K, V>[] base;
	private int capacity;
	
	public HashST() {
		this.capacity = DEFAULT_INITIAL_CAPACITY;
		base = new Node[capacity];
	}
	
	public HashST(int capacity) {
		this.capacity = capacity;
		base = new Node[capacity];
	}
	
	@Override
	public void clear() {
		for (int i = 0; i < base.length; i++)
			base[i] = null;
		size = 0;
	}

	@Override
	public V get(Object object) {
		int position = hash(object) % capacity;
		for (Node<K, V> node = base[position]; node != null; node = node.next) {
			if (object.equals(node.key)) return node.value;
		}
		return null;
	}

	@Override
	public boolean isEmpty() {
		return size == 0;
	}


	@Override
	public V put(K k, V v) {
		int position = hash(k) % capacity;
		base[position] = new Node(hash(k), k, v, base[position]);
		size++;
		resize();
		return v;
	}


	@Override
	public int size() {
		return size;
	}

	
	private int hash(Object k) {
		return (k.hashCode() & 0x7fffffff);
	}
	
    private class Node<K,V> implements Map.Entry<K,V> {
        final int hash;
        final K key;
        V value;
        Node<K,V> next;

        Node(int hash, K key, V value, Node<K,V> next) {
            this.hash = hash;
            this.key = key;
            this.value = value;
            this.next = next;
        }

        public final K getKey()        { return key; }
        public final V getValue()      { return value; }
        public final String toString() { return key + "=" + value; }

        public final int hashCode() {
            return Objects.hashCode(key) ^ Objects.hashCode(value);
        }

        public final V setValue(V newValue) {
            V oldValue = value;
            value = newValue;
            return oldValue;
        }

        public final boolean equals(Object o) {
            if (o == this)
                return true;
            if (o instanceof Map.Entry) {
                Map.Entry<?,?> e = (Map.Entry<?,?>)o;
                if (Objects.equals(key, e.getKey()) &&
                    Objects.equals(value, e.getValue()))
                    return true;
            }
            return false;
        }
    }
    
    private void resize() {
    	if ((size / capacity) > DEFAULT_LOAD_FACTOR) {
    		capacity = capacity * 2;
    		Node<K, V>[] newBase = new Node[capacity];
    		for (int i = 0; i < base.length; i++) {
				if (base[i] == null) continue;
				Node<K, V> node = base[i];
				while (node != null) {
					newBase[node.hash % capacity] = node;
					node = node.next;
				}
			}
    		base = newBase;
    	}
    }
    
    @Override
    public V remove(Object key) {
    	int position = hash(key) % capacity;
    	if (base[position] == null) return null;
    	if (base[position].key.equals(key)) {
    		V v = base[position].value;
    		base[position] = base[position].next; 
    		return v;
    	} else {
        	for (Node<K, V> node = base[position]; node.next != null; node = node.next) {
        		if (node.next.key.equals(key)) {
        			V v = node.next.value;
        			node.next = node.next.next;
        			return v;
        		}
        	}
    	}
    	return null;
    }
    
	@Override
	public Set<Map.Entry<K, V>> entrySet() {
		Set<Map.Entry<K, V>> set = new HashSet<>();
		for (int i = 0; i < base.length; i++) {
			if (base[i] == null) continue;
			Node<K, V> node = base[i];
			while (node != null) {
				set.add(new Entry(node.key, node.value));
				node = node.next;
			}
		}
		return set;
	}
	private class Entry<K, V> implements Map.Entry<K, V> {
		private K k;
		private V v;
		Entry(K k, V v) {
			this.k = k;
			this.v = v;
		}
		@Override
		public K getKey() {
			return k;
		}

		@Override
		public V getValue() {
			return v;
		}

		@Override
		public V setValue(V value) {
			throw new RuntimeException("not supported");
		}
		
	}
}
