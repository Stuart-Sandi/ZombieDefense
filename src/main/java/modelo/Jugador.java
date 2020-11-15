package modelo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;


public class Jugador extends Personaje{
	
	public TipoJugador tipo;
	
	public ArrayList<Item> inventario;
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
		this.inventario = new ArrayList<Item>();
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
		this.inventario = new ArrayList<Item>();
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
		this.inventario = new ArrayList<Item>();
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
			if(!pPersonaje.vivo) {
				muerteZombie(pPersonaje);
				agregarExperiencia(pPersonaje);
			}
		}
	}

	private void agregarExperiencia(Personaje pPersonaje) {
		int expNueva = pPersonaje.nivel*10;
		if(puedeUsarHabilidad(Habilidad.ProbExperienciaExtra)) {
			Random rand = new Random();
			int probDeEvadir = 1 + rand.nextInt(100);
			if(probDeEvadir <= Habilidad.ProbExperienciaExtra.valor) {
				expNueva += rand.nextInt(100);
				System.out.println("Uso habilidad de XP extra");
			}
			
		}
		this.experiencia += expNueva;
		revisarNuevoNivel();
	}

	private void revisarNuevoNivel() {
		if(experiencia > 100) {
			nivel += experiencia/100;
			experiencia = experiencia - 100;
			
		}	
	}

	private void muerteZombie(Personaje pPersonaje) {
		java.util.Random rand = new java.util.Random();
		for(int i = 0; i < rand.nextInt(pPersonaje.nivel); i++) {
			agregarItemRandom();
		}
	}
	
	private void agregarItemRandom() {
		java.util.Random rand = new java.util.Random();
		
		if(rand.nextBoolean()) {
			Arma arma = ArmasPrecargadas.values()[rand.nextInt( ArmasPrecargadas.values().length)].arma;
			try {
				arma = arma.clone();
			} catch (CloneNotSupportedException e) {
				System.out.println("No se pudo agregar el item "+arma+" a su inventario");
			}
			this.armas.put(arma.nombre, arma);
			System.out.println("Se agrego "+arma);
		}else {
			Item item = ConsumiblesPrecargados.values()[rand.nextInt( ConsumiblesPrecargados.values().length)].item;
			try {
				item = item.clone();
			} catch (CloneNotSupportedException e) {
				System.out.println("No se pudo agregar el item "+item+" a su inventario");
			}
			this.inventario.add(item);
			System.out.println("Se agrego "+item);
		}
	}

	private Boolean puedeAtacar(Personaje pPersonaje) {
		return !(armaActual == null) && posicion.distancia(pPersonaje.posicion) <= armaActual.alcance;
	}

	public void usarItem(Item selectedItem) {
		this.vida += selectedItem.valor;
		this.inventario.remove(selectedItem);
		System.out.println("Se uso "+selectedItem+" paso de: "+(vida-selectedItem.valor)+" a "+vida);
	}
	
}
