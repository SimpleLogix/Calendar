package edu.wit.comp1050;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javafx.fxml.FXMLLoader;

public class Temp extends CalendarController {




	
	public static void main(String[] args) {
		
		
		int x=1;
		
		ArrayList<Integer> al = new ArrayList<>();
		al.add(x);
		
		for(int i=0;i<1;i++) {
			int y = al.get(i);
			x = y;
		}
		
		System.out.print(x);

	}

		

}
