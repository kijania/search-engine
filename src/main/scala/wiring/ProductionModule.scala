package wiring

import boot.ConsoleApp
import search.SearchEngine

object ProductionModule {

  val searchEngine = new SearchEngine()
  val consoleApp = new ConsoleApp(searchEngine)
}
