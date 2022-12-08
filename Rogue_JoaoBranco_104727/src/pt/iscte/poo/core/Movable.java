package pt.iscte.poo.core;

import java.util.ArrayList;

import pt.iscte.poo.utils.Point2D;

public interface Movable {
	
	
	int getLife();
	Point2D move(ArrayList<Point2D> arr, Point2D pos, int jogadas);
	int getDamage();
	void hit(int damage);
}
