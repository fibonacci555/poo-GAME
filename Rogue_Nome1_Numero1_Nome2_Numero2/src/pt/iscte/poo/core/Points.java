package pt.iscte.poo.core;

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
}	
