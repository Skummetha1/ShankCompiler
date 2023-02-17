import java.util.ArrayList;

//For (variableReference, start ASTNode, end astNode, collection of statementNodes)
public class ForNode extends StatementNode {
	//fields
	Node start;
	Node end;
	ArrayList<StatementNode> stat = new ArrayList<>();
	VariableReferenceNode varef;

	//class constructor 
public ForNode(VariableReferenceNode varef, Node start, Node end,ArrayList<StatementNode> statement) {
		super();
		this.start = start;
		this.end = end;
		this.stat = statement;
		this.varef = varef;
	}

@Override
//toString
public String ToString() {
	return "ForNode (Start: " + start.ToString() + ", End: " + end.ToString() + ", Statements: " + displaystatementnodes(stat) + ", Variable: " + varef.ToString() + ")";
}

//method to apply tostirng to each index of arraylist
public String displaystatementnodes(ArrayList<StatementNode> list) {
	String statementnodesinfo = "";
	for (StatementNode v : list) {
		statementnodesinfo = statementnodesinfo + "" + v.ToString();
	}

	return statementnodesinfo;

}
//setters and getters
public Node getStart() {
	return start;
}

public void setStart(Node start) {
	this.start = start;
}

public Node getEnd() {
	return end;
}

public void setEnd(Node end) {
	this.end = end;
}

public ArrayList<StatementNode> getStat() {
	return stat;
}

public void setStat(ArrayList<StatementNode> stat) {
	this.stat = stat;
}

public VariableReferenceNode getVaref() {
	return varef;
}

public void setVaref(VariableReferenceNode varef) {
	this.varef = varef;
}

}
