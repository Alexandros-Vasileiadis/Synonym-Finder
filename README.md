# FindSynonym

FindSynonym is a Java program designed to find the most similar word to a given question word from a list of answer 
choices. It utilizes semantic descriptors and cosine similarity scores to determine the most appropriate synonym.

## How to Use

To run the program, follow these steps:

1. Ensure you have the required files: "pg2600.txt", "pg7178.txt", "brown-train-sentences.txt", and "questions.txt".
2. Compile and run the `Main` class.

```bash
javac Main.java
java Main
```

3. The program will read the question-answer pairs from "questions.txt", find the most similar word for each question 
4. word, and write the results to "answers.txt".

## File Description

- `Main.java`: The main class responsible for running the similarity test.
- `BuildSemanticDescriptors.java`: Creates semantic descriptors for words based on their co-occurrence in sentences.
- `GetSentenceLists.java`: Creates lists of sentences from given text.
- `GetSentenceListsFromFiles.java`: Creates lists of sentences from text files.
- `MostSimilarWord.java`: Finds the most similar word to a given word from a list of choices.
- `RunSimilarityTest.java`: Runs a similarity test using semantic descriptors to find the most similar word to a given question word from a list of answer choices.

