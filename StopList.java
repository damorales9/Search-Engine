import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashSet;
import java.util.Scanner;

/**
* This is my code! It’s goal is to act a search engine and take in
* a list of stopwords and a list of documents and then
* take in a word and output what documents that word is in
* CS 312 - Assignment 9
* @author Derek Morales git: damorales
* @version 1.0 9/10/2018 
*/
public class StopList {
	
	protected HashSet<String> stopWords;
	
	/**
	 * Creates a Set of the stop words
	 * @param String filename
	 * @throws FileNotFoundException
	 */
	public StopList(String filename) throws FileNotFoundException
	{
		stopWords = new HashSet<String>();
		Scanner scan = new Scanner(new File(filename));
		scan.useDelimiter(" ");
		while(scan.hasNext())
		{
			stopWords.add(scan.next());
		}
		scan.close();
	}
	
	/**
	 * Check a word to see if it is a stopword
	 * @param String word
	 * @return boolean
	 */
	public boolean isStopWord(String word)
	{
		return stopWords.contains(word);
	}

}
