package pt.iscte.poo.core;

import java.util.ArrayList;
import java.util.List;

import pt.iscte.poo.gui.ImageMatrixGUI;
import pt.iscte.poo.gui.ImageTile;
import pt.iscte.poo.items.Key;
import pt.iscte.poo.map_construction.Door;
import pt.iscte.poo.map_construction.Floor;
import pt.iscte.poo.map_construction.Life;
import pt.iscte.poo.map_construction.Wall;
import pt.iscte.poo.movable.Hero;
import pt.iscte.poo.observer.Observed;
import pt.iscte.poo.observer.Observer;
import pt.iscte.poo.utils.Point2D;
import java.io.FileNotFoundException;


public class EngineExample implements Observer {

	public static final int GRID_HEIGHT = 11;
	public static final int GRID_WIDTH = 10;
	
	private String atual;
	
	private static EngineExample INSTANCE = null;
	private ImageMatrixGUI gui = ImageMatrixGUI.getInstance();
	
	private GameElement hero;
	private ArrayList<GameElement> elements;
	private int turns;
	private ArrayList<Key> keys;
	private ArrayList<Point2D> wallCords;
	private ArrayList<Point2D> everyPos;
	private ArrayList<Door> lockDoorPos;
	private InventoryManagement inv_m;
	
	public static EngineExample getInstance() throws FileNotFoundException {
		if (INSTANCE == null)
			INSTANCE = new EngineExample();
		return INSTANCE;
	}

	
	
	
	private EngineExample() throws FileNotFoundException {		
		gui.registerObserver(this);
		gui.setSize(GRID_WIDTH, GRID_HEIGHT);
		gui.go();
		
		this.atual = "room0";
		this.keys = new ArrayList<Key>();
		System.out.println(this.atual);
		hero = new Hero("Hero", new Point2D(4,4));
		inv_m = new InventoryManagement();
		
		
	}

	public void start() throws FileNotFoundException {
		
		
		addFloor();
		addObjects();
		
		
		gui.setStatusMessage("ROGUE Starter Package - Turns:" + turns);
		gui.update();
		
		
	}
	
	private void addFloor() {
		List<ImageTile> tileList = new ArrayList<>();
		for (int x=0; x!=GRID_WIDTH; x++)
			for (int y=0; y!=GRID_HEIGHT; y++)
				tileList.add(new Floor(new Point2D(x,y)));
		gui.addImages(tileList);
	}
	
	private void addObjects() throws FileNotFoundException{
		
		this.lockDoorPos = new ArrayList<Door>();
		
		
		
		updateLife();
		EntityView a = new EntityView("rooms/"+this.atual);
		
		
		this.elements = new ArrayList<GameElement>();
		
		this.elements = a.getElements();
		if(((Hero) hero).getLife()> 0) {
			gui.addImage(hero);
		}
		
		for(GameElement elem :elements) {
			if(elem.getName().contains("Door")) {this.lockDoorPos.add((Door) elem);}
			
				gui.addImage(elem);}
			
		addWalls();
		for (Point2D cord : wallCords) {
			Wall w = new Wall(cord);
			
			gui.addImage(w);
		}
		for(Door door : lockDoorPos) {
			System.out.println(door.getName() + " - " + door.getState()+ " - " + door.getKeyID());
		}
		for(Door door : lockDoorPos) {
			if(door.getState() == 1) {
				if(this.wallCords.contains(door.getPosition())) {
					this.wallCords.remove(door.getPosition());
					gui.addImage(new Door("DoorOpen", door.getPosition(), door.getDestinationRoom(), door.getDestinationPoint(), door.getKeyID()));
					
				}
			}else {
				if(!this.wallCords.contains(door.getPosition())) {
					this.wallCords.add(door.getPosition());
					gui.addImage(new Door("DoorClosed", door.getPosition(), door.getDestinationRoom(), door.getDestinationPoint(), door.getKeyID()));
					
				}
			}
		}
	}
	
	private void addWalls() throws FileNotFoundException {
		WallView a = new WallView("rooms/"+this.atual);
		a.wallCords();
		this.wallCords = a.getWallCords();
		
	}
	
