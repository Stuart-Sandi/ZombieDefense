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
		this.nivel = 100;
	}
	
	public Personaje(int x, int y) {
		super();
		this.posicion = new Posicion(x, y);
		this.nivel = 100;
	}
	
	public Personaje(Posicion pPosicion) {
		super();
		this.posicion = pPosicion;
		this.nivel = 100;
	}
	
	public int Atacar(Personaje pPersonaje) {
		/*Es ataque esta definido por el nivel del personaje mas el valor de la habilidad si es que tiene
		 * */
		int ataque = nivel;
		if(puedeUsarHabilidad(Habilidad.DannoExtra)) {
			ataque += nivel*Habilidad.DannoExtra.valor;
			System.out.println("Uso habilidad de daño extra, golpeo con: "+ataque);
		}
		pPersonaje.recibirAtaque(ataque);
		System.out.println("Ataco por: "+ataque);
		return ataque;
	}
	
	public void recibirAtaque(int valor) {
		
		if(puedeUsarHabilidad(Habilidad.ProbEvadirAtaques)) {
			Random rand = new Random();
			int probDeEvadir = 1 + rand.nextInt(100);
			if(probDeEvadir <= Habilidad.ProbEvadirAtaques.valor) {
				valor = 0;
				System.out.println("Evadio el ataque");
			}
		}
		this.vida = getVida() - valor;
		if (vida < 0) {
			this.vivo = false;
		}
		System.out.print("Vivo: "+vivo);
		System.out.println(" recibio daño por: "+valor);
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
	
	public int getVida() {
		List<Habilidad> habilidadesDefensivas = Arrays.asList(Habilidad.Defensa, Habilidad.VidaExtra);
		int vidaExtra = valorExtraHabilidades(habilidadesDefensivas);
		return this.vida + vidaExtra;
	}
	
	public int getPasos() {	
		List<Habilidad> habilidadesPasos = Arrays.asList(Habilidad.Pasos);
		int pasosExtra = valorExtraHabilidades(habilidadesPasos);
		return this.pasos + pasosExtra;
	}
	
	private int valorExtraHabilidades(List<Habilidad> habilidadesDefensivas) {
		int valorExtra = 0;
		for (Habilidad habilidad : habilidadesDefensivas) {
			if(puedeUsarHabilidad(habilidad)) {
				valorExtra += habilidad.valor;
			}
		}
		return valorExtra;
	}
}
