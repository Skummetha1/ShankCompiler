
public class StringDataType extends InterpreterDataType{

    String typevalue;

	
	public String ToString() {
		// TODO Auto-generated method stub
		return typevalue.toString();
	}

    //FromString method
	public void FromString(String input) {
		// TODO Auto-generated method stub
		typevalue = input;
	}
	
	public StringDataType() {
	}
	
	//class constructor
	public StringDataType(String typevalue) {
		  this.typevalue = typevalue;
		 }
	
	//setters and getters
	public String getValue() {
		return typevalue;
	}

	public void setValue(String value) {
		this.typevalue = value;
	}

}


