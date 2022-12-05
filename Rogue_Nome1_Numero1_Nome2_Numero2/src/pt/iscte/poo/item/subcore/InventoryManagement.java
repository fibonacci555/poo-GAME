package pt.iscte.poo.item.subcore;


import java.util.ArrayList;
import pt.iscte.poo.movable.Hero;

import pt.iscte.poo.utils.Point2D;
import pt.iscte.poo.utils.Vector2D;
import pt.iscte.poo.core.GameElement;
import pt.iscte.poo.core.Movable;
import pt.iscte.poo.gui.ImageMatrixGUI;


public class InventoryManagement {

	public InventoryManagement() {
		
	}
	
	
	public GameElement verify(ArrayList<GameElement> arr, GameElement hero) {
		
		
		for(GameElement elem :arr) {
			if(elem instanceof Movable || elem.getName().contains("Door")) {
				
			}else {
				if((elem.getPosition().equals(hero.getPosition()) && ((Hero) hero).getInvetory().size() < 3) || (elem.getPosition().equals(hero.getPosition()) && elem.getName().contains("Treasure"))) {
					return elem;
				}
			}
		}return null;
		
	}
	
	
	
	public void keyUtility(Hero hero, int key, ImageMatrixGUI gui ,ArrayList<GameElement> elements , ArrayList<Point2D> everyPos ,ArrayList<Point2D> walls ,ArrayList<Point2D> doors) {
		ArrayList<Point2D> all = new ArrayList<Point2D>();
		all = (ArrayList<Point2D>) walls.clone();
		all.addAll(doors);
		
		switch(key) {
		case 49 : {
			try {
				GameElement elem = hero.getInvetory().get(0);
				if(elem.getName().contains("HealingPotion")) {
					
					hero.healPotion();
	
					hero.removeInventory(elem,gui);
					hero.setNext(0);
				}
				else {
					
					if(!all.contains(hero.getPosition().plus(new Vector2D(1,0)))){
						elem.setPosition(hero.getPosition().plus(new Vector2D(1,0)));
					} else if(!all.contains(hero.getPosition().plus(new Vector2D(0,1)))){
						elem.setPosition(hero.getPosition().plus(new Vector2D(0,1)));
					} else if(!all.contains(hero.getPosition().plus(new Vector2D(-1,0)))){
						elem.setPosition(hero.getPosition().plus(new Vector2D(-1,0)));
					} else if(!all.contains(hero.getPosition().plus(new Vector2D(0,-1)))){
						elem.setPosition(hero.getPosition().plus(new Vector2D(0,-1)));
					}
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
					
					hero.healPotion();
	
					hero.removeInventory(elem,gui);
					hero.setNext(1);
				}
				else {
					if(!all.contains(hero.getPosition().plus(new Vector2D(1,0)))){
						elem.setPosition(hero.getPosition().plus(new Vector2D(1,0)));
					} else if(!all.contains(hero.getPosition().plus(new Vector2D(0,1)))){
						elem.setPosition(hero.getPosition().plus(new Vector2D(0,1)));
					} else if(!all.contains(hero.getPosition().plus(new Vector2D(-1,0)))){
						elem.setPosition(hero.getPosition().plus(new Vector2D(-1,0)));
					} else if(!all.contains(hero.getPosition().plus(new Vector2D(0,-1)))){
						elem.setPosition(hero.getPosition().plus(new Vector2D(0,-1)));
					}
					
					
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
					
					hero.healPotion();
	
					hero.removeInventory(elem,gui);
					hero.setNext(2);
				}
				else {
					if(!all.contains(hero.getPosition().plus(new Vector2D(1,0)))){
						elem.setPosition(hero.getPosition().plus(new Vector2D(1,0)));
					} else if(!all.contains(hero.getPosition().plus(new Vector2D(0,1)))){
						elem.setPosition(hero.getPosition().plus(new Vector2D(0,1)));
					} else if(!all.contains(hero.getPosition().plus(new Vector2D(-1,0)))){
						elem.setPosition(hero.getPosition().plus(new Vector2D(-1,0)));
					} else if(!all.contains(hero.getPosition().plus(new Vector2D(0,-1)))){
						elem.setPosition(hero.getPosition().plus(new Vector2D(0,-1)));
					}
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
