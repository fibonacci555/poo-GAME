package pt.iscte.poo.core;

import java.io.FileWriter;
import java.io.IOException;

import pt.iscte.poo.movable.Hero;

public class Points {
	long initialTime;
	int pontuation;
	public Points() {
		this.initialTime = System.currentTimeMillis();
		this.pontuation = 0;
	}
	
	public void restart() {
		this.initialTime = System.currentTimeMillis();
		this.pontuation = 0;
	}
	
	public int updatePoints(Hero hero,int turns) {
		int lifeMulti = turns * hero.getLife();
		pontuation = pontuation + lifeMulti;
		return pontuation;
	}
	
	public String getTimer() {
		long atualTime = System.currentTimeMillis() - initialTime;
		long Seconds = atualTime / 1000;
		long secondsDisplay = Seconds % 60;
		long Minutes = Seconds / 60;
		return (Minutes + ":" + secondsDisplay);

	}
	
	public void updateFile(int pontuation, String name) {
		try {
		      FileWriter myWriter = new FileWriter("score.txt");
		      myWriter.write(pontuation + ";" + name);
		      myWriter.close();
		      System.out.println("Successfully wrote to the file.");
		    } catch (IOException e) {
		      System.out.println("An error occurred.");
		      e.printStackTrace();
		    }
	}
}	
