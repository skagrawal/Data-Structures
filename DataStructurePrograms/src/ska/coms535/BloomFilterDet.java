package ska.coms535;

import java.nio.ByteBuffer;
import java.util.BitSet;

public class BloomFilterDet {


	public static void main(String []a){

		System.out.println("In main");
		BloomFilterRan obj = new BloomFilterRan(10, 10);
		obj.add("Apple");
		System.out.println(obj.dataSize());
		System.out.println(obj.appears("Apple"));
		
	}

	private int numberOfHashFn = 0;
	private int filterSize = 0;
	private BitSet bitSet;
	private int dataCount;

	private static final int FNV_32_PRIME = 0x01000193;
	private static final int FNV_32_INIT = 0x811c9dc5;

	//	private static final long FNV_64_INIT = 0xcbf29ce484222325L;
	//	private static final long FNV_64_PRIME = 0x100000001b3L;

	BloomFilterDet(int setSize, int bitsPerElement){
		this.filterSize = setSize * bitsPerElement;
		this.numberOfHashFn = (int) (Math.log(2) * filterSize /setSize);
		this.bitSet = new BitSet();
	}

	void add(String s){
		s = s.toLowerCase();
		int[] hashArr = generateKHashes(s.getBytes());
		for(int i = 0; i< hashArr.length; i++){
			bitSet.set((Math.abs(hashArr[i]) % numberOfHashFn), true);
		}
		dataCount++;
	}

	boolean appears(String s){
		s = s.toLowerCase();
		int[] hashes = generateKHashes(s.getBytes());
		for(int i = 0; i< hashes.length; i++){

			if(!bitSet.get(Math.abs(hashes[i]) % numberOfHashFn)){
				return false;
			}
		}
		return true;
	}

	int filterSize(){
		return filterSize;
	}

	int dataSize() {
		return dataCount;
	}

	int numHashes(){
		return numberOfHashFn;
	}


	public int hash32(final byte[] k) {
		int rv = FNV_32_INIT;
		final int len = k.length;
		for(int i = 0; i < len; i++) {
			rv ^= k[i];
			rv *= FNV_32_PRIME;
		}
		return rv;
	}


	public int[] generateKHashes(byte[] arrBytes){
		int[] retArr = new int[numberOfHashFn];
		retArr[0] = hash32(arrBytes);
		for(int i=1; i < retArr.length; i++){
			retArr[i] = hash32(toByteArray(retArr[i-1]));
		}
		return retArr;
	}

	public byte[] toByteArray(int x){
		ByteBuffer putLong = ByteBuffer.allocate(8).putLong(x);
		return putLong.array();
	}

}
