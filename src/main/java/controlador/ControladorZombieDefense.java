package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.HashMap;
import java.util.Map.Entry;

import javax.swing.Icon;
import javax.swing.JButton;

import modelo.Aplicacion;
import modelo.Arma;
import modelo.Bloque;
import modelo.Casilla;
import modelo.Direccion;
import modelo.Edificacion;
import modelo.Jugador;
import modelo.Personaje;
import modelo.Posicion;
import modelo.Spawn;
import modelo.ValoresDefecto;
import modelo.Zombie;
import vista.VentanaInicio;

public class ControladorZombieDefense implements ActionListener, MouseListener, KeyListener {
	private enum Estado{
		ATACANDO,
		MOVIENDO;
	}
	//VARIABLES
	private VentanaInicio vInicio;
	private Aplicacion app;
	private Jugador jugSeleccionado;
	private Estado estado;
	//CONSTRUCTORES
	public ControladorZombieDefense() {
		this.vInicio = new VentanaInicio(this);
		this.app = Aplicacion.getInstance();
		pintarMapa();
		pintarPersonajes();
	}
	
	//METODOS
	public void actionPerformed(ActionEvent arg0) {
		
		switch (arg0.getActionCommand()) {
			case "COMBOBOXARMA":
				seleccionarArma();
				break;
			case "ATAQUE":
				if(jugSeleccionado != null) {
					vInicio.btnAtacar.setEnabled(false);
					vInicio.btnActivarMovimiento.setEnabled(true);
					estado = Estado.ATACANDO;
				}
				break;
			case "MOVIMIENTO":
				if(jugSeleccionado != null) {
					vInicio.btnAtacar.setEnabled(true);
					vInicio.btnActivarMovimiento.setEnabled(false);	
					estado = Estado.MOVIENDO;
				}
				break;
			case "USARITEM":
				break;
				
			case "SALTARTURNO":
				
				break;
		}
		
	}
	
	private void pintarMapa() {
		
		Icon img = null;
		Spawn spawn;
		Bloque bloque;
		Edificacion edificacion;
		
		for (int i = 0; i < modelo.ValoresDefecto.altoTablero; i++) {
            
            for (int j = 0; j < modelo.ValoresDefecto.anchoTablero; j++) {
            	
            	String dato = app.mapa.tablero[i][j].elemento.getClass().getSimpleName();

            	switch (dato) {

            	case "Edificacion":
            		edificacion = (Edificacion) app.mapa.tablero[i][j].elemento;img = edificacion.imagen;
            		break;
            		
            	case "Bloque":
            		bloque = (Bloque) app.mapa.tablero[i][j].elemento;img = bloque.icon;
            		break;
            	
            	case "Spawn":
            		spawn = (Spawn) app.mapa.tablero[i][j].elemento;img = spawn.imagen;
            		break;
            	}
                this.vInicio.tablero[i][j].setIcon(img);

            }
        }
	}
	
	public void pintarPersonajes() {
		
		for (int i = 0; i < modelo.ValoresDefecto.altoTablero; i++) {
            
            for (int j = 0; j < modelo.ValoresDefecto.anchoTablero; j++) {
            	if(app.mapa.tableroPersonajes[i][j] != null) {
            		Personaje personaje = app.mapa.tableroPersonajes[i][j]; 
            		Icon img = personaje.imagen;
            		this.vInicio.tablero[i][j].setIcon(img);
            	}
            		
            	
            }
		}
	}
	
	public void pintarPersonajesConRangoVision() {
		/*Este metodo pinta todo lo que este menor o igual al rango de vision dado en personaje
		 * */
		Personaje[][] tablero = app.mapa.tableroPersonajes;
		int rangoVision = jugSeleccionado.distanciaVision;
		for (int i = 0; i < modelo.ValoresDefecto.altoTablero; i++) {
            
            for (int j = 0; j < modelo.ValoresDefecto.anchoTablero; j++) {
            	if(tablero[i][j] != null && jugSeleccionado.posicion.distancia(new Posicion(i, j))  <= rangoVision ) {
            		Personaje personaje = tablero[i][j]; 
            		Icon img = personaje.imagen;
            		this.vInicio.tablero[i][j].setIcon(img);
            	}
            		
            	
            }
		}
	}
	
