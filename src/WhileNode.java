import java.util.ArrayList;

//While (booleanExpression and collection of statementNodes)
public class WhileNode extends StatementNode{
	
    //fields
	BooleanExpressionNode Boolean;
	ArrayList<StatementNode> statement = new ArrayList<>();
	
	//class constructor
	public WhileNode(BooleanExpressionNode b, ArrayList<StatementNode> statement) {
		this.Boolean = b;
		this.statement = statement;
	}
	
	@Override
	public String ToString() {
		return "WhileNode (Boolean Expression: " + Boolean.ToString() + ", Statements: " + displaystatementnodes(statement) + ")";
	}

	//method to display each index of arraylist
	public String displaystatementnodes(ArrayList<StatementNode> list) {
		String statementnodesinfo = "";
		for (StatementNode v : list) {
			statementnodesinfo = statementnodesinfo + "" + v.ToString();
		}

		return statementnodesinfo;

	}
	
	//setters and getters 
	public BooleanExpressionNode getBoolean() {
		return Boolean;
	}

	public void setBoolean(BooleanExpressionNode b) {
		Boolean = b;
	}

	public ArrayList<StatementNode> getStatement() {
		return statement;
	}

	public void setStatement(ArrayList<StatementNode> statement) {
		this.statement = statement;
	}
	
}
