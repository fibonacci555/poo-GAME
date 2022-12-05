package pt.iscte.poo.core;

import java.util.Comparator;

public class PointsComparator implements Comparator<String>{

	
	

	@Override
	public int compare(String o1, String o2) {
		int numero1 = Integer.parseInt(o1.split(";")[0]);
		int numero2 = Integer.parseInt(o2.split(";")[0]);
		return numero2 -numero1;
	}
	
}
