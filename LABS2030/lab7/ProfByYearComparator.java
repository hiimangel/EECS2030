package studentSystem;

import java.util.Comparator;

public class ProfByYearComparator implements Comparator<Prof> {

	public ProfByYearComparator() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public int compare(Prof prof1, Prof prof2) {
		int profOneYear = prof1.getYear();
		int profTwoYear = prof2.getYear();
		if(profOneYear > profTwoYear) {
			return 1;
		}else if(profOneYear < profTwoYear) {
			return -1;
		}else {
			return 0;
		}
	}

	
		 	/**
	 	   * Compares two Profs by their year, in ascending order.
	 	   * 
	 	   *  
	 	   */
	 	   

}
