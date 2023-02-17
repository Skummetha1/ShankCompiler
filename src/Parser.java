import java.util.ArrayList;

public class Parser {

	// list of tokens
	ArrayList<Token> tokens;
	private ArrayList<Token> lexeme;

	// constructor that accepts collection of Tokens
	public Parser(ArrayList<Token> tokens) {
		// TODO
		// It must have a constructor that accepts your collection of Tokens.
		this.lexeme = tokens;
	}

	// parser method calls expression
	public ArrayList<Node> parse() throws Exception {
		// return expression();
		return functionDefinition();

	}

	
     //parses function calls 
	public FunctionCall functionCall() {
		//method variables
		String name;
		ArrayList<ParameterNode> par = new ArrayList<ParameterNode>();
		ParameterNode param;
		Node n;
		VariableReferenceNode variable;
		String vari = null;
		
		//gets value for name of function
		name = lexeme.get(0).getValue();
		if (matchAndRemove(Token.Type.Identifier) == null) {
			return null;
		}
		
		//function calls can have var or not have var and can be a decimal number 
		//or integer number
		while(Var() || Identifier() || NumberIntOrNumberDec()) {
			
			//if variable without var 
			if (Identifier() == true) {
			//matchAndRemove(Token.Type.Identifier);
			vari = lexeme.get(0).getValue();
			matchAndRemove(Token.Type.Identifier);
			//creating new variable node
			variable = new VariableReferenceNode(vari);
			// adding node to parameternode
			param = new ParameterNode(variable);
			//adding node to arraylist
			par.add(param);
			matchAndRemove(Token.Type.comma);
			//matchAndRemove(Token.Type.EndOfLine);
			
			}
			//if value (real or int)
			if (NumberIntOrNumberDec() == true) {
			n = expression();
			// adding node to parameternode
			param = new ParameterNode(n);
			//adding node to arraylist
			par.add(param);
			matchAndRemove(Token.Type.comma);
			//matchAndRemove(Token.Type.EndOfLine);
			}
			
			//if var variable
			if (Var() == true) {
			//vari = lexeme.get(0).getValue();	
			matchAndRemove(Token.Type.var);
			vari = lexeme.get(0).getValue();
			matchAndRemove(Token.Type.Identifier);
			// adding node to arraylist
			variable = new VariableReferenceNode(vari,true);
			//param = new ParameterNode(variable);
			param = new ParameterNode(variable);
			par.add(param);
			matchAndRemove(Token.Type.comma);
			//matchAndRemove(Token.Type.EndOfLine);
				
	}
		}
		matchAndRemove(Token.Type.EndOfLine);
		//return function node
		return new FunctionCall(name, par);
	}
	
	
	// while method returns a while node
	public WhileNode While() throws Exception {
		/*
		 * while j < 5 begin j:=j+1 end
		 */
		BooleanExpressionNode bol;
		ArrayList<StatementNode> statement = null;
		WhileNode finalnode;

		// matches and removes while patters and creates a whilenode
		// calls body and boolean functions
		if (matchAndRemove(Token.Type.WHILE) == null)
			return null;
		bol = BooleanExpression();
		matchAndRemove(Token.Type.EndOfLine);
		statement = body();
		finalnode = new WhileNode(bol, statement);
		return finalnode;

	}

	public IfNode If() throws Exception {
		// If (booleanExpression, collection of statementNodes, ifNode)
		// “if-elsif-else”

		BooleanExpressionNode bol;
		ArrayList<StatementNode> statement = null;
		IfNode node = null;
		IfNode finalnode;

		// if token is if/elseif or else contintue...else retur null
		if (IfEleifOrElse() == false)
			return null;

		// matches and removes if-elsif-else patters and creates ifnodes
		// calls body and boolean, and if functions
		matchAndRemove(Token.Type.IF);

		matchAndRemove(Token.Type.ELSE);
		matchAndRemove(Token.Type.EndOfLine);
		matchAndRemove(Token.Type.elsif);

		bol = BooleanExpression();

		if (matchAndRemove(Token.Type.then) != null)
			// throw new Exception("No then found");
			matchAndRemove(Token.Type.EndOfLine);
		statement = body();

		// if elseif create new if node
		if (IfEleif()) {
			node = If();
		}

		// if else create new if noed
		if (IfElse()) {
			node = If();
		}

		if (bol == null)
			finalnode = new IfNode(statement);

		// create final node
		finalnode = new IfNode(bol, statement, node);

		return finalnode;

	}

