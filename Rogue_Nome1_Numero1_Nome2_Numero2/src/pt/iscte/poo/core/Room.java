package pt.iscte.poo.core;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import pt.iscte.poo.map_construction.Door;
import pt.iscte.poo.utils.Point2D;

public class Room {
	private ArrayList<GameElement> elements;
	private ArrayList<Point2D> wallCords;
	private ArrayList<Point2D> everyPos;
	private ArrayList<Door> lockDoorPos;
	private int number;
	
	public Room(int number) {
		this.number = number;
	}
	
	public void generateAll() {
		EntityView entities = new EntityView("rooms/"+this.number);
		WallView walls = new WallView("rooms/"+this.number);
		try {
			entities.generateElements();
			walls.wallCords();
			
			wallCords = walls.getWallCords();
			elements = entities.getElements();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		
	}
	
	
	
}
