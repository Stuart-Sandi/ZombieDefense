package modelo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;


public class GeneradorPersonaje {
	public int x, y;
	public ArrayList<TipoPersonaje> tiposPersonaje;
	
	public HashMap<Integer,Personaje> generarZombies(int numeroZombies){
		HashMap<Integer, Personaje> hash = new HashMap<>();
		Random rand = new Random();
		List<TipoPersonaje> tipoZombies = TipoPersonaje.Enemigos();
		for(int i = 0; i<numeroZombies; i++) {
			Personaje p = new Personaje(tipoZombies.get(rand.nextInt(tipoZombies.size())));
			hash.put(p.hashCode(), p);
		}
		return hash;
		
	}
}
