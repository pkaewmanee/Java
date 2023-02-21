public class MTGCard {
	String name;
	String cost;
	String text;

	public MTGCard(String name, String cost, String text) {
		super();
		this.name = name;
		this.cost = cost;
		this.text = text;
	}

	public boolean equals(Object obj) {
		MTGCard o2 = (MTGCard) obj;
		
		if (this.name != o2.name) {
			return false;
		}
		
		else if (this.cost != o2.cost) {
			return false;
		}
		
		else if (this.text != o2.text) {
			return false;
		}
		
		else {
		
			return true;
		
		}

	}

}
