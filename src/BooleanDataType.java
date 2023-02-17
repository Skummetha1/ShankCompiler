
public class BooleanDataType extends InterpreterDataType {

 Boolean typevalue;

	
	public String ToString() {
		// TODO Auto-generated method stub
		return typevalue.toString();
	}

    //FromString method
	public void FromString(String input) throws Exception {
		// TODO Auto-generated method stub
		if (input.equalsIgnoreCase("true"))
			this.typevalue = true;
		if (input.equalsIgnoreCase("false"))
			this.typevalue = false;
		else throw new Exception("Invalid. Only true or false allowed");
	}
	
	public BooleanDataType() {
	}
	
	//class constructor
	public BooleanDataType(boolean typevalue) {
		  this.typevalue = typevalue;
		 }
	
	//setters and getters
	public boolean getValue() {
		return typevalue;
	}

	public void setValue(boolean value) {
		this.typevalue = value;
	}

}


