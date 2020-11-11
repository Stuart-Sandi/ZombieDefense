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
        //this.vInicio.tablero[x][y].setEnabled(false);
        //System.out.println("X: "+x+"  /  Y:"+y);
        seleccionarJugador(this.app.mapa.tablero[x][y].elemento);
        mostrarInformacion();
		
	}
	
	private void seleccionarJugador(Object elemento) {
		try {
			this.jugSeleccionado = (Jugador) elemento;
		} catch (Exception e) {
			// No es necesario manejar la excepcion
		}
		
		
	}
	
	private void mostrarInformacion() {
		/*
		 * Muestra la informacion de cada personaje en la pantalla
		 */
		
		if (this.jugSeleccionado != null) {
			Icon icon = Helpers.getImagenResized(this.jugSeleccionado.tipo.toString(), ".png", 
					this.vInicio.lblPersonaje.getHeight(), this.vInicio.lblPersonaje.getWidth());
			this.vInicio.lblPersonaje.setIcon(icon);
		}
	}

//	@Override
//	public void keyTyped(KeyEvent e) {
//		System.out.println("Llegoooo");
//		if(this.jugSeleccionado != null) {
//			System.out.println("LLego aca");
//			moverJugador(e);
//		}
//		
//	}
	
	private void moverJugador(KeyEvent e) {
		
		switch (e.getKeyCode()) {
		case KeyEvent.VK_UP:
			System.out.println("arriba");
			break;
		case KeyEvent.VK_RIGHT:
			System.out.println("derecha");
			break;
		case KeyEvent.VK_DOWN:
			System.out.println("abajo");
			break;
		case KeyEvent.VK_LEFT:
			System.out.println("izquierda");
			break;
		default:
			break;
		}
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
	public void keyPressed(KeyEvent arg0) {
		 System.out.println("1");
		
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		System.out.println("2");
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		System.out.println("3");
		
	}


}
