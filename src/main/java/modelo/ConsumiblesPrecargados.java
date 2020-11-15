package modelo;

public enum ConsumiblesPrecargados {
	VENDAS(new Item("Vendas", 50)),
	BOTIQUIN(new Item("Botiquin", 100));
	
	Item item;
	ConsumiblesPrecargados(Item item) {
		this.item = item;
	}
}
