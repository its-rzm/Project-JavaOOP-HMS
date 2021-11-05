
public class Consumable {
	private String consumableName;
	private int price;
	
	public Consumable(String consumName, int price) {
		this.consumableName = consumName;
		this.price = price;
	}

	public String getConsumableName() {
		return consumableName;
	}

	public int getPrice() {
		return price;
	}


}
