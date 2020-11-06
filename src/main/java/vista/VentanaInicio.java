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

public class VentanaInicio {

	public JButton[][] tablero;
	private JFrame frame;
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
		
		//JPANEL
		panel = new JPanel();
		panel.setBackground(Color.BLACK);
		panel.setBounds(10, 50, 600, 600 );
        panel.setLayout(new javax.swing.GroupLayout(panel));
		frame.getContentPane().add(panel);
		
		panel2 = new JPanel();
		panel2.setBackground(Color.BLUE);
		panel2.setBounds(625, 50, 325, 600);
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
		
		//ImageIcon
        this.cesped = new ImageIcon((new ImageIcon(getClass().getResource("/Imagenes/cesped.jpg")))
        		.getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT));
		
		
		this.tablero = new JButton[20][20];
		this.generarBotones();
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
