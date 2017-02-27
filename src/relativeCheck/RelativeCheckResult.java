package relativeCheck;

import java.awt.Point;
import java.util.Vector;

public class RelativeCheckResult {
	private Vector<Point[]> identicalHorses = new Vector<Point[]>(); // first point: position left horse, second point: position right horse
	private Vector<Point[]> mismatchedRaces = new Vector<Point[]>(); // first point: position left horse, second point: position right horse

	private Vector<Point[]> tooOldToMatter = new Vector<Point[]>(); // Vector of horses of oldest generation -> not relevant for family tree

	private Vector<Point> unknownHorses = new Vector<Point>(); // first number: left(0) or right (1), second number: position of horse
	private Vector<Point> unknownRaces = new Vector<Point>(); // first number: left(0) or right (1), second number: position of horse

	private boolean mismatchedSexes = false;

	public RelativeCheckResult(String[][] horseNames, String[][] horseRaces, boolean[] isMale) {
		if (horseNames.length != 2 || horseRaces.length != 2 || isMale.length != 2) {
			return;
		}

		for (int i = 0; i < horseNames.length; i++) {
			for (int j = 0; j < horseNames[i].length; j++) {
				String horse1 = horseNames[i][j];
				if (horse1.equals("nicht in DB")) {
					continue;
				}

				for (int k = i; k < horseNames.length; k++) {
					int start = 0;
					if (k == i) {
						start = j + 1;
					}
					for (int l = start; l < horseNames[k].length; l++) {
						String horse2 = horseNames[k][l];
						if (horse2.equals("nicht in DB")) {
							continue;
						}
						if (horse1.equals(horse2)) {
							Point[] positions = { new Point(i, j), new Point(k, l) };
							if (j >= 7 || l >= 7) {
								tooOldToMatter.addElement(positions);
							} else {
								identicalHorses.addElement(positions);
							}
						}
					}
				}
			}
		}

		for (int i = 0; i < horseRaces.length; i++) {
			for (int j = 0; j < horseRaces[i].length; j++) {
				String race1 = horseRaces[i][j];
				if (race1.equals(" Unbekannt")) {
					continue;
				}

				for (int k = i; k < horseNames.length; k++) {
					int start = 0;
					if (k == i) {
						start = j + 1;
					}
					for (int l = start; l < horseNames[k].length; l++) {
						String race2 = horseRaces[k][l];
						if (race2.equals(" Unbekannt")) {
							continue;
						}

						if (!race1.equals(race2)) {
							Point[] positions = { new Point(i, j), new Point(k, l) };
							mismatchedRaces.addElement(positions);
						}
					}
				}
			}
		}

		mismatchedSexes = (isMale[0] == isMale[1]);

		if (!containsError()) {

			for (int i = 0; i < horseNames.length; i++) {
				for (int j = 0; j < horseNames[i].length; j++) {
					if (horseNames[i][j].equals("nicht in DB")) {
						unknownHorses.add(new Point(i, j));
					}
				}
			}

			for (int i = 0; i < horseRaces.length; i++) {
				for (int j = 0; j < horseRaces[i].length; j++) {
					if (horseRaces[i][j].equals(" Unbekannt")) {
						unknownRaces.add(new Point(i, j));
					}
				}
			}
		}
	}

	public boolean containsError() {
		return (identicalHorses.size() != 0 || mismatchedRaces.size() != 0 || mismatchedSexes);
	}

	public boolean containsWarning() {
		return (unknownHorses.size() != 0 || unknownRaces.size() != 0);
	}

	public Vector<Point[]> getIdenticalHorses() {
		return identicalHorses;
	}

	public Vector<Point[]> getMismatchedRaces() {
		return mismatchedRaces;
	}

	public Vector<Point> getUnknownHorses() {
		return unknownHorses;
	}

	public Vector<Point> getUnknownRaces() {
		return unknownRaces;
	}

	public boolean isMismatchedSexes() {
		return mismatchedSexes;
	}

	public Vector<Point[]> getTooOldToMatter() {
		return tooOldToMatter;
	}

	public ResultType getResult() {
		if (containsError()) {
			return ResultType.ERROR;
		} else if (containsWarning()) {
			return ResultType.WARNING;
		} else {
			return ResultType.MATCH;
		}
	}
}
