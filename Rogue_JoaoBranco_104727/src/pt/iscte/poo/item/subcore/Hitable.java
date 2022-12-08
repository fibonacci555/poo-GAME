package pt.iscte.poo.item.subcore;


import java.util.ArrayList;

import pt.iscte.poo.core.GameElement;
import pt.iscte.poo.core.Movable;
import pt.iscte.poo.utils.Direction;
import pt.iscte.poo.utils.Point2D;

public class Hitable {

	
	public Hitable() {
	}
	
	public GameElement isHitable(ArrayList<GameElement> arr1, GameElement hero, int key,ArrayList<Point2D> arr, Point2D pos, int jogadas) {
		
		for( GameElement elem : arr1) {
			if(elem instanceof Movable ) {

			if((Direction.isDirection(key) && (elem.getPosition().equals(hero.getPosition().plus(Direction.directionFor(key).asVector())) || ((Movable) elem).move(arr, pos, jogadas) == hero.getPosition()))){
				return elem;
			}}
		}
		return null;
	}

}
