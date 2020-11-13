package modelo;

public enum Direccion {

	ARRIBA(0,-1),
	ABAJO(0,1),
	DERECHA(1,0),
	IZQUIERDA(-1,0);
	
	public int x;
	public int y;
	private Direccion(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
}
