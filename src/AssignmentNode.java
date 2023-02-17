import java.util.ArrayList;

public class AssignmentNode extends StatementNode {

	//private variables
	 VariableReferenceNode target;
	 Node expression;
	 ArrayList<AssignmentNode> a;
	
	
	//class constructor
		public AssignmentNode(VariableReferenceNode variable, Node node) {
			this.target = variable;
			this.expression = node;
		}
		
		//class constructor to put arraylist in assignmetnnode
		public AssignmentNode(ArrayList<AssignmentNode> a) {
			this.a = a;
			
		}
		

	//getters and setters
	public ArrayList<AssignmentNode> getA() {
		return a;
	}

	public void setA(ArrayList<AssignmentNode> a) {
		this.a = a;
	}
	
	public VariableReferenceNode getVariable() {
		return target;
	}

	public void setVariable(VariableReferenceNode variable) {
		this.target = variable;
	}

	public Node getNode() {
		return expression;
	}

	public void setNode(Node node) {
		this.expression = node;
	}
	
	//boolean method to determine if list is not empty 
		boolean isassignmentlist(ArrayList<AssignmentNode> a) {
			if (a != null) {
				return true;
			}
			return false;
		}
		
    // toString method
	public String ToString() {
		if (isassignmentlist(a) == false)
		return "(Assignment Variable: " + target.ToString() + ", Expression: " + expression.ToString() + ")";
		return displaystatementnodes(this.a);
	}
	
	//method to apply tostring to each index of arraylist
	public String displaystatementnodes(ArrayList<AssignmentNode> list) {
		String statementnodesinfo = "";
		for (AssignmentNode v : list) {
			statementnodesinfo = statementnodesinfo + "" + v.ToString();
		}
		return statementnodesinfo;

	}
}
