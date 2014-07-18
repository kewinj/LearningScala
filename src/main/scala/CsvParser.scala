import scala.util.parsing.combinator._


class CSV(COMMA: String = ",") extends RegexParsers {
	override protected val whiteSpace = """[ \t]""".r
    override def skipWhitespace = false
 
	def DQUOTE  = "\""
	def DQUOTE2 = "\"\"" ^^ { case _ => "\"" }
	def CR      = "\r"
	def LF      = "\n"
	def CRLF    = "\r\n"
	def TXT     = "[^\"%s\r\n]".format(COMMA).r
 
	def file: Parser[List[List[String]]] = repsep(record, CRLF) <~ opt(CRLF)
	def record: Parser[List[String]] = rep1sep(field, COMMA)
	def field: Parser[String] = (escaped|nonescaped)
	def escaped: Parser[String] = (DQUOTE~>((TXT|COMMA|CR|LF|DQUOTE2)*)<~DQUOTE) ^^ { case ls => ls.mkString("")}
	def nonescaped: Parser[String] = (TXT*) ^^ { case ls => ls.mkString("") }
 
    def parseView(i: scala.io.BufferedSource): String = i.getLines.mkString("\r\n")
	def parse(i: scala.io.BufferedSource): List[Map[String, String]] = parse(i.getLines.mkString("\r\n"))
	def parse(s: String): List[Map[String, String]] = parseAll(file, s) match {
		case Success(alllines, _) =>
			val head = alllines.head
			alllines.drop(1) map { line =>
				var theMap = Map[String, String]()
				head.zipWithIndex.map {	e =>
					val fieldName = e._1.replaceAll("\\s+", "")
					if (fieldName != "") theMap = theMap ++ Map(fieldName -> line(e._2))
				}
				theMap
			}
		case _ => List[Map[String, String]]()
	}
 
}