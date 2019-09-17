package boot

import java.io.File

import search.SearchEngine
import wiring.ProductionModule
import scala.collection.JavaConverters._

class ConsoleApp(searchEngine: SearchEngine) {
  def listen(directory: File): Unit = {
    searchEngine.load(directory.listFiles().toList)
    while(true) {
      println("search> ")
      val words = scala.io.StdIn.readLine()
      println(s"You have just written: $words")
    }
  }
}

object ConsoleApp extends App {
  if (args.length == 0) {
    println("Missing directory argument, e.g. invocation: `scala src/main/scala/boot/ConsoleApp src/test/resources`")
  } else {
    val directory = new File(args.head)
    println(s"You will search in directory: ${directory.getAbsolutePath} in files: ${directory.list.toList}")
    ProductionModule.consoleApp.listen(directory)
  }
}
