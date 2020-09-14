package textgen;

import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Random;

/** 
 * An implementation of the MTG interface that uses a list of lists.
 * @author UC San Diego Intermediate Programming MOOC team 
 */
public class MarkovTextGeneratorLoL implements MarkovTextGenerator {

	// The list of words with their next words
	private List<ListNode> wordList; 
	
	// The list of words in string form
	private List<String> wordListString;
	
	// The starting "word"
	private String starter;
	
	// The random number generator
	private Random rnGenerator;
	
	private boolean isTrained;
	
	public MarkovTextGeneratorLoL(Random generator)
	{
		wordList = new LinkedList<ListNode>();
		starter = "";
		rnGenerator = generator;
		wordListString = new LinkedList<String>();
		isTrained = false;
	}
	
	
	/** Train the generator by adding the sourceText */
	@Override
	public void train(String sourceText)
	{
		// TODO: Implement this method
		if (sourceText.equals("")) {
			System.out.println("Text cannot be an empty string.");
		} else {
			String[] sourceList = sourceText.split("[ ]+");
			
			// Set starter to be the first word of sourceText
			starter = sourceList[0];
			
			// Add the prevNode to wordList
			// Add the prevWord to wordListString
			String prevWord = starter;
			ListNode prevNode = new ListNode(prevWord);
			wordList.add(prevNode);
			wordListString.add(prevWord);
			
			for (int i = 1; i < sourceList.length; i++) {
				if (wordListString.contains(prevWord)) {
					int index = wordListString.indexOf(prevWord);
					wordList.get(index).addNextWord(sourceList[i]);
				} else {
					prevNode.addNextWord(sourceList[i]);
					wordList.add(prevNode);
					wordListString.add(prevWord);
				}
				
				// set prevWord to the current word
				prevWord = sourceList[i];
				prevNode = new ListNode(prevWord);
			}
			
			// Add starter to be a next word for the last word in the source text
			String lastWord = sourceList[sourceList.length-1];
			ListNode lastNode = new ListNode(lastWord);
			lastNode.addNextWord(starter);
			wordList.add(lastNode);
			wordListString.add(lastWord);
			
			isTrained = true;
		}
	}


	/** 
	 * Generate the number of words requested.
	 */
	@Override
	public String generateText(int numWords) {
	    // TODO: Implement this method
		if (isTrained == false) {
			return "";
		}
		
		String currWord = starter;
		String output = "";
		output += currWord + " ";
		int wordsGenerated = 1;
		
		while (wordsGenerated < numWords) {
			if (wordListString.contains(currWord)) {
				int index = wordListString.indexOf(currWord);
				ListNode currNode = wordList.get(index);
				String randomWord = currNode.getRandomNextWord(rnGenerator);
				output += randomWord + " ";
				currWord = randomWord;
				wordsGenerated++;
				//System.out.println("Words generated so far: " + wordsGenerated);
			}
		}
		return output;
	}
	
	
	// Can be helpful for debugging
	@Override
	public String toString()
	{	

		String toReturn = "";
		for (ListNode n : wordList)
		{
			toReturn += n.toString();
		}
	
		return toReturn;
	}
	
	/** Retrain the generator from scratch on the source text */
	@Override
	public void retrain(String sourceText)
	{
		// TODO: Implement this method.
		// Clear the instance fields
		if (isTrained == false) {
			System.out.println("Use the train method first");
		} else {
			wordList.clear();
			wordListString.clear();
			starter = "";
			
			if (sourceText.equals("")) {
				System.out.println("Text string cannot be empty");
			} else {
				String[] sourceList = sourceText.split("[ ]+");
				
				// Set starter to be the first word of sourceText
				starter = sourceList[0];
						
				// Add the prevNode to wordList
				// Add the prevWord to wordListString
				String prevWord = starter;
				ListNode prevNode = new ListNode(prevWord);
				wordList.add(prevNode);
				wordListString.add(prevWord);
						
				for (int i = 1; i < sourceList.length; i++) {
					if (wordListString.contains(prevWord)) {
						int index = wordListString.indexOf(prevWord);
						wordList.get(index).addNextWord(sourceList[i]);
					} else {
						prevNode.addNextWord(sourceList[i]);
						wordList.add(prevNode);
						wordListString.add(prevWord);
					}
							
					// set prevWord to the current word
					prevWord = sourceList[i];
					prevNode = new ListNode(prevWord);
				}
						
					// Add starter to be a next word for the last word in the source text
					String lastWord = sourceList[sourceList.length-1];
					ListNode lastNode = new ListNode(lastWord);
					lastNode.addNextWord(starter);
					wordList.add(lastNode);
					wordListString.add(lastWord);
			}
		}
	}
	
	// TODO: Add any private helper methods you need here.
	
	
	/**
	 * This is a minimal set of tests.  Note that it can be difficult
	 * to test methods/classes with randomized behavior.   
	 * @param args
	 */
	public static void main(String[] args)
	{
		// feed the generator a fixed random value for repeatable behavior
		MarkovTextGeneratorLoL gen = new MarkovTextGeneratorLoL(new Random(42));
		gen.train("hi there hi Leo");
		System.out.println(gen);
		System.out.println(gen.generateText(20));
		
		String input = "I love cats. I hate dogs. I I I I I I I I I I I I I I I I love cats. I I I I I I I I I I I I I I I I hate dogs. I I I I I I I I I like books. I love love. I am a text generator. I love cats. I love cats. I love cats. I love love love socks.";
        gen.retrain(input);
        System.out.println(gen);
        System.out.println(gen.generateText(50));
		/*
		String textString = "Hello.  Hello there.  This is a test.  Hello there.  Hello Bob.  Test again.";
		System.out.println(textString);
		gen.train(textString);
		System.out.println(gen);
		System.out.println(gen.generateText(3));
		String textString2 = "I love cats. I hate dogs. I I I I I I I I I I I I I I I I love cats. I I I I I I I I I I I I I I I I hate dogs. I I I I I I I I I like books. I love love. I am a text generator. I love cats. I love cats. I love cats. I love love love socks.";
		System.out.println(textString2);
		gen.retrain(textString2);
		System.out.println(gen);
		System.out.println(gen.generateText(20));
		*/
	}

}

/** Links a word to the next words in the list 
 * You should use this class in your implementation. */
class ListNode
{
    // The word that is linking to the next words
	private String word;
	
	// The next words that could follow it
	private List<String> nextWords;
	
	ListNode(String word)
	{
		this.word = word;
		nextWords = new LinkedList<String>();
	}
	
	public String getWord()
	{
		return word;
	}

	public void addNextWord(String nextWord)
	{
		nextWords.add(nextWord);
	}
	
	public String getRandomNextWord(Random generator)
	{
		// TODO: Implement this method
	    // The random number generator should be passed from 
	    // the MarkovTextGeneratorLoL class
		int listLength = nextWords.size();
		int rn = generator.nextInt(listLength);
		String randomWord = nextWords.get(rn);
	    return randomWord;
	}

	public String toString()
	{
		String toReturn = word + ": ";
		for (String s : nextWords) {
			toReturn += s + "->";
		}
		toReturn += "\n";
		return toReturn;
	}
	
}


