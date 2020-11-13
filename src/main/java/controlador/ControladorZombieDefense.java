package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.Icon;
import javax.swing.JButton;

import modelo.Aplicacion;
import modelo.Bloque;
import modelo.Casilla;
import modelo.Direccion;
import modelo.Edificacion;
import modelo.Jugador;
import modelo.Personaje;
import vista.VentanaInicio;

public class ControladorZombieDefense implements ActionListener, MouseListener, KeyListener {

	//VARIABLES
	private VentanaInicio vInicio;
	private Aplicacion app;
	private Jugador jugSeleccionado;
	
	//CONSTRUCTORES
	public ControladorZombieDefense() {
		this.vInicio = new VentanaInicio(this);
		this.app = new Aplicacion();
		pintarMapa();
		
	}
	
	//METODOS
	public void actionPerformed(ActionEvent arg0) {
		
		switch (arg0.getActionCommand()) {
			
		}
		
	}
	
	private void pintarMapa() {
		
		Icon img = null;
		Personaje personaje;
		Bloque bloque;
		Edificacion edificacion;
		
		for (int i = 0; i < modelo.ValoresDefecto.altoTablero; i++) {
            
            for (int j = 0; j < modelo.ValoresDefecto.anchoTablero; j++) {
            	
            	String dato = app.mapa.tablero[i][j].elemento.getClass().getSimpleName();

            	switch (dato) {
            	
            	case "Jugador":
            		personaje = (Personaje) app.mapa.tablero[i][j].elemento; img = personaje.imagen;
            		break;
            		
            	case "Edificacion":
            		edificacion = (Edificacion) app.mapa.tablero[i][j].elemento;img = edificacion.imagen;
            		break;
            		
            	case "Bloque":
            		bloque = (Bloque) app.mapa.tablero[i][j].elemento;img = bloque.icon;
            		break;
            	}
                this.vInicio.tablero[i][j].setIcon(img);

            }
        }
	}
	

	@Override
	public void mouseClicked(MouseEvent arg0) {
		
		JButton botonTemp1 = (JButton)arg0.getComponent();
        String identificadorBoton = botonTemp1.getActionCommand();
        int x = Integer.parseInt(identificadorBoton.substring(0,identificadorBoton.indexOf(",")));
        int y = Integer.parseInt(identificadorBoton.substring(1+identificadorBoton.indexOf(",")));
        seleccionarJugador(this.app.mapa.tablero[x][y].elemento);
        mostrarInformacion();
		
	}
	
	private void seleccionarJugador(Object elemento) {
		try {
			this.jugSeleccionado = (Jugador) elemento;
		} catch (Exception e) {
			reestablecerValores();
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
			
			this.vInicio.lblPasos.setText("PASOS: "+this.jugSeleccionado.pasos);
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {
		
		if (this.jugSeleccionado != null) {
			evento(e);
		}
	}
	
	private void evento(KeyEvent e) {
		
		boolean validar = false;
		Direccion direccion = null;
		
		switch (e.getKeyCode()) {
		case KeyEvent.VK_UP:
			validar = validarCampoVacio(Direccion.ARRIBA,this.jugSeleccionado);
			direccion = Direccion.ARRIBA;
			break;
		case KeyEvent.VK_RIGHT:
			validar = validarCampoVacio(Direccion.DERECHA,this.jugSeleccionado);
			direccion = Direccion.DERECHA;
			break;
		case KeyEvent.VK_DOWN:
			validar = validarCampoVacio(Direccion.ABAJO,this.jugSeleccionado);
			direccion = Direccion.ABAJO;
			break;
		case KeyEvent.VK_LEFT:
			validar = validarCampoVacio(Direccion.IZQUIERDA,this.jugSeleccionado);
			direccion = Direccion.IZQUIERDA;
			break;
		default:
			break;
		}
		
		if (validar) {
			moverJugador(direccion);
		}
		
	}

	private void moverJugador(Direccion direccion) {
		
		//Genera la nueva posicion
		int x = this.jugSeleccionado.posicion.x + direccion.x;
		int y = this.jugSeleccionado.posicion.y + direccion.y;
		
		this.app.mapa.tablero[this.jugSeleccionado.posicion.x][this.jugSeleccionado.posicion.y].elemento = 
				this.app.mapa.tablero[x][y].elemento;
		
		//Actualiza la posicion 
		this.jugSeleccionado.posicion.x = x;
		this.jugSeleccionado.posicion.y = y;
		
		this.app.mapa.tablero[this.jugSeleccionado.posicion.x][this.jugSeleccionado.posicion.y].elemento = this.jugSeleccionado;
		
		pintarMapa();
		
		
	}

	private boolean validarCampoVacio(Direccion direccion, Personaje personaje) {
		/*
		 * Se encarga de validar que la nueva posicion del jugador no sea
		 */
		
		int x = personaje.posicion.x + direccion.x;
		int y = personaje.posicion.y + direccion.y;
		
		String dato = app.mapa.tablero[x][y].elemento.getClass().getSimpleName();
		
		System.out.println(dato);
		
		if (("Bloque").equals(dato)) {
			System.out.println("Llegoooooo");
			return true;
		}
		return false;
		
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
