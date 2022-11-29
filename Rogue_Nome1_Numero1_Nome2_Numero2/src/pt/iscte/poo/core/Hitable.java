package pt.iscte.poo.core;


import java.util.ArrayList;

import pt.iscte.poo.utils.Direction;

public class Hitable {

	
	public Hitable() {
	}
	
	public GameElement isHitable(ArrayList<GameElement> arr, GameElement hero, int key) {
		
		for( GameElement elem : arr) {
			if(elem instanceof Movable ) {

			if((Direction.isDirection(key) && (elem.getPosition().equals(hero.getPosition().plus(Direction.directionFor(key).asVector()))))){
				return elem;
			}}
		}
		return null;
	}

}
