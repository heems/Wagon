//  SHOOTING MINIGAME!
import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

public class Wagon{
	private int distanceTraveled;
	private int speedBoost;
	private int food;
	private int health;
	private int turnCount;
	private int choice;
	private int foodPerTurn;
	private int wagonSpeed;
	private boolean done;
	private ArrayList<Integer> highscores;
	private String toWrite;
	private boolean gun;
	private int foodmultiplier;
	private boolean boxer;
	private boolean kidney;
	private int numPeople;
	private int doctorRejections;
	private String name;
	private int player;
	private ArrayList<Item> store;
	private ArrayList<Item> inv;
	private Item oven;

	
	public Wagon(){
		done = false;
		speedBoost = 1;
		turnCount = 0;
		distanceTraveled = 0;
		food = 10;
		health = 10;
		foodPerTurn = 0;
		wagonSpeed = 5;
		highscores = new ArrayList<Integer>();
		toWrite = new String();
		foodmultiplier = 5;
		kidney = false;
		numPeople = 1;
		doctorRejections = 0;
		boxer = false;
		store = new ArrayList<Item>();
		inv = new ArrayList<Item>();
		HealthGain sandwich = new HealthGain(2, 2, "sandwich");
		HealthGain milk = new HealthGain(1, 1, "expired milk");
		SpeedBoost coke = new SpeedBoost(8, 2, "strange white powder");
		oven = new Item(2, false, "oven");
		store.add(sandwich);
		store.add(milk);
		store.add(oven);
		store.add(coke);
	}
	public static void main (String [] args){
		System.out.println("You are on a wagon.");
		System.out.println("\n\n");
		Wagon game = new Wagon();
		game.play();
	}

	public void play(){
		intro();
		Missions.espionage(player);
		while(!done){
			turnCount++;
			food -= numPeople;
			playTurn();
			randomEvent();
			checkDone();	
		}
	}
	
	public void intro(){
		System.out.println("Who are you?");
		System.out.println("(1) Jockey");
		System.out.println("(2) Chef");
		System.out.println("(3) Boxer");
		System.out.println("(4) Tank");
		player = Prompt.getInt("-> ",1,4);
		switch(player){
			case 1: wagonSpeed += 3; break;
			case 2: foodmultiplier += 2; break;
			case 3: boxer = true; break;
			case 4: health = 20; break;
			default:
		}
	}
		
	public void playTurn(){
		System.out.printf("You have traveled %2d miles, have %2d health, %3d food, and %2d horses%n", distanceTraveled, health, food, wagonSpeed);
		System.out.printf("Inventory: %s", inv);
		System.out.println();
		if(food < 1){
			lowFood();
		}
		System.out.println("(1) Go forward.");
		System.out.println("(2) Get food.");
		System.out.println("(3) Use item.");
		System.out.println("(4) Cook.");
		choice = Prompt.getInt("\n\nWhat do you want to do -> ");
		switch(choice){
			case 1: forward(); break;
			case 2: food(); break;
			case 3: use(); break;
			case 4: cook(); break;
			default: 
		}
	}
	public void lowFood(){
		System.out.println("You should really get some food soon");
		if(food < 0){
			System.out.println("You lose health for not eating.");
			health += food;
		}
		if(food < -3){
			System.out.println("One of your horses gets mad because you have no food and runs away with your wagon.  You spend three turns trying to catch it.");
			turnCount += 3;
		}
	}
	public void forward(){
		int subtotal = (int)(Math.random() * wagonSpeed * speedBoost) + 1;
		System.out.printf("%nYou travel %1d miles.%n", subtotal);
		distanceTraveled += subtotal;	
	}
	public void food(){
		int subtotal = (int)(Math.random() * 5) + 1;
		food += subtotal;
		System.out.println("\nYou get " + subtotal + " food\n");
		
	}
	public void use(){
		int choice = Prompt.getInt("Index of item to be used\n->");
		Item toUse = inv.get(choice);
		if(toUse instanceof HealthGain){
			health += ((HealthGain)toUse).use();
		}
		else if(toUse instanceof SpeedBoost){
			speedBoost += ((SpeedBoost)toUse).use();
		}
		inv.remove(choice);
		
	}
	public void randomEvent(){
		System.out.println("\n");
		int random = (int)(Math.random() * 12);
		switch(random){
			case 1:
				clumsy(); break;
			case 2:
				goats(); break;
			case 3:
				//kickedByHorse(); break;
			case 4:
				horseSalesmen(); break;
			case 5:
				doctor(); break;
			case 6:
				if(!gun){
					guns();
				} 
				break;		
			case 7:
				doctor(); break;
			case 8:
				if(!gun){
					others(); break;	
				}
			case 9:
				kidneys(); break;
			case 10:
				store(); break;
			case 11:
				if(turnCount > 50)
					Missions.espionage(player); 
				break;
			default:
				break;
		}
		newLine(); 
	}
	
	public void newLine(){
		System.out.println();
		System.out.print("\n----------------------------------------------\n");
		System.out.println();
	}
	
	public void checkDone(){
		if(distanceTraveled > 100){
			System.out.println("YOU WON IN " + turnCount + " TURNS!");
			highScore();
			done = true;
		}
		if(health < 0){
			System.out.println("YOU DIED IN " + turnCount + " TURNS!");
			done = true;
		}
		
	}
	
	public void clumsy(){
		System.out.println("You fall of your wagon losing 2 health.");
		health -= 2;
	}
	
