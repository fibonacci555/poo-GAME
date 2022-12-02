package pt.iscte.poo.core;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import pt.iscte.poo.movable.Hero;
import pt.iscte.poo.utils.Point2D;
import pt.iscte.poo.utils.Vector2D;
import pt.iscte.poo.map_construction.Door;
import pt.iscte.poo.map_construction.Life;
import pt.iscte.poo.gui.ImageMatrixGUI;
import pt.iscte.poo.items.Key;


public class InventoryManagement {

	public InventoryManagement() {
		
	}
	
	
	public GameElement verify(ArrayList<GameElement> arr, GameElement hero) {
		
		
		for(GameElement elem :arr) {
			if(elem instanceof Movable || elem.getName().contains("Door")) {
				
			}else {
				if((elem.getPosition().equals(hero.getPosition()) && ((Hero) hero).getInvetory().size() < 3)) {
					return elem;
				}
			}
		}return null;
		
	}
	
	
	
	public void keyUtility(Hero hero, int key, ImageMatrixGUI gui ,ArrayList<GameElement> elements , ArrayList<Point2D> everyPos) {
		
	
		switch(key) {
		case 49 : {
			try {
				GameElement elem = hero.getInvetory().get(0);
				if(elem.getName().contains("HealingPotion")) {
					
					if(hero.getLife() >= 7) {
						hero.setLife(10);
					} else {
						hero.setLife(hero.getLife() + 3);
					}
	
					hero.removeInventory(elem,gui);
					hero.setNext(0);
				}
				else {
					
					elem.setPosition(hero.getPosition().plus(new Vector2D(1,0)));
					elements.add(elem);
					everyPos.add(elem.getPosition());
					hero.removeInventory(elem,gui);
					gui.addImage(elem);
					hero.setNext(0);
					
					
					
				
					
					
				}
			}
			catch (IndexOutOfBoundsException e) {
			}
		}
		case 50 : {
			try {
				GameElement elem = hero.getInvetory().get(1);
				if(elem.getName().contains("HealingPotion")) {
					
					if(hero.getLife() >= 7) {
						hero.setLife(10);
					} else {
						hero.setLife(hero.getLife() + 3);
					}
	
					hero.removeInventory(elem,gui);
					hero.setNext(1);
				}
				else {
					
					elem.setPosition(hero.getPosition().plus(new Vector2D(1,0)));
					elements.add(elem);
					everyPos.add(elem.getPosition());
					hero.removeInventory(elem,gui);
					gui.addImage(elem);
					hero.setNext(1);
					
					
					
					
				
					
					
				}
			}
			catch (IndexOutOfBoundsException e) {
			}
		}
		case 51 : {
			try {
				GameElement elem = hero.getInvetory().get(2);
				if(elem.getName().contains("HealingPotion")) {
					
					if(hero.getLife() >= 7) {
						hero.setLife(10);
					} else {
						hero.setLife(hero.getLife() + 3);
					}
	
					hero.removeInventory(elem,gui);
					hero.setNext(2);
				}
				else {
					elem.setPosition(hero.getPosition().plus(new Vector2D(1,0)));
					elements.add(elem);
					everyPos.add(elem.getPosition());
					hero.removeInventory(elem,gui);
					gui.addImage(elem);
					hero.setNext(2);
					
					
				
					
					
				}
			}
			catch (IndexOutOfBoundsException e) {
			}
		}
		}
		
	
		
	}
	

	
	
	
	
	
}
