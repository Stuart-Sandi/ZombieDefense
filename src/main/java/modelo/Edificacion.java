package modelo;

import javax.swing.Icon;

public class Edificacion {

	public int vida;
	public Posicion posicion;
	public Icon imagen;
	
	public Edificacion(int pVida, Posicion pPosicion, Icon pIcon) {
		this.vida = pVida;
		this.posicion = pPosicion;
		this.imagen = pIcon;
	}
}
