import java.io.*;
import java.util.*;
import java.lang.*;

public class CLI {
    
    private static final String DEBUG_COMMAND = "@@debug";

    /**
     * Main function which takes in user input and processes it
     * @param args - arguments given by the user
     * Expected complexity:  linear
     * @throws IOException 
     */
    public static void main (String [] args) throws IOException
    {
        //Checking to see if user actually put in documents
        if(args.length < 2 || ("-d".equals(args[0]) && args.length < 3))
        {
            System.out.println("Usage: java CLI [-d] stoplist documents");
            System.exit(-1);                  
        }  
        //Boolean to see if we have the -d flag, if we do start at index 2, otherwise index 1
        Boolean includeDocs = "-d".equals(args[0]);
        int start = includeDocs ? 2 : 1;
        InvertedIndex ii = new InvertedIndex(args[start - 1]);
        
        long startTime = System.currentTimeMillis();
        System.out.println("first argument" + args[0] + "Second argument" + args[1] );
        
        for(int i = start; i < args.length; i ++)
        {
            Document d = new Document(args[i]);
            ii.addDocument(d);
        }
        long buildStop = System.currentTimeMillis();
        long buildTime = buildStop - startTime;
        System.out.println("@@build time " + buildTime + "ms");
        try
        {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            
            for(String line = br.readLine(); line!=null; line = br.readLine())
            {
                if(DEBUG_COMMAND.equals(line))
                {
                    ii.dumpIndex();
                }
                else
                {
                		//line = line.replaceAll("[ˆa-zA-Z]", "");
                    HashSet<Document> docs = ii.multiWordQuery(line);
                    System.out.println("--- found in " + (docs == null ? 0 : docs.size()) +"documents");
                    
                    if(docs != null)
                    {
                        for(Document d : docs)
                        {
                            System.out.println(d.documentName + ", ");
                        }
                        System.out.println(" ");
                    }
                    if(includeDocs && docs != null)
                    {
                        for(Document d: docs)
                        {
                            d.printText();
                        }
                    }
                
                 }
               }   
       br.close();      
        }
    catch(Exception ex)
    {
        System.out.println("ah sorry but" + ex);
        ex.printStackTrace();
    }
    long stopTime = System.currentTimeMillis();
    long queryTime = stopTime - buildStop;
    System.out.println("@@query time" + queryTime + "ms"); 
    
	

    }
}
