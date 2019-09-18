To run application write a command with example directory path:

sbt "run src/test/resources"

Now you will be able to search for the given words

to quit searching type :quit

--------------------------------

Solution is searching for unique input words matching unique words from the files.

Rank is a ratio of the number of matching unique input words to all unique input words.

I consider all punctuation marks as a part of the words same as letters.

--------------------------------

TODOs:

- Take care of all specific symbols in the text like commas, dots etc

- Load documents from files chunk by chunk

- Replace in memory database with standalone cache to separate memory used by database and by application

- Use timeouts in searching, to prevent blocking threads
