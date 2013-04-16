//  Karthik Bala
//  August 31, 2012
//  OpenFile.java
//  Opens a file for reading.

import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.File;

public class OpenFile{
	public static Scanner open(String filestring){
		Scanner fromfile = null;
		File filename = new File(filestring);
		try{
			fromfile = new Scanner(filename);
		}
		catch(FileNotFoundException e){
			System.out.println("\nSorry, can't open the file.\n");
			System.exit(1);
		}
		return fromfile;
	}
}