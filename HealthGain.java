public class HealthGain extends Item{
	private int healthGain;
	public HealthGain(int co, int hg, String n){
		super(co, true, n);
		healthGain = hg;
	}
	
	public int use(){
		return healthGain;
	}


}