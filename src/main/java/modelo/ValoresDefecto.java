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
			   
			   listaPosMuroY[] = {0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,
					   			0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,
					   			0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
					   			19,19,19,19,19,19,19,19,19,19,19,19,19,19,19,19,19,19,
					   			15,15,15,17,18},
			   
			   listaPosMuroX[] = {19,19,19,19,19,19,19,19,19,19,19,19,19,19,19,19,19,19,19,19,
					   			0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
					   			1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,
					   			1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,
					   			1,2,4,4,4};
	
				//ORDEN: LINEA ABAJO, LINEA ARRIBA, LINEA iZQUIERDA, LINEA DERECHA, MUROS BASE
}
