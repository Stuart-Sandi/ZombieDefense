package modelo;

public class Zombie extends Personaje{
	public TipoZombie tipo;

	public Zombie(TipoZombie pTipo) {
		super();
		this.tipo = pTipo;	
		this.vida = pTipo.vida;
		this.cantidadAcciones = pTipo.cantidadAcciones;
		this.pasos = pTipo.pasos;
		this.cantidadAcciones = pTipo.cantidadAcciones;
		this.distanciaVision = pTipo.distanciaVision;
		this.experiencia = pTipo.experiencia;
		this.vivo = true;
	}

	public Zombie(TipoZombie pTipo, int x, int y) {
		super(x, y);
		this.tipo = pTipo;	
		this.vida = pTipo.vida;
		this.cantidadAcciones = pTipo.cantidadAcciones;
		this.pasos = pTipo.pasos;
		this.cantidadAcciones = pTipo.cantidadAcciones;
		this.distanciaVision = pTipo.distanciaVision;
		this.experiencia = pTipo.experiencia;
		this.vivo = true;
	}
	
}