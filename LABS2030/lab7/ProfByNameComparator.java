package studentSystem;

import java.util.Comparator;

public class ProfByNameComparator implements Comparator<Prof> {

	public int compare(Prof prof1, Prof prof2) {
		// TODO Auto-generated method stub
		if(prof1.getName() == prof2.getName()) {
			return 0;
		}else {
			return prof1.getName().compareTo(prof2.getName());
		}
	}

}
