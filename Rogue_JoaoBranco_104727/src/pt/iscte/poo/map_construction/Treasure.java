package pt.iscte.poo.map_construction;

import pt.iscte.poo.core.GameElement;
import pt.iscte.poo.utils.Point2D;

public class Treasure extends GameElement{

	public Treasure(String type, Point2D pos) {
		super(type, pos, 1);
	}

	@Override
	public String getName() {
		return super.getName();
	}

	@Override
	public Point2D getPosition() {
		return super.getPosition();
	}

	@Override
	public int getLayer() {
		return super.getLayer();
	}

}
