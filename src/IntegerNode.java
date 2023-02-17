
public class IntegerNode extends Node{
	
	//private variable
	private Integer number;

	@Override
	//toStrring override
	public String ToString() {
		// TODO Auto-generated method stub
		return number.toString();
	}
	
    //class constructor 
	public IntegerNode(Integer number) {
		this.number = number;
	}
	
	public Integer getNumber() {
		return number;
	}

	
	}

	