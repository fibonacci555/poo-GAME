package pt.iscte.poo.core;

import java.util.ArrayList;

import pt.iscte.poo.movable.Hero;
import pt.iscte.poo.items.Key;
import pt.iscte.poo.map_construction.Door;

public class LevelPassing {
	public LevelPassing() {}
	
	
	public ArrayList<Door> verifyDoor(GameElement hero, ArrayList<Door> doors) {
		ArrayList<Door> doors_new = doors;
		
		String door_id = null;
		for(GameElement item : ((Hero) hero).getInvetory()) {
			if(item.getName().contains("Key")) {
				door_id = ((Key) item).getID();
			}
		}
		
		System.out.println(door_id);
		if(door_id != null) {
			for(Door door: doors_new) {
				if(door.getKeyID() != null) {
					if(door.getKeyID().contains(door_id)) {
						GameElement a = new Door("DoorOpen",door.getPosition(), door.getDestinationRoom(),door.getDestinationPoint(),door.getKeyID());
						doors_new.remove(door);
						doors_new.add((Door) a);
					}
				}
			}
		}
		
		
		return doors_new;
		
	}

}