package modelo;

import java.util.Random;

import javax.swing.Icon;

import controlador.Helpers;

public class Spawn {
	
	public Icon imagen;
	public Posicion posicion;
	
	public Spawn(Posicion pPosicion) {
		this.posicion = pPosicion;
		this.imagen = Helpers.getImagenResized("Spawn", ".png", ValoresDefecto.anchoTablero, ValoresDefecto.altoTablero);
	}
	
	public Zombie generarZombie(Posicion pPosicion) {
		Random rand = new Random();
		TipoZombie[] tiposZombies = TipoZombie.values();
		TipoZombie tZombie = tiposZombies[(rand.nextInt(tiposZombies.length))];
		Zombie p = new Zombie(tZombie);
		p.imagen = Helpers.getImagenResized(tZombie.toString(), ".png", ValoresDefecto.anchoTablero, ValoresDefecto.altoTablero);
		p.posicion = pPosicion;
		return p;
	}
}