	// matches and removes for patters and creates fornodes

	public ForNode For() throws Exception {
		/*
		 * for i from 1 to 10 begin write i end
		 */
		// For (variableReference, start ASTNode, end astNode, collection of
		// statementNodes)

		// if for continue....else return null
		if (matchAndRemove(Token.Type.FOR) == null)
			return null;

		// store value of first token in string
		String name = lexeme.get(0).getValue();
		if (matchAndRemove(Token.Type.Identifier) == null) {
			throw new Exception("Could not find identifier");
		}

		// createvariable refrencenode with stored string value
		VariableReferenceNode var = new VariableReferenceNode(name);
		if (matchAndRemove(Token.Type.from) == null)
			throw new Exception("Couldn't find from");

		// calls expression
		Node start = expression();

		if (matchAndRemove(Token.Type.to) == null)
			throw new Exception("Couldn't find to");

		Node end = expression();
		matchAndRemove(Token.Type.EndOfLine);
		ArrayList<StatementNode> statement = body();

		// returns fornode
		return new ForNode(var, start, end, statement);

	}

	public RepeatNode repeat() throws Exception {
		// Repeat (collection of statementNodes, booleanexpression )
		ArrayList<StatementNode> statement = null;
		BooleanExpressionNode bol;
		RepeatNode finalnode;

		// matches and removes repeat pattern and creates repeatnode
		// calls body and boolean,
		if (matchAndRemove(Token.Type.repeat) == null)
			return null;
		if (matchAndRemove(Token.Type.EndOfLine) == null)
			throw new Exception("No EndOfLine found");
		statement = body();
		if (matchAndRemove(Token.Type.until) == null)
			throw new Exception("No until found");
		matchAndRemove(Token.Type.EndOfLine);

		bol = BooleanExpression();
		matchAndRemove(Token.Type.EndOfLine);
		// creates repeatnode
		finalnode = new RepeatNode(bol, statement);
		// returns repeatnode
		return finalnode;
	}

	
	public BooleanExpressionNode BooleanExpression() throws Exception {
		Node bol = null;
		//Node right = null;
		//BooleanExpressionNode bol = null;
		//Token.Type type = null;
		
		bol = (BooleanExpressionNode) expression();
		
		//bol = new BooleanExpressionNode(left, right, type);
		return (BooleanExpressionNode) bol;
	}
	// booleanExpression method in the parser that generates a
	// booleanExpressionNode.
	/*public BooleanExpressionNode BooleanExpression() throws Exception {
		Node left = null;
		Node right = null;
		BooleanExpressionNode bol = null;
		Token.Type type = null;

		// calls expression
		left = expression();
		// matches and removes tokentype and sets it to "type"
		if (matchAndRemove(Token.Type.LessThan) != null)
			type = Token.Type.LessThan;
		if (matchAndRemove(Token.Type.GreaterThan) != null)
			type = Token.Type.GreaterThan;
		if (matchAndRemove(Token.Type.equal) != null)
			type = Token.Type.equal;
		if (matchAndRemove(Token.Type.notEqual) != null)
			type = Token.Type.notEqual;
		if (matchAndRemove(Token.Type.LessThanOrEqualTo) != null)
			type = Token.Type.LessThanOrEqualTo;
		if (matchAndRemove(Token.Type.GreaterThanOrEqualTo) != null)
			type = Token.Type.GreaterThanOrEqualTo;

		// calls expression again for right side
		right = expression();
		// creates new booleanexpression node
		bol = new BooleanExpressionNode(left, right, type);
		return bol;
	}*/

