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
import modelo.Jugador;
import modelo.Mapa;
import vista.VentanaInicio;

public class ControladorZombieDefense implements ActionListener, MouseListener, KeyListener {

	//VARIABLES
	private VentanaInicio vInicio;
	private Aplicacion app;
	private Jugador jugSeleccionado;
	//CONSTRUCTORES
	public ControladorZombieDefense() {
		this.vInicio = new VentanaInicio(this);
		this.app.mapa = new Mapa();
		pintarMapa();
	}
	
	//METODOS
	public void actionPerformed(ActionEvent arg0) {
		
		switch (arg0.getActionCommand()) {
			
		}
		
	}
	private void pintarMapa() {
		for (int i = 0; i < modelo.ValoresDefecto.altoTablero; i++) {
            
            for (int j = 0; j < modelo.ValoresDefecto.anchoTablero; j++) {
                Icon img = (Icon) app.mapa.tablero[i][j].elemento;
                this.vInicio.tablero[i][j].setIcon(img);

            }
        }
	}
	
	private void pintarJugadores() {
		for (Jugador jug : app.) {
			
		}
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		
		JButton botonTemp1 = (JButton)arg0.getComponent();
        String identificadorBoton = botonTemp1.getActionCommand();
        int x = Integer.parseInt(identificadorBoton.substring(0,identificadorBoton.indexOf(",")));
        int y = Integer.parseInt(identificadorBoton.substring(1+identificadorBoton.indexOf(",")));
        this.vInicio.tablero[x][y].setEnabled(false);
        System.out.println("X: "+x+"  /  Y:"+y);
        seleccionarJugador(this.app.mapa.tablero[x][y].elemento);
		
	}
	
	private void seleccionarJugador(Object elemento) {
		try {
			this.jugSeleccionado = (Jugador) elemento;
		} catch (Exception e) {
			// No es necesario manejar la excepcion
		}
		
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		if(this.jugSeleccionado != null) {
			moverJugador(e);
		}
		
	}
	
	private void moverJugador(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_UP:
			
			break;
		case KeyEvent.VK_RIGHT:
			
			break;
		case KeyEvent.VK_DOWN:
			
			break;
		case KeyEvent.VK_LEFT:
			
			break;
		default:
			break;
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
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

}
