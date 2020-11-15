package vista;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import controlador.ControladorZombieDefense;
import modelo.Posicion;
import modelo.ValoresDefecto;

import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Image;
import javax.swing.JComboBox;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class VentanaInicio {

	public JButton[][] tablero;
	
	private JFrame frame;
	
	public JLabel 
			lblTitulo, 
			lblOpciones,
			lblPersonaje,
			lblP,
			lblH,
			lblF,
			lblHabilidad, 
			lblPasos;
	
	public JComboBox 
			comboBoxArma, 
			comboBoxItem;
	
	public JPanel 
			panel, 
			panel2;
	
	public JButton
			btnAtacar,
			btnUsar,
			btnActivarMovimiento,
			btnSaltarTurnoPersonaje;
			
	private ControladorZombieDefense controlador;

	/**
	 * Create the application.
	 */
	public VentanaInicio(ControladorZombieDefense pControlador) {
		this.controlador = pControlador;
		initialize();
	}

	

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		//JFRAME
		frame = new JFrame();
		//frame.setFocusable(false);
		frame.setBounds(100, 100, 980, 700);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.getContentPane().setLayout(null);
		frame.setLocationRelativeTo(null);
		frame.setTitle("Zombie Defense");
		
		//JPANEL
		panel = new JPanel();
		panel.setBackground(Color.BLACK);
		panel.setBounds(10, 50, 600, 600 );
        panel.setLayout(new javax.swing.GroupLayout(panel));
		frame.getContentPane().add(panel);
		panel.setFocusable(true);
		
		panel2 = new JPanel();
		panel2.setBackground(Color.LIGHT_GRAY);
		panel2.setBounds(625, 50, 325, 600);
		panel2.setLayout(null);
		panel2.setFocusable(true);
		frame.getContentPane().add(panel2);
		
		panel.addKeyListener(controlador);
		panel2.addKeyListener(controlador);
		
		
		//JLabel
		lblTitulo = new JLabel("TABLERO");
		lblTitulo.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 20));
		lblTitulo.setBounds(10, 11, 109, 38);
		frame.getContentPane().add(lblTitulo);
		
		lblOpciones = new JLabel("OPCIONES");
		lblOpciones.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 20));
		lblOpciones.setBounds(625, 11, 109, 38);
		frame.getContentPane().add(lblOpciones);
		
		lblP = new JLabel("PERSONAJE");
		lblP.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 15));
		lblP.setBounds(10, 10, 130, 20);
		panel2.add(lblP);
		
		lblH = new JLabel("HABILIDADES");
		lblH.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 15));
		lblH.setBounds(10, 225, 150, 20);
		panel2.add(lblH);
		
		lblF = new JLabel("FUNCIONES");
		lblF.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 15));
		lblF.setBounds(115, 355, 150, 20);
		panel2.add(lblF);
		
//		JLabel lblNewLabel_1 = new JLabel("ARMAS :");
//		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
//		lblNewLabel_1.setBounds(15, 439, 54, 14);
//		panel2.add(lblNewLabel_1);
//		
//		JLabel lblNewLabel_1_1 = new JLabel("MOVIMIENTO :");
//		lblNewLabel_1_1.setHorizontalAlignment(SwingConstants.CENTER);
//		lblNewLabel_1_1.setBounds(15, 389, 89, 14);
//		panel2.add(lblNewLabel_1_1);
//		
//		JLabel lblNewLabel_1_2 = new JLabel("ITEMS:");
//		lblNewLabel_1_2.setHorizontalAlignment(SwingConstants.CENTER);
//		lblNewLabel_1_2.setBounds(15, 497, 54, 14);
//		panel2.add(lblNewLabel_1_2);
		
		lblPasos = new JLabel("PASOS: 0");
		lblPasos.setHorizontalAlignment(SwingConstants.CENTER);
		lblPasos.setBounds(34, 414, 118, 14);
		panel2.add(lblPasos);
		
		lblPersonaje = new JLabel();
		lblPersonaje.setBounds(10, 40, 175, 175);
		panel2.add(lblPersonaje);
		
		lblHabilidad = new JLabel();
		lblHabilidad.setBounds(15, 250, 300, 100);
		panel2.add(lblHabilidad);
		
		
		//ComboBox
		comboBoxArma = new JComboBox();
		comboBoxArma.setFocusable(false);
		comboBoxArma.setBounds(34, 464, 182, 22);
		comboBoxArma.addActionListener(controlador);
		comboBoxArma.setActionCommand("COMBOBOXARMA");
		panel2.add(comboBoxArma);
		
		comboBoxItem = new JComboBox();
		comboBoxItem.setFocusable(false);
		comboBoxItem.setBounds(34, 522, 182, 22);
		panel2.add(comboBoxItem);
		
		
		//JButton
		btnAtacar = new JButton("ATACAR");
		btnAtacar.setBounds(226, 464, 89, 23);
		btnAtacar.setFocusable(false);
		btnAtacar.addActionListener(controlador);
		btnAtacar.setActionCommand("ATAQUE");
		panel2.add(btnAtacar);
		
		btnActivarMovimiento = new JButton("ACTIVAR MOVIMIENTO");
		btnActivarMovimiento.setBounds(138, 410, 177, 23);
		btnActivarMovimiento.setFocusable(false);
		btnActivarMovimiento.addActionListener(controlador);
		btnActivarMovimiento.setActionCommand("MOVIMIENTO");
		panel2.add(btnActivarMovimiento);
		
		btnUsar = new JButton("USAR");
		btnUsar.setBounds(226, 522, 89, 23);
		btnUsar.setFocusable(false);
		btnUsar.addActionListener(controlador);
		btnUsar.setActionCommand("USARITEM");
		panel2.add(btnUsar);
		
		btnSaltarTurnoPersonaje = new JButton("SALTAR TURNO PERSONAJE");
		btnSaltarTurnoPersonaje.setBounds(62, 566, 203, 23);
		btnSaltarTurnoPersonaje.setFocusable(false);
		btnSaltarTurnoPersonaje.addActionListener(controlador);
		btnSaltarTurnoPersonaje.setActionCommand("SALTARTURNO");
		panel2.add(btnSaltarTurnoPersonaje);
			
		this.tablero = new JButton[ValoresDefecto.altoTablero][ValoresDefecto.anchoTablero];
		this.generarBotones();
		frame.setVisible(true);
	}
	
	
	private void generarBotones(){
        
        for (int i = 0; i < this.tablero.length; i++) {
            
            for (int j = 0; j < this.tablero[0].length; j++) {
                
                this.tablero[i][j] = new JButton();
                this.tablero[i][j].setOpaque(true);
                this.tablero[i][j].setFocusable(false);
                this.panel.add(this.tablero[i][j]);
                this.tablero[i][j].setBounds(30*j, 30*i, 30, 30);
                this.tablero[i][j].addMouseListener(controlador);
                this.tablero[i][j].setAction(new Posicion(i,j));
                this.tablero[i][j].setActionCommand("CASILLA");
            }
        }
    }
}
