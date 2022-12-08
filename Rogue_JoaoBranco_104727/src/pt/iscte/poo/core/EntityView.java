package pt.iscte.poo.core;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import pt.iscte.poo.items.Key;
import pt.iscte.poo.map_construction.Door;
import pt.iscte.poo.utils.Point2D;

public class EntityView {
	String name;
	
	private ArrayList<GameElement> elementos;
	
	
	
	

	public EntityView(String name) {
		this.name = name + ".txt";
	}
	
	public void generateElements() throws FileNotFoundException {
		File file = new File(this.name);
		Scanner scanner = new Scanner(file);
		ArrayList<GameElement> elementos = new ArrayList<GameElement>();
		while(scanner.hasNextLine()) { 
				
				String[] line = scanner.nextLine().split(",");
				
				if (line.length == 3 ) {
					//System.out.println(line[0] + " x: " +line[1] + " y: " + line[2] );
					elementos.add(GameElement.create(line[0], new Point2D(Integer.parseInt(line[1]),Integer.parseInt(line[2]))));
				}
				
				else if (line.length >= 3) {
					
					if(line[0].contains("Door")) { 	
						if (line.length == 7) {
						elementos.add((GameElement) new Door(
								line[0]+"Closed", 
								(new Point2D(Integer.parseInt(line[1]),Integer.parseInt(line[2])))
								,line[3],
								(new Point2D(Integer.parseInt(line[4]),Integer.parseInt(line[5]))), line[6]));}
						else {
							elementos.add((GameElement) new Door(
									line[0]+"Open", 
									(new Point2D(Integer.parseInt(line[1]),Integer.parseInt(line[2])))
									,line[3],
									(new Point2D(Integer.parseInt(line[4]),Integer.parseInt(line[5]))), "0"));
						}
					}
					else if (line[0].contains("Key")) {
						//System.out.println(line[0] + " x: " +line[1] + " y: " + line[2] );
						elementos.add((GameElement) new Key(
								line[0], 
								(new Point2D(Integer.parseInt(line[1]),Integer.parseInt(line[2])))
								,line[3]));
					}
					
					
				}
				this.elementos = elementos;
		}
		scanner.close();
		
	}
	
	public ArrayList<GameElement> getElements() throws FileNotFoundException{
		generateElements();
		return elementos;
	}
	
	
	
}

