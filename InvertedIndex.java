import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
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

public class InvertedIndex 
{
	protected HashMap<String, HashSet<Document>> index;
	protected HashSet<String> files;
	protected StopList stopWords;
	
	/**
	 * Creates the InvertedIndex with a name of a stoplist file
	 * @param String stopList
	 * @throws FileNotFoundException
	 */
	public InvertedIndex(String stopList) throws FileNotFoundException
	{
		index = new HashMap<String, HashSet<Document>>();
		files = new HashSet<String>();
		stopWords = new StopList(stopList);
	}
	
	/**
	 * Add a document to the InvertedIndex
	 * linear O(n)
	 * @param Document
	 * @throws IOException
	 */
	public void addDocument(Document d) throws IOException
	{
		for(String word : d.documentWords)
		{
			if(index.get(word) == null)
			{
				index.put(word, new HashSet<Document>());
			}
			index.get(word).add(d);
		}
	}
	
	/**
	 * Search a query to find what documents the query is in
	 * @param String line
	 * @return HashSet<Document> 
	 * @throws FileNotFoundException
	 */
	public HashSet<Document> multiWordQuery(String line) throws FileNotFoundException
	{
		HashSet<Document> temp = null;
		HashSet<String> wordsInLine = new HashSet<String>();
		Scanner scan = new Scanner(line);
		scan.useDelimiter(" ");
		while(scan.hasNext())
		{
			String word = scan.next();
			word = word.replaceAll("\\P{L}", "");
			if(!stopWords.isStopWord(word))
			{
				wordsInLine.add(word);
			}
		}
		
		for (String wordInLine : wordsInLine)
		{
			if(temp == null) 
			{
				temp = index.get(wordInLine);
			}
			else 
			{
				temp = combineSets(index.get(wordInLine), temp);
			}
		}
		return temp;
	}
	
	/**
	 * Combines two sets of documents for multiple word queries
	 * @param HashSet<Document> setA
	 * @param HashSet<Document> setB
	 * @return HashSet<Document> finalSet
	 */
	public  HashSet<Document> combineSets(HashSet<Document> setA, HashSet<Document> setB )
	{
		HashSet<Document> finalSet = new HashSet<Document>();
		if (setA == null)
		{
			setA = new HashSet<Document>();
		}
		for (Document d : setB)
		{
			if (setA.contains(d))
			{
				finalSet.add(d);
			}
		}
		return finalSet;	
	}
	
	/**
	 * Prints the entire index
	 */
	public void dumpIndex()
	{
		for (String word : index.keySet())
		{
			System.out.println(index.get(word).toString());
		}
	}
	
}
