package general;

public abstract class ShortTimeMemory {
	private static String selectedRace = "";
	private static boolean selectedMale = true;
	private static boolean selectedFemale = true;
	private static String searchedString = "";
	private static String nameFavListShown = "Alle";

	public static String getSelectedRace() {
		return selectedRace;
	}

	public static void setSelectedRace(String selectedRace) {
		ShortTimeMemory.selectedRace = selectedRace;
	}

	public static boolean isSelectedMale() {
		return selectedMale;
	}

	public static void setSelectedMale(boolean selectedHengst) {
		ShortTimeMemory.selectedMale = selectedHengst;
	}

	public static boolean isSelectedFemale() {
		return selectedFemale;
	}

	public static void setSelectedFemale(boolean selectedStute) {
		ShortTimeMemory.selectedFemale = selectedStute;
	}

	public static String getSearchedString() {
		return searchedString;
	}

	public static void setSearchedString(String searchedString) {
		ShortTimeMemory.searchedString = searchedString;
	}

	public static String getNameFavListShown() {
		return nameFavListShown;
	}

	public static void setNameFavListShown(String name) {
		ShortTimeMemory.nameFavListShown = name;
	}
}
