package FindSynonym;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Scanner;

/**
 * hw4.GetSentenceListsFromFiles creates a list with sentences from texts in the files of the given filenames
 *
 * @version 1.0
 * @since 17/04/23
 */

public class GetSentenceListsFromFiles {

	private GetSentenceLists t;

	/**
	 * The constructor creates the lists with sentences from the given files
	 * @param filenames Filenames of the files with the text
	 */
	public GetSentenceListsFromFiles(List<String> filenames) {
		for (String filename : filenames) {
			t = new GetSentenceLists(getTextFromFile(filename));
		}
	}

	/**
	 * This method returns the text that is the file with the given filename
	 * @param filename Filenames of the files with the text
	 * @return The text from the given filename
	 */
	private static String getTextFromFile(String filename) {
		StringBuilder temp = new StringBuilder();
		File myObj = new File(filename);
		Scanner scan;
		try {
			scan = new Scanner(myObj);
			while (scan.hasNext()) {
				temp.append(scan.nextLine()).append(" ");
			}
			scan.close();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		return temp.toString();
	}

	/**
	 * Returns the list with sentences
	 * @return The list with sentences
	 */
	public GetSentenceLists getGetSentenceLists() {
		return t;
	}

	/**
	 * @return The string representation of the list
	 */
	public String toString() {
		return t.toString();
	}
}
