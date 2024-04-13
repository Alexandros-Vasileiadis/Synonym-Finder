package FindSynonym;

import java.util.HashMap;
import FindSynonym.GetSentenceLists.GetWordsLists;


/**
 * hw4.BuildSemanticDescriptors creates the semantic descriptors for the words from given lists with sentences
 *
 * @version 1.0
 * @since 17/04/23
 */

public class BuildSemanticDescriptors {

	private static HashMap<String, InnerSemDesc> desc = new HashMap<>();

	/**
	 * The inner class InnerSemDesc creates a hashmap from a given sentence, which stores the amount of
	 * times each word has appeared with other words the sentences
	 *
	 */
	protected class InnerSemDesc {

		private HashMap<String, Integer> innerDesc = new HashMap<>();

		/**
		 * The constructor gets a String word and uses the @link putValue method to place it in the hashmap
		 * @param word Gets the word that has to be put in the hashmap
		 */
		private InnerSemDesc(String word) {
			putValue(word);
		}

		public InnerSemDesc(InnerSemDesc original) {
			innerDesc = original == null ? new HashMap<>() : new HashMap<>(original.innerDesc);
		}

		/**
		 * The constructor gets a String word and according to the information in the hashmap, either increases it's
		 * appearing rate by one, or appends it to the table initializing the value of the appearing rate with one
		 * @param word Gets the word that has to be put in the hashmap
		 *
		 */
		private InnerSemDesc putValue(String word) {
			innerDesc.compute(word, (k, v) -> (v == null) ? 1 : v + 1);
			return this;
		}


		public HashMap<String, Integer> getInnerDesc() {
			return new HashMap<>(innerDesc);
		}

		/**
		 * @return Returns the String representation of the hashmap
		 */
		public String toString() {
			StringBuilder temp = new StringBuilder();

			temp.append("{");
			for (String key : innerDesc.keySet()) {
				temp.append("'");
				temp.append(key).append("':").append(innerDesc.get(key));

				temp.append(", ");
			}
			temp.delete(temp.length()-2, temp.length());
			temp.append("},");

			return temp.toString();
		}
	}

	public HashMap<String, InnerSemDesc> getDesc() {
		return new HashMap<>(desc);
	}

	/**
	 * The constructor computes the semantic descriptors based on the co-occurrence of the words in the sentence.
	 * It iterates through the list, and for every word it increases the appearing rate of other word that appear
	 * in the same sentence
	 * @param text The list from sentences
	 */
	public BuildSemanticDescriptors(GetSentenceLists text) {
		for (GetWordsLists sentence : GetSentenceLists.sentences) {
			for (int i = 0; i < sentence.words.size(); i++) {
				String word = sentence.words.get(i);
				boolean flag = true;
				for (int k = 0; k < sentence.words.size(); k++) {
					if (word.equals(sentence.words.get(k)) && i > k) {
						flag = false;
						break;
					}
				}
				if (flag)
					for (String word2 : sentence.words) {
						if (!word.equals(word2))
							desc.compute(word, (k, v) -> (v == null) ? new InnerSemDesc(word2) : v.putValue(word2));
					}
			}
		}
	}

	/**
	 * The constructor computes the semantic descriptors based on the co-occurrence of the words in the sentence.
	 * @param text The list with the sentences from different files
	 */
	public BuildSemanticDescriptors(GetSentenceListsFromFiles text) {
		this(text.getGetSentenceLists());
	}

	/**
	 * @return Returns the String representation of the semantic descriptors
	 */
	public String toString() {
		StringBuilder temp = new StringBuilder();

		temp.append("{");
		for (String t : desc.keySet()) {

			temp.append("'");
			temp.append(t).append("': ").append(desc.get(t).toString());
			temp.append("\n\n");
		}
		temp.delete(temp.length()-3, temp.length());
		temp.append("\n\n");
		temp.append("}");

		return temp.toString();
	}
}
