package modelo;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.Icon;

import controlador.Helpers;

public class Mapa {
	
	public Casilla[][] tablero;
	public ArrayList<Spawn> puntosSpawn;
	public ArrayList<Posicion> listaRuido;
	public Personaje[][] tableroPersonajes;
	public Icon imgBloque, imgBase;
	public Posicion posicionBase;
	
	public Mapa() {
		super();
		this.posicionBase = new Posicion(ValoresDefecto.posBase[0],ValoresDefecto.posBase[1]);
		this.imgBloque = Helpers.getImagenResized("cesped", ".jpg", ValoresDefecto.imagenTablero, ValoresDefecto.imagenTablero);
		this.imgBase = Helpers.getImagenResized("Base", ".png", ValoresDefecto.imagenTablero, ValoresDefecto.imagenTablero);
		this.tablero = new Casilla[ValoresDefecto.altoTablero][ValoresDefecto.anchoTablero];
		this.tableroPersonajes = new Personaje[ValoresDefecto.altoTablero][ValoresDefecto.anchoTablero];
		this.puntosSpawn = new ArrayList<>();
		this.listaRuido = new ArrayList<>();
		this.generarTablero();
		this.generarObstaculos();
		this.generarSpawnPoint();
		this.generarBase();
	}
	
	public Posicion encontrarCasillaVacia() {
		
		Posicion posicion;
		
		while (true) {
			
			int x = (new Random()).nextInt(19) + 1;
			int y = (new Random()).nextInt(19) + 1;
			
			if (!this.validarPosInvalidaSpawn(x, y)) {
				
				System.out.println("X: "+x+" Y: "+y);
				if ((this.tablero[x][y].elemento.getClass().getSimpleName()).equals("Bloque")) {
					
					if (this.tableroPersonajes[x][y] == null) {
						posicion = new Posicion(x,y);
						break;
					}
					
				}
			}
		}
		
		
		return posicion;
	}
	
	public boolean validarPosInvalidaSpawn(int x, int y) {
		
		ArrayList<Integer> lX = new ArrayList<>();
		ArrayList<Integer> lY = new ArrayList<>();
		
		for (int i = 0; i < ValoresDefecto.listaPosNoSpawnX.length; i++) {
			lX.add(ValoresDefecto.listaPosNoSpawnX[i]);
			lY.add(ValoresDefecto.listaPosNoSpawnY[i]);
		}
		
		if (lX.contains(x) && lY.contains(y)) {
			return true;
		}
		return false;
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
		Icon imgRio = Helpers.getImagenResized("Rio", ".png", ValoresDefecto.imagenTablero, ValoresDefecto.imagenTablero);
		
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
		/*
		 * Se encarga de generar la base aliada
		 */
		
		Edificacion base = new Edificacion(ValoresDefecto.vidaBase,this.posicionBase,imgBase);
		Casilla<Edificacion> casilla = new Casilla<Edificacion>(base);
		tablero[this.posicionBase.x][this.posicionBase.y] = casilla;
	}
	
	public void generarSpawnPoint() {
		
		for (int i = 0; i < 2; i++) {
			Posicion posicion = this.encontrarCasillaVacia();
			if (posicion != null) {
				
				Spawn spawn = new Spawn(posicion);
				this.tablero[spawn.posicion.x][spawn.posicion.y] = new Casilla<Spawn>(spawn);
				this.puntosSpawn.add(spawn);
				System.out.println("Lista Spawn: "+this.puntosSpawn.size());
			}
		}
		
	}
	
	public ArrayList<Zombie> generarZombie() {
		/*
		 * Se encarga de generar zombies por cada uno de los puntos de spawn
		 */
		ArrayList<Zombie> nuevosZombies = new ArrayList<>();
		for (int i = 0; i < this.puntosSpawn.size(); i++) {
			
			Spawn spawn = this.puntosSpawn.get(i);
			Posicion posicion = new Posicion(spawn.posicion.x,spawn.posicion.y);
			Zombie zombie = spawn.generarZombie(posicion);
			this.tableroPersonajes[zombie.posicion.x][zombie.posicion.y] = zombie;
			nuevosZombies.add(zombie);
		}
		return nuevosZombies;
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
