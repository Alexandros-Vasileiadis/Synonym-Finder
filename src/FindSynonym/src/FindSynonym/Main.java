package FindSynonym;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>This class is responsible for running a similarity test between words.</p>
 *
 * To run this program, you just need to run Main class. You should have files: "pg2600.txt", "pg7178.txt",
 * "brown-train-sentences.txt" and "questions.txt". This program will take the first 3 files to create a hashmap
 * for each word. After that, it will take questions from the last file, which is provided as a first parameter
 * of the {@link RunSimilarityTest} constructor, and answer each one in the self-created file, "answers.txt".
 *
 * @see GetSentenceLists
 * @see GetSentenceListsFromFiles
 * @see BuildSemanticDescriptors
 * @see MostSimilarWord
 * @see RunSimilarityTest
 *
 */

public class Main {
	
	/**
	*The main method runs the similarity test by creating a list of filenames to teach the program, and then running a similarity test.
	*
	*@param args command line arguments
	*@throws FileNotFoundException if the file specified is not found
	*/
	public static void main(String[] args) throws FileNotFoundException {
		// files to "teach" this code
		List<String> filenames = new ArrayList<>();
		filenames.add("pg2600.txt");
		filenames.add("pg7178.txt");
		filenames.add("brown-train-sentences.txt");

		//running a similarity test
		BuildSemanticDescriptors tt = new BuildSemanticDescriptors(new GetSentenceListsFromFiles(filenames));
		new RunSimilarityTest("questions.txt", tt.getDesc());
	}
}
