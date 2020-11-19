package modelo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;


public class Jugador extends Personaje{
	
	public TipoJugador tipo;
	public boolean ataque,item,movi;
	public ArrayList<Item> inventario;
	public HashMap<String, Arma> armas;
	public Arma armaActual;
	public Boolean validarHabilidadDTurno;
	
	public Jugador(TipoJugador pTipo) {
		super();
		this.validarHabilidadDTurno = false;
		this.tipo = pTipo;
		this.vida = pTipo.vida;
		this.cantidadAcciones = pTipo.cantidadAcciones;
		this.pasos = pTipo.pasos;
		this.cantidadAcciones = pTipo.cantidadAcciones;
		this.distanciaVision = pTipo.distanciaVision;
		this.experiencia = pTipo.experiencia;
		this.habilidades = pTipo.habilidades.habilidades;
		this.vivo = true;
		this.ataque = this.item = this.movi = false;
		this.inventario = new ArrayList<Item>();
		this.armas = new HashMap<String, Arma>();
		this.armas.put(pTipo.armaInicial.nombre, pTipo.armaInicial);
	}
	
	public Jugador(TipoJugador pTipo, int x, int y) {
		super(x, y);
		this.tipo = pTipo;	
		this.vida = pTipo.vida;
		this.validarHabilidadDTurno = false;
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
		this.validarHabilidadDTurno = false;
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
		}else {
			Aplicacion.getInstance().mapa.tableroPersonajes[posicionAnterior.x][posicionAnterior.y] = null;
			Aplicacion.getInstance().mapa.tableroPersonajes[posicion.x][posicion.y] = this;
			
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
	public String Atacar(Personaje pPersonaje, int arma) {
		String dato = "";
		if(puedeAtacar(pPersonaje)) {
			dato += arma+" de daño ";
			dato += super.Atacar(pPersonaje, arma);
			
			if(!pPersonaje.vivo) {
				dato += muerteZombie(pPersonaje);
				dato += agregarExperiencia(pPersonaje);
			}
		}
		return dato;
	}

	private String agregarExperiencia(Personaje pPersonaje) {
		String mensaje = "";
		int expNueva = pPersonaje.nivel*10;
		if(puedeUsarHabilidad(Habilidad.ProbExperienciaExtra)) {
			Random rand = new Random();
			int probDeEvadir = 1 + rand.nextInt(100);
			if(probDeEvadir <= Habilidad.ProbExperienciaExtra.valor) {
				expNueva += rand.nextInt(100);
				//System.out.println("Uso habilidad de XP extra");
			}
			
		}
		this.experiencia += expNueva;
		mensaje += "\n";
		mensaje += revisarNuevoNivel();
		return mensaje;
	}

	private String revisarNuevoNivel() {
		String mensaje = "";
		if(experiencia > 100) {
			nivel += experiencia/100;
			experiencia = experiencia - 100;
			mensaje += "El "+this.tipo.toString()+" ha aumentado de nivel\n";
		}	
		return mensaje;
	}

	private String muerteZombie(Personaje pPersonaje) {
		String mensaje = "";
		java.util.Random rand = new java.util.Random();
		for(int i = 0; i < rand.nextInt(pPersonaje.nivel); i++) {
			mensaje += agregarItemRandom();
		}
		return mensaje;
	}
	
	private String agregarItemRandom() {
		String mensaje = "";
		java.util.Random rand = new java.util.Random();
		
		if(rand.nextBoolean()) {
			Arma arma = ArmasPrecargadas.values()[rand.nextInt( ArmasPrecargadas.values().length)].arma;
			try {
				arma = arma.clone();
			} catch (CloneNotSupportedException e) {}
			this.validarArmaRepetida(arma);

		}else {
			Item item = ConsumiblesPrecargados.values()[rand.nextInt( ConsumiblesPrecargados.values().length)].item;
			try {
				item = item.clone();
				mensaje += this.tipo.toString()+" encontro un item\n";
			} catch (CloneNotSupportedException e) {}
			this.inventario.add(item);

		}
		return mensaje;
	}
	
	private String validarArmaRepetida(Arma arma) {
		String mensaje = "";
		if (!this.armas.containsKey(arma.nombre)) {
			this.armas.put(arma.nombre, arma);
			mensaje += this.tipo.toString()+" encontro un arma\n";
		}
		return mensaje;
	}

	private Boolean puedeAtacar(Personaje pPersonaje) {
		return !(armaActual == null) && posicion.distancia(pPersonaje.posicion) <= armaActual.alcance;
	}

	public String usarItem(Item selectedItem) {
		/*
		 * Metodo encargado de usar el item seleccionado en el jugador
		 */
		String mensaje = "";
		if(!(selectedItem.nombre).equals(ConsumiblesPrecargados.POCION.item.nombre)) {
			if (puedeUsarHabilidad(Habilidad.VidaExtra)) {
				this.vida += selectedItem.valor+Habilidad.VidaExtra.valor;
				mensaje += this.tipo.toString()+" uso el item "+selectedItem.nombre+" y la habilidad para aumnetar "
				+selectedItem.valor+"+"+Habilidad.VidaExtra.valor+" de vida\n";
			}else {
				this.vida += selectedItem.valor;
				mensaje += this.tipo.toString()+" uso el item "+selectedItem.nombre+" y aumentó "+selectedItem.valor+" de vida\n";
			}
			
			
		}else {
			mensaje += this.tipo.toString()+" uso el item "+selectedItem.nombre+" y aumentó "+selectedItem.valor+" de experencia\n";
			this.experiencia += selectedItem.valor;
		}
		
		this.inventario.remove(selectedItem);
		this.item = true;
		return mensaje;
	}
	
	public void modificarAcciones(Estado estado) {
		/*
		 * Es el metodo encargado de ir revisando que acciones ya se hicieron en el turno
		 */
		
		switch (estado){
		
		case ATACANDO:
			if (puedeUsarHabilidad(Habilidad.AccionesPorTurno) && !this.validarHabilidadDTurno) {
				this.validarHabilidadDTurno = true;
			}else {
				this.ataque = true;
			}
			
			break;
			
		case USANDOITEM:
			
			if (puedeUsarHabilidad(Habilidad.AccionesPorTurno) && !this.validarHabilidadDTurno) {
				this.validarHabilidadDTurno = true;
			}else {
				this.item = true;
			}
			break;
			
		case MOVIENDO:
			this.movi = true;
			break;
		}
	}
	
	public boolean totalAcciones() {
		/*
		 * Metodo encargado de devolver true si ya hizo todas las acciones del turno
		 */
		
		if (this.ataque && this.item && this.movi) {
			return true;
		}
		return false;
	}
	
	public String reestablecerAcciones() {
		
		String mensaje = "";
		
		if (totalAcciones()) {
			if (this.puedeUsarHabilidad(Habilidad.Pasos)) {
				Random rand = new Random();
				int pasosExtra = 1 + rand.nextInt(5);
				mensaje += "Se le asignó "+pasosExtra+" paso extra al "+this.tipo.toString()+"\n";  
				this.pasos = this.tipo.pasos+pasosExtra;
			}else {
				this.pasos = this.tipo.pasos;
			}
			
			this.ataque = this.item = this.movi = this.validarHabilidadDTurno = false;
		}
		return mensaje;
	}
	
	public void saltarTurno() {
		
		this.ataque = this.item = this.movi = true;
	}
	
}
