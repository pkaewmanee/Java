public class TestMTG {
	public static void main(String[] args) {
		MTGCard a = new MTGCard("Lava Axe", "4R", "Lava Axe deals 5 damage to target player");
		MTGCard b = new MTGCard("Lava Axe", "4R", "Lava Axe deals 5 damage to target player");
		MTGCard c = new MTGCard("Lava Axel", "4R", "Lava Axe deals 5 damage to target player");
		MTGCard d = new MTGCard("Lava Axe", "4r", "Lava Axe deals 5 damage to target player");
		MTGCard e = new MTGCard("Lava Axe", "4R", "Lava Axe deals 4 damage to target player");
		MTGCard f = new MTGCard("Lava Axe", "4R", "Lava Axe deals 3 damage to target player");
		MTGCard g = new MTGCard("Lava Axe", "4R", "Lava Axe deals 5 damage to target player");

		System.out.println("a and a are equal?: " + (a==a));
		System.out.println("a and b are equal?: " + (a==b));
		System.out.println("a and c are equal?: " + (a==c));
		System.out.println("a and d are equal?: " + (a==d));
		System.out.println("a and e are equal?: " + (a==e));
		System.out.println("c and f are equal?: " + (c==f));
		System.out.println("b and g are equal?: " + (b==g));
		
		System.out.println("---------------Now, assign f = a:");
		f=a;
		System.out.println("b and f are equal after assignment?: " + (b==f));	
	}

}
