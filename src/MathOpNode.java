public class MathOpNode extends Node {
	private Node left; //left node
	private Node right; //right node
	private Operation mathOp; //Operation 
	
	//enum operation
	public enum Operation {
		add, 
		subtract, 
		multiply, 
		divide,
		mod;
	}
	
	//class constructor 
	public MathOpNode(Operation mathOp, Node operandLeft, Node operandRight) {
		this.mathOp = mathOp;
		this.left = operandLeft;
		this.right = operandRight;
	}

	@Override
	//toString method
	public String ToString() {
		// TODO Auto-generated method stub
		 return "MathOp(" + this.mathOp.name() + ", " + left.ToString() +", "+ right.ToString() + ")";

	}
	public Node getOperandLeft() {
		return left;
	}
	public Node getOperandRight() {
		return right;
	}
	public String getOp()
	{
	return this.mathOp.name();
	}


}
