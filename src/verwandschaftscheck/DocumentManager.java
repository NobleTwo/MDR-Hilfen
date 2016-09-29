package verwandschaftscheck;

import java.io.*;
import java.util.*;

public class DocumentManager {
	String dataname;
	Vector<RelativeHorse> population = new Vector<RelativeHorse>();
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
			if (current.father != null) {
				if (current.father.name.equals(n))
					current.father = null;
			}
			if (current.mother != null) {
				if (current.mother.name.equals(n))
					current.mother = null;
			}
			if (current.name.equals(n)) {
				population.remove(i);
				i--;
			}
		}
	}

	public int find(String n) {
		int i = 0;
		while (i < this.population.size()) {
			if (this.population.get(i).name.equals(n)) {
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
		if (rh.name.equals("null"))
			return -2;
		int numberOfHorses = this.population.size();
		for (int i = 0; i < numberOfHorses; i++) {
			RelativeHorse h = population.elementAt(i);
			if (h.name.equals(rh.name)) {
				if (rh.father != null) {
					h.father = rh.father;
				}
				if (rh.mother != null) {
					h.mother = rh.mother;
				}
				if (!rh.race.equals(" Unbekannt")) {
					h.race = rh.race;
				}
				if(priorizeNew){
					h.isFavourite = rh.isFavourite;
				}
				return -1;
			}
		}
		this.population.add(rh);
		return this.population.size() - 1;
	}

	String populationToString() {
		String result = "";
		if (this.population != null) {
			for (int i = 0; i < this.population.size(); i++) {
				RelativeHorse current = population.elementAt(i);
				result = result + "\n" + current.name + detSep;

				RelativeHorse temp;
				if ((temp = current.father) != null) {
					result = result + temp.name + detSep;
				} else {
					result = result + "null" + detSep;
				}
				if ((temp = current.mother) != null) {
					result = result + temp.name + detSep;
				} else {
					result = result + "null" + detSep;
				}
				result += current.race + detSep + current.isFavourite + detSep + current.isMale();
			}
		}
		return result;
	}

	int save() {
		PrintWriter pwriter = null;
		try {
			pwriter = new PrintWriter(new BufferedWriter(new FileWriter(this.dataname)));
			pwriter.print(this.populationToString());
			pwriter.flush();
			pwriter.close();
		} catch (IOException ioe) {
			return -1;
		}
		return 0;
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
						father = population.get(find(father.name));
					}
				}
				if (!splitIntoDetails[2].equals("null")) {
					mother = new RelativeHorse(splitIntoDetails[2], null, null);
					this.addHorse(mother);
					if (this.addHorse(mother) == -1) {
						mother = population.get(find(mother.name));
					}
				}
				boolean isFavourite = splitIntoDetails[4].equals("true");
				boolean isMale = true;
				if (splitIntoDetails.length == 6) {
					isMale = splitIntoDetails[5].equals("true");
					this.addHorse(new RelativeHorse(splitIntoDetails[0], father, mother, splitIntoDetails[3], isFavourite, isMale));
				} else {
					this.addHorse(new RelativeHorse(splitIntoDetails[0], father, mother, splitIntoDetails[3], isFavourite, isMale));
					throwException = true;
				}

			}
		}
		if (throwException) {
			save();
			throw new IllegalFileException(fatal);
		}
		return 1;
	}

	public static void main(String[] args) {
		DocumentManager dm = new DocumentManager();
		RelativeHorse rh1 = new RelativeHorse("1", null, null, "Englisches Vollblut", false, false);
		RelativeHorse rh2 = new RelativeHorse("2", null, null, "Englisches Vollblut", false, true);
		dm.addHorse(rh1);
		dm.addHorse(rh2);
		dm.addHorse(new RelativeHorse("DerErste", rh1, null, "Englisches Vollblut", false, true));
		dm.save();
		try {
			dm.analyzeFile();
		} catch (IllegalFileException e) {
			System.out.println("Fehler");
		}
	}
}