package modelo;

import java.util.Random;

import javax.swing.Icon;

import controlador.Helpers;

public class Spawn {
	
	Icon imagen;
	Posicion posicion;
	
	public Spawn(Posicion pPosicion) {
		this.posicion = pPosicion;
		this.imagen = Helpers.getImagenResized("Spawn", ".png", ValoresDefecto.anchoTablero, ValoresDefecto.altoTablero);
	}
	
	public Zombie generarZombie() {
		Random rand = new Random();
		TipoZombie[] tiposZombies = TipoZombie.values();
		Zombie p = new Zombie( tiposZombies[(rand.nextInt(tiposZombies.length))]);
		return p;
	}
}
