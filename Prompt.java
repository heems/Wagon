//  Karthik Bala
//  Propmt.java
//  August 22, 2012
//  Contains methods that facilitate prompting for numerical (int and double) values.

import java.util.Scanner;

public class Prompt{
	
	public static int getInt(String ask){
		boolean badinput = false;
		int value = 0;
		
		do{
			String input = getString(ask);
			badinput = false;
			try{
				value = Integer.parseInt(input);
			}
			catch(NumberFormatException e){
				badinput = true;
			}
		}
		while(badinput);
		
		return value;
	}
			
	public static int getInt(String ask, int min, int max){
		int value;
		System.out.println("\n");
		do{
			value = getInt(ask);
		}
		while(value < min || value > max);
		return value;
	}
	
	public static String getString (String ask){
		Scanner keyboard = new Scanner (System.in);
		System.out.print(ask);
		String input = keyboard.nextLine();
		return input;
	}

	public static char getChar (String ask){
		Scanner keyboard = new Scanner (System.in);
		System.out.print(ask);
		String input = keyboard.nextLine();
		char inputChar = 0;
		if (!input.isEmpty()){
			inputChar = input.toCharArray()[0];
		}
		return inputChar;
	}  
	
	public static double getDouble (String ask){
		boolean badinput = false;
		double value = 0.0;
		
		do{
			badinput = false;
			String input = getString(ask);
			try{
				value = Double.parseDouble(input);
			}
			catch(NumberFormatException e){
				badinput = true;
			}
		}
		while(badinput);
		
		return value;
	}
	public static double getDouble (String ask, double min, double max){
		double value;
		do{
			String stringmin = String.format("%6.2f", min);   //format the doubles to 2 decimal places
			String stringmax = String.format("%6.2f", max);

			value = getDouble(ask + "(" + stringmin + " - " + stringmax + ") -> ");
		}
		while(value < min || value > max);
		return value;
	}
}