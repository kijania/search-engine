Here will be description of building application with sbt and running it:
scala src/main/scala/boot/ConsoleApp src/test/resources

Also some overview of main choices and potential future improvements

TODOs:
Take care of all specific symbols in the text like commas, dots etc
Block querying when loading file, e.g. by putting in memory map inside Akka Actor
Load documents from files chunk by chunk
Replace in memory database with standalone cache to separate memory used by database and by application
Use timeouts in searching, to prevent blocking threads
Prepare second database instance, and load documents to database instances one by one, to have always one available for searching queries
