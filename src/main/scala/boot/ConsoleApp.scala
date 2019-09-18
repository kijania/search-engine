package boot

import java.io.File

import search.SearchEngine
import wiring.ProductionModule

class ConsoleApp(searchEngine: SearchEngine) {

  def loadToEngine(directory: File): Unit =
    searchEngine.load(directory.listFiles().toList)

  def listen(): Unit = {
    var isListening = true
    while(isListening) {
      println("search> ")
      scala.io.StdIn.readLine() match {
        case words if words == ":quit" =>
          isListening = false
        case words =>
          searchEngine.printRankFor(words.split(" ").toList)
      }
    }
  }
}

object ConsoleApp extends App {
  if (args.length == 0) {
    println("Missing directory argument, e.g. invocation: `scala src/main/scala/boot/ConsoleApp src/test/resources`")
  } else {
    val directory = new File(args.head)
    println(s"${directory.list.length} files read in directory ${directory.getAbsolutePath}")
    ProductionModule.consoleApp.loadToEngine(directory)
    ProductionModule.consoleApp.listen()
  }
}
