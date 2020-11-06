package modelo;
import java.util.HashMap;
import java.util.Random;


public class GeneradorZombie {
	public int x, y;
	
	public HashMap<Integer,Zombie> generarZombies(int numeroZombies){
		HashMap<Integer, Zombie> hash = new HashMap<>();
		Random rand = new Random();
		TipoZombie[] tipoZombies = TipoZombie.values();
		for(int i = 0; i<numeroZombies; i++) {
			Zombie p = new Zombie( tipoZombies[(rand.nextInt(tipoZombies.length))] );
			hash.put(p.hashCode(), p);
		}
		return hash;
		
	}
	
}
