# search

The directory Nim.docs includes a JavaDoc example for the game OneRowNim.

  <a href="https://loyola312fa18.github.io/search/Nim.docs/index.html"> JavaDoc served from GitHub Pages </a>
  
  <a href="http://www.cs.loyola.edu/~binkley/312/src/asn9.javadoc-example/Nim.docs"> JavaDoc served from Linux </a>


The directory testing includes a simple test case.  note that the script 
`test0` assumes that your code is in the directors src.

# OOA

Use Case Diagram name:
usecasediagram.jpg
 -- use cases are end-to-end actions the actor wants to perform. 'enter flag' does not qualify here :)


UML Diagram name:
asn9UML.pdf

-- this is not a bad first go.  you are missing some key classes and attributes.  have a deeper think!

-- no is-a ... has-a ??


Use Cases:
1.
name: Enter Query
Actor: Customer and CLI
Purpose: the customer puts in the keyword they want to find from a file
Main Success Scenario: the search engine finds a file with the keyword or
keywords in it and displays the names of the file to the user
-- 'a file' or 'all files' ?
Alternative Scenario: there is no file that has the entered keyword in it
Alternative Scenario: the user doesn't enter any keyword and the search engine
brings up all the files.
-- ??


2.
name: Search for keyword in files
Actor: Search Engine
-- i'm not sure i'd call this a use case as it is something the system does as
-- part of answering a user's query ... right?
Purpose: the Search Engine will take the keywords from the query and parse
through the contents of each file looking for the keywords and stashing the
words in a hashset and mapping them to a file that has the word or keywords in
it.
Main Success Scenario: the Search Engine searches through the files and finds
the key words from the user and spits out the names of the files and if
requested the contents of the files.
Alternative Scenario: the Search Engine does not find the keyword or words in
any file and doesn't return any filenames.
Alternative Scenario: the Search Engine has to deal with no keywords inputted
and must decide what to do.


# OOD
-- this is more an OOD2.  by skipping the OOD1, there is a lack of a tie to the OOA.  as a result the following will be hard to code from :(

### Inverted Index:
<<<<<<< HEAD
=======
```
>>>>>>> a38edfb9fc783173e5c14d5e01dc7c63f7619d8d
attributes:
index  -map<String, list<Files>>
stopList  -stopList()

services:
readFiles(Set<Files> file)  - scans through the documents
search(String)   - searches through the file looking for the files with the query in it
-- 'searches through the file'  ... what file?

```

### Query:
```
attributes:
searchPhrases  -set<String>

services:
initialize(set of strings)
combine  -removes the white spaces of the phrase or word sent in by the user

```
-- what services does class query provide to the other classes?


### StopList:
```
attributes:
stopList   - List<String>

services:
initialize()
isStopWord(String word) - if the word is a stop word return true, otherwise
                          return false
```

#### Document:
```
attributes:
filename   -String
wordList   -Set<String>

services:
scanFile(String filename)  - scans through the file and adds all the words in
                             the file into a set
containsWord(String word)  - scans the document and searches for the word in the                             set of the words in the document
-- i'm unclear how containsWord will be used?
```


 





