import java.util.ArrayList;

public class FunctionNode extends CallableNode {
	

	// two collection classes of VariableNode
	ArrayList<VariableNode> Params = new ArrayList<>();
	ArrayList<VariableNode> Vars = new ArrayList<>();
	ArrayList<StatementNode> stat = new ArrayList<>();
	String name; // initialize string
	ArrayList<Node> node = new ArrayList<>();

	// class constructor
	
	
	
	public FunctionNode(String name, ArrayList<VariableNode> params, ArrayList<VariableNode> vars, ArrayList<StatementNode> statements) {
		this.Params = params;
		this.Vars = vars;
		this.name = name;
		this.stat = statements;
	}
	
	public FunctionNode(String name) {
		this.name = name;
	}
	

	// toString method to displayy function Node
	public String ToString() {
		// TODO Auto-generated method stub
		return "\nFunction name: " + this.name + "\n\nParameters Variables: " + displayvariablenodes(this.Params)
				+ "\n\nLocal variables: " + displayvariablenodes(this.Vars) + "\n\nStatements: "
				+ displaystatementnodes(this.stat);
	}

	// method to display variable nodes in arraylist
	public String displayvariablenodes(ArrayList<VariableNode> list) {
		String variablenodesinfo = "";
		for (VariableNode v : list) {
			variablenodesinfo = variablenodesinfo + "\n" + v.ToString();
		}
		return variablenodesinfo;

	}

	// method to display assignment nodes in arraylist
	public String displaystatementnodes(ArrayList<StatementNode> list) {
		String statementnodesinfo = "";
		for (StatementNode v : list) {
			statementnodesinfo = statementnodesinfo + "\n" + v.ToString();
		}

		return statementnodesinfo;

	}
	//setters and getters
	public ArrayList<VariableNode> getParams() {
		return Params;
	}

	public void setParams(ArrayList<VariableNode> params) {
		Params = params;
	}

	public ArrayList<VariableNode> getVars() {
		return Vars;
	}

	public void setVars(ArrayList<VariableNode> vars) {
		Vars = vars;
	}

	public ArrayList<StatementNode> getStat() {
		return stat;
	}

	public void setStat(ArrayList<StatementNode> stat) {
		this.stat = stat;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
}


	
	


