
public class CharacterDataType extends InterpreterDataType {

    char typevalue;

	
	public String ToString() {
		// TODO Auto-generated method stub
		return Character.toString(typevalue);
	}

    //FromString method
	public void FromString(String input) throws Exception {
		typevalue = input.charAt(0);
		if(input.length()>1)
			throw new Exception ("Invalid. Only one character allowed");
		// TODO Auto-generated method stub
		
		
	}
	
	public CharacterDataType() {
	}
	
	//class constructor
	public CharacterDataType(char typevalue) {
		  this.typevalue = typevalue;
		 }
	
	//setters and getters
	public char getValue() {
		return typevalue;
	}

	public void setValue(char value) {
		this.typevalue = value;
	}

}


