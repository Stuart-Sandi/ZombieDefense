package modelo;

import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.Icon;

import controlador.Helpers;

public class Aplicacion {
	static Aplicacion self;
	public int nivel;
	public ArrayList <Bloque> bloques;
	public ArrayList <Personaje> personajes;
	public ArrayList <Zombie> zombies;
	public ArrayList <Edificacion> edificaciones;
	
	//public HashMap<Integer, GeneradorZombie> puntoSpawn;
	public HashMap<String, Icon> imagenes;
	public Mapa mapa;
	
	public static Aplicacion getInstance() {
		if (self == null)
			self = new Aplicacion();
		return self;
	}
	
	public Aplicacion() {
		super();
		this.nivel = 1;
		this.mapa = new Mapa();
		this.bloques = new ArrayList<>();
		this.personajes = new ArrayList<>();
		this.edificaciones = new ArrayList<>();
		this.zombies = new ArrayList<>();
		generarJugadores();
		generarZombies();
	}
		
	public void generarJugadores() {
		
		TipoJugador[] listTipoJugadores = TipoJugador.values();
		ArrayList<Posicion> listPosiciones = new ArrayList<Posicion>(){ 
            /**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			{ 
                add(new Posicion(5, 14)); 
                add(new Posicion(2, 14)); 
                add(new Posicion(5, 17)); 
        
            } 
        };
		for (int i = 0; i < listPosiciones.size(); i++) {
			
			Jugador jug = new Jugador(listTipoJugadores[i], listPosiciones.get(i));
			jug.imagen = Helpers.getImagenResized(listTipoJugadores[i].toString(), ".png",ValoresDefecto.imagenTablero, ValoresDefecto.imagenTablero);
			this.personajes.add(jug);
			this.mapa.tableroPersonajes[jug.posicion.x][jug.posicion.y] = jug;
		}
		
	}
	
	public void generarZombies() {
		zombies.removeAll(zombies);
		zombies.addAll(this.mapa.generarZombie());
		System.out.println(zombies.size());
	}
	
	public void moverZombies() {
		for (Zombie zombie : zombies) {
			zombie.Comportarse();
		}
	}
}
