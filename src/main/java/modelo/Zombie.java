package modelo;

import java.util.ArrayList;

import javax.swing.Icon;

public class Zombie extends Personaje{
	public TipoZombie tipo;
	public int dano;
	
	public Zombie(TipoZombie pTipo) {
		super();
		this.tipo = pTipo;	
		this.vida = pTipo.vida;
		this.pasos = pTipo.pasos;
		this.dano = pTipo.dano;
		this.cantidadAcciones = 0;
		this.distanciaVision = pTipo.distanciaVision;
		this.vivo = true;
		this.nivel = pTipo.nivel;
		this.habilidades = pTipo.habilidades.habilidades;
	}

	public Zombie(TipoZombie pTipo, int x, int y) {
		super(x, y);
		this.tipo = pTipo;	
		this.vida = pTipo.vida;
		this.dano = pTipo.dano;
		this.pasos = pTipo.pasos;
		this.cantidadAcciones = 0;
		this.distanciaVision = pTipo.distanciaVision;
		this.vivo = true;
		this.nivel = pTipo.nivel;
		this.habilidades = pTipo.habilidades.habilidades;
	}

	public String Comportarse() {
		String mensaje = intentoAtaque();
		if(mensaje == "") {
			if(!intentoVerYaAvanzar()) {
				if(!intentoEscucharYaAvanzar()) {
					Direccion direccion = posicion.getDireccion(Aplicacion.getInstance().mapa.posicionBase);
            		this.Mover(direccion);
            		//System.out.println("Avanzar a la base");
				}
				
			}
			
		}
		return mensaje;
		
	}

	private boolean intentoEscucharYaAvanzar() {
		ArrayList<Posicion> posicionesRuido = Aplicacion.getInstance().mapa.listaRuido;
		if(posicionesRuido.size() > 0) {
			Direccion direccion = posicion.getDireccion(posicionesRuido.remove(0));
    		this.Mover(direccion);
    	//	System.out.println("Escucho");
    		return true;
		}
		return false;
	}

	private boolean intentoVerYaAvanzar() {
		Personaje[][] tablero = Aplicacion.getInstance().mapa.tableroPersonajes;

		for (int i = 0; i < modelo.ValoresDefecto.altoTablero; i++) {
            
            for (int j = 0; j < modelo.ValoresDefecto.anchoTablero; j++) {
            	if(tablero[i][j] != null && posicion.distancia(new Posicion(i, j))  <= distanciaVision &&
            		Aplicacion.getInstance().mapa.tableroPersonajes[i][j] != this) {
            		Direccion direccion = posicion.getDireccion(new Posicion(i, j));
            		this.Mover(direccion);
            		//System.out.println("Vio y avanzo");
            		return true;
            	}
            		
            	
            }
		}
		return false;
	}

	private String intentoAtaque() {
		String mensaje = "";
		Posicion posicionActual;
		Direccion[] direcciones = Direccion.values();
		
		for (Direccion direccion : direcciones) {
			
			posicionActual = this.posicion.Copy();
			posicionActual.Mover(direccion);
			
			if(posicionActual.x < ValoresDefecto.altoTablero && posicionActual.y < ValoresDefecto.anchoTablero &&
				posicionActual.x >= 0 && posicionActual.y >= 0) {
				//es posible revisar
				Personaje personajeRevisado = Aplicacion.getInstance().mapa.tableroPersonajes[posicionActual.x][posicionActual.y];
				
				if(personajeRevisado != null && personajeRevisado.getClass().getName() == Jugador.class.getName()) {
					
					mensaje = this.tipo.toString()+" atacó al "+((Jugador)personajeRevisado).tipo.toString();
					mensaje += " por "+this.dano+" "+this.Atacar(personajeRevisado, this.dano);
					
					if(!personajeRevisado.vivo) {
						mensaje = this.tipo.toString()+" mató al "+((Jugador)personajeRevisado).tipo.toString()+"\n";
		        		Aplicacion.getInstance().mapa.tableroPersonajes
		        		[personajeRevisado.posicion.x][personajeRevisado.posicion.y] = null;
		        		Aplicacion.getInstance().jugadores.remove(personajeRevisado);
		        	}
				}
			}
			
		}
		return mensaje;
	}
	
}
