package relativeCheck;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Vector;

/**
 * 
 * deprecated
 *
 */
public class DocumentManager {
	String dataname;
	public Vector<RelativeHorse> population = new Vector<RelativeHorse>();
	String detSep = "%%%"; // separates details of a horse
	String[] races = new String[] { " Unbekannt", "Achal-Tekkiner", "American Paint Horse", "American Quarter Horse", "Andalusier", "Appaloosa", "Berber", "Cape Boerperd", "Criollo",
			"Deutsches Reitpony", "Englisches Vollblut", "Hannoveraner", "Holsteiner", "Lipizzaner", "Lusitano", "Oldenburger", "Pinto", "Trakehner", "Vollblutaraber" };

	public DocumentManager(String dn) {
		this.dataname = dn;
		population = new Vector<RelativeHorse>();
	}

	public DocumentManager() {
		this.dataname = "database.txt";
	}

	public void removeHorse(String n) {
		for (int i = 0; i < population.size(); i++) {
			RelativeHorse current = population.get(i);
			if (current.getFather() != null) {
				if (current.getFather().getName().equals(n))
					current.setFather(null);
			}
			if (current.getMother() != null) {
				if (current.getMother().getName().equals(n))
					current.setMother(null);
			}
			if (current.getName().equals(n)) {
				population.remove(i);
				i--;
			}
		}
	}

	public int find(String n) {
		int i = 0;
		while (i < this.population.size()) {
			if (this.population.get(i).getName().equals(n)) {
				return i;
			}
			i++;
		}
		return -1;
	}

	public int addHorse(RelativeHorse rc){
		return addHorse(rc, false);
	}
	
	int addHorse(RelativeHorse rh, boolean priorizeNew) { // returns -1 if horse is already known and the index of insertion if horse is new
		if (rh.getName().equals("null"))
			return -2;
		int numberOfHorses = this.population.size();
		for (int i = 0; i < numberOfHorses; i++) {
			RelativeHorse h = population.elementAt(i);
			if (h.getName().equals(rh.getName())) {
				if (rh.getFather() != null) {
					h.setFather(rh.getFather());
				}
				if (rh.getMother() != null) {
					h.setMother(rh.getMother());
				}
				if (!rh.getRace().equals(" Unbekannt")) {
					h.setRace(rh.getRace());
				}
				if(priorizeNew){
					for(String fav: rh.getFavourites())
						h.addFavourite(fav);
				}
				return -1;
			}
		}
		this.population.add(rh);
		return this.population.size() - 1;
	}

	/**
	 * 
	 * @return -1, falls auf File nicht zugreifbar; 0, falls File nicht lesbar;
	 *         1, falls fehlerlos; -2, falls leere Datenbank
	 * @throws IllegalFileException
	 */
	public int analyzeFile() throws IllegalFileException {
		File file = new File(this.dataname);
		if (!file.canRead() || !file.isFile()) {
			return -1;
		}
		FileReader fr = null;
		int c;
		StringBuffer buff = new StringBuffer();
		this.population = new Vector<RelativeHorse>();

		try {
			fr = new FileReader(file);
			while ((c = fr.read()) != -1) {
				buff.append((char) c);
			}
			fr.close();
		} catch (IOException ioe) {
			System.out.println("fail");
			return 0;
		}

		String[] splitIntoHorses = buff.toString().split("\n");
		boolean throwException = false;
		boolean fatal = false;
		for (int i = 1; i < splitIntoHorses.length; i++) {
			String splitIntoDetails[] = splitIntoHorses[i].split(detSep);
			if (splitIntoDetails.length != 6 && splitIntoDetails.length != 5) {
				throwException = true;
				fatal = true;
			} else {
				RelativeHorse father = null;
				RelativeHorse mother = null;
				if (!splitIntoDetails[1].equals("null")) {
					father = new RelativeHorse(splitIntoDetails[1], null, null);
					if (this.addHorse(father) == -1) {
						father = population.get(find(father.getName()));
					}
				}
				if (!splitIntoDetails[2].equals("null")) {
					mother = new RelativeHorse(splitIntoDetails[2], null, null);
					this.addHorse(mother);
					if (this.addHorse(mother) == -1) {
						mother = population.get(find(mother.getName()));
					}
				}
				boolean isFavourite = splitIntoDetails[4].equals("true");
				boolean isMale = true;
				Vector<String> favourites = new Vector<String>();
				if(isFavourite)
					favourites.add("Standardfavoriten");
				if (splitIntoDetails.length == 6) {
					isMale = splitIntoDetails[5].equals("true");
					this.addHorse(new RelativeHorse(splitIntoDetails[0], father, mother, splitIntoDetails[3], favourites, isMale,-1));
				} else {
					this.addHorse(new RelativeHorse(splitIntoDetails[0], father, mother, splitIntoDetails[3], favourites, isMale,-1));
					throwException = true;
				}

			}
		}
		if (throwException) {
			throw new IllegalFileException(fatal);
		}
		return 1;
	}
}