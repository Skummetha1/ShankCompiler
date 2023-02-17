//abstract class
abstract class InterpreterDataType {
	
	public abstract String ToString();
    public abstract void FromString(String input) throws Exception; // sets the value of the data type by parsing the string

}
