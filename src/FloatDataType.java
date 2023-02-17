
public class FloatDataType extends InterpreterDataType{

	Float typevalue;

	
	public String ToString() {
		// TODO Auto-generated method stub
		return Float.toString(typevalue);
	}

    //FromString method
	public void FromString(String input) {
		// TODO Auto-generated method stub
		typevalue = Float.parseFloat(input);
	}
	
	public FloatDataType() {
	}
	
	//class constructor
	public FloatDataType(float typevalue) {
		  this.typevalue = typevalue;
		 }
	
	//setters and getters
	public float getValue() {
		return typevalue;
	}

	public void setValue(float value) {
		this.typevalue = value;
	}

}
