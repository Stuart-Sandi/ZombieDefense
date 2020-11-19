package modelo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import javax.swing.Icon;

public class Personaje {
	public Posicion posicion;
	public int pasos, distanciaVision, vida, cantidadAcciones, nivel, experiencia;
	public Boolean vivo;
	public Icon imagen;
	public ArrayList<Habilidad> habilidades;
	public Personaje() {
		super();
		this.posicion = new Posicion();
		this.nivel = 1;
		this.vivo = true;
	}
	
	public Personaje(int x, int y) {
		super();
		this.posicion = new Posicion(x, y);
		this.nivel = 1;
	}
	
	public Personaje(Posicion pPosicion) {
		super();
		this.posicion = pPosicion;
		this.nivel = 1;
	}
	
	public String Atacar(Personaje pPersonaje, int arma) {
		String mensaje = "";
		/*Es ataque esta definido por el nivel del personaje mas el valor de la habilidad si es que tiene
		 * */
		int ataque = arma;
		if(puedeUsarHabilidad(Habilidad.DannoExtra)) {
			int danoExtra = nivel*Habilidad.DannoExtra.valor;
			ataque += danoExtra;
			mensaje += "con daño extra de "+danoExtra+" ";

		}
		mensaje += pPersonaje.recibirAtaque(ataque);
		return mensaje;
	}
	
	public String recibirAtaque(int valor) {
		
		String mensaje = "";
		if(puedeUsarHabilidad(Habilidad.ProbEvadirAtaques)) {
			Random rand = new Random();
			int probDeEvadir = 1 + rand.nextInt(100);
			if(probDeEvadir <= Habilidad.ProbEvadirAtaques.valor) {
				valor = 0;
				mensaje += " pero evitó el ataque";
			}
		}
		if (puedeUsarHabilidad(Habilidad.Defensa)) {
			if (valor != 0) {
				valor  = valor/2;
				System.out.println("Valor nuevo: "+valor);
				mensaje += ", pero utiilizo defensa";
			}
		}
		
		this.vida = this.vida - valor;
		if (vida < 0) {
			this.vivo = false;
		}
		
		mensaje += "\n";
		return mensaje;
	}
	
	public void Mover(Direccion pDireccion) {
		Posicion posicionAnterior = posicion.Mover(pDireccion);
		Boolean sePuedeMover = revisarMovimiento();
		if(sePuedeMover) {
			this.posicion = posicionAnterior;
		}else {
			Aplicacion.getInstance().mapa.tableroPersonajes[posicionAnterior.x][posicionAnterior.y] = null;
			Aplicacion.getInstance().mapa.tableroPersonajes[posicion.x][posicion.y] = this;
		}
		
	}
	
	protected Boolean revisarMovimiento() {
		Casilla[][] tablero  = Aplicacion.getInstance().mapa.tablero;
		if(posicion.x < ValoresDefecto.altoTablero && posicion.y < ValoresDefecto.anchoTablero &&
			posicion.x >= 0 && posicion.y >= 0) {
				String nombreClaseCasilla = tablero[posicion.x][posicion.y].elemento.getClass().getName();
				Boolean condicion = ( nombreClaseCasilla == Edificacion.class.getName());
				return condicion;
		}else {
			return false;
		}

	}
	
	protected Boolean puedeUsarHabilidad(Habilidad hab) {
		return (habilidades.contains(hab) && nivel >= hab.nivel);
	}
}
