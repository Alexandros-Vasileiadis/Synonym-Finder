/**
 * This package provides classes for computing the closeness of words based on semantic similarity.
 * The learning/training is done by processing a large corpus of English text (i.e. without the use of dictionaries),
 * in order to compute the semantic similarity of any pair of words. The semantic similarity between
 * any two words is determined by measuring how closely related their meanings are.
 *
 * The package includes the following classes:
 * - {@link FindSynonym.GetSentenceLists}: Creates a list with sentences from a given text.
 * - {@link FindSynonym.GetSentenceListsFromFiles}: Creates a list with sentences from texts in files.
 * - {@link FindSynonym.BuildSemanticDescriptors}: Builds semantic descriptors based on co-occurrence of words in sentences.
 * - {@link FindSynonym.MostSimilarWord}: Finds the most similar word to a given word from a list of choices.
 * - {@link FindSynonym.RunSimilarityTest}: Runs a similarity test to find the percentage of correct words.
 */
package FindSynonym;
