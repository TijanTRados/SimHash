public class Hamming {
	
	public boolean distance(byte[] prvi, byte[] drugi, int max){
		
		int counter = 0;
		
		//Za svaki bit, ukoliko je razlicit, povecaj brojac
		for (int i = 0; i<128; i++){
				if (prvi[i] != drugi[i])
					counter++;
				
				//Ako izades van dopustenog raspona, vrati false
				if (counter > max)
					return false;
			}
		
		return true;
	}
}