	private void hits(int key) {
		System.out.println("Vida: " + ((Hero) hero).getLife() + " | Ataque: "+ ((Hero) hero).getDamage());
		
		Hitable h = new Hitable();
		if(h.isHitable(elements,hero,key) != null) {
			Movable mob = (Movable) h.isHitable(elements,hero,key);
			
			
			mob.hit(((Hero) hero).getDamage());
			((Hero) hero).hit(mob.getDamage());
			updateLife();
			System.out.println("------------Fight-----------");
			System.out.println("Hero: " + ((Hero) hero).getLife());
			System.out.println(((ImageTile) mob).getName() + ": " + mob.getLife());
			if( ((Movable) h.isHitable(elements,hero,key)).getLife() <= 0) {
				elements.remove(mob);
				everyPos.remove(mob);
				gui.removeImage((ImageTile)mob);}
			}
		else {
		}
	}

	
	private void updateLife() {
		double life = ((Hero) hero).getLife();
		
		for(int j = 0; j != 5; j++) {
			gui.addImage(new Life("Red",new Point2D((int) j,10)));
		}
		for(int j = 5; j != 10; j++) {
			gui.addImage(new Life("Black",new Point2D((int) j,10)));
		}
		for(int x = 0; x < life ; x++) {
			gui.addImage(new Life("Green",new Point2D(((int) life/2 - 1  - x),10)));
		}
		if(life % 2 != 0 || life == 1) {
			gui.addImage(new Life("RedGreen",new Point2D((int) (life/2),10)));
		}
	}
	
	
	private void updatePos() {
		this.everyPos = new ArrayList<>();
		for(GameElement elem :elements) {
			if((elem.getName().contains("Door")) || (elem.getName().contains("Sword")) || (elem.getName().contains("HealingPotion"))
					|| (elem.getName().contains("Armor"))|| (elem.getName().contains("Treasure"))|| (elem.getName().contains("Key"))) {
			}else {
				everyPos.add(elem.getPosition());}
			}
			
		for (Point2D cord : wallCords) {
			everyPos.add(cord);
		}
	}
	
	private void doorsUpdate() throws FileNotFoundException {
		
		LevelPassing b = new LevelPassing();
		this.lockDoorPos = b.verifyDoor(hero, this.lockDoorPos,keys, gui);
		for(Door door : lockDoorPos) {
			if(door.getState() == 1) {
				wallCords.remove(door.getPosition());
				
				
				if(hero.getPosition().equals(door.getPosition())) {
					this.atual = door.getDestinationRoom();
					for(GameElement i: this.elements) {
						gui.removeImage(i);
					}
					ArrayList<GameElement> inv = ((Hero) hero).getInvetory();
					
					for(GameElement item: ((Hero) hero).getInvetory()) {
						if(item.getName().contains("Key")) {((Hero) hero).removeInventory(item); gui.removeImage(item);}
					}
					
					for(Door door1 : lockDoorPos) {
						
						gui.removeImage(door1);
					}
					
					start();
					System.out.println("Nivel passed");
					
					
					
					
					hero.setPosition(door.getDestinationPoint());
					((Hero) hero).setInvetory(inv);
					
					break;
				}
			}
		}
		
	}
	
	
	private void moveAll(int key) {
		((Hero) hero).move(everyPos,key);
		everyPos.add(hero.getPosition());
		for(GameElement elem :elements) {
			if(elem instanceof Movable ) {
				((Movable) elem).move(everyPos,hero.getPosition(), this.turns);
				everyPos.add(elem.getPosition());
				}}
	}
	
	
	private void updateInventory() {
		GameElement elem = inv_m.verify(elements, hero);
		if(elem !=null) {
			((Hero) hero).addInventory(elem);
			gui.removeImage(elem);
		
			elements.remove(elem);
			if(!elem.getName().contains("Key")) {
				GameElement n = GameElement.create(elem.getName(), new Point2D(10-((Hero) hero).getNext(), 10));
				gui.addImage(n);
				
			}else {
				this.keys.add((Key) elem);
				try {
					
					doorsUpdate();
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}
				((Hero) hero).removeInventory(elem);
			}
			
			
		}
	}
	
	@Override
	public void update(Observed source) {
		if (ImageMatrixGUI.getInstance().wasWindowClosed() || 0>= (((Hero) hero).getLife())) {
			gui.removeImage(hero);
			System.out.println("Ending");	}
		else {
			
			
		
		
			
			
			System.out.println("---------------------------------");
			System.out.print("Elements -> ");
			for(GameElement elem: elements) {
				System.out.print(elem.getName()+ ", ");
			}
			System.out.println();
			System.out.print("Keys -> ");
			for(Key elem: keys) {
				System.out.print(elem.getID()+ ", ");
			}
			System.out.println();
			System.out.print("lockDoorPos -> ");
			for(Door elem: lockDoorPos) {
				System.out.print(elem.getKeyID()+ ", ");
			}
			System.out.println();
			
			System.out.print("Inventory -> ");
			for(GameElement elem: ((Hero) hero).getInvetory()) {
				System.out.print(elem.getName()+ ", ");
			}
			System.out.println();
			System.out.println("---------------------------------");
			
			
			
			
			
			
			
			
			
			updatePos();
			for(Key a : keys) {
				System.out.println(a.getID());
			}
			int key = ((ImageMatrixGUI) source).keyPressed();
			moveAll(key);
			updatePos();
			updateInventory();
			try {
				doorsUpdate();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			hits(key);
			
			turns++;
			
			gui.setStatusMessage("ROGUE Starter Package - Turns:" + turns);
			gui.update();
		}
	}
}