	public void goats(){
		System.out.println("A herd of goat attack your wagon.");
		System.out.println("(1) Bribe them away with food.");
		System.out.println("(2) FIGHT THE GOATS.");
		choice = Prompt.getInt("\n\n What do you want to do -> ");
		switch(choice){
			case 1: 
				if(food > 4){
					System.out.println("You give the goats 5 food and they leave."); food -= 5; 
					break;
				}else{
					System.out.println("You don't have enough food.");
				}
			case 2: 
				if(boxer){
					System.out.println("Being a boxer, you punch one of the goats in the face and the rest run away.");
					break;
				}else if(gun){
					System.out.println("You're gun makes quick work of the goats.");
					health -= 3;
					break;
				}else{
					System.out.println("You lose 5 health fighting the goats."); 
					health -= 5; 
					break;					
				}
			default: 
		}
	}
	
	public void kickedByHorse(){
		System.out.println("One of your horses gets mad and kicks you.");
		health -= 1;
	}	
	
	public void horseSalesmen(){
		int choice = Prompt.getInt("You chance upon a traveling horse salesmen.  Would you like to by a horse for 5 food? \n (1) yes \n (2) no \n ->");
		if(choice == 1){
			if(food < 5){
				System.out.println("You can't afford a horse.  The salesmen punches you for wasting his time.");
				health--;
			}
			if(food > 5){
				System.out.println("You attach the horse to your wagon.");
				wagonSpeed++;
				food -= 5;
			}
		}
	}	
	
	public void doctor(){
		int choice = Prompt.getInt("You chance upon a traveling doctor.  Would you like to heal completely for 5 food? \n (1) yes \n (2) no \n ->");
		if(choice == 1){
			if(food < 5){
				System.out.println("You can't afford that.  The doctor punches you for wasting his time.");
				health--;
			}
			if(food > 5){
				System.out.println("You replenish your health.");
				health = 10;
				food -= 5;
				doctorRejections = 0;
			}
		}
		if(choice == 2){
			doctorRejections++;
			if(doctorRejections > 3){
				System.out.println("The doctor gets mad at you for always rejecting his services");
				System.out.println("He sends three waves of goat at you");
				goats();
				goats();
				goats();
				doctorRejections = 0;
			}
		}
	}	
	
	public void guns(){
		int choice = Prompt.getInt("You chance upon a traveling gun salesmen.  Would you like to buy a gun for 15 food? \n (1) yes \n (2) no \n ->");
		if(choice == 1){
			if(food < 15){
				System.out.println("You can't afford that.  The gun salesmen punches you for wasting his time.");
				health--;
			}
			if(food > 15){
				System.out.println("You buy a gun.");
				gun = true;
				food -= 15;
			}
		}
		if(choice == 2){
			
		}
	}	
	
	public void others(){
		int choice = Prompt.getInt("While on your wagon, someone waves you down and asks for a ride.  Do you let him on? \n (1) yes \n (2) no \n->");
		if(choice == 1){
			if(food > 15){
				System.out.println("The hungry hitchhiker drools at the site of all your food.  He then steals your kidney, takes some food, and runs away.");
				health -= 2;
				food -= 2;
				kidney = true;
			}
			if(food < 15){
				System.out.println("The hitchhiker gives you the rest of his horses and his gun.");
				wagonSpeed += 3;
				foodPerTurn++;
				gun = true;
				numPeople++;
			}
		}		
		
	}
	
	public void kidneys(){
		if(kidney){
			System.out.println("YOU SEE THE GUY WHO STOLE YOUR KIDNEY EARLIER!");
			System.out.println("He says, 'hey man I really bad about the whole kidney thing, here's some food.'");
			food += 5;
		}
	}
	
	public void store(){
		System.out.println("YOU FIND A WALMART!");
		System.out.println("This is what they have.");
		System.out.println("(0)     nothing");		
		for(int i = 0; i < store.size(); i++){
			System.out.printf("(%d)	%4s%n", i + 1, store.get(i));
		}
		int choice = Prompt.getInt("What do you want?\n->", -1, store.size());
		if(choice == 0)
			return;
		Item temp = store.get(choice - 1);
		inv.add(temp);
		food -= temp.cost;
		store.remove(choice - 1);
	}
	
	public void cook(){
		if(!inv.contains(oven)){
			System.out.println("You don't have anything to cook with.");
			return;
		}
		int choice1 = Prompt.getInt("Index of item 1 -> ", 1, inv.size());
		int choice2 = Prompt.getInt("Index of item 2 -> ", 2, inv.size());
		System.out.println("You end up burning yourself.");
		health -= 1;
	}

	
	public void highScore(){
		readToString();
		writeString();
		printScores();
	}
	public void readToString(){	//  Opens the file and gets the next line
		Scanner fromfile = OpenFile.open("highscores.txt");
		highscores.add(turnCount);
		while(fromfile.hasNext()){
			highscores.add(fromfile.nextInt());
		}
		Sorts.insertionSort(highscores);
		for(int i = 0; i < highscores.size(); i++){
			toWrite += highscores.get(i);
			toWrite += "\n";
		}
	}
	
	public void writeString(){	//  Writes toWrite to the file
		try{
        	File newfile = new File("highscores.txt");
        	PrintWriter writer = new PrintWriter(newfile);
        	writer.print(toWrite);
			writer.close();  
		}catch(IOException e){
            System.out.println("Could not write to the file, sorry.");
            System.exit(1);			
		}		
	}
	
	public void printScores(){
		boolean yourscore = true;
		System.out.println("\n\n       HIGH SCORES      \n\n");
		for(int i = 0; i < highscores.size(); i++){
			if(highscores.get(i) == turnCount && yourscore){
				System.out.printf("%n%3d                   %5d%n", i + 1, highscores.get(i));
				yourscore = false;	
			}else{
				System.out.printf("%n%3d                   %5d%n", i + 1, highscores.get(i));
			}			
		}
		System.out.println();
	}
}