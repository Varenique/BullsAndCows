import java.io.File 
import java.io.FileInputStream
import scala.annotation.tailrec
import System.nanoTime

object WorkWithFiles {
     def main(args: Array[String]): Unit = {
     val filesListNames =new java.io.File("./filesOfData").listFiles.filter(_.getName.endsWith(".txt"))
     for(file <- filesListNames) {
        println(file)
        println ("size : " + file.length())
     }
     println("Result:")
     val initTime = System.currentTimeMillis
     var result: Array[File] = sort(filesListNames, 0, 1);
     val elapsedTime = System.currentTimeMillis-initTime
     for(file <- result){ 
       println(file)
       
     }    
     println("Time is " + elapsedTime + "ms")
     val buf = scala.collection.mutable.ListBuffer.empty[Any]
     val counterOfNumbers = scala.collection.mutable.ListBuffer (0,0,0,0,0,0,0,0,0,0)
     var sourceList: List[Any]= Nil;
     for(file <- result){ 
       val source = scala.io.Source.fromFile("./filesOfData/" + file.getName()).getLines.reduceLeft(_+_)
       
       for (element <- source){
         buf += element
       }
 }
     for (i <- buf) {
       i match {
       case '0' => counterOfNumbers(0)+=1
       case '1' => counterOfNumbers(1)+=1
       case '2' => counterOfNumbers(2)+=1
       case '3' => counterOfNumbers(3)+=1
       case '4' => counterOfNumbers(4)+=1
       case '5' => counterOfNumbers(5)+=1
       case '6' => counterOfNumbers(6)+=1
       case '7' => counterOfNumbers(7)+=1
       case '8' => counterOfNumbers(8)+=1
       case '9' => counterOfNumbers(9)+=1
       }
     }
     var i: Integer = 0;
     println("Statistics map : ")
     for (times <- counterOfNumbers){
       println ( i + " - was used " + times + " times")
       i+=1
     }
     
   }
   

    @tailrec
    def sort(array: Array[File], current: Int, i: Int): Array[File] = {      
      if(i < array.length && current < array.length){
        if(array(current).length() <= array(i).length()){
          sort(array, current, i+1)  
        }
        else{
          var temp = array(current)
          array(current) = array(i)
          array(i) = temp
          sort (array, current, i+1)
        }
      }
      else {
        if(i>= array.length){
          sort(array, current+1, current+2)          
        }
        else {
          return array
        }
      }
    }  
}
  




