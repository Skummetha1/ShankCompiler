import java.util.ArrayList;

//If (booleanExpression, collection of statementNodes, ifNode)
public class IfNode extends StatementNode{
	//fields
	BooleanExpressionNode bol;
	ArrayList<StatementNode> statement = new ArrayList<>();
	IfNode a;
	
	
	//class constructor
	public IfNode(BooleanExpressionNode bol, ArrayList<StatementNode> statement, IfNode a) {
		this.bol = bol;
		this.statement = statement;
		this.a = a;
	}
	
	//class constructor 
	public IfNode(ArrayList<StatementNode> statement) {
		this.statement = statement;
	}
	
	//method returns true if a is not null
	public boolean node(IfNode a) {
		if (a == null)
		return false;
		return true;
	}
	
    //tostring override
	@Override
	public String ToString() {
		if(node(a) == true)
			return "\nIfNode(Boolean Expression: " + bol.ToString() + ", Statement: " + displaystatementnodes(this.statement) + "\nElsIf/ElseNode: "+ a.ToString()+ ")";
		if (this.bol == null)
		return "IfNode(Statement: " + displaystatementnodes(this.statement)+ ")";
		return "IfNode(Boolean Expression: " + bol.ToString() + ", Statement: " + displaystatementnodes(this.statement)+ ")";
	}
	
	//method to display statement nodes and use tostring
	public String displaystatementnodes(ArrayList<StatementNode> list) {
		String statementnodesinfo = "";
		for (StatementNode v : list) {
			statementnodesinfo = statementnodesinfo + "" + v.ToString();
		}
		return statementnodesinfo;
	}
	
    //setters and getters
	public IfNode getA() {
		return a;
	}

	public void setA(IfNode a) {
		this.a = a;
	}

	public BooleanExpressionNode getBol() {
		return bol;
	}

	public void setBol(BooleanExpressionNode bol) {
		this.bol = bol;
	}

	public ArrayList<StatementNode> getStatement() {
		return statement;
	}

	public void setStatement(ArrayList<StatementNode> statement) {
		this.statement = statement;
	}
	

}
