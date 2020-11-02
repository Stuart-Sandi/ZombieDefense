package Vista;

import java.awt.EventQueue;

import javax.swing.JFrame;

import Controlador.ControladorZombieDefense;

public class VentanaInicio {

	private JFrame frame;
	
	private ControladorZombieDefense controlador;

	/**
	 * Create the application.
	 */
	public VentanaInicio(ControladorZombieDefense pControlador) {
		this.controlador = pControlador;
		initialize();
		setActionCommands();
		setActionListener();
	}

	

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 600, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		frame.setVisible(true);
	}
	
	private void setActionListener() {
	/*
	 * Agrega los listener a los componentes del frame
	 */
		
	}

	private void setActionCommands() {
	/*
	 * Agrega el tipo de listener que esperará el controlador
	 */
		
	}
}
