/**
 * 
 */
package ska.coms535.BloomFilter;

import java.nio.ByteBuffer;
import java.util.BitSet;

/**
 * @author Shubham
 *
 */
public class BloomFilterRan {


	private int numberOfHashFn = 0;
	private int filterSize = 0;
	private BitSet bitSet;
	private int dataCount;

	private static final int FNV_32_PRIME = 0x01000193;
	private static final int FNV_32_INIT = 0x811c9dc5;

	//	private static final long FNV_64_INIT = 0xcbf29ce484222325L;
	//	private static final long FNV_64_PRIME = 0x100000001b3L;

	public BloomFilterRan(int setSize, int bitsPerElement){
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
			if(!bitSet.get(Math.abs(hashes[i]) % numberOfHashFn)) return false;
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

	/* Jenkins's one-at-a-time hash is adapted here from a WWW page by Bob Jenkins
	 * https://en.wikipedia.org/wiki/Jenkins_hash_function
	 */
	public int jenkinsHashFn(byte[] key){
		int hash = 0;
		for(int i = 0; i < key.length; i++)
		{
			hash += key[i];
			hash += (hash << 10);
			hash ^= (hash >> 6);
		}
		hash += (hash << 3);
		hash ^= (hash >> 11);
		hash += (hash << 15);
		return hash;
	}


	public int[] generateKHashes(byte[] arrBytes){
		int[] retArr = new int[numberOfHashFn];
		byte[] temp = arrBytes;
		for(int i= 0; i < retArr.length; i++){
			int rand = temp.length % 2;

			switch(rand){
			case 0 : 
				retArr[i] =  jenkinsHashFn(temp);
			case 1:
				retArr[i] =  jenkinsHashFn(temp);
			default:
				retArr[i] =  hash32(temp);
			}
			temp = toByteArray(retArr[i]);
		}
		return retArr;
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

	public byte[] toByteArray(int x){
		ByteBuffer putLong = ByteBuffer.allocate(8).putLong(x);
		return putLong.array();
	}
}