	// Identifier assignment expression endOfLine
	// returns arraylist of assignmentnodes
	public AssignmentNode Assignments() throws Exception {
		// variables
		//ArrayList<AssignmentNode> assignments = new ArrayList<AssignmentNode>();
		VariableReferenceNode variable = null;
		Node expression = null;
		AssignmentNode assign = null;

		// checks if assignment is in token stream
		if (variableDataType(Token.Type.Assignment) == null)
			//throw new Exception("no assignment operator found");
			return null;
			

		// while loop
		//while (Identifier()) {
			// value of the first token if identifier
			String name = lexeme.get(0).getValue();
			if(name == null) {
				return null;
			}
			if (name.equals("write") || name.equals("read") )
				return null;
				if(Identifier()) {
			// adding node to arraylist
			variable = new VariableReferenceNode(name);
			// checks for idetifier assignment and expression
			// if any of these are missing throws exception
			if (matchAndRemove(Token.Type.Identifier) == null)
				throw new Exception("no assignment operator found");
			if (matchAndRemove(Token.Type.Assignment) == null)
				throw new Exception("no assignment operator found");
			// calls expression
			expression = expression();
			if (matchAndRemove(Token.Type.EndOfLine) == null)
				throw new Exception("EndOfLine not found after statement has finished");
			if (expression == null)
				throw new Exception("no value given after assignment");
			assign = new AssignmentNode(variable, expression);

		}
		// if arraylist is null return null
		//if (assignments.size() == 0)
		//	return null;
		return assign;
	}

	// "statements()” calls “statement()"
	public ArrayList<StatementNode> Statements() throws Exception {
		ArrayList<StatementNode> statements = new ArrayList<>();

		boolean b = true;
		while(b) {
		StatementNode a = Statement();
		if(a != null) {
			statements.add(a);
		}
		else b = false;
		}
		return statements;
		
	}

	// returns the result of our assignment() function if not null
	// public ArrayList<StatementNode> Statement() throws Exception {
	public StatementNode Statement() throws Exception {
		IfNode b = null;
		WhileNode c = null;
		ForNode d = null;
		RepeatNode f = null;
	    AssignmentNode e = null;
	    FunctionCall g = null;
		
		//ArrayList<AssignmentNode> a = Assignments();
		boolean a = true;
		while (a) {
	    b = If();
	    if (b != null) {
	    	break;
	    }
		c = While();
		if(c != null) {
			break;
		}
		d = For();
		if(d != null){
		break;
		}
		f = repeat();
		if(f != null) {
			break;
		}
		//AssignmentNode e = null;
		e = Assignments();
		if (e != null) {
			break;
		}
		g = functionCall();
		if(g != null) {
			break;
		}
		if(b == null&& c == null&&d == null&&f == null&& e == null&& g == null) {
			return  null;
		}
		}
		// puts arraylist of assignments in an assignmentnode
//		if (a != null)
//			e = new AssignmentNode(a);

		
		// if not null then return statementnode
		if (b != null)
			return b;

		if (c != null)
			return c;

		if (d != null)
			return d;

		if (e != null)
			return e;

		if (f != null)
			return f;
		
		if (g != null)
			return g;

		return null;

	}

