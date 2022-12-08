package pt.iscte.poo.core;


import pt.iscte.poo.gui.ImageTile;
import pt.iscte.poo.items.Armor;
import pt.iscte.poo.items.HealingPotion;
import pt.iscte.poo.items.Key;
import pt.iscte.poo.items.Sword;
import pt.iscte.poo.map_construction.Door;
import pt.iscte.poo.map_construction.Treasure;
import pt.iscte.poo.movable.Bat;
import pt.iscte.poo.movable.Hero;
import pt.iscte.poo.movable.Scorpio;
import pt.iscte.poo.movable.Skeleton;
import pt.iscte.poo.movable.Thug;
import pt.iscte.poo.utils.Point2D;

public abstract class GameElement implements ImageTile {
	
	
	private Point2D position;
	private int layer;
	private String type;
	
	
	
	public GameElement(String type, Point2D pos, int layer) {
		this.type = type;
		this.position = pos;
		this.layer = layer;
	}
	

	public void setType(String new_type) {
		this.type = new_type;
	}
	
	
	
	public static GameElement create(String type, Point2D pos) {
		switch(type) {
		// Movable
		case "Hero" :
			return new Hero(type,pos);
		case "Skeleton" :
			return new Skeleton(type,pos);
		case "Bat" :
			return new Bat(type,pos);
		case "Thug" :
			return new Thug(type,pos);
		case "Scorpio" :
			return new Scorpio(type,pos);
		// Items
		case "Armor" :
			return new Armor(type,pos);
		case "HealingPotion" :
			return new HealingPotion(type,pos);
		case "Key":
			return new Key(type,pos);
		case "Sword" :
			return new Sword(type,pos);
		// Map
			
		case "Door" :
			return new Door(type,pos);
		case "Treasure" :
			return new Treasure(type,pos);
		
		}
		
		return null;
		
	}
	
	
	
	@Override 
	public String getName() {
		return type;
	}
	
	@Override 
	public Point2D getPosition() {
		return position;
	}
	
	@Override 
	public int getLayer() {
		return layer;
	}
	public void setPosition(Point2D newpos) {
		this.position = newpos;
	}



	
	
	
}
