package pt.iscte.poo.movable;

import java.util.ArrayList;

import pt.iscte.poo.core.GameElement;
import pt.iscte.poo.core.Movable;
import pt.iscte.poo.utils.Direction;
import pt.iscte.poo.utils.Point2D;
import pt.iscte.poo.utils.Vector2D;

public class Bat extends GameElement implements Movable{
	private int life;
	private int damage;
	
	public Bat(String type, Point2D pos) {
		super(type, pos, 1);
		this.life = 3;
		this.damage = 1;
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
	@Override
	public Point2D move(ArrayList<Point2D> arr, Point2D pos, int jogadas) {
		Vector2D vec = Vector2D.movementVector(getPosition(),pos); 
		if(Math.random() > 0.5) {
			if(!arr.contains(super.getPosition().plus(vec))){
				  super.setPosition(super.getPosition().plus(vec));
			}}
			else {
				Direction randDirection = Direction.random();
				Vector2D randVector = randDirection.asVector(); 
				if(!arr.contains(super.getPosition().plus(randVector))){
					  super.setPosition(super.getPosition().plus(randVector));
				}
			}
		return super.getPosition().plus(vec);
	}


	@Override
	public int getLife() {
		// TODO Auto-generated method stub
		return this.life;
	}


	@Override
	public int getDamage() {
		if(Math.random() > 0.5) {
			if((this.life ) < 3) {
				this.life = this.life + 1;
				}
			else {this.life = 3;}
			return this.damage;
		}
		else {return 0;}
	}


	@Override
	public void hit(int damage) {
		if (this.life - damage < 0) {
			this.life = 0;
		}else {
			this.life = this.life - damage;
		}
		
		
	}

}
