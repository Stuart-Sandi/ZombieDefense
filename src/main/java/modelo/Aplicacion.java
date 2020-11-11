package modelo;

import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.Icon;

import controlador.Helpers;

public class Aplicacion {
	
	public Bloque[][] bloques;
	public Personaje[][] personajes;
	public Edificacion[][] edificaciones;
	public HashMap<Integer, GeneradorZombie> puntoSpawn;
	public HashMap<String, Icon> imagenes;
	public Mapa mapa;
	public Icon imgBloque;
	
	public Aplicacion() {
		super();
		this.mapa = new Mapa();
		this.bloques = new Bloque[ValoresDefecto.altoTablero][ValoresDefecto.anchoTablero];
		this.personajes = new Personaje[ValoresDefecto.altoTablero][ValoresDefecto.anchoTablero];
		this.edificaciones = new Edificacion[ValoresDefecto.altoTablero][ValoresDefecto.anchoTablero];
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
                //this.bloques[i][j] = bloque;

            }
        }
	}
		
	public void generarJugadores() {
		
		TipoJugador[] listTipoJugadores = TipoJugador.values();
		ArrayList<Posicion> listPosiciones = new ArrayList<Posicion>(){ 
            { 
                add(new Posicion(16, 8)); 
                add(new Posicion(10, 3)); 
                add(new Posicion(10, 8));  
            } 
        };
		for (int i = 0; i < listPosiciones.size(); i++) {
			
			Jugador jug = new Jugador(listTipoJugadores[i], listPosiciones.get(i));
			jug.imagen = Helpers.getImagenResized(listTipoJugadores[i].toString(), ".png",ValoresDefecto.imagenTablero, ValoresDefecto.imagenTablero);
			this.personajes[jug.posicion.x][jug.posicion.y] = jug;
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
			this.mapa.tablero[x][y] = casilla;
			
		}
		
		//Agrega la base
		Posicion posicion = new Posicion(19,0);
		Edificacion base = new Edificacion(ValoresDefecto.vidaMuro,posicion,imgBase);
		Casilla<Edificacion> casilla = new Casilla<Edificacion>(base);
		this.mapa.tablero[19][0] = casilla;
		
	}
	
}
