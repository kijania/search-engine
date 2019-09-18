package search

import java.io.File

import scala.io.Source

class SearchEngine {
  type FileName = String
  type Word = String

  private var filesContent: Map[FileName, Set[Word]] = _

  def load(files: List[File]): Unit =
    filesContent = files.map(file => file.getName -> loadFileContent(file)).toMap

  private def loadFileContent(file: File): Set[Word] = {
    Source.fromFile(file).getLines().mkString(" ")
      .split(" ").toSet
  }

  def getFilesContent: Map[FileName, Set[Word]] = filesContent

  def printRankFor(words: List[Word]): Unit = {
    rankFor(words.toSet) match {
      case ranking if ranking.isEmpty =>
        println("no matches found search")
      case ranking =>
        ranking.foreach {
          case (fileName, rank) =>
            println(s"$fileName : $rank%")
        }
    }
  }

  def rankFor(words: Set[Word]): List[(FileName, Int)] = {
    filesContent.keys
      .map(fileName => (fileName, rankForFileName(fileName, words)))
      .toList
      .sortBy { case (_, rank) => - rank }
      .take(10)
      .filterNot { case (_, rank) => rank == 0 }
  }

  private def rankForFileName(fileName: FileName, words: Set[Word]): Int = {
    filesContent(fileName).intersect(words).size * 100 / words.size
  }
}
