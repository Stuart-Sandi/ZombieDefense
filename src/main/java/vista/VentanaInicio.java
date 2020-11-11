package vista;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import controlador.ControladorZombieDefense;

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
	public JLabel lblPersonaje, lblHabilidad, lblPasos;
	public JComboBox comboBoxArma, comboBoxItem;
	public JPanel panel, panel2;
	public ImageIcon cesped;
	
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
		
		//JFRAME
		frame = new JFrame();
		frame.setBounds(100, 100, 980, 700);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.getContentPane().setLayout(null);
		frame.setLocationRelativeTo(null);
		frame.setTitle("Zombie Defense");
		
		//ImageIcon
        this.cesped = new ImageIcon((new ImageIcon(getClass().getResource("/Imagenes/muro1.jpg")))
        		.getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT));
		
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
		panel.addKeyListener(controlador);
		panel2.addKeyListener(controlador);
		frame.getContentPane().add(panel2);
		
		
		//JLabel
		JLabel lblNewLabel = new JLabel("TABLERO");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 20));
		lblNewLabel.setBounds(10, 11, 109, 38);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblOpciones = new JLabel("OPCIONES");
		lblOpciones.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 20));
		lblOpciones.setBounds(625, 11, 109, 38);
		frame.getContentPane().add(lblOpciones);
		
		JLabel lblP = new JLabel("PERSONAJE");
		lblP.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 15));
		lblP.setBounds(115, 10, 130, 20);
		panel2.add(lblP);
		
		JLabel lblH = new JLabel("HABILIDADES");
		lblH.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 15));
		lblH.setBounds(110, 225, 150, 20);
		panel2.add(lblH);
		
		JLabel lblF = new JLabel("FUNCIONES");
		lblF.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 15));
		lblF.setBounds(115, 355, 150, 20);
		panel2.add(lblF);
		
		JLabel lblNewLabel_1 = new JLabel("ARMAS :");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(15, 439, 54, 14);
		panel2.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("MOVIMIENTO :");
		lblNewLabel_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_1.setBounds(15, 389, 89, 14);
		panel2.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_2 = new JLabel("ITEMS:");
		lblNewLabel_1_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_2.setBounds(15, 497, 54, 14);
		panel2.add(lblNewLabel_1_2);
		
		lblPasos = new JLabel("PASOS: 0");
		lblPasos.setHorizontalAlignment(SwingConstants.CENTER);
		lblPasos.setBounds(34, 414, 118, 14);
		panel2.add(lblPasos);
		
		lblPersonaje = new JLabel();
		lblPersonaje.setBounds(75, 40, 175, 175);
		//lblPersonaje.setIcon(cesped);
		panel2.add(lblPersonaje);
		
		lblHabilidad = new JLabel();
		lblHabilidad.setBounds(15, 250, 300, 100);
		lblHabilidad.setIcon(cesped);
		panel2.add(lblHabilidad);
		
		
		//ComboBox
		comboBoxArma = new JComboBox();
		comboBoxArma.setBounds(34, 464, 182, 22);
		panel2.add(comboBoxArma);
		
		comboBoxItem = new JComboBox();
		comboBoxItem.setBounds(34, 522, 182, 22);
		panel2.add(comboBoxItem);
		
		
		//JButton
		JButton btnNewButton = new JButton("ATACAR");
		btnNewButton.setBounds(226, 464, 89, 23);
		panel2.add(btnNewButton);
		
		JButton btnActivarMovimiento = new JButton("ACTIVAR MOVIMIENTO");
		btnActivarMovimiento.setBounds(138, 410, 177, 23);
		panel2.add(btnActivarMovimiento);
		
		JButton btnUsar = new JButton("USAR");
		btnUsar.setBounds(226, 522, 89, 23);
		panel2.add(btnUsar);
		
		JButton btnSaltarTurnoPersonaje = new JButton("SALTAR TURNO PERSONAJE");
		btnSaltarTurnoPersonaje.setBounds(62, 566, 203, 23);
		panel2.add(btnSaltarTurnoPersonaje);
			
		this.tablero = new JButton[20][20];
		this.generarBotones();
		this.tablero[0][0].setIcon(cesped);
		frame.setVisible(true);
	}
	
	
	private void generarBotones(){
        
        for (int i = 0; i < modelo.ValoresDefecto.altoTablero; i++) {
            
            for (int j = 0; j < modelo.ValoresDefecto.anchoTablero; j++) {
                
                this.tablero[i][j] = new JButton();
                this.tablero[i][j].setOpaque(true);
                this.panel.add(this.tablero[i][j]);
                this.tablero[i][j].setBounds(30*i, 30*j, 30, 30);
                this.tablero[i][j].setActionCommand(i+","+j);//i+","+j
                this.tablero[i][j].addMouseListener(controlador);
                
            }
        }
    }
	
	private void generarBase() {
		
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
