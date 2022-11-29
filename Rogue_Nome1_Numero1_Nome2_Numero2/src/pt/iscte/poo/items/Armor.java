package pt.iscte.poo.items;

import pt.iscte.poo.core.GameElement;
import pt.iscte.poo.utils.Point2D;

public class Armor extends GameElement{

	public Armor(String type, Point2D pos) {
		super(type, pos, 1);
	}
	
	public void hit( int damage, int life) {
		if(Math.random() < 0.5) {
			life = life - damage;
		}
	}
	
	
	
	
	
}
