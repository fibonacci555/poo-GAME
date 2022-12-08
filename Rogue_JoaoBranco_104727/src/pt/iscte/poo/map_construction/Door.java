package pt.iscte.poo.map_construction;

import pt.iscte.poo.core.GameElement;
import pt.iscte.poo.utils.Point2D;

public class Door extends GameElement {

	public final static int TRANCADA = 0;
	public final static int DESTRANCADA = 1;
	private int state;
	private String room_destino;
	private Point2D destino;
	private String key_id;

	public Door(String type, Point2D position) {
		super(type,position,1);
		this.state = TRANCADA;
	}
	
	public Door(String type, Point2D position, String rd, Point2D dest, String key_id) {
		super(type,position,1);
		if (key_id != "0" && type.contains("DoorClosed")) {this.state = TRANCADA; this.key_id = key_id; }else {this.state = DESTRANCADA;}
		
		this.room_destino = rd;
		this.destino = dest;
	}
	
	public String getKeyID() {
		return key_id;
	}
	
	public Point2D getDestinationPoint() {
		return destino;
	}
	
	public String getDestinationRoom() {
		return room_destino;
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
	
	public void unlock() {
		super.setType("DoorOpen");
		this.state = DESTRANCADA;
	}
	
	public int getState() {
		return this.state;
	}

}
