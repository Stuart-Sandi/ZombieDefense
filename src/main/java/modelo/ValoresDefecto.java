package modelo;

public interface ValoresDefecto {
	public static final int vida = 100,
			   pasos = 20, 
			   vidaMuro = 200,
			   vidaBase = 500,
			   distanciaVision = 5, 
			   cantidadAcciones = 3, 
			   nivel = 1, 
			   experiencia = 0,
			   anchoTablero = 20,
			   altoTablero = 20,
			   imagenTablero = 30,
			   posBase[] = {1,18},
			   listaPosNoSpawnX[] = {1,2,3},
			   listaPosNoSpawnY[] = {16,17,18},
			   listaPosMuroY[] = {19,18,17,16,15,15,15,15,17,18,19,19,19,19},
			   listaPosMuroX[] = {0,0,0,0,0,1,2,4,4,4,4,3,2,1};
}
