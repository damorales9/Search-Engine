import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Scanner;

/**
* This is my code! It’s goal is to act a search engine and take in
* a list of stopwords and a list of documents and then
* take in a word and output what documents that word is in
* CS 312 - Assignment 9
* @author Derek Morales git: damorales
* @version 1.0 9/10/2018 
*/
public class Document 
{
	
	protected String documentName;
	protected HashSet<String> documentWords;
	
	/**
	 * Creates an empty document
	 */
	public Document()
	{
		documentName = "";
		documentWords = new HashSet<String>();
	}
	
	/**
	 * Creates a Document object with a name and a Set of its words
	 * @param String name
	 * @throws IOException
	 */
	public Document(String name) throws IOException
	{
		documentWords = new HashSet<String>();
		documentName = name;
		Scanner scan = new Scanner(new File(name));
		while(scan.hasNext())
		{
			documentWords.add(scan.next());
		}
		scan.close();
	}
	
	/**
	 * prints out all the words in the document
	 */
	public void printText()
	{
		String asRead;
		try 
		{
			Scanner scanner = new Scanner(new File(documentName));
			scanner.useDelimiter("\\A");
			while(scanner.hasNext())
			{
				asRead = scanner.next();
				System.out.println(asRead);
			}
		}
		catch (Exception e)
		{
			
		}
	}
	
	/**
	 * Returns an iterator to iterate over a Document
	 * @return Iterator<String>
	 */
	public Iterator<String> iterator()
	{
		return new Scanner(documentName).useDelimiter("[ˆa-zA-Z]+");
	}


}
