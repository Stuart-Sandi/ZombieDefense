package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;

import vista.VentanaInicio;

public class ControladorZombieDefense implements ActionListener, MouseListener {

	//VARIABLES
	private VentanaInicio vInicio;
	
	//CONSTRUCTORES
	public ControladorZombieDefense() {
		this.vInicio = new VentanaInicio(this);
	}
	
	//METODOS
	public void actionPerformed(ActionEvent arg0) {
		
		switch (arg0.getActionCommand()) {
			
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
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
