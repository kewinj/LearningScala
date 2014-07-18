object Hello {
    
  val who = "CharlotteKJ"  
  val dataFile = "C:\\Temp\\foo.csv"
  var formatUTF8 = "UTF-8";
  
  
  def main(args: Array[String]): Unit = {
    println("Hello, " + who + "!")
    
    //val csv = new CSV();
    //println(csv.parseView(scala.io.Source.fromFile(dataFile, formatUTF8)))
    println ("<root><applications>");
    new CSV().parse(scala.io.Source.fromFile(dataFile, formatUTF8)) foreach { row =>
    
        println ("<application>");
        row foreach ( (column) => println (new Field(column._1, column._2).toXml()))
        println ("<\\application>");
    }
    println ("<\\applications><\\root>");
    
    println("Goodbye")
    
    
  }
}