	// calls this function in parser constructor
	public ArrayList<Node> functionDefinition() throws Exception {
		ArrayList<StatementNode> statements = new ArrayList<>();
        ArrayList<Node> node = new ArrayList<>();
		// looks for “define”
        
    while(Define() == true) {
		if (matchAndRemove(Token.Type.define) == null) {
			throw new Exception(" Could not find define");
		}

		// If it find that token, it starts building a functionAST node
		// It populates the name from the identifier
		String name = lexeme.get(0).getValue();
		if (matchAndRemove(Token.Type.Identifier) == null) {
			throw new Exception("Could not find identifier");
		}

		// then looks for the left parenthesis
		if (matchAndRemove(Token.Type.LPAREN) == null) {
			// throw new Exception("Could not find parenthasis");
			throw new Exception("no Left Parernthaiss found after function declaration");
		}
		// arraylist of parameter values by calling variables function
		ArrayList<VariableNode> params = variables();
		// looks for right parenthasis
		if (matchAndRemove(Token.Type.RPAREN) == null) {
			throw new Exception("no Right Parenthasis found after function declaration");
		}
		// removes end of line
		matchAndRemove(Token.Type.EndOfLine);

		ArrayList<VariableNode> conAndvar = new ArrayList<>();
		conAndvar = constants();
		conAndvar.addAll(variables()); // adds variables and constants into one arraylist
		conAndvar.addAll(constants());
		// math and remove endofline
		matchAndRemove(Token.Type.EndOfLine);
		// calls body function
		statements = body();
		// returns function node
		FunctionNode f = new FunctionNode(name, params, conAndvar, statements);
		node.add(f);
}
    return node;
//return null;
	}

	// returns arraylist of variable nodes
	public ArrayList<VariableNode> constants() throws Exception {
		if (matchAndRemove(Token.Type.constants) == null) {
			// throw new Exception("No constants");
			// return null;
		}
		matchAndRemove(Token.Type.EndOfLine);
		return processConstants(); // returns arraylist of variable nodes
	}

	// method----name/value pairs in this format: Identifier equals number endOfLine
	public ArrayList<VariableNode> processConstants() throws Exception {
		String name;
		Token token = null;
		VariableNode.DataType datatype = null;
		Node node = null;
		ArrayList<VariableNode> variablenodes = new ArrayList<>();

		// while loop until it doesn’t find an identifier anymore
		while (Identifier()) {

			token = variableDataType(Token.Type.equal);
			if (token.getType() == Token.Type.NUMBERINT)
				datatype = VariableNode.DataType.integer;
			if (token.getType() == Token.Type.STRING);
				datatype = VariableNode.DataType.String;
			if (token.getType() == Token.Type.CHAR);
				datatype = VariableNode.DataType.Char;
			if (token.getType() == Token.Type.BOOLEAN);
				datatype = VariableNode.DataType.Boolean;
			if (token.getType() == Token.Type.NUMBERFLOAT)
				datatype = VariableNode.DataType.real;

			// while loop inside while loop
			// matches and removes and makes variablenodes
			while (Identifier()) {
				name = this.lexeme.get(0).getValue();
				if (matchAndRemove(Token.Type.Identifier) == null) {
					throw new Exception("Could not find identifier");
				}
				if (matchAndRemove(Token.Type.equal) == null) {
					throw new Exception("Could not find equal");
				}
				if (NumberIntOrNumberDec() == false) {
					throw new Exception("Could not find value");
				}
				node = expression();
				matchAndRemove(Token.Type.NUMBERINT);
				matchAndRemove(Token.Type.NUMBERFLOAT);
				matchAndRemove(Token.Type.STRING);
				matchAndRemove(Token.Type.CHAR);
				matchAndRemove(Token.Type.BOOLEAN);
				
				variablenodes.add(new VariableNode(name, datatype, node));

			}
			// when token is EndOfLine then make a variable node with the two string values
			if (matchAndRemove(Token.Type.EndOfLine) == null) {
				throw new Exception("Could not find endofline");
			}

		}
		// return arraylist of nodes
		return variablenodes;
	}

	// returns list of variable nodes
	public ArrayList<VariableNode> variables() throws Exception

