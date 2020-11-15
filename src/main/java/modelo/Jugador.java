package modelo;

import java.util.HashMap;


public class Jugador extends Personaje{
	
	public TipoJugador tipo;
	
	public HashMap<Integer, Item> inventario;
	public HashMap<String, Arma> armas;
	public Arma armaActual;
	
	public Jugador(TipoJugador pTipo) {
		super();
		this.tipo = pTipo;
		this.vida = pTipo.vida;
		this.cantidadAcciones = pTipo.cantidadAcciones;
		this.pasos = pTipo.pasos;
		this.cantidadAcciones = pTipo.cantidadAcciones;
		this.distanciaVision = pTipo.distanciaVision;
		this.experiencia = pTipo.experiencia;
		this.habilidades = pTipo.habilidades.habilidades;
		this.vivo = true;
		this.inventario = new HashMap<Integer, Item>();
		this.armas = new HashMap<String, Arma>();
		this.armas.put(pTipo.armaInicial.nombre, pTipo.armaInicial);
	}
	
	public Jugador(TipoJugador pTipo, int x, int y) {
		super(x, y);
		this.tipo = pTipo;	
		this.vida = pTipo.vida;
		this.cantidadAcciones = pTipo.cantidadAcciones;
		this.pasos = pTipo.pasos;
		this.cantidadAcciones = pTipo.cantidadAcciones;
		this.distanciaVision = pTipo.distanciaVision;
		this.experiencia = pTipo.experiencia;
		this.vivo = true;
		this.habilidades = Habilidades.Pistolero.habilidades;
		this.inventario = new HashMap<Integer, Item>();
	}

	public Jugador(TipoJugador pTipo, Posicion posicion) {
		super(posicion);
		this.tipo = pTipo;	
		this.vida = pTipo.vida;
		this.cantidadAcciones = pTipo.cantidadAcciones;
		this.pasos = pTipo.pasos;
		this.cantidadAcciones = pTipo.cantidadAcciones;
		this.distanciaVision = pTipo.distanciaVision;
		this.experiencia = pTipo.experiencia;
		this.vivo = true;
		this.habilidades = pTipo.habilidades.habilidades;
		this.inventario = new HashMap<Integer, Item>();
		this.armas = new HashMap<String, Arma>();
		this.armas.put(pTipo.armaInicial.nombre, pTipo.armaInicial);
	}
	
	@Override
	public void Mover(Direccion pDireccion) {
		
		Posicion posicionAnterior = posicion.Mover(pDireccion);
		Boolean sePuedeMover = revisarMovimiento();
		if(!sePuedeMover) {
			this.posicion = posicionAnterior;
		}
	}
	
	@Override
	protected Boolean revisarMovimiento() {
		Casilla[][] tablero  = Aplicacion.getInstance().mapa.tablero;
		
		if( super.revisarMovimiento()) {
			if(tipo == TipoJugador.Ninja &
			   Habilidad.Escalar.nivel <= this.nivel) {
				return true;
			}else {
				return false;
			}
			
		}
		return true;

	}
	
	@Override
	public void Atacar(Personaje pPersonaje) {
		if(puedeAtacar(pPersonaje)) {
			super.Atacar(pPersonaje);
		}else {
			
		}
	}

	private Boolean puedeAtacar(Personaje pPersonaje) {
		return !(armaActual == null) && posicion.distancia(pPersonaje.posicion) <= armaActual.alcance;
	}
	
}
