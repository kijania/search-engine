package search

import java.io.{File, PrintWriter}

import org.scalatest.{FlatSpec, MustMatchers}
import search.SearchEngineSpec._

class SearchEngineSpec extends FlatSpec with MustMatchers {
  it must "load file content" in new Fixture(fileA :: Nil) {
    searchEngine.getFilesContent("fileA.txt") mustBe
      Set("obla", "di", "obla", "da,", "life", "goes", "on,", "bra")
  }

  it must "rank files regarding to words matching" in new Fixture(fileA :: fileB :: fileC :: Nil) {
    searchEngine.rankFor(Set("bra", "not", "obla")) mustBe
      List("fileA.txt"-> 66, "fileB.txt" -> 33)

    searchEngine.rankFor(Set("Be", "brave")) mustBe empty

    searchEngine.rankFor(Set("dee,", "da,", "aa,", "da")) mustBe
      List("fileC.txt" -> 100, "fileA.txt" -> 25, "fileB.txt" -> 25)
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

  new PrintWriter("target/fileB.txt") {
    write("Oompa loompa doompety da\nIf you're not greedy, you will go far")
    close
  }
  val fileB = new File("target/fileB.txt")

  new PrintWriter("target/fileC.txt") {
    write("Meri mehbooba, meri mehbooba\nZara tasveer se tu nikalke samne aa, meri mehbooba\n\nObla-dee, dee, dee, obla-da, da, da\nObla-doo, doo, doo, what to do")
    close
  }
  val fileC = new File("target/fileC.txt")
}