	{
		String name;
		Token token = null;
		// Node node = null;
		VariableNode.DataType datatype = null;
		ArrayList<VariableNode> variablenodes = new ArrayList<VariableNode>();

		// removes variables and endofline
		matchAndRemove(Token.Type.variables);
		matchAndRemove(Token.Type.EndOfLine);

		// while Identifier
		while (IdentifierOrVar()) {
			token = variableDataType(Token.Type.colon);
			if (token.getType() == Token.Type.integer)
				datatype = VariableNode.DataType.integer;
			if (token.getType() == Token.Type.STRING)
				datatype = VariableNode.DataType.String;
			if (token.getType() == Token.Type.CHAR)
				datatype = VariableNode.DataType.Char;
			if (token.getType() == Token.Type.BOOLEAN)
				datatype = VariableNode.DataType.Boolean;
			if (token.getType() == Token.Type.real)
				datatype = VariableNode.DataType.real;

			// while loop inside while loop
			//while (Identifier()) {
			while(IdentifierOrVar()) {
				// matches and removes and creates variable nodes
				if (matchAndRemove(Token.Type.var) != null) {
					name = this.lexeme.get(0).getValue();
					matchAndRemove(Token.Type.Identifier);
					matchAndRemove(Token.Type.comma);
					VariableNode a = new VariableNode("var",name, datatype);
					variablenodes.add(a);
					
				}
				else {	
				name = this.lexeme.get(0).getValue();
				matchAndRemove(Token.Type.Identifier);
				matchAndRemove(Token.Type.comma);
				VariableNode a = new VariableNode(name, datatype);
				variablenodes.add(a);
			}
			}
			matchAndRemove(Token.Type.colon);
			matchAndRemove(Token.Type.integer);
			matchAndRemove(Token.Type.real);
			matchAndRemove(Token.Type.BOOLEAN);
			matchAndRemove(Token.Type.CHAR);
			matchAndRemove(Token.Type.STRING);
			if (matchAndRemove(Token.Type.EndOfLine) != null) {
			}
			if (matchAndRemove(Token.Type.semicolon) != null) {
			}
		} // end of while loop

		return variablenodes; // return statement

	}

	// method calls statements and checks for begin and end and throws exceptions
	public ArrayList<StatementNode> body() throws Exception {

		if (matchAndRemove(Token.Type.begin) == null) {
			throw new Exception("Could not find begin");
		}
		if (matchAndRemove(Token.Type.EndOfLine) == null) {
			throw new Exception("Could not find EndofLine");
		}

		ArrayList<StatementNode> assignments = Statements();

		if (matchAndRemove(Token.Type.end) == null) {
			throw new Exception("Could not find end");
		}
		if (matchAndRemove(Token.Type.EndOfLine) == null) {
			throw new Exception("Could not find EndOfLine");
		}
		return assignments;
	}

	// term method----TERM { (PLUS or MINUS) TERM}
	private Node expression() {
		// set left to term()
		Node left = term();
		Node right = null;
		
		while (AddOrSubtract() || BooleanOperator()){
		if((AddOrSubtract())) {
		//while (AddOrSubtract())
			// if plus token found then set right to term() and left to a new mathOpNode
			// with add
			if (matchAndRemove(Token.Type.PLUS) != null) {
				right = term();
				left = new MathOpNode(MathOpNode.Operation.add, left, right);
			}

			// if plus token found then set right to term() and left to a new mathOpNode
			// with subtract
			else if ((matchAndRemove(Token.Type.MINUS) != null)) {
				right = term();
				left = new MathOpNode(MathOpNode.Operation.subtract, left, right);
			}
		}
		
		else if (BooleanOperator()) {
			if ((matchAndRemove(Token.Type.GreaterThan) != null)) {
				//right = term();
				right = expression();
				left = new BooleanExpressionNode(left,right, Token.Type.GreaterThan);
			}
			else if ((matchAndRemove(Token.Type.LessThan) != null)) {
				//right = term();
				right = expression();
				left = new BooleanExpressionNode(left,right, Token.Type.LessThan);
				
			}
			else if ((matchAndRemove(Token.Type.GreaterThanOrEqualTo) != null)) {
				//right = term();
				right = expression();
				left = new BooleanExpressionNode(left,right, Token.Type.GreaterThanOrEqualTo);
			}
			else if ((matchAndRemove(Token.Type.LessThanOrEqualTo) != null)) {
				//right = term();
				right = expression();
				left = new BooleanExpressionNode(left,right, Token.Type.LessThanOrEqualTo);
			}
			else if ((matchAndRemove(Token.Type.equal) != null)) {
				//right = term();
				right = expression();
				left = new BooleanExpressionNode(left,right, Token.Type.equal);
			}
			else if ((matchAndRemove(Token.Type.notEqual) != null)) {
				//right = term();
				right = expression();
				left = new BooleanExpressionNode(left,right, Token.Type.notEqual);
			}
		}
		
		}
		if (left instanceof VariableReferenceNode)
			return new BooleanExpressionNode(left);
		
		return left;
	}

