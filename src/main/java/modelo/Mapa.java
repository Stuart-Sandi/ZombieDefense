package modelo;
import javax.swing.Icon;

import controlador.Helpers;

public class Mapa {
	
	public Casilla[][] tablero;
	public Personaje[][] tableroPersonajes;
	public Icon imgBloque, imgBase;
	private Posicion posicionBase;
	
	public Mapa() {
		super();
		this.posicionBase = new Posicion(ValoresDefecto.posBase[0],ValoresDefecto.posBase[1]);
		this.imgBloque = Helpers.getImagenResized("cesped", ".jpg", ValoresDefecto.imagenTablero, ValoresDefecto.imagenTablero);
		this.imgBase = Helpers.getImagenResized("Base", ".png", ValoresDefecto.imagenTablero, ValoresDefecto.imagenTablero);
		this.tablero = new Casilla[ValoresDefecto.altoTablero][ValoresDefecto.anchoTablero];
		this.tableroPersonajes = new Personaje[ValoresDefecto.altoTablero][ValoresDefecto.anchoTablero];
		this.generarTablero();
		this.generarObstaculos();
		this.generarBase();
	}
	
	public Posicion encontrarCasillaVacia() {
		
		return null;
	}
	
	public void generarTablero() {
		
		for (int i = 0; i < modelo.ValoresDefecto.anchoTablero; i++) {
            
            for (int j = 0; j < modelo.ValoresDefecto.altoTablero; j++) {
                Bloque bloque = new Bloque(imgBloque);
                Casilla<Bloque> casilla = new Casilla<Bloque>(bloque);
                tablero[i][j] = casilla;
                
            }
        }
	}
	
	public void generarObstaculos() {
		
		Icon imgMuro = Helpers.getImagenResized("muro", ".jpg", ValoresDefecto.imagenTablero, ValoresDefecto.imagenTablero);
		
		for (int i = 0; i < ValoresDefecto.listaPosMuroX.length; i++) {
			
			int x = ValoresDefecto.listaPosMuroX[i];
			int y = ValoresDefecto.listaPosMuroY[i];
			
			Posicion posicion = new Posicion(x,y);
			Edificacion muro = new Edificacion(ValoresDefecto.vidaMuro,posicion,imgMuro);
			Casilla<Edificacion> casilla = new Casilla<Edificacion>(muro);
			tablero[x][y] = casilla;
			
		}
		
		
	}
	
	public void generarBase() {
		
		Edificacion base = new Edificacion(ValoresDefecto.vidaBase,this.posicionBase,imgBase);
		Casilla<Edificacion> casilla = new Casilla<Edificacion>(base);
		tablero[this.posicionBase.x][this.posicionBase.y] = casilla;
	}
	

}
