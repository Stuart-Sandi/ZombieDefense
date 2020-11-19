package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JOptionPane;

import modelo.Aplicacion;
import modelo.Arma;
import modelo.Bloque;
import modelo.Casilla;
import modelo.Direccion;
import modelo.Edificacion;
import modelo.Estado;
import modelo.Item;
import modelo.Jugador;
import modelo.Personaje;
import modelo.Posicion;
import modelo.Spawn;
import modelo.ValoresDefecto;
import modelo.Zombie;
import vista.VentanaInicio;

public class ControladorZombieDefense implements ActionListener, MouseListener, KeyListener {
	
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
					estado = Estado.ATACANDO;
				}
				break;
			case "MOVIMIENTO":
				if(jugSeleccionado != null) {
					estado = Estado.MOVIENDO;
				}
				break;
			case "USARITEM":
				if(jugSeleccionado != null) {
					String mensaje = this.jugSeleccionado.usarItem((Item)vInicio.comboBoxItem.getSelectedItem());
					this.vInicio.TAAccion.append(mensaje);
					this.jugSeleccionado.modificarAcciones(Estado.USANDOITEM);
					this.validarAccionesRealizadas(this.jugSeleccionado);
					actualizarPantalla();
				}
				break;
				
			case "SALTARTURNO":
				if (this.jugSeleccionado != null) {
					this.jugSeleccionado.saltarTurno();
					this.cambiarTurno();
					JOptionPane.showMessageDialog(null, "Ha saltado el turno de este personaje");
				}else {
					JOptionPane.showMessageDialog(null, "Debe seleccionar un jugador");
				}
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
		if (this.jugSeleccionado != null) {
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
		
	}
	
	@Override
	public void mouseClicked(MouseEvent arg0) {
		
		JButton botonTemp1 = (JButton)arg0.getComponent();
		modelo.Posicion posicionBoton = (Posicion) botonTemp1.getAction();
        int x = posicionBoton.x;
        int y = posicionBoton.y;
        Personaje personajeClickeado = this.app.mapa.tableroPersonajes[x][y];
        
        if(personajeClickeado != null) {
        	
        	//Seleccionar el jugador para el movimiento
        	if(personajeClickeado.getClass().getName() != Zombie.class.getName()) {
        		
        		seleccionarJugador(personajeClickeado);
				mostrarInformacion(personajeClickeado);
		    
			//Seleccionar el Zombie para ser atacado	
	        }else if(jugSeleccionado != null && estado == Estado.ATACANDO){

	        	String dano = jugSeleccionado.Atacar(personajeClickeado, ((Arma)this.vInicio.comboBoxArma.getSelectedItem()).valor );
	        	this.jugSeleccionado.modificarAcciones(Estado.ATACANDO);
	        	app.mapa.listaRuido.add(jugSeleccionado.posicion.Copy());
	        	this.validarAccionesRealizadas(this.jugSeleccionado);
	        	this.estado = Estado.MOVIENDO;
	        	
	        	String mensaje = "El "+this.jugSeleccionado.tipo.toString()+" atacó al "+
	        	((Zombie)personajeClickeado).tipo.toString()+" por "+dano;
	        	
	        	if(!personajeClickeado.vivo) {
	        		
	        		this.app.mapa.tableroPersonajes[x][y] = null;
	        		app.zombies.remove(personajeClickeado);
	        		mensaje = "El "+this.jugSeleccionado.tipo.toString()+" mató al "+
	        				((Zombie)personajeClickeado).tipo.toString()+" por "+dano+"\n";
	        		
	        		if (app.zombies.isEmpty()) {
	        			aumentarNivel();
	        		}
	        		
	        	}
	        	
	        	if (dano != "") {
	        		this.vInicio.TAAccion.append(mensaje);
	        	}else {
	        		this.vInicio.TAAccion.append("Objetivo fuera de alcance\n");
	        	}
	        
	        //Seleccionar el zombie para mostrar su informacion
	        }else {
	        	this.jugSeleccionado = null;
	        	mostrarInformacion(personajeClickeado);
	        }
        	actualizarPantalla();
        	return;
        }
        pintarPersonajes();
	}
	
	private void cambiarTurno() {
		
		boolean validar = false;
		
		ArrayList <Jugador> jugadores = this.app.jugadores;
		
		//Pregunta si todos los jugadores hicieron sus movimientos
		for (Jugador jugador : jugadores) {
			if (jugador.totalAcciones()) {
				validar = false;
			}else {
				validar = true;
				break;
			}
		}
		
		if (!validar) {
			this.jugSeleccionado = null;
			String mensaje = this.app.moverZombies();//ejecuta el turno de los zombies
			this.vInicio.TAAccion.append(mensaje);
			this.actualizarPantalla();
			this.reestablecerValoresJugadores();//Reestablece las acciones del personaje
			
			if (this.app.jugadores.isEmpty()) {
				JOptionPane.showMessageDialog(null, "¡¡Los zombies han matado los jugadores y destruido la base!! GAME OVER");
			}
		}
		
	}

	private void aumentarNivel() {
		
		app.nivel++;
		this.vInicio.lblNivel.setText("NIVEL: "+app.nivel);
		app.mapa.listaRuido = new ArrayList<>(); // Reinicia el nivel de ruido
		app.mapa.generarSpawnPoint();
		app.generarZombies();
		this.reestablecerValoresJugadores();
		
	}
	
	private void actualizarPantalla() {
		pintarMapa();
		pintarPersonajesConRangoVision();
		mostrarInformacion(this.jugSeleccionado);
	}

	private void seleccionarJugador(Personaje pJugador) {
		try {
			this.jugSeleccionado = (Jugador) pJugador;
			validarAccionesRealizadas(this.jugSeleccionado);
			if (this.jugSeleccionado.totalAcciones()) {
				JOptionPane.showMessageDialog(null, "Ya realizó todas las acciones del turno con este personaje");
				return;
			}
			actualizarPantalla();
			actionPerformed(new ActionEvent(vInicio, vInicio.hashCode(), "MOVIMIENTO"));
			estado = Estado.MOVIENDO;
			
		} catch (Exception e) {}
		
		
	}
	
	private void validarAccionesRealizadas(Jugador jugador) {
		
		if (jugador.totalAcciones()) {
			this.vInicio.btnSaltarTurnoPersonaje.setEnabled(false);
		}else {
			this.vInicio.btnSaltarTurnoPersonaje.setEnabled(true);
		}

		//Verifica si el jugador ya ataco una vez
		if(jugador.ataque) {
			this.vInicio.btnAtacar.setEnabled(false);
			this.vInicio.comboBoxArma.setEnabled(false);
		}else {
			this.vInicio.btnAtacar.setEnabled(true);
			this.vInicio.comboBoxArma.setEnabled(true);
		}
		
		//Verifica si el jugador ya usó un item una vez
		if(jugador.item) {
			this.vInicio.comboBoxItem.setEnabled(false);
			this.vInicio.btnUsar.setEnabled(false);
		}else {
			this.vInicio.comboBoxItem.setEnabled(true);
			this.vInicio.btnUsar.setEnabled(true);
		}
	}
	
	private void seleccionarArma() {
		if(jugSeleccionado != null) {
			jugSeleccionado.armaActual = (Arma) vInicio.comboBoxArma.getSelectedItem();
		}
	}
	
	private void reestablecerValoresJugadores() {
		/*
		 * Metodo encargado de reestablecer las acciones por turno de cada jugador
		 */
		
		ArrayList <Jugador> jugadores = this.app.jugadores;
		String mensaje = "";
		
		//Pregunta si todos los jugadores hicieron sus movimientos
		for (Jugador jugador : jugadores) {
			mensaje += jugador.reestablecerAcciones();
		}
		this.vInicio.TAAccion.append(mensaje);
	}

	private void mostrarInformacion(Personaje personaje) {
		/*
		 * Muestra la informacion de cada personaje en la pantalla
		 */
		
		if (personaje != null) {
			
			if ((personaje.getClass().getSimpleName().equals("Jugador"))){
				//Se setea la imagen del jugador
				Jugador j = (Jugador)personaje;
				Icon icon = Helpers.getImagenResized(j.tipo.toString(), ".png", 
						this.vInicio.lblPersonaje.getHeight(), this.vInicio.lblPersonaje.getWidth());
				this.vInicio.lblPersonaje.setIcon(icon);
				
				//Se setea la informacion en los labels
				this.vInicio.lblVida.setText("Vida: "+j.vida);
				this.vInicio.lblPasos.setText("Pasos: "+j.pasos);
				this.vInicio.lblVision.setText("Vision: "+j.distanciaVision);
				this.vInicio.lblNivelP.setText("Nivel: "+j.nivel);
				this.vInicio.lblExperencia.setText("Experencia: "+j.experiencia);
				
				String habilidades = "";
				for (int i = 0; i < j.habilidades.size(); i++) {
					
					if (j.habilidades.get(i).nivel <= this.app.nivel) {
						habilidades += "  "+(i+1)+"/"+j.habilidades.get(i).nombre+"\n\n";
					}else {
						habilidades += "  "+(i+1)+"/"+j.habilidades.get(i).nombre+" (Bloqueada nivel: "+
								j.habilidades.get(i).nivel+")"+"\n\n";
					}
				}
				this.vInicio.TAhabilidades.setText(habilidades);
				
				HashMap<String, Arma> armas = j.armas;
				this.vInicio.comboBoxArma.removeAllItems();
				for (Entry<String, Arma> entry : armas.entrySet()) {
					this.vInicio.comboBoxArma.addItem(entry.getValue());
		        }
				this.vInicio.comboBoxItem.removeAllItems();
				ArrayList<Item> inventario = j.inventario;
				this.vInicio.comboBoxItem.removeAll();
				for (Item item : inventario) {
					this.vInicio.comboBoxItem.addItem(item);
				}
			}else {
				//Se setea la imagen del jugador
				Zombie j = (Zombie)personaje;
				Icon icon = Helpers.getImagenResized(j.tipo.toString(), ".png", 
						this.vInicio.lblPersonaje.getHeight(), this.vInicio.lblPersonaje.getWidth());
				this.vInicio.lblPersonaje.setIcon(icon);
				
				//Se setea la informacion en los labels
				this.vInicio.lblVida.setText("Vida: "+j.vida);
				this.vInicio.lblPasos.setText("Pasos: ");
				this.vInicio.lblVision.setText("Vision: ");
				this.vInicio.lblNivelP.setText("Nivel: ");
				this.vInicio.lblExperencia.setText("Experencia: ");
				
				this.vInicio.comboBoxArma.removeAllItems();
				this.vInicio.comboBoxItem.removeAllItems();
				
				String habilidades = "";
				for (int i = 0; i < j.habilidades.size(); i++) {
					
					habilidades += "  "+(i+1)+"/"+j.habilidades.get(i).nombre+"\n\n";
				}
				this.vInicio.TAhabilidades.setText(habilidades);

			}
			
			
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {
		
		if (this.jugSeleccionado != null && this.estado == Estado.MOVIENDO && this.jugSeleccionado.pasos != 0) {
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
			this.jugSeleccionado.Mover(direccion);
			quitarPasos();
			actualizarPantalla();
		}
		
	}
	
	private void quitarPasos() {
		this.jugSeleccionado.pasos--;
		if (this.jugSeleccionado.pasos == 0) {
			this.jugSeleccionado.modificarAcciones(Estado.MOVIENDO);
			this.cambiarTurno();
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
	public void keyReleased(KeyEvent arg0) {
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		
	}


}