	// term method----FACTOR { (times or divide) FACTOR}
	private Node term() {
		// parse left side by calling factor
		Node left = factor();
		Node right = null;

		// if first token is type TIMES or Divide
		while (MultiplyOrDivideOrMod())
			if (matchAndRemove(Token.Type.TIMES) != null) {
				// right calls factor
				right = factor();
				// left creates a new node
				left = new MathOpNode(MathOpNode.Operation.multiply, left, right);
			}

			else if (matchAndRemove(Token.Type.DIVIDE) != null) {
				right = factor();// right calls factor
				left = new MathOpNode(MathOpNode.Operation.divide, left, right);// creats new node
			} else if (matchAndRemove(Token.Type.mod) != null) {
				right = factor();// right calls factor
				left = new MathOpNode(MathOpNode.Operation.mod, left, right);// creats new node
			}

		// return left
		return left;
	}

	// FACTOR = {-} number or lparen EXPRESSION rparen
	public Node factor() {
		Node left = null; // sets left to null

		// if float number found then return a float node
		Token token = matchAndRemove(Token.Type.NUMBERFLOAT);
		if (token != null) {
			return new FloatNode(Float.parseFloat(token.getValue()));
		}

		// if intriguer number found then return an integer node
		token = matchAndRemove(Token.Type.NUMBERINT);
		if (token != null) {
			return new IntegerNode(Integer.parseInt(token.getValue()));
		}
		token = matchAndRemove(Token.Type.TRUE);
		if (token != null) {
			return new BoolNode(true);
		}
		
		token = matchAndRemove(Token.Type.FALSE);
		if (token != null) {
			return new BoolNode(false);
		}
		
		token = matchAndRemove(Token.Type.charContents);
		if (token != null) {
			return new CharNode((token.getValue()).charAt(0));
			
		}
		token = matchAndRemove(Token.Type.stringContents);
		if (token != null) {
			return new StringNode(token.getValue());
		}
		
		
		token = matchAndRemove(Token.Type.Identifier);
		if (token != null) {
			return new VariableReferenceNode(token.getValue());
		}
		// if left parenthesis found call expression and then check for right
		// parenthesis
		// if right parenthesis not found then return null
		if (matchAndRemove(Token.Type.LPAREN) != null) {
			left = expression();
		}
		if (left == null)
			return null;

		if (matchAndRemove(Token.Type.RPAREN) != null)
			return left;

		else
			return null;

	}

	// if the next token in line is of a specific tokenType, remove it from the list
	// and return it
	public Token matchAndRemove(Token.Type tokenType) {
		Token returntoken = null;
		// if first token in list is of specified tokenType
		if (this.lexeme.get(0).getType() == tokenType) {
			returntoken = lexeme.get(0); // return current token
			lexeme.remove(0); // remove token
		}

		return returntoken; // return statement
	}

