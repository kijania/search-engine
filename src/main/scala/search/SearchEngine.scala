package search

import java.io.File
import java.util.concurrent.atomic.AtomicLong

import scala.io.Source

class SearchEngine {
  type FileName = String
  type Id = Long
  type Word = String
  type FileContent = Map[Id, Word]

  private var filesContent: Map[FileName, FileContent] = _

  def load(files: List[File]): Unit =
    filesContent = files.map(file => file.getName -> loadFileContent(file)).toMap

  private def loadFileContent(file: File): Map[Id, Word] = {
    val id = new AtomicLong(0)

    Source.fromFile(file).getLines().mkString(" ")
      .split(" ")
      .map(word => id.incrementAndGet() -> word).toMap
  }

  def getFilesContent: Map[FileName, FileContent] = filesContent
}
