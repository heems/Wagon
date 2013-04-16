public class Missions{	
	public static void espionage(int p){
		System.out.println("\n\n");
		System.out.println("You hit a sign that reads \" GOAT HEADQUARTERS \".");
		System.out.println("This is the perfect opportunity to get back at those pesky goats that always attack your wagon.");
		System.out.println("(1) GO");
		System.out.println("(2) NO");
		int choice = Prompt.getInt("-> ", 1, 2);
		if(choice == 2)
			return;
		System.out.println("You can either go through the (1) front door or (2) the second story window.");
		choice = Prompt.getInt("-> ", 1, 2);
		if(choice == 1 && p != 4){
			System.out.println("The door is locked.  You try to kick it open but stub your toe.");
			System.out.println("The goats hear your yell and run at you.");
			System.out.println("You run back to the wagon as fast as you can.");
			return;
		}		
		if(choice == 1 && p == 4){
			System.out.println("The door is locked.  You smash it open.");
		}
		if(choice == 2 && p != 4){
			System.out.println("You climb through the window.");
		}
		if(choice == 2 && p == 4){
			System.out.println("\n\nYou weigh like 1000 pounds so you collapse the second story of the goat building and faint.");
			faint();
		}
		if(choice == 1){
			System.out.println("The goats come running to their splintered door.");
			System.out.println("(1) RUN (2) ATTACK (3) CRY");
			choice = Prompt.getInt("-> ", 1, 3);
			switch(choice){
				case 1: System.out.println("\n\nYou try to run but then remember you weigh 1000 pounds and fall over.");
						System.out.println("You roll all the way back to your wagon.\n\n");
						return;
				case 2: System.out.println("You punch through the goats with ease and make your way to the kitchen.");
						break;
				case 3: System.out.println("You break down and begin to sob.  You just wanted to be in a wagon.  You didn't sign up for this.");
						System.out.println("Your cry is so loud the goats begin to tremble and then explode.");
						System.out.println("You pick yourself up and make your way to the kitchen.");
						break;
			}

		}
		if(choice == 2){
			System.out.println("Where to next?");
			System.out.println("(1) Kitchen (2) Bathroom");
			choice = Prompt.getInt("-> ", 1, 2);
			switch(choice){
				case 1: break;
				case 2: System.out.println("You step into the goat bathroom, slip on a puddle, and faint.");
						faint();
			}

		}
		System.out.println("");
	}	
	public static void faint(){
		System.out.println("...");
		System.out.println("...");
		System.out.println("...");
		System.out.println("You wake up in your wagon.\n\n");
		return;
	}
}