//class extends Node
public class VariableReferenceNode extends Node {

	//private string variable
	private String name;
	private boolean var;

	//class constructor
	public VariableReferenceNode(String name) {
		this.name = name;
	}
	
	public VariableReferenceNode(String name, boolean var) {
		this.name = name;
		this.var = var;
	}

	
	public boolean isVar() {
		return var;
	}


	public void setVar(boolean var) {
		this.var = var;
	}


	//getter and setter
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	//toString method
	public String ToString() {
		//if (var == true)
			//return ("var " + name);
		return name;
	}
}

