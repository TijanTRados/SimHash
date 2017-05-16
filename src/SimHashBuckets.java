import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.HashSet;

public class SimHashBuckets {

	public static void main(String[] args){
		
		md5 md5 = new md5();
		Hamming hamming = new Hamming();
		BufferedReader reader = null;
		String line;
		int counter, Z, K;
		int N = 0;
		int Q = 0;
		
		reader = new BufferedReader(new InputStreamReader(System.in));
		
		//Procitaj broj tekstova
		try {
			N = Integer.parseInt(reader.readLine());
		} catch (NumberFormatException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		byte[][] polje = new byte [N][128];
		
		//Procitaj sve tekstove i vrati niz bitova koje simHash vraca + spremaj rezultate u polje
		for (int i = 0; i < N; i++){
			try {
				line = reader.readLine();
				polje[i] = md5.simHash(line);		
			} catch (IOException e) {
				e.printStackTrace();
			} 
		}	
	
		//Racunanje kandidata
		HashMap<Integer, HashSet<Integer>> kandidati = new HashMap<Integer, HashSet<Integer>>();
		
		//Za svaki pojas
		for (int pojas = 1; pojas <= 8; pojas++){
			
			//napravi pretince
			HashMap<Integer, HashSet<Integer>> pretinci = new HashMap<Integer, HashSet<Integer>>();
			
			//Za svaki tekst
			for (int trenutni_id = 0; trenutni_id < N; trenutni_id++){
				byte[] hash = polje[trenutni_id];
				//Pretvori hash bitove u integer za taj pojas
				int val = hash2int(pojas, hash);
				HashSet<Integer> tekstovi_u_pretincu = new HashSet<Integer>();
				
				//Pronadi kandidate i spremi u dictionary
				if (pretinci.get(val)!=null){
					tekstovi_u_pretincu = pretinci.get(val);
					for(Integer tekst_id : tekstovi_u_pretincu){
						if (kandidati.get(trenutni_id)==null) kandidati.put(trenutni_id, new HashSet<>());
						if(kandidati.get(trenutni_id).contains(tekst_id) == false) kandidati.get(trenutni_id).add(tekst_id);
						 
						if (kandidati.get(tekst_id)==null) kandidati.put(tekst_id, new HashSet<>());
						if (kandidati.get(tekst_id).contains(trenutni_id) == false)	kandidati.get(tekst_id).add(trenutni_id);
					}
				} else tekstovi_u_pretincu = new HashSet<>();
			
			tekstovi_u_pretincu.add(trenutni_id);
			pretinci.put(val, tekstovi_u_pretincu);
			}
		}
				
		//Procitaj broj upita
		try {
			Q = Integer.parseInt(reader.readLine());
		} catch (NumberFormatException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		//Procitaj sve upite i automatski ih rjesavaj
				for (int i = 0; i<Q; i++){
					try {
						line = reader.readLine();
						String[] IK = line.split("\\s+");
						Z = Integer.parseInt(IK[0]);
						K = Integer.parseInt(IK[1]);
						
						counter = 0;
							for (Integer kandidat : kandidati.get(Z)){
								if(hamming.distance(polje[Z], polje[kandidat], K))
									counter++;
							}
						System.out.println(counter);
						
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
	}
	
	//Hash2int funkcija
	public static int hash2int(int pojas, byte[] hash){
		int result = 0;
		int counter = 15;
		int start = (pojas-1)*16;
		int kraj = pojas*16;
		for (int i = start; i<kraj; i++){
			if (hash[i]==1) {
				result+= (int) Math.pow(2, counter);
			} counter--;
		}
		return result;
	}
}