	@Override
	public void mouseClicked(MouseEvent arg0) {
		
		JButton botonTemp1 = (JButton)arg0.getComponent();
		modelo.Posicion posicionBoton = (Posicion) botonTemp1.getAction();
        int x = posicionBoton.x;
        int y = posicionBoton.y;
        Personaje personajeClickeado = this.app.mapa.tableroPersonajes[x][y];
        
        if(personajeClickeado != null) {
        	
        	if(personajeClickeado.getClass().getName() != Zombie.class.getName()) {
        		seleccionarJugador(personajeClickeado);
				mostrarInformacion();
		        	
	        }else if(jugSeleccionado != null && estado == Estado.ATACANDO){
	        	jugSeleccionado.Atacar(personajeClickeado);
	        }
        }
	        

        
		
	}
	
	private void seleccionarJugador(Personaje pJugador) {
		try {
			this.jugSeleccionado = (Jugador) pJugador;
			pintarMapa();
			pintarPersonajesConRangoVision();
			actionPerformed(new ActionEvent(vInicio, vInicio.hashCode(), "MOVIMIENTO"));
			estado = Estado.MOVIENDO;
		} catch (Exception e) {
			reestablecerValores();
		}
		
		
	}
	
	private void seleccionarArma() {
		if(jugSeleccionado != null) {
			jugSeleccionado.armaActual = (Arma) vInicio.comboBoxArma.getSelectedItem();
		}
	}
	
	private void reestablecerValores() {
		this.jugSeleccionado = null; 
		this.vInicio.lblPersonaje.setIcon(null);
		this.vInicio.lblPasos.setText("PASOS: 0");
		
	}

	private void mostrarInformacion() {
		/*
		 * Muestra la informacion de cada personaje en la pantalla
		 */
		
		if (this.jugSeleccionado != null) {
			Icon icon = Helpers.getImagenResized(this.jugSeleccionado.tipo.toString(), ".png", 
					this.vInicio.lblPersonaje.getHeight(), this.vInicio.lblPersonaje.getWidth());
			this.vInicio.lblPersonaje.setIcon(icon);
			HashMap<String, Arma> armas = jugSeleccionado.armas;
			this.vInicio.comboBoxArma.removeAllItems();
			for (Entry<String, Arma> entry : armas.entrySet()) {
				this.vInicio.comboBoxArma.addItem(entry.getValue());
	        }
			
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {
		
		if (this.jugSeleccionado != null && this.estado == Estado.MOVIENDO) {
			evento(e);
		}
	}
	
	private void evento(KeyEvent e) {
		
		Direccion direccion = null;
		
		switch (e.getKeyCode()) {
		case KeyEvent.VK_UP:
			direccion = Direccion.ARRIBA;
			break;
		case KeyEvent.VK_RIGHT:
			direccion = Direccion.DERECHA;
			break;
		case KeyEvent.VK_DOWN:
			direccion = Direccion.ABAJO;
			break;
		case KeyEvent.VK_LEFT:
			direccion = Direccion.IZQUIERDA;
			break;
		default:
			break;
		}
		if (direccion != null && app.mapa.validarCampoVacio(direccion, jugSeleccionado)) {
			moverJugador(direccion);
		}
		
	}

	private void moverJugador(Direccion direccion) {
		
		//Genera la nueva posicion
		this.app.mapa.tableroPersonajes[this.jugSeleccionado.posicion.x][this.jugSeleccionado.posicion.y] = null;
		this.jugSeleccionado.Mover(direccion);
		this.app.mapa.tableroPersonajes[this.jugSeleccionado.posicion.x][this.jugSeleccionado.posicion.y] = this.jugSeleccionado;
		
		pintarMapa();
		pintarPersonajesConRangoVision();
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		
	}


}
