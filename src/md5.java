import java.util.BitSet;
import org.apache.commons.codec.digest.DigestUtils;

public class md5 {
	
	public byte[] simHash(String text){
		
		BitSet hash;
		byte[] sh = new byte[128];
		
		String[] jedinke = text.split("\\s+");
		//Za svaku jedinku
		for(String jedinka : jedinke) {
			
			//Izracunaj simHash i razdvoji bitove
			hash = BitSet.valueOf(DigestUtils.md5(jedinka));
			for (int i = 0; i<128; i++){
				if (hash.get(i))
					sh[i]+=1;
				else
					sh[i]-=1;
			}
		}
		
		//Za svaki bit dovrsi simHash
		for (int i=0; i<128; i++){
			if (sh[i]>=0)
				sh[i]=1;
			else
				sh[i]=0;
		}
		
		//Vrati bitove rezultata
	    return sh;
	}
}
