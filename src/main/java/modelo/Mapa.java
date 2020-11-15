package modelo;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.Icon;

import controlador.Helpers;

public class Mapa {
	
	public Casilla[][] tablero;
	public ArrayList<Spawn> puntosSpawn;
	public Personaje[][] tableroPersonajes;
	public Icon imgBloque, imgBase;
	private Posicion posicionBase;
	
	public Mapa() {
		super();
		this.posicionBase = new Posicion(ValoresDefecto.posBase[0],ValoresDefecto.posBase[1]);
		this.imgBloque = Helpers.getImagenResized("cesped", ".jpg", ValoresDefecto.imagenTablero, ValoresDefecto.imagenTablero);
		this.imgBase = Helpers.getImagenResized("Base", ".png", ValoresDefecto.imagenTablero, ValoresDefecto.imagenTablero);
		this.tablero = new Casilla[ValoresDefecto.altoTablero][ValoresDefecto.anchoTablero];
		this.tableroPersonajes = new Personaje[ValoresDefecto.altoTablero][ValoresDefecto.anchoTablero];
		this.puntosSpawn = new ArrayList<>();
		this.generarTablero();
		this.generarObstaculos();
		this.generarSpawnPoint();
		this.generarBase();
	}
	
	public Posicion encontrarCasillaVacia() {
		
		Posicion posicion;
		ArrayList<Integer> listaX = new ArrayList<>();
		ArrayList<Integer> listaY = new ArrayList<>();
		
		while (true) {
			
			int x = (new Random()).nextInt(19-8+1) + 8;
			int y = (new Random()).nextInt(19-12+1) + 12;
			
			System.out.println("X: "+x+" Y: "+y);
			if ((this.tablero[x][y].elemento.getClass().getSimpleName()).equals("Bloque")) {
				posicion = new Posicion(x,y);
				break;
			}
			
			//Hace estos if en caso de no quedar mas espacio para generar spawns
			if (!listaX.contains(x)) {
				listaX.add(x);
			}
			if (!listaY.contains(y)) {
				listaX.add(y);
			}
			
			if (listaX.size() == 7 && listaY.size() == 11) {
				return null;
			}
		}
		
		
		return posicion;
	}
	
	public void generarTablero() {
		
		for (int i = 0; i < modelo.ValoresDefecto.anchoTablero; i++) {
            
            for (int j = 0; j < modelo.ValoresDefecto.altoTablero; j++) {
                Bloque bloque = new Bloque(imgBloque);
                Casilla<Bloque> casilla = new Casilla<Bloque>(bloque);
                tablero[i][j] = casilla;
                
            }
        }
	}
	
	public void generarObstaculos() {
		
		Icon imgMuro = Helpers.getImagenResized("muro", ".jpg", ValoresDefecto.imagenTablero, ValoresDefecto.imagenTablero);
		
		for (int i = 0; i < ValoresDefecto.listaPosMuroX.length; i++) {
			
			int x = ValoresDefecto.listaPosMuroX[i];
			int y = ValoresDefecto.listaPosMuroY[i];
			
			Posicion posicion = new Posicion(x,y);
			Edificacion muro = new Edificacion(ValoresDefecto.vidaMuro,posicion,imgMuro);
			Casilla<Edificacion> casilla = new Casilla<Edificacion>(muro);
			tablero[x][y] = casilla;
			
		}
		
		
	}
	
	public void generarBase() {
		
		Edificacion base = new Edificacion(ValoresDefecto.vidaBase,this.posicionBase,imgBase);
		Casilla<Edificacion> casilla = new Casilla<Edificacion>(base);
		tablero[this.posicionBase.x][this.posicionBase.y] = casilla;
	}
	

	public void generarSpawnPoint() {
		
		Posicion posicion = this.encontrarCasillaVacia();
		if (posicion != null) {
			
			Spawn spawn = new Spawn(posicion);
			this.tablero[spawn.posicion.x][spawn.posicion.y] = new Casilla<Spawn>(spawn);
			this.puntosSpawn.add(spawn);
		}
	}
	
	public void generarZombie() {
		
		for (int i = 0; i < this.puntosSpawn.size(); i++) {
			
			Spawn spawn = this.puntosSpawn.get(i);
			Zombie zombie = spawn.generarZombie(spawn.posicion);
			this.tableroPersonajes[zombie.posicion.x][zombie.posicion.y] = zombie;
			
		}
	}
	
	public boolean validarCampoVacio(Direccion direccion, Personaje personaje) {
		/*
		 * Se encarga de validar que la nueva posicion del jugador no este ocupada
		 */
		
		int x = personaje.posicion.x + direccion.x;
		int y = personaje.posicion.y + direccion.y;
		if(x < ValoresDefecto.altoTablero && y < ValoresDefecto.anchoTablero &&
			x >= 0 && y >= 0) {
			Personaje jug = tableroPersonajes[x][y];
			
			return jug == null;
		}else{
			return false;
		}
		
	}

}
