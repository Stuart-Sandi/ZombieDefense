package modelo;

import javax.swing.Icon;

public class Edificacion {

	public int vida;
	public Posicion posicion;
	public Icon imagen;
	
	public Edificacion(int pVida, int x, int y, Icon pIcon) {
		this.vida = pVida;
		this.posicion = new Posicion(x,y);
		this.imagen = pIcon;
	}
}
