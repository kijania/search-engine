package search

import java.io.{File, PrintWriter}

import org.scalatest.{FlatSpec, MustMatchers}
import search.SearchEngineSpec._

class SearchEngineSpec extends FlatSpec with MustMatchers {
  it must "load file content" in new Fixture(fileA :: Nil) {
    searchEngine.getFilesContent("fileA.txt").toSet mustBe Map(
      1 -> "obla",
      2 -> "di",
      3 -> "obla",
      4 -> "da,",
      5 -> "life",
      6 -> "goes",
      7 -> "on,",
      8 -> "bra"
    ).toSet
  }

  class Fixture(files: List[File]) {
    val searchEngine = new SearchEngine()
    searchEngine.load(files)
  }
}

object SearchEngineSpec {
  new PrintWriter("target/fileA.txt") {
    write("obla di obla da,\nlife goes on, bra")
    close
  }
  val fileA = new File("target/fileA.txt")
}
