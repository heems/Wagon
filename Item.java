public class Item{
	public int cost;
	public boolean consumable;
	public String name;
	public Item(int co, boolean c, String n){
		cost = co;
		consumable = c;
		name = n;
	}
	
	public String toString(){
		return String.format("%5s: $%2d", name, cost);
	}
}