package FindSynonym;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * hw4.GetSentenceLists creates a list with sentences from a given String text
 *
 * @version 1.0
 * @since 17/04/23
 */

public class GetSentenceLists {

	static List<GetWordsLists> sentences = new ArrayList<>();

	/**
	 * Inner class GetWordsLists which creates the list which contain words from the
	 * given sentence
	 *
	 */
	public class GetWordsLists {

		List<String> words;

		/**
		 * Constructor for the GetWordsLists inner class
		 * @param sentence A string sentence
		 */
		private GetWordsLists(String sentence) {
			words = getTokens(sentence, "+//(//)//$//%//*//_//://;// //'//-//--//\"");
		}

		public String toString() {
			StringBuilder temp = new StringBuilder();

			temp.append("{");
			for (String word : words) {
				temp.append("'");
				temp.append(word).append("': ").append(words.get(words.indexOf(word)));
				temp.append(", ");
			}
			temp.append("}");

			return temp.toString();
		}

		List<String> getWords(){
			return words;
		}
	}

	/**
	 * Constructor which creates a List from sentences from the given text
	 * @param text The text with sentences
	 */
	public GetSentenceLists(String text) {
		List<String> temp = getTokens(text, ".//?//!");

		for (String sentence : temp) {
			sentences.add(new GetWordsLists(sentence));
		}
	}

	/**
	 * Returns a list with substrings which are created by dividing the given string with the delimiter
	 * @param str The string that has to be divided into substrings using the given delimiter
	 * @param delimiter The breaking point for the new substring
	 * @return The list with substrings
	 */
	private List<String> getTokens(String str, String delimiter) {
		List<String> tokens = new ArrayList<>();
		StringTokenizer tokenizer = new StringTokenizer(str, delimiter);
		while (tokenizer.hasMoreElements()) {
			tokens.add(tokenizer.nextToken().toLowerCase());
		}
		return tokens;
	}

	/**
	 * @return The string representation of the list
	 */
	public String toString() {
		StringBuilder temp = new StringBuilder();
		for (GetWordsLists sentence : sentences) {
			temp.append(sentence.getWords().toString());
			temp.append("\n\n");
		}
		return temp.toString();
	}
}
