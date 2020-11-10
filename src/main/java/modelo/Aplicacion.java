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
	
	public Aplicacion() {
		super();
		this.mapa = new Mapa();
		this.bloques = new Bloque[ValoresDefecto.altoTablero][ValoresDefecto.anchoTablero];
		this.personajes = new Personaje[ValoresDefecto.altoTablero][ValoresDefecto.anchoTablero];
		this.edificaciones = new Edificacion[ValoresDefecto.altoTablero][ValoresDefecto.anchoTablero];
		generarTablero();
		generarJugadores();
		generarObstaculos();
	}

	//cesped.jpg
	public void generarTablero() {
		Icon imgBloque = Helpers.getImagenResized("cesped", ".jpg", ValoresDefecto.imagenTablero, ValoresDefecto.imagenTablero);
		for (int i = 0; i < modelo.ValoresDefecto.altoTablero; i++) {
            
            for (int j = 0; j < modelo.ValoresDefecto.anchoTablero; j++) {
                Bloque bloque = new Bloque(imgBloque);
                Casilla casilla = new Casilla<Icon>(bloque.icon);
                this.mapa.tablero[i][j] = casilla;
                this.bloques[i][j] = bloque;

            }
        }
	}
		
	public void generarJugadores() {
		ArrayList<Jugador> lista = new ArrayList<Jugador>();
		TipoJugador[] listTipoJugadores = TipoJugador.values();
		ArrayList<Posicion> listPosiciones = new ArrayList<Posicion>(){ 
            { 
                add(new Posicion(16, 8)); 
                add(new Posicion(10, 3)); 
                add(new Posicion(10, 8));  
            } 
        };
		for (int i = 0; i < listPosiciones.size(); i++) {
			
			System.out.println(listTipoJugadores[i].toString());
			Jugador jug = new Jugador(listTipoJugadores[i], listPosiciones.get(i));
			jug.img = Helpers.getImagenResized(listTipoJugadores[i].toString(), ".png",ValoresDefecto.imagenTablero, ValoresDefecto.imagenTablero);
			lista.add(jug);
			this.personajes[jug.posicion.x][jug.posicion.y] = jug;
			Casilla<Icon> casilla = new Casilla<Icon>(jug.img);
			this.mapa.tablero[jug.posicion.x][jug.posicion.y] = casilla;
		}
		
	}
	
	public void generarObstaculos() {
		
		Icon imgMuro = Helpers.getImagenResized("muro", ".jpg", ValoresDefecto.imagenTablero, ValoresDefecto.imagenTablero);
		Icon imgBase = Helpers.getImagenResized("Base", ".png", ValoresDefecto.imagenTablero, ValoresDefecto.imagenTablero);
		
		for (int i = 0; i < ValoresDefecto.listaPosMuroX.length; i++) {
			
			int x = ValoresDefecto.listaPosMuroX[i];
			int y = ValoresDefecto.listaPosMuroY[i];
			
			Casilla<Icon> casilla = new Casilla<Icon>(imgMuro);
			Edificacion muro = new Edificacion(ValoresDefecto.vidaMuro,x,y,imgMuro);
			this.mapa.tablero[x][y] = casilla;
			this.edificaciones[x][y] = muro;
			
		}
		
		//Agrega la base
		Casilla<Icon> casilla = new Casilla<Icon>(imgBase);
		Edificacion base = new Edificacion(ValoresDefecto.vidaMuro,19,0,imgBase);
		this.mapa.tablero[19][0] = casilla;
		this.edificaciones[19][0] = base;
		
		
	}
	
}
