package pt.iscte.poo.core;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.ConcurrentModificationException;

import pt.iscte.poo.gui.ImageMatrixGUI;
import pt.iscte.poo.movable.Hero;
import pt.iscte.poo.items.Key;
import pt.iscte.poo.map_construction.Door;

public class LevelPassing {
	public LevelPassing() {}
	
	
	public ArrayList<Door> verifyDoor(GameElement hero, ArrayList<Door> doors,ArrayList<Key> keys, ImageMatrixGUI gui) {
		ArrayList<Door> doors_new = doors;
		ArrayList<String> keys_IDS = new ArrayList<String>();
		if(!(keys == null)) {
			for(Key key : keys) {
				keys_IDS.add(key.getID());
			}
		}
		
		
		
		String door_id = null;
		for(GameElement item : ((Hero) hero).getInvetory()) {
			if(item.getName().contains("Key")) {
				door_id = ((Key) item).getID();
			}
		}
		
		try {
			for(Door doorl : doors) {
				if(doorl.getKeyID() != null && keys != null) {
					for(Key key : keys) {
					if (doorl.getKeyID().contains( key.getID()) ) {
						GameElement a = new Door("DoorOpen",doorl.getPosition(), doorl.getDestinationRoom(),doorl.getDestinationPoint(),key.getID());
						
						doors_new.remove(doorl);
						gui.removeImage(doorl);
						doors_new.add((Door) a);
						gui.addImage(a);
						
					}
				}
				}
				
			}
			
			if(door_id != null) {
				for(String k : keys_IDS) {
					for(Door door: doors) {
						if(door.getKeyID() != null) {
							if(door.getKeyID().contains(k)) {
								
								GameElement a = new Door("DoorOpen",door.getPosition(), door.getDestinationRoom(),door.getDestinationPoint(),door_id);
								
								doors_new.remove(door);
								gui.removeImage(door);
								doors_new.add((Door) a);
								gui.addImage(a);
							}
						}
					}
				}
			}
		} catch (ConcurrentModificationException door) {
			
		}
		
		
//		for(Door door : doors_new) {
//			System.out.println(door.getName() + " - " + door.getState()+ " - " + door.getKeyID());
//		}
		
		
		return doors_new;
		
	}

}