package pt.iscte.poo.map_construction;

import pt.iscte.poo.gui.ImageTile;
import pt.iscte.poo.utils.Point2D;

public class Life implements ImageTile{
	private String name;
	private Point2D position;

	public Life(String name, Point2D pos) {
		this.name = name;
		this.position = pos;
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return this.name;
	}

	@Override
	public Point2D getPosition() {
		// TODO Auto-generated method stub
		return this.position;
	}

	@Override
	public int getLayer() {
		// TODO Auto-generated method stub
		return 0;
	}

}
