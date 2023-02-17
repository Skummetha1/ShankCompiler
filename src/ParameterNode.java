
public class ParameterNode extends Node{
	//class fields
	Node con;
	VariableReferenceNode var;
	String vari;
	
	//class constructor
	public ParameterNode(Node con, VariableReferenceNode var) {
		this.con = con;
		this.var = var;
	}
	
	//class constructor
	public ParameterNode(Node con) {
	this.con = con;
	}
	//class constructor
	public ParameterNode(String vari, Node con) {
		this.vari = vari;
		this.con = con;
		}
	//class constructor
	public ParameterNode(VariableReferenceNode var) {
		this.var = var;
	}
	
	//setters and getters
	public Node getCon() {
		return con;
	}

	public void setCon(Node con) {
		this.con = con;
	}

	public VariableReferenceNode getVar() {
		return var;
	}

	public void setVar(VariableReferenceNode var) {
		this.var = var;
	}


	@Override
	public String ToString() {
		if (vari != null) {
			
			return vari.toString() + " " + con.ToString();
		}
		else if (con != null) {
			return con.ToString();
		}
		
		else if (var != null) {
			return var.ToString();
		}
		return null;
}
	}
