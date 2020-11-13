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
	public Icon imgBloque;
	
	public Aplicacion() {
		super();
		this.mapa = new Mapa();
		this.bloques = new ArrayList<>();
		this.personajes = new ArrayList<>();
		this.edificaciones = new ArrayList<>();
		this.imgBloque = Helpers.getImagenResized("cesped", ".jpg", ValoresDefecto.imagenTablero, ValoresDefecto.imagenTablero);
		generarTablero();
		generarJugadores();
		generarObstaculos();
	}

	//cesped.jpg
	public void generarTablero() {
		
		for (int i = 0; i < modelo.ValoresDefecto.altoTablero; i++) {
            
            for (int j = 0; j < modelo.ValoresDefecto.anchoTablero; j++) {
                Bloque bloque = new Bloque(imgBloque);
                Casilla<Bloque> casilla = new Casilla<Bloque>(bloque);
                this.mapa.tablero[i][j] = casilla;

            }
        }
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
	
	public void generarObstaculos() {
		
		Icon imgMuro = Helpers.getImagenResized("muro", ".jpg", ValoresDefecto.imagenTablero, ValoresDefecto.imagenTablero);
		Icon imgBase = Helpers.getImagenResized("Base", ".png", ValoresDefecto.imagenTablero, ValoresDefecto.imagenTablero);
		
		for (int i = 0; i < ValoresDefecto.listaPosMuroX.length; i++) {
			
			int x = ValoresDefecto.listaPosMuroX[i];
			int y = ValoresDefecto.listaPosMuroY[i];
			
			Posicion posicion = new Posicion(x,y);
			Edificacion muro = new Edificacion(ValoresDefecto.vidaMuro,posicion,imgMuro);
			Casilla<Edificacion> casilla = new Casilla<Edificacion>(muro);
			this.edificaciones.add(muro);
			this.mapa.tablero[x][y] = casilla;
			
		}
		
		//Agrega la base
		Posicion posicion = new Posicion(19,0);
		Edificacion base = new Edificacion(ValoresDefecto.vidaMuro,posicion,imgBase);
		Casilla<Edificacion> casilla = new Casilla<Edificacion>(base);
		this.edificaciones.add(base);
		this.mapa.tablero[19][0] = casilla;
		
	}
	
}
