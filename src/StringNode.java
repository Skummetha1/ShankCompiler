
public class StringNode extends Node {

	//private variable
	private String str;

	@Override
	//toStrring override
	public String ToString() {
		// TODO Auto-generated method stub
		return str.toString();
	}
	
    //class constructor 
	public StringNode(String str) {
		this.str = str;
	}
	
	//setters and getters 
	public void setStr(String str) {
		this.str = str;
	}

	public String getStr() {
		return str;
	}

	}
