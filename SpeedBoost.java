public class SpeedBoost extends Item{
	private int boost;
	public SpeedBoost(int co, int b, String n){
		super(co, true, n);
		boost = b;
	}
	
	public int use(){
		return boost;
	}


}