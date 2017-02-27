package tournamentCalculator;

public class Wettbewerbspferd {
	public double[] disziplinenwerte; // in %
	// Dressur, Springen, Western, Rennen, Fahren
	public double[] grundlagenwerte; // in %
	// Grundgangarten, Wendigkeit, Gelassenheit, Tempo, Kondition
	public String[] interieurwerte; // als Wörter
	// Temperament, Gelehrigkeit, Leistungsbereitschaft, Aufmerksamkeit, Gutmütigkeit
	// Nervenstärke, Intelligenz, Siegeswille, Furchtlosigkeit, Sozialverhalten
	public int anzahlPferdewerte;
	public int anzahlPferdezahlen;

	public Wettbewerbspferd() {
		disziplinenwerte = new double[5];
		grundlagenwerte = new double[5];
		interieurwerte = new String[10];

		anzahlPferdezahlen = disziplinenwerte.length + grundlagenwerte.length;
		anzahlPferdewerte = anzahlPferdezahlen + interieurwerte.length;
	}

	public void weiseWertezu(double[] diswerte, double[] grundlwerte, String[] intwerte) {
		disziplinenwerte = diswerte;
		grundlagenwerte = grundlwerte;
		interieurwerte = intwerte;
	}
}