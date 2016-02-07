package ska.coms535.BloomFilter;

import java.util.BitSet;

public class BloomFilterDet {

	
	private int k = 0;
	private int filterSize = 0;
	BloomFilterDet(int setSize, int bitsPerElement){
		filterSize = setSize * bitsPerElement;
		k = (int) (Math.log(2) *filterSize /setSize);
		BitSet bitSet = new BitSet();
		
	}
	
	void add(String s){
		s = s.toLowerCase();
		
	}
	
	boolean appears(String s){
		s = s.toLowerCase();
		
		
		return false;
	}
	
	int filterSize(){
		
		
		return 0;
	}
	
	int dataSize() {
		
		
		return 0;
	}
	
	int numHashes(){
		return k;
	}
}
