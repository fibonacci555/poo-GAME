package pt.iscte.poo.core;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import pt.iscte.poo.utils.Point2D;

public class WallView {
	private String name;
	
	private ArrayList<Point2D> cords;

	public WallView(String name) {
		this.name = name + ".txt";
	}
	
	public void wallCords() throws FileNotFoundException {
		File file = new File(this.name);
		Scanner scanner = new Scanner(file);
		int y = 0;
		ArrayList<Point2D> cords = new ArrayList<Point2D>();
		while(scanner.hasNextLine()) { 
				
				String line = scanner.nextLine();
				
				for(int x = 0; x != line.length();x++ ) {
					
					if(line.charAt(x) == '#') {
						Point2D n = new Point2D(x,y);
						cords.add(n);
					}
				}
				y++;
		}
		scanner.close();
		this.cords = cords;
	}
	
	public ArrayList<Point2D> getWallCords(){
		return cords;
	}
	
}
