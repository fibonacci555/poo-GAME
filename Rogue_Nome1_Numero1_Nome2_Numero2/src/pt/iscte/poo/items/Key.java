package pt.iscte.poo.items;

import pt.iscte.poo.core.GameElement;
import pt.iscte.poo.utils.Point2D;

public class Key extends GameElement{
	
	private String id;
	
	public Key(String type, Point2D pos) {
		super(type,pos,1);
		
	}
	
	public Key(String type, Point2D pos, String id) {
		super(type,pos,1);
		this.id = id;
		
	}

	
	public String getID() {
		return this.id;
	}

}
