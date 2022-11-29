package pt.iscte.poo.core;

import java.util.ArrayList;

import pt.iscte.poo.movable.Hero;
import pt.iscte.poo.map_construction.Door;
import pt.iscte.poo.items.Key;


public class InventoryManagement {

	public InventoryManagement() {
		
	}
	
	
	public GameElement verify(ArrayList<GameElement> arr, GameElement hero) {
		
		
		for(GameElement elem :arr) {
			if(elem instanceof Movable || elem.getName().contains("Door")) {
				
			}else {
				if(elem.getPosition().equals(hero.getPosition())) {
					return elem;
				}
			}
		}return null;
		
	}
	

	
	
	
	
	
}
