/**
 * 
 */
package ska.ds;

import java.util.LinkedHashMap;

/**
 * @author Shubham (tech.shubham@gmail.com)
 * Incomplete
 */


class LRUCache {
    
    LinkedHashMap<Integer,Integer> map;
    public LRUCache(int capacity) {
        map = new LinkedHashMap<Integer,Integer>(capacity);
    }
    
    public int get(int key) {
        return map.get(key);
    }
    
    public void set(int key, int value) {
        map.put(key,value);
    }
    
    public static void main(String[] args) {
		// TODO Auto-generated method stub

    	LRUCache lru = new LRUCache(10);
    	lru.set(1,1);
    	System.out.println(lru.get(1));
	}
}
