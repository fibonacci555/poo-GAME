package pt.iscte.poo.core;

import java.util.ArrayList;
import java.util.List;

import pt.iscte.poo.gui.ImageMatrixGUI;
import pt.iscte.poo.gui.ImageTile;
import pt.iscte.poo.item.subcore.Hitable;
import pt.iscte.poo.item.subcore.InventoryManagement;
import pt.iscte.poo.item.subcore.LevelPassing;
import pt.iscte.poo.item.subcore.Points;
import pt.iscte.poo.items.Key;
import pt.iscte.poo.map_construction.Door;
import pt.iscte.poo.map_construction.Floor;
import pt.iscte.poo.map_construction.Grass;
import pt.iscte.poo.map_construction.Life;
import pt.iscte.poo.map_construction.Wall;
import pt.iscte.poo.movable.Hero;
import pt.iscte.poo.observer.Observed;
import pt.iscte.poo.observer.Observer;
import pt.iscte.poo.utils.Point2D;
import java.io.FileNotFoundException;

public class Engine implements Observer {

	public static final int GRID_HEIGHT = 11;
	public static final int GRID_WIDTH = 10;

	private String atual;

	private static Engine INSTANCE = null;
	private ImageMatrixGUI gui = ImageMatrixGUI.getInstance();
	private boolean winSituation;
	private GameElement hero;
	private ArrayList<GameElement> elements;
	private Points score;
	private int turns;
	private ArrayList<Key> keys;
	private ArrayList<Point2D> wallCords;
	private ArrayList<Point2D> everyPos;
	private ArrayList<Point2D> everyDoor;
	private ArrayList<Door> lockDoors;
	private ArrayList<Point2D> lockDoorsPos;

	private int final_score;

	private InventoryManagement inv_m;
	private ArrayList<Room> rooms;

	public static Engine getInstance() throws FileNotFoundException {
		if (INSTANCE == null)
			INSTANCE = new Engine();
		return INSTANCE;
	}

	private Engine() throws FileNotFoundException {
		gui.registerObserver(this);
		gui.setSize(GRID_WIDTH, GRID_HEIGHT);
		gui.go();
		this.winSituation = false;
		this.atual = "room0";
		this.score = new Points();
		this.everyDoor = new ArrayList<Point2D>();
		this.keys = new ArrayList<Key>();
		this.lockDoorsPos = new ArrayList<Point2D>();
		hero = new Hero("Hero", new Point2D(4, 4));
		inv_m = new InventoryManagement();
		rooms = new ArrayList<Room>();
		for (int i = 0; i != 4; i++) {
			rooms.add(new Room("room" + i));
		}

	}

	public void start() throws FileNotFoundException {

		addFloor();
		addObjects();

		gui.setStatusMessage("ROGUE Starter Package - Turns:" + turns);
		gui.update();

	}

	public void restart() throws FileNotFoundException {
		System.out.println("Restart");
		gui.clearImages();
		this.winSituation = false;
		this.atual = "room0";
		hero = new Hero("Hero", new Point2D(4, 4));
		elements.clear();
		wallCords.clear();
		lockDoors.clear();
		lockDoorsPos.clear();
		everyPos.clear();
		keys.clear();
		turns = 0;
		score.restart();
		rooms = new ArrayList<Room>();
		for (int i = 0; i != 4; i++) {
			rooms.add(new Room("room" + i));
		}
		start();

	}

	private void addFloor() {
		List<ImageTile> tileList = new ArrayList<>();
		for (int x = 0; x != GRID_WIDTH; x++)
			for (int y = 0; y != GRID_HEIGHT; y++)
				if (Math.random() <= 0.8) {
					tileList.add(new Floor(new Point2D(x, y)));
				} else {
					tileList.add(new Grass(new Point2D(x, y)));
				}

		gui.addImages(tileList);
	}

