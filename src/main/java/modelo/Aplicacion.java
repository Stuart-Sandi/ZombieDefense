package modelo;

import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.Icon;

import controlador.Helpers;

public class Aplicacion {
	static Aplicacion self;
	public ArrayList <Bloque> bloques;
	public ArrayList <Personaje> personajes;
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
		this.mapa = new Mapa();
		this.bloques = new ArrayList<>();
		this.personajes = new ArrayList<>();
		this.edificaciones = new ArrayList<>();
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
                add(new Posicion(8, 16)); 
                add(new Posicion(3, 10)); 
                add(new Posicion(8, 10));  
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
		
		this.mapa.generarZombie();
	}
}
