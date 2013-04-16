//  Karthik Bala
//  Sorts.java
//  December 7 2012
//  Contains methods for every sort and calculates the number of steps needed
//  to carry out the sort.

import java.util.ArrayList;

public class Sorts
{
	public static int bubbleSort (ArrayList <Integer> array)    //  Sorts the array using a bubble sort and calculates the steps needed to do so
	{
		int steps = 0;
		steps++;  //  Starting at outer = 0
		for (int outer = 0; outer < array.size() - 1; outer++)
		{
			steps += 2;	//  when outer < array.size() is true and outer++
			for (int inner = 0; inner < array.size() - outer - 1; inner++)
			{
				steps += 3;	//  when inner is true, inner++, boolean below in if 
				if (array.get(inner).intValue() > array.get(inner + 1).intValue())
				{
					Integer temp = array.get(inner);
					array.set(inner, array.get(inner + 1));
					array.set((inner + 1), temp);
                    steps += 3; //  for things in if statement
				}
			}
			steps += 2;	//  int inner = 0 and checking inner boolean for last time
		}
        steps++; // check outer boolean for last time
		return steps;

	}

	public static int selectionSort (ArrayList <Integer> array)  //  Sorts the array using a selection sort and calculates the steps needed to do so
	{
		int min, temp;
		int steps = 0;
		steps++;	//  outer starts at 0
		for (int outer = 0; outer < array.size() - 1; outer++)
		{
			steps++;	//  min = outer
			min = outer;
			steps += 2;	//  checking outer < array.size() and outer++
			for (int inner = outer + 1; inner < array.size(); inner++)
			{
				steps += 3;	//  checking inner < array.size(), checking if statement boolean, inner++
				if (array.get(inner) < array.get(min))
				{
					steps++;    //  things in if statement
					min = inner; // a new smallest item is found
				}
			}
			
			//swap array[outer] & array[min]
			temp = array.get(outer);
			array.set(outer, array.get(min));
			array.set(min, temp);
			steps += 3;	//  temp = array.get(outer), array.sets
			steps++;	//  int inner = outer + 1		
		}
		steps++;  //  checking array.size() - 1 for the last time
		return steps;
	}

	public static int insertionSort (ArrayList <Integer> array)	{  //  Sorts the array using an insertion sort and calculates the steps needed to do so  
		int steps = 0;
		steps++; //  outer = 1
		for (int outer = 1; outer < array.size(); outer++)
		{
			steps += 4;	// position = outer, key = array.get(position), outer++, boolean in for
			int position = outer;
			int key = array.get(position);

			// Shift larger values to the right
			
			while (position > 0 && array.get(position - 1) > key)
			{
				array.set(position, array.get(position - 1));
				position--;
				steps += 4;	//  statements in while loop, while booleans	
			}
			array.set(position, key);
			steps += 3;  //  array.set and checking while loop for last time
		}
		steps++;	//  for boolean for the last time
		
		return steps;
	}
	
	public static int mergeSort (ArrayList<Integer> a, int from, int to)    //  Splits array into chunks and calls merge to sort them, calculates number of steps to merge sort array
	{
		int steps = 0;
		int middle = (from + to) / 2;
		steps += 2;	//  if boolean and int middle
		if (to - from < 2)
		{
			steps++;	//  if boolean			
			if (to > from && a.get(to) < a.get(from))
			{
				int atemp = a.get(to);
				a.set(to, a.get(from));
				a.set(from, atemp);
				steps += 3; //  above statements
			}
		}
		else{	
			steps += mergeSort(a, from, middle);
			steps += mergeSort(a, middle + 1, to);
			steps += merge(a, from, middle, to);
		}
		
		return steps;
	}

	public static int merge (ArrayList<Integer> a, int from, int middle, int to)    //  Does the actual sorting for the merge sort
	{
		int steps = 0;
		int i = from, j = middle + 1, k = from;
		int [] temp = new int [a.size()];
		steps += 4; //  intializing variables
		while (i <= middle && j <= to)
		{
			steps += 2;	//  while boolean, if boolean
			if (a.get(i) < a.get(j)){
				temp[k] = a.get(i);
				i++;
				steps += 2;	//  if statements
			}
			else{
				temp[k] = a.get(j);
				j++;
				steps += 2;	//  else statements
			}
			k++;
			steps ++;	//  k++
		}
		steps++;	//  last while boolean check
		
		while (i <= middle){
			temp[k] = a.get(i);
			i++;
			k++;
			steps += 4;	//  while statements, while boolean
		}
		steps++;	//  while boolean last check
		while (j <= to){
			temp[k] = a.get(j);
			j++;
			k++;
			steps += 4;	//  while statements, while boolean
		}
		steps++;	//  while boolean last check
		for (k = from; k <= to; k++){
			a.set(k, temp[k]);	
			steps += 2;	//  for loop boolean, k++, and above a.set(k, temp[k]) step
		}
		steps += 2;	//  k = from in for loop and loop last check
		return steps;
	}
}