	private void addObjects() throws FileNotFoundException {
		boolean verif = false;

		Room room_atual = new Room("");
		for (Room room : this.rooms) {
			if (room.getNumber().contains(this.atual)) {
				room_atual = room;
				verif = room.getAtivo() == 1;

			}
		}

		if (verif) {

			this.lockDoors = new ArrayList<Door>();
			this.elements = new ArrayList<GameElement>();
			this.lockDoorsPos = new ArrayList<Point2D>();
			this.wallCords = room_atual.getWallCords();
			this.everyPos = room_atual.getEveryPos();
			this.elements = room_atual.getElements();
		} else {

			this.lockDoorsPos = new ArrayList<Point2D>();
			this.lockDoors = new ArrayList<Door>();
			EntityView a = new EntityView("rooms/" + this.atual);
			this.elements = new ArrayList<GameElement>();
			this.elements = a.getElements();
		}

		updateLife();

		if (((Hero) hero).getLife() > 0) {
			gui.addImage(hero);
		}

		for (GameElement elem : elements) {
			if (elem.getName().contains("Door")) {
				this.lockDoors.add((Door) elem);
			}

			gui.addImage(elem);
		}

		addWalls();
		for (Point2D cord : wallCords) {
			Wall w = new Wall(cord);

			gui.addImage(w);
		}

	}

	private void addWalls() throws FileNotFoundException {
		WallView a = new WallView("rooms/" + this.atual);
		a.wallCords();
		this.wallCords = a.getWallCords();

	}

	@SuppressWarnings("unlikely-arg-type")
	private void hits(int key) {
		System.out.println("Vida: " + ((Hero) hero).getLife() + " | Ataque: " + ((Hero) hero).getDamage());

		if (((Hero) hero).getScorpio()) {
			((Hero) hero).setLife(((Hero) hero).getLife() - 1);
		}

		Hitable h = new Hitable();
		if (h.isHitable(elements, hero, key,everyPos, hero.getPosition(), this.turns) != null) {
			Movable mob = (Movable) h.isHitable(elements, hero, key,everyPos, hero.getPosition(), this.turns);
			if (((ImageTile) mob).getName().contains("Scorpio")) {
				((Hero) hero).hitByScorpio();
			}

			mob.hit(((Hero) hero).getDamage());
			((Hero) hero).hit(mob.getDamage());
			updateLife();
			System.out.println("------------Fight-----------");
			System.out.println("Hero: " + ((Hero) hero).getLife());
			System.out.println(((ImageTile) mob).getName() + ": " + mob.getLife());
			if (((Movable) h.isHitable(elements, hero, key,everyPos, hero.getPosition(), this.turns)).getLife() <= 0) {
				elements.remove(mob);
				everyPos.remove(mob);
				gui.removeImage((ImageTile) mob);
			}
		} else {
		}
	}

	private void sincRooms() {
		for (Room a : rooms) {
			if (a.getNumber().contains(this.atual)) {

				a.setAll(this.elements, this.wallCords, this.everyPos, this.lockDoors);
			}
		}
	}

	private void updateLife() {

		int life = ((Hero) hero).getLife();

		for (int j = 0; j != 5; j++) {
			gui.addImage(new Life("Red", new Point2D((int) j, 10)));
		}
		for (int j = 5; j != 10; j++) {
			gui.addImage(new Life("Black", new Point2D((int) j, 10)));
		}
		for (int x = 0; x < life; x++) {
			gui.addImage(new Life("Green", new Point2D(((int) life / 2 - 1 - x), 10)));
		}
		if (life % 2 != 0 || life == 1) {
			gui.addImage(new Life("RedGreen", new Point2D((int) (life / 2), 10)));
		}
	}

	private void updatePos() {

		this.everyPos = new ArrayList<>();
		for (GameElement elem : elements) {
			if ((elem.getName().contains("Door")) || (elem.getName().contains("Sword"))
					|| (elem.getName().contains("HealingPotion")) || (elem.getName().contains("Armor"))
					|| (elem.getName().contains("Key")) || elem.getName().contains("Treasure")) {
			}

			else {
				everyPos.add(elem.getPosition());
			}
		}

		for (Point2D cord : wallCords) {
			everyPos.add(cord);
		}
	}

