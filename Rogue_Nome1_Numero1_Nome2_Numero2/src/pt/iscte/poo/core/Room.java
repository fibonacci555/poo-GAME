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
	private String number;
	private int ativo; 
	
	public Room(String number) {
		this.number = number;
		ArrayList<GameElement> elements = new ArrayList<GameElement>();
		ArrayList<Point2D> wallCords = new ArrayList<Point2D>();
		ArrayList<Point2D> everyPos = new ArrayList<Point2D>();
		ArrayList<Door> lockDoorPos = new ArrayList<Door>();
		ativo = 0;
		
		
	}
	
	public String getNumber() {
		return number;
	}
	
	public void setAll(ArrayList<GameElement> elements,ArrayList<Point2D> wallCords,ArrayList<Point2D> everyPos,ArrayList<Door> lockDoorPos) {
		ativo = 1;
		this.elements = elements;
		this.wallCords = wallCords;
		this.everyPos = everyPos;
		this.lockDoorPos = lockDoorPos;
	}
	
	public int getAtivo() {
		return ativo;
	}
	
	public ArrayList<GameElement> getElements() {
		return this.elements;
	}
	
	public ArrayList<Point2D> getWallCords() {
		return this.wallCords;
	}
	
	public ArrayList<Point2D> getEveryPos() {
		return this.everyPos;
	}

	public ArrayList<Door> getDoors() {
		return this.lockDoorPos;
	}
	
	
}
	
	
	

