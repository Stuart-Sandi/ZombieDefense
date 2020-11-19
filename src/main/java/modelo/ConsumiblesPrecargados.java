package modelo;

public enum ConsumiblesPrecargados {
	VENDAS(new Item("Vendas(+25 vida)", 25)),
	BOTIQUIN(new Item("Botiquin(+50 vida)", 50)),
	POCION(new Item("Pocion(+25 exp)", 25));
	
	Item item;
	ConsumiblesPrecargados(Item item) {
		this.item = item;
	}
}
