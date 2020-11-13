package modelo;

import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.Icon;

import controlador.Helpers;

public class Aplicacion {
	
	public ArrayList <Bloque> bloques;
	public ArrayList <Personaje> personajes;
	public ArrayList <Edificacion> edificaciones;
	
	//public HashMap<Integer, GeneradorZombie> puntoSpawn;
	public HashMap<String, Icon> imagenes;
	public Mapa mapa;
	
	public Aplicacion() {
		super();
		this.mapa = new Mapa();
		this.bloques = new ArrayList<>();
		this.personajes = new ArrayList<>();
		this.edificaciones = new ArrayList<>();
		generarJugadores();
	}
		
	public void generarJugadores() {
		
		TipoJugador[] listTipoJugadores = TipoJugador.values();
		ArrayList<Posicion> listPosiciones = new ArrayList<Posicion>(){ 
            /**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			{ 
                add(new Posicion(16, 8)); 
                add(new Posicion(10, 3)); 
                add(new Posicion(10, 8));  
            } 
        };
		for (int i = 0; i < listPosiciones.size(); i++) {
			
			Jugador jug = new Jugador(listTipoJugadores[i], listPosiciones.get(i));
			jug.imagen = Helpers.getImagenResized(listTipoJugadores[i].toString(), ".png",ValoresDefecto.imagenTablero, ValoresDefecto.imagenTablero);
			this.personajes.add(jug);
			Casilla<Personaje> casilla = new Casilla<Personaje>(jug);
			this.mapa.tablero[jug.posicion.x][jug.posicion.y] = casilla;
		}
		
	}
	
	public void generarSpawnPoint() {
		
		Posicion posicion = new Posicion();
		Spawn spawn = new Spawn(null);
	}
	
}
