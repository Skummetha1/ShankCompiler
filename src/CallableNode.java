import java.util.ArrayList;

abstract class CallableNode extends Node{

	
	ArrayList<VariableNode> Vars = new ArrayList<>();
	String name; // initialize string

	
	//setters and getters
	public ArrayList<VariableNode> getVars() {
		return Vars;
	}

	public void setVars(ArrayList<VariableNode> vars) {
		Vars = vars;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
