package tournamentCalculator;

public class Wettbewerbsrechner {
	public Wettbewerbspferd p; // Pferd, das betrachtet wird
	public double[] interieurzahlen; // für die Zahlenentsprechungen des Interieurs
	public double[] alleWerte;
	public Wettbewerbsdatenbank db; // Relevante Eigenschaften für Wettbewerbe
	public double durchschnitt; // Durchschnittlicher Wert der Grundlagen- und Disziplinenwerte
	public double punktewertungExzellent; // Anzahl der Punkte, die für das Prädikat "Exzellent" im Interieur vergeben werden sollen
	public double[][] wettbewerbschancen; // Array, das die Prozentwerte des Maximalwerts für eine Disziplin speichert
	public double[] maxima; // maximal erreichbare Werte eines perfekten Pferdes in allen Disziplinen, Reihenfolge EALMS
	public int nachskalierungInterieur; // Gewichtung des Interieurs im Vergleich zu Disziplinen/ Grundgangarten
	public int[] grenzen; // Wertgrenzen für Wettbewerbszulassung
	public int anzahlWettbewerbsniveaus;

	public Wettbewerbsrechner(Wettbewerbspferd pfe) {
		p = pfe;
		punktewertungExzellent = 1.67;
		db = new Wettbewerbsdatenbank();
		interpretiereInterieur(p.interieurwerte);
		alleWerte = new double[p.anzahlPferdewerte];
		schreibeWerteInEinArray();
		nachskalierungInterieur = 2;
		skaliereInterieur();
		anzahlWettbewerbsniveaus = 5;
		wettbewerbschancen = new double[db.anzahlWettbewerbsdisziplinen][anzahlWettbewerbsniveaus];

		maxima = new double[anzahlWettbewerbsniveaus];
		for (int i = 0; i < maxima.length; i++) {
			maxima[i] = 4 * 100 + 3 * 100 * punktewertungExzellent / nachskalierungInterieur;
		}
		grenzen = new int[anzahlWettbewerbsniveaus];
		grenzen[0] = 19;
		grenzen[1] = 39;
		grenzen[2] = 59;
		grenzen[3] = 79;
		grenzen[4] = 100;

		for (int i = 0; i < anzahlWettbewerbsniveaus; i++) {
			maxima[i] = maxima[i] + grenzen[i];
		}

		bezieheZulassungenEin();

		for (int i = 0; i < wettbewerbschancen.length; i++) {
			for (int j = 0; j < wettbewerbschancen[0].length; j++) {
				if (wettbewerbschancen[i][j] != -100) {
					wettbewerbschancen[i][j] = berechneDisziplinenchance(db.disziplinen[i], maxima[j]);
				}
			}
		} // end of for

	}

	public void interpretiereInterieur(String[] interieurwerte) {
		interieurzahlen = new double[interieurwerte.length];
		for (int i = 0; i < interieurwerte.length; i++) {
			if (interieurwerte[i] == "Exzellent") {
				interieurzahlen[i] = punktewertungExzellent;
			} else if (interieurwerte[i] == "Gut") {
				interieurzahlen[i] = 1.33;
			} else if (interieurwerte[i] == "In Ordnung") {
				interieurzahlen[i] = 1.00;
			} else if (interieurwerte[i] == "Schlecht") {
				interieurzahlen[i] = 0.67;
			} else {
				interieurzahlen[i] = 0.33;
			}
		}
	}

	public void skaliereInterieur() {
		durchschnitt = 0;
		for (int i = 0; i < p.anzahlPferdezahlen; i++) {
			durchschnitt = durchschnitt + alleWerte[i];
		}
		durchschnitt = durchschnitt / p.anzahlPferdezahlen;
		for (int i = p.anzahlPferdezahlen; i < alleWerte.length; i++) {
			alleWerte[i] = alleWerte[i] * durchschnitt / nachskalierungInterieur;
		}
	}

	public void schreibeWerteInEinArray() {
		for (int i = 0; i < alleWerte.length; i++) {
			if (i < p.disziplinenwerte.length) {
				alleWerte[i] = p.disziplinenwerte[i];
			} else if (i < p.disziplinenwerte.length + p.grundlagenwerte.length) {
				alleWerte[i] = p.grundlagenwerte[i - p.disziplinenwerte.length];
			} else {
				alleWerte[i] = interieurzahlen[i - p.disziplinenwerte.length - p.grundlagenwerte.length];
			}
		}
	}

	public double berechneDisziplinenchance(boolean[] wichtigeWettbewerbe, double maximum) {
		double chance = 0;
		for (int i = 0; i < wichtigeWettbewerbe.length; i++) {
			if (wichtigeWettbewerbe[i] == true) {
				chance = chance + alleWerte[i];
			}
		}
		chance = chance / maximum * 100;
		return chance;
	}

	public void bezieheZulassungenEin() {
		for (int disziplin = 0; disziplin < wettbewerbschancen.length; disziplin++) {
			boolean niveauGefunden = false;
			for (int niveau = wettbewerbschancen[0].length - 1; niveau >= 0; niveau--) {
				if (niveauGefunden == false) {
					niveauGefunden = true;
					wettbewerbschancen[disziplin][niveau] = 0;
					int überprüfterWert = 0;
					while (wettbewerbschancen[disziplin][niveau] != -100 && überprüfterWert < p.anzahlPferdezahlen && niveau > 0) {
						if (db.disziplinen[disziplin][überprüfterWert] == true && alleWerte[überprüfterWert] <= grenzen[niveau - 1]) {
							wettbewerbschancen[disziplin][niveau] = -100;
							niveauGefunden = false;
						}
						überprüfterWert++;
					}
				} else {
					wettbewerbschancen[disziplin][niveau] = -100;
				}

			}
		}
	}

	public static void main(String[] args) {
		double[] disziplinen = new double[5];
		double[] grundlagen = new double[5];
		String[] interieur = new String[10];

		disziplinen[0] = 5;
		disziplinen[1] = 28;
		disziplinen[2] = 29;
		disziplinen[3] = 24;
		disziplinen[4] = 23;

		grundlagen[0] = 24;
		grundlagen[1] = 25;
		grundlagen[2] = 23;
		grundlagen[3] = 29;
		grundlagen[4] = 25;

		interieur[0] = "In Ordnung";
		interieur[1] = "Gut";
		interieur[2] = "Exzellent";
		interieur[3] = "Exzellent";
		interieur[4] = "Gut";
		interieur[5] = "Exzellent";
		interieur[6] = "Gut";
		interieur[7] = "Gut";
		interieur[8] = "Gut";
		interieur[9] = "Gut";

		Wettbewerbspferd pferd = new Wettbewerbspferd();
		pferd.weiseWertezu(disziplinen, grundlagen, interieur);
		Wettbewerbsrechner w = new Wettbewerbsrechner(pferd);
		for (int i = 0; i < w.wettbewerbschancen.length; i++) {
			System.out.println(w.wettbewerbschancen[i][0]);
		}
		for (int i = 0; i < w.wettbewerbschancen.length; i++) {
			System.out.println(w.wettbewerbschancen[i][1]);
		}
		for (int i = 0; i < w.alleWerte.length; i++) {
			System.out.println(w.alleWerte[i]);
		}
	}
}