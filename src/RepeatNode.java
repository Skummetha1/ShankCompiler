import java.util.ArrayList;

//Repeat (booleanExpression and collection of statementNodes)
public class RepeatNode extends StatementNode {
	//fields
		BooleanExpressionNode Boolean;
		ArrayList<StatementNode> statement = new ArrayList<>();
		
		//class constructor
		public RepeatNode(BooleanExpressionNode b, ArrayList<StatementNode> statement) {
			this.Boolean = b;
			this.statement = statement;
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
		
		//tostring method
		@Override
		public String ToString() {
			return "RepeatNode (UntilBoolean: " + Boolean.ToString() + ", Statement: " + displaystatementnodes(statement) + ")";
		}
		
		//method to display statementnodes by applying tostring to each index of the arraylist
		public String displaystatementnodes(ArrayList<StatementNode> list) {
			String statementnodesinfo = "";
			for (StatementNode v : list) {
				statementnodesinfo = statementnodesinfo + "" + v.ToString();
			}

			return statementnodesinfo;

		}
	}

	
