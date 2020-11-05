package modelo;

import java.util.Arrays;
import java.util.List;

public enum TipoPersonaje {
	ZombieNormal,
	ZombieListener,
	ZombieKirstein,
	ZombieChayanne,
	Pistolero,
	Sniper,
	Tanque,
	Ninja;
	
	
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
