import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class SimHash {
	
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
				for (int j = 0; j<N; j++){
						if(hamming.distance(polje[Z], polje[j], K))
							counter++;
				}
				
				System.out.println(counter-1);
				
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	} 
}
