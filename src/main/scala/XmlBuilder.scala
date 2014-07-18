
class Field(name : String, value : String){
    def toXml() = "<" +name +">"+ value +"</"+ name +">";
}