	private void doorsUpdate() throws FileNotFoundException {

		for (Door door : lockDoors) {
			if (!everyDoor.contains(door.getPosition()))
				everyDoor.add(door.getPosition());
			if (door.getName().contains("Closed") && !(lockDoorsPos.contains(door.getPosition()))) {
				lockDoorsPos.add(door.getPosition());
			} else if (door.getName().contains("Open") && (lockDoorsPos.contains(door.getPosition()))) {
				lockDoorsPos.add(door.getPosition());
			}
		}

		LevelPassing b = new LevelPassing();
		this.lockDoors = b.verifyDoor(hero, this.lockDoors, keys, gui);
		for (Door door : lockDoors) {
			if (door.getState() == 1) {

				lockDoorsPos.remove(door.getPosition());

				if (hero.getPosition().equals(door.getPosition())) {
					sincRooms();
					this.atual = door.getDestinationRoom();
					gui.clearImages();
					ArrayList<GameElement> inv = ((Hero) hero).getInvetory();

					for (GameElement item : ((Hero) hero).getInvetory()) {
						if (item.getName().contains("Key")) {
							((Hero) hero).removeInventory(item, gui);
						}
					}

					for (Door door1 : lockDoors) {
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

		((Hero) hero).move(everyPos, lockDoorsPos, key);
		everyPos.add(hero.getPosition());
		for (GameElement elem : elements) {
			if (elem instanceof Movable) {
				everyPos.addAll(everyDoor);
				((Movable) elem).move(everyPos, hero.getPosition(), this.turns);
				everyPos.add(elem.getPosition());
			}
		}
	}

	private void updateInventory(int key) {
		inv_m.keyUtility((Hero) hero, key, gui, elements, everyPos, wallCords, everyDoor);
		GameElement elem = inv_m.verify(elements, hero);

		if (elem != null) {
			if (elem.getName().contains("Treasure")) {
				this.winSituation = true;

			} else {
				((Hero) hero).addInventory(elem);

				gui.removeImage(elem);

				elements.remove(elem);
				if (!elem.getName().contains("Key")) {
					elem.setPosition(new Point2D(10 - ((Hero) hero).getNext(), 10));
					gui.addImage(elem);

				} else {
					if (!this.keys.contains((Key) elem)) {
						this.keys.add((Key) elem);
					}

					try {

						doorsUpdate();
					} catch (FileNotFoundException e) {
						e.printStackTrace();
					}
					((Hero) hero).removeInventory(elem, gui);
				}

			}
		}
		for (GameElement elem1 : ((Hero) hero).getInvetory()) {
			elem1.setPosition(new Point2D(9 - ((Hero) hero).getInvetory().indexOf(elem1), 10));
		}

	}

	@Override
	public void update(Observed source) {

		if (ImageMatrixGUI.getInstance().wasWindowClosed()) {

		}

		else if (0 >= (((Hero) hero).getLife())) {
			gui.removeImage(hero);
			gui.setMessage("You Lose!\nYour pontuation was: " + score.updatePoints((Hero) hero, turns)
					+ "\nThe game will restart automatically!");
			System.out.println("Ending");
			try {
				restart();
			} catch (FileNotFoundException e) {

				e.printStackTrace();
			}
		} else if (winSituation) {
			String name = gui.askUser("Insert your name");
			ArrayList<String> top5 = new ArrayList<String>();
			gui.setMessage(score.updateFile(final_score, name));
			try {
				restart();
			} catch (FileNotFoundException e) {

				e.printStackTrace();
			}

		} else {
			final_score = score.updatePoints((Hero) hero, turns);
			
			updatePos();
			
			int key = ((ImageMatrixGUI) source).keyPressed();
			moveAll(key);
			try {
				doorsUpdate();
				
			} catch (FileNotFoundException e) {

			}
			updateLife();
			updateInventory(key);
			
			hits(key);
			updateLife();

			final_score = score.updatePoints((Hero) hero, turns);
			turns++;

			gui.setStatusMessage("ROGUE Starter Package - Turns:" + turns);
			gui.setStatusMessage("Score: " + final_score);
			gui.update();
		}
	}
}
