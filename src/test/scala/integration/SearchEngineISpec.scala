package integration

import java.io.File

import boot.ConsoleApp
import org.scalatest.{FlatSpec, MustMatchers}
import search.SearchEngine

class SearchEngineISpec extends FlatSpec with MustMatchers {
  it must "rank words for the files from the disc" in new Fixture {
    val ranking = searchEngine.rankFor("All work and no play makes Jack a dull boy.".split(" ").toSet)
    ranking.length mustBe 10
    ranking mustBe
      List("shining.txt" -> 100,
        "thinking.txt" -> 80, "walking.txt" -> 80, "fouling.txt" -> 80, "playing.txt" -> 80,
        "eleventh.txt" -> 70, "praying.txt" -> 70, "goalkeeper.txt" -> 70, "sleeping.txt" -> 70,
        "shooting.txt" -> 60
      )
  }

  class Fixture {
    val searchEngine = new SearchEngine()
    val consoleApp = new ConsoleApp(searchEngine)
    consoleApp.loadToEngine(new File("src/test/resources"))
  }
}
