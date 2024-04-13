package FindSynonym;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

/**
 * This class runs a similarity test using semantic descriptors to find the most similar word to a given question word
 * from a list of answer choices, and calculates the percentage of correct answers. It reads the questions and answers
 * from a text file and outputs the chosen answers and percentage of correct answers to another file.
 *
 * @version 1.0
 * @since 19-04-2023
 */
public class RunSimilarityTest {

    /**
     * Creates a new RunSimilarityTest object with the specified file name and semantic descriptors.
     *
     * @see MostSimilarWord
     * @param filename the name of the file to read word pairs from
     * @param semantic_descriptors a map of word vectors used to calculate similarity
     */
    public RunSimilarityTest(String filename, HashMap<String, BuildSemanticDescriptors.InnerSemDesc> semantic_descriptors) {

        try {
            Scanner scanner = new Scanner(new File(filename));
            PrintWriter writer = new PrintWriter("answers.txt");
            int questionSum = 0, rightAnswersSum = 0, cnt = 1;

            // Read each line of the input file and process the question and answer choices
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] words = line.split(" ");

                String questionWord = words[0];
                String answerWord = words[1];
                ArrayList<String> choices = new ArrayList<>();

                for (int i = 2; i < words.length && !words[i].equals(""); i++)
                    choices.add(words[i]);

                MostSimilarWord word = new MostSimilarWord(questionWord, choices, semantic_descriptors);
                String answer = word.getSynonym();
                writer.println(cnt++ + ") Word to test: " + questionWord + "\nResult: " + answer + "\n");

                rightAnswersSum += answer.equals(answerWord) ? 1 : 0;
                questionSum++;
            }

            writer.printf("\nPercentage of right answers is %.2f%n", (double) rightAnswersSum / questionSum * 100);

            writer.close();
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + filename);
        }
    }
}

