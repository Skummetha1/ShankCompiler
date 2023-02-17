
public class IntDataType extends InterpreterDataType{

	Integer typevalue;
	
	public String ToString() {
		// TODO Auto-generated method stub
		return Integer.toString(typevalue);
	}

	//sets the value of the data type by parsing the string
	public void FromString(String input) {
		Integer.parseInt(input);
		typevalue = Integer.parseInt(input);
	}

	//class constructor
	public IntDataType(Integer typevalue) {
		this.typevalue = typevalue;
	}
	
	public IntDataType() {
		
	}

	//setter and getters
	public Integer getTypevalue() {
		return typevalue;
	}

	//class constructor
	public void setTypevalue(Integer typevalue) {
		this.typevalue = typevalue;
	}
}


