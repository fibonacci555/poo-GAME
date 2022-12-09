package pt.iscte.poo.item.subcore;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

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
	
	public void cleanFile() {
		File scoreFile = new File("score.txt");
		try {
			scoreFile.createNewFile();
			FileWriter writer = new FileWriter(scoreFile);
			for(int i = 0; i< 5; i++) {
				writer.write("99999999999999;null\n");
			}
			writer.close();
		} catch (IOException e) {
		
		}
		
		
	}
	
	
	
	public String updateFile(int pontuation, String name) {
		File scoreFile = new File("score.txt");
		ArrayList<String> top5scores = new ArrayList<String>();
		ArrayList<String> top5 = new ArrayList<String>();
		if(scoreFile.exists()) {
			try {
				Scanner reader = new Scanner(scoreFile);
				
				while(reader.hasNextLine()) {
					
					String[] data = reader.nextLine().split(";");
					if(data.length != 0) {
						top5scores.add(data[0]);
						top5.add(data[0] + ";" + data[1]);
					}
					
					
				}
				
			
			
				for(String score : top5scores) {
					if(Integer.parseInt(score) > pontuation) {
						top5.remove(4);
						top5.add(pontuation + ";" + name);
						break;
					}
					
				}
			
				
				
				Collections.sort(top5,new PointsComparator());
				
				
				FileWriter writer = new FileWriter(scoreFile);
				for(String score : top5) {
				
					writer.write(score + "\n");;
					
				}
				
				writer.close();
				reader.close();
				
			}catch (IOException e) {
				System.out.println("File Does Not Exist");
			}
			
			String top5_to_string = "You Win!\nYour score was: " + pontuation + "\n" + "\n"
					+ "Score List:" + "\n"
					+ "1º - "+top5.get(0).split(";")[0] + " - " + top5.get(0).split(";")[1] + "\n"
					+ "2º - "+top5.get(1).split(";")[0] + " - " + top5.get(1).split(";")[1] + "\n"
					+ "3º - "+top5.get(2).split(";")[0] + " - " + top5.get(2).split(";")[1]+ "\n"
					+ "4º - "+top5.get(3).split(";")[0] + " - " + top5.get(3).split(";")[1]+ "\n"
					+ "5º - "+top5.get(4).split(";")[0] + " - " + top5.get(4).split(";")[1]
							
					
							+ "\n\n\nThe game will restart automatically!";
			
			
			
			
			
			
			return top5_to_string;
		} else {
			cleanFile();
			
			return updateFile(pontuation,name);
		}
		}}
		
		
	

