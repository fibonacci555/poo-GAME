package pt.iscte.poo.movable;

import java.util.ArrayList;

import pt.iscte.poo.core.GameElement;
import pt.iscte.poo.core.Movable;
import pt.iscte.poo.gui.ImageMatrixGUI;
import pt.iscte.poo.utils.Direction;
import pt.iscte.poo.utils.Point2D;

public class Hero extends GameElement implements Movable{

	private int life;
	private int damage;
	private ArrayList<GameElement> inventory;
	private int next;
	private boolean scorpio_hit;
	

	public Hero(String type, Point2D position) {
		super(type,position,1);
		this.life = 10;
		this.damage = 1;
		
		this.inventory = new ArrayList<>();
		next = 0;
	}

	@Override
	public String getName() {
		return "Hero";
	}

	public void move(ArrayList<Point2D> arr,ArrayList<Point2D> doors, int key) {
		if(Direction.isDirection(key) && !doors.contains(super.getPosition().plus(Direction.directionFor(key).asVector()))&&!arr.contains(super.getPosition().plus(Direction.directionFor(key).asVector()))){
			  super.setPosition(super.getPosition().plus(Direction.directionFor(key).asVector()));
		}
	}
	
	public void hit(int hit) {
			
			for(GameElement elem : inventory) {
				if(elem.getName().contains("Armor")) {
					if(Math.random() < 0.5) {
						if(this.life - hit < 0) {
							this.life = 0;
						} else {
							this.life = this.life - hit;
							return;
						}
						
					}	else {
						
						return;
					}
				}
			}
		
			if(this.life - hit < 0) {
				this.life = 0;
			} else {
				this.life = this.life - hit;
				return;
			}
			
			
			
		
		
	}
	
	public void setNext(int next) {
		this.next = next;
	}
	
	
	public void setLife(int nlife) {
		this.life = nlife;
	}
	
	public int getLife() {
		return this.life;
	}
	
	
	public ArrayList<GameElement> getInvetory(){
		return inventory;
	}
	
	public void addInventory(GameElement toAdd) {
	
		
		
		if(inventory.size()<=3) {
			inventory.add(toAdd);next++;
		}
		
		
	}
	
	public void setInvetory(ArrayList<GameElement> new_inv){
		this.inventory = new_inv;
	}
	public void removeInventory(GameElement toAdd, ImageMatrixGUI gui) {
		inventory.remove(toAdd);
		gui.removeImage(toAdd);
		
		next--;
		
	}
	
	public int getNext() {
		return next;
	}
	
	public boolean getScorpio() {
		return this.scorpio_hit;
	}
	
	public void hitByScorpio() {
		this.scorpio_hit = true;
	}
	
	public void healPotion() {
		this.scorpio_hit = false;
		if(this.life >= 7) {
			this.life = 10;
		} else {
			this.life = this.life + 3;
		}
	}
	
	

	public int getDamage() {
		for(GameElement elem:inventory) {
			if(elem.getName().contains("Sword")) {
				return this.damage*2;
			}
		}return this.damage;
	}
	@Override
	public Point2D getPosition() {
		return super.getPosition();
	}

	@Override
	public int getLayer() {
		return 1;
	}

	@Override
	public Point2D move(ArrayList<Point2D> arr, Point2D pos, int jogadas) {
		return null;
		// TODO Auto-generated method stub
		
	}

	
}
