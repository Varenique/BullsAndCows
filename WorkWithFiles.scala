import java.io.File
import java.io.FileInputStream
import scala.annotation.tailrec
import System.nanoTime
import scala.collection.mutable.ListBuffer
import util.control.Breaks._

object WorkWithFiles {
  def main(args: Array[String]): Unit = {
    val filesListNames = new java.io.File("./filesOfData").listFiles.filter(_.getName.endsWith(".txt"))
    for (file <- filesListNames) {
      println(file)
      println("size : " + file.length())
    }
    println("Result:")
    val initTime = System.currentTimeMillis
    var result: Array[File] = sort(filesListNames, 0, 1);
    val elapsedTime = System.currentTimeMillis - initTime
    for (file <- result) {
      println(file)

    }
    println("Time is " + elapsedTime + "ms")
    val buf = scala.collection.mutable.ListBuffer.empty[Any]
    val counterOfNumbers = scala.collection.mutable.ListBuffer(0, 0, 0, 0, 0, 0, 0, 0, 0, 0)
    var sourceList: List[Any] = Nil;
    for (file <- result) {
      val source = scala.io.Source.fromFile("./filesOfData/" + file.getName()).getLines.reduceLeft(_ + _)
      for (element <- source) {
        buf += element
      }
    }
    for (i <- buf) {
      i match {
        case '0' => counterOfNumbers(0) += 1
        case '1' => counterOfNumbers(1) += 1
        case '2' => counterOfNumbers(2) += 1
        case '3' => counterOfNumbers(3) += 1
        case '4' => counterOfNumbers(4) += 1
        case '5' => counterOfNumbers(5) += 1
        case '6' => counterOfNumbers(6) += 1
        case '7' => counterOfNumbers(7) += 1
        case '8' => counterOfNumbers(8) += 1
        case '9' => counterOfNumbers(9) += 1
      }
    }
    var i: Integer = 0;
    println("Statistics map : ")
    for (times <- counterOfNumbers) {
      println(i + " - was used " + times + " times")
      i += 1
    }
    val buffer = ListBuffer.empty[ListBuffer[Int]]
    var resultCoincidence = ListBuffer.empty[Int]
    for (file <- result) {
      buffer += getLists(file)
    }
    resultCoincidence = findCoincidence(buffer)
    println("Max length of coincidence is "+ resultCoincidence(0)+"\n"+"Numbers: ")
    i=1
    while (i<resultCoincidence(0)+1){
      println(resultCoincidence(i))
      i+=1
    }
    println("Files: "+ result(resultCoincidence(i))+", "+result(resultCoincidence(i+1)))

  }

  @tailrec
  def sort(array: Array[File], current: Int, i: Int): Array[File] = {
    if (i < array.length && current < array.length) {
      if (array(current).length() <= array(i).length()) {
        sort(array, current, i + 1)
      } else {
        var temp = array(current)
        array(current) = array(i)
        array(i) = temp
        sort(array, current, i + 1)
      }
    } else {
      if (i >= array.length) {
        sort(array, current + 1, current + 2)
      } else {
        return array
      }
    }
  }
  def getLists(file: File): ListBuffer[Int] = {
    val currentFile = scala.collection.mutable.ListBuffer.empty[Int]
    var i: Integer = 0
    var countNumber: Integer = 0
    var skip: Integer = 8
    var number: Integer = 0

    i = 0
    countNumber = 0
    skip = 8
    number = 0
    val source = scala.io.Source.fromFile("./filesOfData/" + file.getName()).getLines.reduceLeft(_ + _)
    for (element <- source) {
      if (i > 7) {
        if (skip < 8) {
          skip += 1

        } else {
          if (countNumber < 4) {
            number = number * 10
            number += Integer.parseInt(element.toString())
          }
          if (countNumber == 3) {
            countNumber = -1
            currentFile += number
            number = 0
            skip = 0
          }
          countNumber += 1
        }
      }
      i += 1
    }
    return currentFile
  }
  def findCoincidence(list: ListBuffer[ListBuffer[Int]]): ListBuffer[Int] = {
    var index: Integer = 0
    var i: Integer = 0
    var currentInd: Integer = 0
    var count: Integer = 0
    var currentBuf = ListBuffer.empty[Int]
    var tempResult = ListBuffer[Int](0)
    var result = ListBuffer[Int](0)
    //var zeroBuf = ListBuffer[Int](0)
    var check: Boolean = false
    //println("List si "+list)
    for (oneFile <- list) {
      currentBuf.clear()
      for (each <- oneFile) {
        currentBuf += each

      }

      index = currentInd + 1
      while (index < list.length) {
        for (allNumbers <- currentBuf) {
          if(!check){
             i=0
          }
          breakable {
              while (i< list(index).length) {
            list(index)(i) match {
              case `allNumbers` => {
                count += 1
                tempResult(0) = count
                tempResult += allNumbers
                check=true

              }
              case _ => {
                if (tempResult(0) > result(0)) {
                  tempResult+=index
                  tempResult+=currentInd
                  result = tempResult
                }
                count=0
                tempResult = ListBuffer[Int](0)
                check = false
              }
            }
            i+=1
            if(check) {
              break
            }
          }
                if (tempResult(0) > result(0)) {
                  tempResult+=index
                  tempResult+=currentInd
                  result = tempResult
                }
                count=0
                tempResult =  ListBuffer[Int](0)
                
                check = false   
        }
          
        }
        check = false
        count=0
        tempResult =  ListBuffer[Int](0)
        index += 1
      }
      currentInd+=1
    }
    return result
  }

}
