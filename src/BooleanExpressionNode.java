
public class BooleanExpressionNode extends StatementNode {

	//fields 
	Node Left;
	Node Right;
	Node variable;
	Token.Type condition;
	BooleanExpressionNode bol;
	
	
	
	//class constructor 
	public BooleanExpressionNode(Node Left, Node Right, Token.Type Condition) {
		this.condition = Condition;
		this.Left = Left;
		this.Right = Right;
	}
	public BooleanExpressionNode(Node vari) {
		this.variable = vari;
		
	}
	
	//setters and getters 
	public Node getLeft() {
		return Left;
	}

	public void setLeft(Node left) {
		Left = left;
	}

	public Node getRight() {
		return Right;
	}

	public void setRight(Node right) {
		Right = right;
	}

	public Token.Type getCondition() {
		return condition;
	}

	public void setCondition(Token.Type condition) {
		this.condition = condition;
	}

	
	//method to check if boolean node is null
	public boolean node(BooleanExpressionNode a) {
		if (a == null)
		return false;
		return true;
	}
	
	@Override
	//toString override 
	public String ToString() {
		if(node(bol)==false && this.Left != null )
		return "BooleanExpression(Left: " + Left.ToString() + ", Right: " + Right.ToString() + ", Condition: " + this.condition.name() + ")";
		if (variable != null)
			return "BooleanExpression(Boolean variable: " + variable.ToString() + ")";
		//return "No boolean Expression";
		return null;
			
		
	}

	public BooleanExpressionNode getBol() {
		return bol;
	}

	public void setBol(BooleanExpressionNode bol) {
		this.bol = bol;
	}
	

}
