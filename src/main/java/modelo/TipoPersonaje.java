package modelo;

import java.util.Arrays;
import java.util.List;

public enum TipoPersonaje {
	//pasos, distanciaVision, vida, cantidadAcciones, experiencia;
	ZombieNormal(7,7,100,3,1),
	ZombieListener(15,15,90,3,20),
	ZombieKirstein(10,10,200,3,50),
	ZombieChayanne(10,10,120,3,30),
	Pistolero(8,8,100,3,10),
	Sniper(5,5,80,3,20),
	Tanque(7,7,150,3,25),
	Ninja(17,17,75,3,15);
	
	
	public int pasos, distanciaVision, vida, cantidadAcciones, experiencia;
	TipoPersonaje(int pasos, int distanciaVision, int vida, int cantidadAcciones, int experiencia) {
		this.pasos = pasos;
		this.distanciaVision = distanciaVision;
		this.vida = vida;
		this.cantidadAcciones = cantidadAcciones;
		this.experiencia = experiencia;
		
	}
	public static List Enemigos () {
		return Arrays.asList(
				ZombieNormal,
				ZombieListener,
				ZombieKirstein,
				ZombieChayanne);
	}
	public static List Aliados() {
		return Arrays.asList(
				Pistolero,
				Sniper,
				Tanque,
				Ninja);
	}
}
