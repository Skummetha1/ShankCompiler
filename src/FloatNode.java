
public class FloatNode extends Node{
	//private variable
	private Float number;

	@Override
	//toStrring override
	public String ToString() {
		// TODO Auto-generated method stub
		return number.toString();
	}
	
    //class constructor 
	public FloatNode(Float number) {
		this.number = number;
	}
	
	public Float getNumber() {
		return number;
	}
	

	
	}
	