package FindSynonym;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import FindSynonym.BuildSemanticDescriptors.InnerSemDesc;

/**
 * This class represents a word and its most similar word in a given set of choices based on their
 * cosine similarity score. It uses a HashMap of word embeddings to compute the cosine similarity
 * between the word and each of the choices.
 *
 * @version 1.0
 * @since 19-04-2023
 */
public class MostSimilarWord {

    /**
     * The list of cosine similarities between the input word and the words in the given set of choices.
     */
    private final LinkedList<Double> similarity;

    /**
     * The list of choices from which the most similar word is to be returned.
     */
    private final ArrayList<String> choices;

    /**
     * Constructs a new MostSimilarWord object for the given word and set of choices.
     *
     * @param word The word to find the most similar word for.
     * @param choices A list of candidate words to compare with the input word.
     * @param semantic_descriptors A HashMap of word embeddings representing the semantic descriptors of words.
     */
    public MostSimilarWord(String word, ArrayList<String> choices,
                           HashMap<String, InnerSemDesc> semantic_descriptors){

        similarity = new LinkedList<>();
        this.choices = new ArrayList<>(choices);
        InnerSemDesc wordCopy = semantic_descriptors.get(word);

        for(String choice : choices) {
            InnerSemDesc choiceCopy = semantic_descriptors.get(choice);

            if (wordCopy != null && choiceCopy != null)
                similarity.add(cosineSimilarity(wordCopy.getInnerDesc(), choiceCopy.getInnerDesc()));
            else
                similarity.add(-1.0);
        }
    }

    /**
     * Computes the cosine similarity between two vectors.
     *
     * @param vec1 A vector represented as a HashMap of word frequencies.
     * @param vec2 A vector represented as a HashMap of word frequencies.
     * @return The cosine similarity between the two vectors.
     */
    public static double cosineSimilarity(HashMap<String, Integer> vec1, HashMap<String, Integer> vec2) {
        double dotProduct = 0.0;

        for (String x : vec1.keySet())
            if (vec2.containsKey(x))
                dotProduct += vec1.get(x) * vec2.get(x);

        return dotProduct / (norm(vec1) * norm(vec2));
    }

    /**
     * Computes the Euclidean norm of a vector.
     *
     * @param vec A vector represented as a HashMap of word frequencies.
     * @return The Euclidean norm of the vector.
     */
    private static double norm(HashMap<String, Integer> vec) {
        double sum_of_squares = 0.0;
        for (int x : vec.values())
            sum_of_squares += x * x;

        return Math.sqrt(sum_of_squares);
    }

    /**
     * Returns the most similar word from the set of choices, based on the similarity scores calculated
     * during construction.
     *
     * @return the most similar word from the set of choices.
     */
    public String getSynonym(){
        int synonymInd = similarity.indexOf(similarity.getLast());
        double maxSim = similarity.get(synonymInd);

        // Find the index of the choice with the highest similarity score
        for(int i = synonymInd - 1 ; i >= 0 ; i--){
            if(similarity.get(i) >= maxSim){
                maxSim = similarity.get(i);
                synonymInd = i;
            }
        }

        return choices.get(synonymInd);
    }
}