	// boolean method to check if first token is of type MINUS or PLUS
	public boolean AddOrSubtract() {

		if (this.lexeme.get(0).getType() == Token.Type.MINUS) {
			return true;
		}
		if (this.lexeme.get(0).getType() == Token.Type.PLUS) {
			return true;
		} else
			return false;
	}
	
	
	public boolean BooleanOperator() {

		if (this.lexeme.get(0).getType() == Token.Type.GreaterThan) {
			return true;
		}
		if (this.lexeme.get(0).getType() == Token.Type.LessThan) {
			return true;
		}
		if (this.lexeme.get(0).getType() == Token.Type.LessThanOrEqualTo) {
			return true;
		}
		if (this.lexeme.get(0).getType() == Token.Type.notEqual) {
			return true;
		}
		if (this.lexeme.get(0).getType() == Token.Type.equal) {
			return true;
		}
		if (this.lexeme.get(0).getType() == Token.Type.GreaterThanOrEqualTo) {
			return true;
		} else
			return false;
	}

	// boolean method to check if first token is of type MINUS or PLUS
	public boolean MultiplyOrDivideOrMod() {

		if (this.lexeme.get(0).getType() == Token.Type.TIMES) {
			return true;
		}
		if (this.lexeme.get(0).getType() == Token.Type.DIVIDE) {
			return true;
		}
		if (this.lexeme.get(0).getType() == Token.Type.mod) {
			return true;
		} else
			return false;
	}

	// boolean method to check if first token is of NUMBERINT or NUMBERFLOAT
	public boolean NumberIntOrNumberDec() {
		if (this.lexeme.get(0).getType() == Token.Type.NUMBERINT) {
			return true;
		}
		if (this.lexeme.get(0).getType() == Token.Type.NUMBERFLOAT) {
			return true;
		} else
			return false;
	}

	public boolean define() {
		if (this.lexeme.get(0).getType() == Token.Type.define) {
			return true;
		} else
			return false;
	}

	public boolean IfEleifOrElse() {
		if (this.lexeme.get(0).getType() == Token.Type.ELSE) {
			return true;
		}
		if (this.lexeme.get(0).getType() == Token.Type.elsif) {
			return true;
		}
		if (this.lexeme.get(0).getType() == Token.Type.IF) {
			return true;
		} else
			return false;
	}

	private boolean IfEleif() {
		if (this.lexeme.get(0).getType() == Token.Type.elsif) {
			return true;
		}
		// TODO Auto-generated method stub
		return false;
	}

	private boolean IfElse() {
		if (this.lexeme.get(0).getType() == Token.Type.ELSE) {
			return true;
		}
		// TODO Auto-generated method stub
		return false;
	}

	// boolean method to check if first token is of type Identifier
	public boolean Identifier() {

		if (this.lexeme.get(0).getType() == Token.Type.Identifier) {
			return true;
		} else
			return false;
	}
	public boolean Define() {

		if(this.lexeme.isEmpty() == true)
			return false;
		if (this.lexeme.get(0).getType() == Token.Type.define) {
			return true;
			
		} 
		
		else
			return false;
	}

	public boolean IdentifierOrVar() {
		if (this.lexeme.get(0).getType() == Token.Type.var) {
			return true;
		}
		if (this.lexeme.get(0).getType() == Token.Type.Identifier) {
			return true;
		
		} else
			return false;
	}
	
	public boolean Var() {

		if (this.lexeme.get(0).getType() == Token.Type.var) {
			return true;
		} else
			return false;
	}
	
	
	// checks for specified token and if it is there it returns the next token
	public Token variableDataType(Token.Type tokenType) {
		Token returntoken = null;
		// looping through the list
		for (int i = 0; i < lexeme.size(); i++) {
			if (lexeme.get(i).getType() == tokenType) {
				return lexeme.get(i + 1);
			}
		}
		return returntoken; // return statement
	}

}


