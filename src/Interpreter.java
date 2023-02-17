
import java.util.ArrayList;
import java.util.HashMap;

public class Interpreter {
	static HashMap<String, InterpreterDataType> variables = new HashMap<String, InterpreterDataType>();

	static HashMap<String, CallableNode> cal = new HashMap<>();

	public static HashMap<String, CallableNode> getCal() {
		cal.put("write", null);
		cal.put("read", null);
		cal.put("getRandom", null);
		cal.put("squareRoot", null);
		cal.put("intToReal", null);
		cal.put("realToInt", null);
		cal.put("right", null);
		cal.put("left", null);
		cal.put("substring", null);
		return cal;
	}

	public static void setCal(HashMap<String, CallableNode> cal) {
		Interpreter.cal = cal;
	}

	// resolve method takes a node as parameter and returns float value
	public static float resolve(Node n) {

		// if n is of type Integer node
		if (n instanceof IntegerNode)
			return ((IntegerNode) n).getNumber(); // return value

		// if n is of type Float node
		if (n instanceof FloatNode)
			return ((FloatNode) n).getNumber(); // return value

		if (n instanceof VariableReferenceNode) {
			String vrn = ((VariableReferenceNode) n).ToString();
			InterpreterDataType idt = variables.get(vrn);
			if (idt instanceof FloatDataType)
				return ((FloatDataType) idt).getValue();
			else if (idt instanceof IntDataType)
				return ((IntDataType) idt).getTypevalue();
		}

		// if n is of type MathOpNode
		if (n instanceof MathOpNode) {
			if (((MathOpNode) n).getOp() == "add")
				// calls resolve on left and right and adds
				return resolve(((MathOpNode) n).getOperandLeft()) + resolve(((MathOpNode) n).getOperandRight());
			if (((MathOpNode) n).getOp() == "subtract")
				// calls resolve on left and right and subtracts
				return resolve(((MathOpNode) n).getOperandLeft()) - resolve(((MathOpNode) n).getOperandRight());
			if (((MathOpNode) n).getOp() == "multiply")
				// calls resolve on left and right and multiplies
				return resolve(((MathOpNode) n).getOperandLeft()) * resolve(((MathOpNode) n).getOperandRight());
			if (((MathOpNode) n).getOp() == "divide")
				// calls resolve on left and right and divides
				return resolve(((MathOpNode) n).getOperandLeft()) / resolve(((MathOpNode) n).getOperandRight());
		}
		return 0;
	}
	
	public static String resolveString(Node n) {
		if (n instanceof StringNode)
			return ((StringNode) n).getStr();
		
		// if n is of type Float node
		if (n instanceof VariableReferenceNode) {
			String vrn = ((VariableReferenceNode) n).ToString();
			InterpreterDataType idt = variables.get(vrn);
			if (idt instanceof StringDataType)
				return ((StringDataType) idt).getValue();
		}
		if (n instanceof MathOpNode) {
			if (((MathOpNode) n).getOp() == "add") {
				if(((MathOpNode) n).getOperandLeft() instanceof StringNode && ((MathOpNode) n).getOperandRight() instanceof StringNode)
				return resolveString(((MathOpNode) n).getOperandLeft()) + resolveString(((MathOpNode) n).getOperandRight());
			
				else if(((MathOpNode) n).getOperandLeft() instanceof StringNode && ((MathOpNode) n).getOperandRight() instanceof CharNode)
					return resolveString(((MathOpNode) n).getOperandLeft()) + resolveCharacter(((MathOpNode) n).getOperandRight());
				
				else if(((MathOpNode) n).getOperandLeft() instanceof CharNode && ((MathOpNode) n).getOperandRight() instanceof StringNode)
					return resolveCharacter(((MathOpNode) n).getOperandLeft()) + resolveString(((MathOpNode) n).getOperandRight());
				else if(((MathOpNode) n).getOperandLeft() instanceof VariableReferenceNode && ((MathOpNode) n).getOperandRight() instanceof StringNode)
					return resolveString(((MathOpNode) n).getOperandLeft()) + resolveString(((MathOpNode) n).getOperandRight());
				else if(((MathOpNode) n).getOperandLeft() instanceof StringNode && ((MathOpNode) n).getOperandRight() instanceof VariableReferenceNode)
					return resolveString(((MathOpNode) n).getOperandLeft()) + resolveString(((MathOpNode) n).getOperandRight());
				else if(((MathOpNode) n).getOperandLeft() instanceof VariableReferenceNode && ((MathOpNode) n).getOperandRight() instanceof VariableReferenceNode)
					return resolveString(((MathOpNode) n).getOperandLeft()) + resolveString(((MathOpNode) n).getOperandRight());
				else if(((MathOpNode) n).getOperandLeft() instanceof VariableReferenceNode && ((MathOpNode) n).getOperandRight() instanceof CharNode)
					return resolveString(((MathOpNode) n).getOperandLeft()) + resolveCharacter(((MathOpNode) n).getOperandRight());
				else if(((MathOpNode) n).getOperandLeft() instanceof CharNode && ((MathOpNode) n).getOperandRight() instanceof VariableReferenceNode)
					return resolveCharacter(((MathOpNode) n).getOperandLeft()) + resolveString(((MathOpNode) n).getOperandRight());
		
		}
		}
		return null;
		
	}
	
	public static char resolveCharacter(Node n) {
		if (n instanceof CharNode)
			return ((CharNode) n).getCha();
		
		// if n is of type Float node
		if (n instanceof VariableReferenceNode) {
			String vrn = ((VariableReferenceNode) n).ToString();
			InterpreterDataType idt = variables.get(vrn);
			if (idt instanceof CharacterDataType)
				return ((CharacterDataType) idt).getValue();
		}
		return '\0';
		
	}
	
	public static boolean resolveBoolean(Node n) {
		float left = 0;
		float right = 0;
		Boolean left1;
		Boolean right1 = null;
		if (n instanceof BoolNode)
			return ((BoolNode) n).getBool();
		
		// if n is of type Float node
		if (n instanceof VariableReferenceNode) {
			String vrn = ((VariableReferenceNode) n).ToString();
			InterpreterDataType idt = variables.get(vrn);
			if (idt instanceof BooleanDataType)
				return ((BooleanDataType) idt).getValue();
		}
		
		
		if (n instanceof BooleanExpressionNode) {
			if(((BooleanExpressionNode) n).variable != null) {
				String vrn = ((BooleanExpressionNode) n).variable.ToString();
				InterpreterDataType idt = variables.get(vrn);
				if (idt instanceof BooleanDataType)
					return ((BooleanDataType) idt).getValue();
			}
			
			if (((BooleanExpressionNode) n).getCondition() == Token.Type.GreaterThan ) {
				if(((BooleanExpressionNode) n).Left instanceof FloatNode || ((BooleanExpressionNode) n).Left instanceof IntegerNode
						|| ((BooleanExpressionNode) n).Left instanceof MathOpNode || ((BooleanExpressionNode) n).Left instanceof VariableReferenceNode
						|| ((BooleanExpressionNode) n).Left instanceof BoolNode)
				left = resolve(((BooleanExpressionNode) n).getLeft());
				if(((BooleanExpressionNode) n).Right instanceof FloatNode || ((BooleanExpressionNode) n).Right instanceof IntegerNode
						|| ((BooleanExpressionNode) n).Right instanceof MathOpNode|| ((BooleanExpressionNode) n).Right instanceof VariableReferenceNode)
				right = resolve(((BooleanExpressionNode) n).getRight());
				if (left > right)
				return true;
			}
            if (((BooleanExpressionNode) n).getCondition() == Token.Type.LessThan ) {
            	if(((BooleanExpressionNode) n).Left instanceof FloatNode || ((BooleanExpressionNode) n).Left instanceof IntegerNode
						|| ((BooleanExpressionNode) n).Left instanceof MathOpNode|| ((BooleanExpressionNode) n).Left instanceof VariableReferenceNode)
				left = resolve(((BooleanExpressionNode) n).getLeft());
				if(((BooleanExpressionNode) n).Right instanceof FloatNode || ((BooleanExpressionNode) n).Right instanceof IntegerNode
						|| ((BooleanExpressionNode) n).Right instanceof MathOpNode|| ((BooleanExpressionNode) n).Right instanceof VariableReferenceNode)
				right = resolve(((BooleanExpressionNode) n).getRight());
				if (left < right)
				return true;
			}
            if (((BooleanExpressionNode) n).getCondition() == Token.Type.GreaterThanOrEqualTo ) {
            	if(((BooleanExpressionNode) n).Left instanceof FloatNode || ((BooleanExpressionNode) n).Left instanceof IntegerNode
						|| ((BooleanExpressionNode) n).Left instanceof MathOpNode|| ((BooleanExpressionNode) n).Left instanceof VariableReferenceNode)
				left = resolve(((BooleanExpressionNode) n).getLeft());
				if(((BooleanExpressionNode) n).Right instanceof FloatNode || ((BooleanExpressionNode) n).Right instanceof IntegerNode
						|| ((BooleanExpressionNode) n).Right instanceof MathOpNode|| ((BooleanExpressionNode) n).Right instanceof VariableReferenceNode)
				right = resolve(((BooleanExpressionNode) n).getRight());
				if (left >= right)
				return true;
			}
            if (((BooleanExpressionNode) n).getCondition() == Token.Type.LessThanOrEqualTo ) {
            	if(((BooleanExpressionNode) n).Left instanceof FloatNode || ((BooleanExpressionNode) n).Left instanceof IntegerNode
						|| ((BooleanExpressionNode) n).Left instanceof MathOpNode|| ((BooleanExpressionNode) n).Left instanceof VariableReferenceNode)
				left = resolve(((BooleanExpressionNode) n).getLeft());
				if(((BooleanExpressionNode) n).Right instanceof FloatNode || ((BooleanExpressionNode) n).Right instanceof IntegerNode
						|| ((BooleanExpressionNode) n).Right instanceof MathOpNode|| ((BooleanExpressionNode) n).Right instanceof VariableReferenceNode)
				right = resolve(((BooleanExpressionNode) n).getRight());
				if (left <= right)
				return true;
			}
            if (((BooleanExpressionNode) n).getCondition() == Token.Type.equal ) {
				if(((BooleanExpressionNode) n).Left instanceof FloatNode || ((BooleanExpressionNode) n).Left instanceof IntegerNode
						|| ((BooleanExpressionNode) n).Left instanceof MathOpNode) {
				left = resolve(((BooleanExpressionNode) n).getLeft());
            	
				if(((BooleanExpressionNode) n).Right instanceof FloatNode || ((BooleanExpressionNode) n).Right instanceof IntegerNode
						|| ((BooleanExpressionNode) n).Right instanceof MathOpNode|| ((BooleanExpressionNode) n).Right instanceof VariableReferenceNode)
				right = resolve(((BooleanExpressionNode) n).getRight());
				
				if (Float.compare(left, right) == 0)
					return true;
				}
				
				if ((((BooleanExpressionNode) n).Left instanceof VariableReferenceNode|| ((BooleanExpressionNode) n).Left instanceof BoolNode)) {
	            	left1 = resolveBoolean(((BooleanExpressionNode) n).getLeft());
				if ((((BooleanExpressionNode) n).Left instanceof VariableReferenceNode|| ((BooleanExpressionNode) n).Left instanceof BoolNode))
					right1 = resolveBoolean(((BooleanExpressionNode) n).getRight());
				
				if(Boolean.compare(left1, right1)==0) {
					return true;
				}
	
				}
			}
            if (((BooleanExpressionNode) n).getCondition() == Token.Type.notEqual) {
            	
            	if(((BooleanExpressionNode) n).Left instanceof FloatNode || ((BooleanExpressionNode) n).Left instanceof IntegerNode
						|| ((BooleanExpressionNode) n).Left instanceof MathOpNode) {
				left = resolve(((BooleanExpressionNode) n).getLeft());
            	
				if(((BooleanExpressionNode) n).Right instanceof FloatNode || ((BooleanExpressionNode) n).Right instanceof IntegerNode
						|| ((BooleanExpressionNode) n).Right instanceof MathOpNode|| ((BooleanExpressionNode) n).Right instanceof VariableReferenceNode)
				right = resolve(((BooleanExpressionNode) n).getRight());
				
				if (Float.compare(left, right) != 0)
					return true;
				}
				
				if ((((BooleanExpressionNode) n).Left instanceof VariableReferenceNode|| ((BooleanExpressionNode) n).Left instanceof BoolNode)) {
	            	left1 = resolveBoolean(((BooleanExpressionNode) n).getLeft());
				if ((((BooleanExpressionNode) n).Left instanceof VariableReferenceNode|| ((BooleanExpressionNode) n).Left instanceof BoolNode))
					right1 = resolveBoolean(((BooleanExpressionNode) n).getRight());
				
				if(Boolean.compare(left1, right1)!=0) {
					return true;
				}
	
				}
			}
           
		}
		return false;
	}
	
	
	

	// it should take a FunctionNode (i.e. the function to interpret)
	// and a collection of InterpreterDataType – the parameters to the function.
	public static void InterpretFunction(FunctionNode f, ArrayList<InterpreterDataType> idt) throws Exception {

		int i;

		// checks each parameter in function
		for (i = 0; i < f.Params.size(); i++) {
			// if parameter is type interger than add an IntdataType to hashmap
			if (f.Params.get(i).getdatatype().equals(VariableNode.DataType.integer) == true) {
				IntDataType id = new IntDataType();
				variables.put(f.Params.get(i).getName(), id);
			}
			// if parameter is type float then add a floatdatatype to hashmap
			if (f.Params.get(i).getdatatype().equals(VariableNode.DataType.real) == true) {
				variables.put(f.Params.get(i).getName(), new FloatDataType());
			}
			if (f.Params.get(i).getdatatype().equals(VariableNode.DataType.Boolean) == true) {
				variables.put(f.Params.get(i).getName(), new BooleanDataType());
			}
			if (f.Params.get(i).getdatatype().equals(VariableNode.DataType.Char) == true) {
				variables.put(f.Params.get(i).getName(), new CharacterDataType());
			}
			if (f.Params.get(i).getdatatype().equals(VariableNode.DataType.String) == true) {
				variables.put(f.Params.get(i).getName(), new StringDataType());
			}
		}

		// checks each constant and local variables
		for (i = 0; i < f.Vars.size(); i++) {
			// if constant that is integer type then add intdatatype with value set
			if (f.Vars.get(i).getdatatype().equals(VariableNode.DataType.integer) == true
					&& f.Vars.get(i).getValue() != null) {
				Integer d = Integer.parseInt(f.Vars.get(i).getValue().ToString());
				IntDataType s = new IntDataType();
				variables.put(f.Vars.get(i).getName(), s);
				s.setTypevalue(d);

			}
			// if variable with integer type add intdatatype to hashmap
			if (f.Vars.get(i).getdatatype().equals(VariableNode.DataType.integer) == true
					&& f.Vars.get(i).getValue() == null) {
				variables.put(f.Vars.get(i).getName(), new IntDataType());
			}
			// if constant with real data type then add realdatatype tp hashmap and sets
			// initial value
			if (f.Vars.get(i).getdatatype().equals(VariableNode.DataType.real) == true
					&& f.Vars.get(i).getValue() != null) {
				Float d = Float.parseFloat(f.Vars.get(i).getValue().ToString());
				FloatDataType r = new FloatDataType();
				variables.put(f.Vars.get(i).getName(), r);
				r.setValue(d);
			}
			// if variable of real datatype add to hashmap
			if (f.Vars.get(i).getdatatype().equals(VariableNode.DataType.real) == true
					&& f.Vars.get(i).getValue() == null) {
				variables.put(f.Vars.get(i).getName(), new FloatDataType());
			}
			
			// if constant that is integer type then add intdatatype with value set
			if (f.Vars.get(i).getdatatype().equals(VariableNode.DataType.Boolean) == true
					&& f.Vars.get(i).getValue() != null) {
			Boolean d = Boolean.valueOf(f.Vars.get(i).getValue().ToString());
			BooleanDataType s = new BooleanDataType();
			variables.put(f.Vars.get(i).getName(), s);
			s.setValue(d);

			}
			// if variable with integer type add intdatatype to hashmap
			if (f.Vars.get(i).getdatatype().equals(VariableNode.DataType.Boolean) == true
				&& f.Vars.get(i).getValue() == null) {
			variables.put(f.Vars.get(i).getName(), new BooleanDataType());
			}
			
			
			if (f.Vars.get(i).getdatatype().equals(VariableNode.DataType.Char) == true
					&& f.Vars.get(i).getValue() != null) {
			char d = Character.valueOf(f.Vars.get(i).getValue().ToString().charAt(0));
			CharacterDataType s = new CharacterDataType();
			variables.put(f.Vars.get(i).getName(), s);
			s.setValue(d);

			}
			// if variable with integer type add intdatatype to hashmap
			if (f.Vars.get(i).getdatatype().equals(VariableNode.DataType.Char) == true
				&& f.Vars.get(i).getValue() == null) {
			variables.put(f.Vars.get(i).getName(), new CharacterDataType());
			}
			
			if (f.Vars.get(i).getdatatype().equals(VariableNode.DataType.String) == true
					&& f.Vars.get(i).getValue() != null) {
			String d = String.valueOf(f.Vars.get(i).getValue().ToString());
			StringDataType s = new StringDataType();
			variables.put(f.Vars.get(i).getName(), s);
			s.setValue(d);

			}
			// if variable with integer type add intdatatype to hashmap
			if (f.Vars.get(i).getdatatype().equals(VariableNode.DataType.String) == true
				&& f.Vars.get(i).getValue() == null) {
			variables.put(f.Vars.get(i).getName(), new StringDataType());
			}
				

		}

		// if list is not null(if function is not start)
		if (idt != null) {
			for (i = 0; i < idt.size(); i++) {
				// checks if datatypes of parameters and idt list match
				if (idt.get(i) instanceof IntDataType
						&& f.Params.get(i).getdatatype() == VariableNode.DataType.integer) {
				} 
				else if (idt.get(i) instanceof FloatDataType
						&& f.Params.get(i).getdatatype() == VariableNode.DataType.real) {
					
				} 
				else if (idt.get(i) instanceof StringDataType
						&& f.Params.get(i).getdatatype() == VariableNode.DataType.String) {
					
				}
				else if (idt.get(i) instanceof CharacterDataType
						&& f.Params.get(i).getdatatype() == VariableNode.DataType.Char) {
					
				}
				else if (idt.get(i) instanceof BooleanDataType
						&& f.Params.get(i).getdatatype() == VariableNode.DataType.Boolean) {
					
				}
				else
					throw new Exception("parameter types dont match");
			}
		}
		// calls interpretblock function
		InterpretBlock(f.stat, variables);

	}

	// InterpretBlock should take the collection of statements and a hashmap of
	// variables.
	public static void InterpretBlock(ArrayList<StatementNode> s, HashMap<String, InterpreterDataType> variables)
			throws Exception {
		int i;
		// loops statement nodes
		for (i = 0; i < s.size(); i++) {
			// if functioncall statementnode
			if (s.get(i) instanceof FunctionCall) {
				String a = ((FunctionCall) s.get(i)).getName();
				ArrayList<InterpreterDataType> idt = new ArrayList<InterpreterDataType>();

				// inbuilt variadic function
				if (a.equals("read")) {
					boolean empty = ((FunctionCall) s.get(i)).getParam().isEmpty();
					boolean var = false;
					if (empty == true)
						// throws exception if no parameters
						throw new Exception("no parameter");

					int size = ((FunctionCall) s.get(i)).getParam().size();
					// loops through parameter of read function
					for (int j = 0; j < size; j++) {
						String y = ((FunctionCall) s.get(i)).getParam().get(j).var.getName();
						var = ((FunctionCall) s.get(i)).getParam().get(i).var.isVar();
						// adds datatypes to list
						if (variables.containsKey(y)) {
							InterpreterDataType k = variables.get(y);
							idt.add(k);
						}
					}

					// only execute if variable has var or else throw exception
					if (var == true) {
						Read r = new Read();
						r.execute(idt);
					} else
						throw new Exception("cannot update variable that is not var");

				}

				else if (a.equals("write")) {
					boolean empty = ((FunctionCall) s.get(i)).getParam().isEmpty();
					if (empty == true)
						throw new Exception("no parameter");

					int size = ((FunctionCall) s.get(i)).getParam().size();
					// loops through parameters of write function
					for (int j = 0; j < size; j++) {

						String y = ((FunctionCall) s.get(i)).getParam().get(j).var.ToString();
						if (variables.containsKey(y)) {
							InterpreterDataType k = variables.get(y);
							idt.add(k);
						}
					}
					// calls code for inbuiltfunction
					Write w = new Write();
					w.execute(idt);

				} else if (a.equals("getRandom")) {
					// can only have 1 parameters for random function
					int size = ((FunctionCall) s.get(i)).getParam().size();
					if (size != 1)
						throw new Exception("parameter sizes don't match");

					String y = ((FunctionCall) s.get(i)).getParam().get(0).var.ToString();
					Boolean var = ((FunctionCall) s.get(i)).getParam().get(0).var.isVar();
					if (variables.containsKey(y)) {
						// adds datatypes to list
						InterpreterDataType k = variables.get(y);
						idt.add(k);
					}
					// only execute if variable is var or else throw exception
					if (var == true) {
						GetRandom gr = new GetRandom();
						gr.execute(idt);
					}

					else
						throw new Exception("cannot update variable that is not var");

				}

				else if (a.equals("squareRoot")) {
					// can only have 2 parameters for this function
					int size = ((FunctionCall) s.get(i)).getParam().size();
					if (size != 2)
						throw new Exception("parameter sizes don't match");

					// checks if first parameter is a constant and if it is execute this block
					if (((FunctionCall) s.get(i)).getParam().get(0).con != null) {
						String f = ((FunctionCall) s.get(i)).getParam().get(0).con.ToString();
						Float fl = Float.parseFloat(f);
						// first parameter must be of float type
						FloatDataType l = new FloatDataType();
						// set value to the value of the constant
						l.setValue(fl);
						idt.add(l);

						String u = ((FunctionCall) s.get(i)).getParam().get(1).var.ToString();
						Boolean var = ((FunctionCall) s.get(i)).getParam().get(1).var.isVar();
						if (variables.containsKey(u)) {
							// second parameter must be of int type
							InterpreterDataType k = variables.get(u);
							idt.add(k);
							// if second parameter is var variable execute block
							if (var == true) {
								SquareRoot sq = new SquareRoot();
								sq.execute(idt);
							}

							else
								throw new Exception("cannot update variable that is not var");
						}
					}
					// if first parameter is not constant execute this block
					else {
						// adds datatypes to list
						String y = ((FunctionCall) s.get(i)).getParam().get(0).var.ToString();
						if (variables.containsKey(y)) {
							InterpreterDataType k = variables.get(y);
							idt.add(k);
						}

						String u = ((FunctionCall) s.get(i)).getParam().get(1).var.ToString();
						Boolean var = ((FunctionCall) s.get(i)).getParam().get(1).var.isVar();
						if (variables.containsKey(u)) {
							InterpreterDataType k = variables.get(u);
							idt.add(k);
						}
						// if second type is var variable execute block
						if (var == true) {
							SquareRoot sq = new SquareRoot();
							sq.execute(idt);
						}

						else
							throw new Exception("cannot update variable that is not var");

					}
				}

				else if (a.equals("intToReal")) {
					// only two parameters allowed
					int size = ((FunctionCall) s.get(i)).getParam().size();
					if (size != 2)
						throw new Exception("parameter sizes don't match");

					// checks if first parameter is a constant and if it is execute this block
					if (((FunctionCall) s.get(i)).getParam().get(0).con != null) {
						String f = ((FunctionCall) s.get(i)).getParam().get(0).con.ToString();
						// parse constant value to Float
						Integer in = Integer.parseInt(f);
						// first parameter must be of int data type
						IntDataType l = new IntDataType();
						l.setTypevalue(in);
						idt.add(l);

						String u = ((FunctionCall) s.get(i)).getParam().get(1).var.ToString();
						Boolean var = ((FunctionCall) s.get(i)).getParam().get(1).var.isVar();
						if (variables.containsKey(u)) {
							InterpreterDataType k = variables.get(u);
							idt.add(k);
							// only call function if second variable is var variable
							if (var == true) {
								IntegerToReal itr = new IntegerToReal();
								itr.execute(idt);
							}

							else
								throw new Exception("cannot update variable that is not var");

						}
					}
					// if value is not constant execute this block
					else {
						// this block checks to see if first parameter is int type and second parameter
						// is float type and then if the second parameter
						// is var variable it executes the called function
						String y = ((FunctionCall) s.get(i)).getParam().get(0).var.ToString();
						if (variables.containsKey(y)) {
							InterpreterDataType k = variables.get(y);
							idt.add(k);
						}

						String u = ((FunctionCall) s.get(i)).getParam().get(1).var.ToString();
						Boolean var = ((FunctionCall) s.get(i)).getParam().get(1).var.isVar();
						if (variables.containsKey(u)) {
							InterpreterDataType k = variables.get(u);
							idt.add(k);
						}
						if (var == true) {
							IntegerToReal itr = new IntegerToReal();
							itr.execute(idt);
						}

						else
							throw new Exception("cannot update variable that is not var");

					}
				}

				else if (a.equals("realToInt")) {
					// can only have 2 parameters
					int size = ((FunctionCall) s.get(i)).getParam().size();
					if (size != 2)
						throw new Exception("parameter sizes don't match");
					// if first parameter is constant execute this block
					// this block checks parameter types and if the second paramter is var variable
					// and then calls the execute method
					if (((FunctionCall) s.get(i)).getParam().get(0).con != null) {
						String f = ((FunctionCall) s.get(i)).getParam().get(0).con.ToString();
						Float fl = Float.parseFloat(f);
						FloatDataType l = new FloatDataType();
						l.setValue(fl);
						idt.add(l);

						String u = ((FunctionCall) s.get(i)).getParam().get(1).var.ToString();
						Boolean var = ((FunctionCall) s.get(i)).getParam().get(1).var.isVar();
						if (variables.containsKey(u)) {
							InterpreterDataType k = variables.get(u);
							idt.add(k);
							if (var == true) {
								RealToInteger rti = new RealToInteger();
								rti.execute(idt);
							}

							else
								throw new Exception("cannot update variable that is not var");

						}
					}
					// this block checks to see if first parameter is float type and second
					// parameter is int type and then if the second parameter
					// is var variable it executes the called function
					else {
						String y = ((FunctionCall) s.get(i)).getParam().get(0).var.ToString();
						if (variables.containsKey(y)) {
							InterpreterDataType k = variables.get(y);
							idt.add(k);
						}

						String u = ((FunctionCall) s.get(i)).getParam().get(1).var.ToString();
						Boolean var = ((FunctionCall) s.get(i)).getParam().get(1).var.isVar();
						if (variables.containsKey(u)) {
							InterpreterDataType k = variables.get(u);
							idt.add(k);
						}
						if (var == true) {
							RealToInteger rti = new RealToInteger();
							rti.execute(idt);
						}

						else
							throw new Exception("cannot update variable that is not var");
					}
				}
				
				else if (a.equals("right")) {
					int size = ((FunctionCall) s.get(i)).getParam().size();
					if (size != 3)
						throw new Exception("parameter sizes don't match for right function");
					
					if (((FunctionCall) s.get(i)).getParam().get(0).var != null) {
						String f = ((FunctionCall) s.get(i)).getParam().get(0).var.ToString();
						if (variables.containsKey(f)) {
							InterpreterDataType k = variables.get(f);
							idt.add(k);
						}
					}
					
					if (((FunctionCall) s.get(i)).getParam().get(1) != null) {
						String f = ((FunctionCall) s.get(i)).getParam().get(1).var.ToString();
						if(variables.containsKey(f)) {
							InterpreterDataType k = variables.get(f);
							idt.add(k);
						}
					}
					
					if (((FunctionCall) s.get(i)).getParam().get(2) != null) {
						String f = ((FunctionCall) s.get(i)).getParam().get(2).var.ToString();
						if(variables.containsKey(f)) {
							InterpreterDataType k = variables.get(f);
							idt.add(k);
						}
						
					}
					Boolean var = ((FunctionCall) s.get(i)).getParam().get(2).var.isVar();
						if (var == true) {
							Right rti = new Right();
							rti.execute(idt);
						}

						else
							throw new Exception("cannot update variable that is not var");

					}
				
                else if (a.equals("left")) {
    					int size = ((FunctionCall) s.get(i)).getParam().size();
    					if (size != 3)
    						throw new Exception("parameter sizes don't match for right function");
    					
    					if (((FunctionCall) s.get(i)).getParam().get(0).var != null) {
    						String f = ((FunctionCall) s.get(i)).getParam().get(0).var.ToString();
    						if (variables.containsKey(f)) {
    							InterpreterDataType k = variables.get(f);
    							idt.add(k);
    						}
    					}
    					
    					if (((FunctionCall) s.get(i)).getParam().get(1) != null) {
    						String f = ((FunctionCall) s.get(i)).getParam().get(1).var.ToString();
    						if(variables.containsKey(f)) {
    							InterpreterDataType k = variables.get(f);
    							idt.add(k);
    						}
    					}
    					
    					if (((FunctionCall) s.get(i)).getParam().get(2) != null) {
    						String f = ((FunctionCall) s.get(i)).getParam().get(2).var.ToString();
    						if(variables.containsKey(f)) {
    							InterpreterDataType k = variables.get(f);
    							idt.add(k);
    						}
    						
    					}
    					Boolean var = ((FunctionCall) s.get(i)).getParam().get(2).var.isVar();
    						if (var == true) {
    							Left rti = new Left();
    							rti.execute(idt);
    						}

    						else
    							throw new Exception("cannot update variable that is not var");

    					}
					
                else if (a.equals("substring")) {
                	int size = ((FunctionCall) s.get(i)).getParam().size();
					if (size != 4)
						throw new Exception("parameter sizes don't match for right function");
					
					if (((FunctionCall) s.get(i)).getParam().get(0).var != null) {
						String f = ((FunctionCall) s.get(i)).getParam().get(0).var.ToString();
						if (variables.containsKey(f)) {
							InterpreterDataType k = variables.get(f);
							idt.add(k);
						}
					}
					
					if (((FunctionCall) s.get(i)).getParam().get(1) != null) {
						String f = ((FunctionCall) s.get(i)).getParam().get(1).var.ToString();
						if(variables.containsKey(f)) {
							InterpreterDataType k = variables.get(f);
							idt.add(k);
						}
					}
					if (((FunctionCall) s.get(i)).getParam().get(2) != null) {
						String f = ((FunctionCall) s.get(i)).getParam().get(2).var.ToString();
						if(variables.containsKey(f)) {
							InterpreterDataType k = variables.get(f);
							idt.add(k);
						}
					}
					if (((FunctionCall) s.get(i)).getParam().get(3) != null) {
						String f = ((FunctionCall) s.get(i)).getParam().get(3).var.ToString();
						if(variables.containsKey(f)) {
							InterpreterDataType k = variables.get(f);
							idt.add(k);
						}
						
					}
					Boolean var = ((FunctionCall) s.get(i)).getParam().get(3).var.isVar();
						if (var == true) {
							Substring rti = new Substring();
							rti.execute(idt);
						}

						else
							throw new Exception("cannot update variable that is not var");

					}
				

				// if function call is not a builtin function then execute this block
				else {

					FunctionNode function = null;

					int size = ((FunctionCall) s.get(i)).getParam().size();
					// loops through parameters of function call
					for (int j = 0; j < size; j++) {
						// get the name of the function
						String name = ((FunctionCall) s.get(i)).getName();
						// checks to see if it is in hashmap and if it is then get that function
						if (cal.containsKey(name)) {
							function = (FunctionNode) cal.get(name);
						}

						String y = ((FunctionCall) s.get(i)).getParam().get(j).var.ToString();
						if (variables.containsKey(y)) {
							// add the Interpreterdatatypes
							InterpreterDataType k = variables.get(y);
							idt.add(k);
						}

					}
					// only calls InterpretFunction if the parameters of the functioncall match the
					// parameters of the called function
					if (idt.size() == function.Params.size()) {
						InterpretFunction(function, idt);

					} else
						throw new Exception("Parameter sizes of function and function call don't match");

				}

			}

			else if (s.get(i) instanceof AssignmentNode) {
				String str = null;
				AssignmentNode va = ((AssignmentNode) s.get(i));

				str = va.target.ToString();

				if (variables.containsKey(str)) {
					// add the Interpreterdatatypes
					InterpreterDataType k = variables.get(str);

					// if variable is float type
					if (k instanceof FloatDataType) {
						// if node is a float number call resolve function
						if ((va.getNode()) instanceof FloatNode) {
							Float fl = resolve(va.getNode());
							((FloatDataType) k).setValue(fl);
						}
						// if node is an integer number throgh exception b/c can't have type conversions
						if (va.getNode() instanceof IntegerNode) {
							throw new Exception("int not valid for type float");
						}
						if (va.getNode() instanceof StringNode) {
							throw new Exception("string not valid for type float");
						}
						if (va.getNode() instanceof CharNode) {
							throw new Exception("char not valid for type float");
						}
						if (va.getNode() instanceof BoolNode) {
							throw new Exception("boolean not valid for type float");
						}
						// if node is a variable
						else if ((va.getNode()) instanceof VariableReferenceNode) {
							VariableReferenceNode v = (VariableReferenceNode) (va.getNode());
							// get type of variable
							InterpreterDataType id = variables.get(v.ToString());
							// if proper type call resolve function
							if (id instanceof FloatDataType) {
								Float fl = resolve(va.getNode());
								((FloatDataType) k).setValue(fl);
							}
							// if int type through exception
							if (id instanceof IntDataType) {
								throw new Exception("int not valid for type float");
							}
							if (id instanceof StringDataType) {
								throw new Exception("string not valid for type float");
							}
							if (id instanceof CharacterDataType) {
								throw new Exception("char not valid for type float");
							}
							if (id instanceof BooleanDataType) {
								throw new Exception("boolean not valid for type float");
							}

						}
						// if a mathoperation node then call resolve
						else if ((va.getNode()) instanceof MathOpNode) {

							Float fl = resolve(va.getNode());
							((FloatDataType) k).setValue(fl);
						}
					} // end of float datatype if

					
					
					
					// if assignment variable is type int
					else if (k instanceof IntDataType) {
						// if expression side is integer number then call resolve
						if ((va.getNode()) instanceof IntegerNode) {
							Integer in = (int) resolve(va.getNode());
							((IntDataType) k).setTypevalue(in);
						}
						// if expression side is a float then throw exception
						if (va.getNode() instanceof FloatNode) {
							throw new Exception("float not valid for type int");
						}
						if (va.getNode() instanceof StringNode) {
							throw new Exception("string not valid for type int");
						}
						if (va.getNode() instanceof CharNode) {
							throw new Exception("char not valid for type int");
						}
						if (va.getNode() instanceof BoolNode) {
							throw new Exception("boolean not valid for type int");
						}
						// if a variable
						else if ((va.getNode()) instanceof VariableReferenceNode) {
							VariableReferenceNode v = (VariableReferenceNode) (va.getNode());
							// get variable data type
							InterpreterDataType id = variables.get(v.ToString());
							// call reoslve if int datattype and cast to int
							if (id instanceof IntDataType) {
								Integer in = (int) resolve(va.getNode());
								((IntDataType) k).setTypevalue(in);
							}
							// if float type then throw exception
							if (id instanceof FloatDataType) {
								throw new Exception("float not valid for type int");
							}
							if (id instanceof StringDataType) {
								throw new Exception("string not valid for type int");
							}
							if (id instanceof CharacterDataType) {
								throw new Exception("char not valid for type int");
							}
							if (id instanceof BooleanDataType) {
								throw new Exception("boolean not valid for type int");
							}

						}
						// if node is mathoperation then call reoslve and cast to int
						else if ((va.getNode()) instanceof MathOpNode) {
							Integer in = (int) resolve(va.getNode());
							((IntDataType) k).setTypevalue(in);
						}

					}
					
					
					
					else if (k instanceof StringDataType) {
						// if expression side is integer number then call resolve
						if ((va.getNode()) instanceof StringNode) {
							String in = (String) resolveString(va.getNode());
							((StringDataType) k).setValue(in);
						}
						if ((va.getNode()) instanceof MathOpNode) {
							String in = (String) resolveString(va.getNode());
							((StringDataType) k).setValue(in);
						}
						// if expression side is a float then throw exception
						if (va.getNode() instanceof FloatNode) {
							throw new Exception("float not valid for type string");
						}
						if (va.getNode() instanceof BoolNode) {
							throw new Exception("boolean not valid for type string");
						}
						if (va.getNode() instanceof IntegerNode) {
							throw new Exception("int not valid for type string");
						}
						// if a variable
						else if ((va.getNode()) instanceof VariableReferenceNode) {
							VariableReferenceNode v = (VariableReferenceNode) (va.getNode());
							// get variable data type
							InterpreterDataType id = variables.get(v.ToString());
							// call reoslve if int datattype and cast to int
							if (id instanceof StringDataType) {
								String in = (String) resolveString(va.getNode());
								((StringDataType) k).setValue(in);
							}
							// if float type then throw exception
							if (id instanceof FloatDataType) {
								throw new Exception("float not valid for type string");
							}
							if (id instanceof BooleanDataType) {
								throw new Exception("boolean not valid for type string");
							}
							if (id instanceof IntDataType) {
								throw new Exception("int not valid for type string");
							}

						}
						// if node is mathoperation then call reoslve and cast to int
						//else if ((va.getNode()) instanceof MathOpNode) {
						//	Integer in = (int) resolve(va.getNode());
						//	((IntDataType) k).setTypevalue(in);
						//}

					}
					
					
					else if (k instanceof CharacterDataType) {
						// if expression side is integer number then call resolve
						if ((va.getNode()) instanceof CharNode) {
							char in = (char) resolveCharacter(va.getNode());
							((CharacterDataType) k).setValue(in);
						}
						// if expression side is a float then throw exception
						if (va.getNode() instanceof FloatNode) {
							throw new Exception("float not valid for type char");
						}
						if (va.getNode() instanceof BoolNode) {
							throw new Exception("boolean not valid for type char");
						}
						if (va.getNode() instanceof IntegerNode) {
							throw new Exception("int not valid for type char");
						}
						// if a variable
						else if ((va.getNode()) instanceof VariableReferenceNode) {
							VariableReferenceNode v = (VariableReferenceNode) (va.getNode());
							// get variable data type
							InterpreterDataType id = variables.get(v.ToString());
							// call reoslve if int datattype and cast to int
							if (id instanceof CharacterDataType) {
								char in = (char) resolveCharacter(va.getNode());
								((CharacterDataType) k).setValue(in);
							}
							// if float type then throw exception
							if (id instanceof FloatDataType) {
								throw new Exception("float not valid for type char");
							}
							if (id instanceof BooleanDataType) {
								throw new Exception("boolean not valid for type char");
							}
							if (id instanceof IntDataType) {
								throw new Exception("int not valid for type char");
							}

						}
						
						
				}
					
					else if (k instanceof BooleanDataType) {
						// if expression side is integer number then call resolve
						if ((va.getNode()) instanceof BoolNode) {
							boolean in = (boolean) resolveBoolean(va.getNode());
							((BooleanDataType) k).setValue(in);
						}
						// if expression side is a float then throw exception
						if (va.getNode() instanceof FloatNode) {
							throw new Exception("float not valid for type char");
						}
						if (va.getNode() instanceof StringNode) {
							throw new Exception("string not valid for type char");
						}
						if (va.getNode() instanceof CharNode) {
							throw new Exception("char not valid for type char");
						}
						if (va.getNode() instanceof IntegerNode) {
							throw new Exception("int not valid for type char");
						}
						else if ((va.getNode()) instanceof BooleanExpressionNode) {
							boolean in = (boolean) resolveBoolean(va.getNode());
							((BooleanDataType) k).setValue(in);
						}
						// if a variable
						else if ((va.getNode()) instanceof VariableReferenceNode) {
							VariableReferenceNode v = (VariableReferenceNode) (va.getNode());
							// get variable data type
							InterpreterDataType id = variables.get(v.ToString());
							// call reoslve if int datattype and cast to int
							if (id instanceof BooleanDataType) {
								boolean in = (boolean) resolveBoolean(va.getNode());
								((BooleanDataType) k).setValue(in);
							}
							// if float type then throw exception
							if (id instanceof FloatDataType) {
								throw new Exception("float not valid for type char");
							}
							if (id instanceof StringDataType) {
								throw new Exception("string not valid for type char");
							}
							if (id instanceof CharacterDataType) {
								throw new Exception("char not valid for type char");
							}
							if (id instanceof IntDataType) {
								throw new Exception("int not valid for type char");
							}

						}
			}
					
					
				}
			}

			else if (s.get(i) instanceof IfNode) {
				IfNode in = ((IfNode) s.get(i));
				//Boolean bol = EvaluateBooleanExpression(in.bol, variables);
				Boolean bol = resolveBoolean(in.bol);
				// if first if node is true then call InterpretBlock
				if (bol == true) {
					InterpretBlock(in.statement, variables);
					//break;

				} else {
					// sets to elsif node
					in = in.a;
					while (in != null) {

						if (in.bol.condition != null || in.bol.variable != null) {
							//bol = EvaluateBooleanExpression(in.bol, variables);
							bol = resolveBoolean(in.bol);
							// if bollean expression is false get next ifnode
							if (!bol) {
								in = in.a;
							}
							// if boolean expression is true then call interpretblock
							if (bol) {
								InterpretBlock(in.statement, variables);
							break;
							}

						}
						// if boolean condition is null then else node and call interpret block
						else {
							InterpretBlock(in.statement, variables);
							break;
						}

					} // end while
				}

			}

			else if (s.get(i) instanceof WhileNode) {
				WhileNode wh = ((WhileNode) s.get(i));
				//Boolean bol = EvaluateBooleanExpression(wh.Boolean, variables);
				Boolean bol = resolveBoolean(wh.Boolean);

				// if boolean expression is true then call while block
				while (bol == true) {
					InterpretBlock(wh.statement, variables);
					//bol = EvaluateBooleanExpression(wh.Boolean, variables);
					bol = resolveBoolean(wh.Boolean);
				}
			}

			else if (s.get(i) instanceof ForNode) {
				ForNode fo = ((ForNode) s.get(i));
				String a = fo.start.ToString();
				Integer start = Integer.parseInt(a);
				String b = fo.end.ToString();
				Integer end = Integer.parseInt(b);
				VariableReferenceNode va = fo.varef;
				// gets datatyppe ofo variable
				IntDataType idt = (IntDataType) variables.get(va.ToString());
				// sets the value of the variable to the start integer value
				idt.setTypevalue(start);

				// increment for node
				if (start < end) {
					for (idt.getTypevalue(); idt.typevalue < end; idt.typevalue++) {
						InterpretBlock(fo.stat, variables);
					}
				}

				// decrement for node
				if (start > end) {
					for (idt.getTypevalue(); idt.typevalue > end; idt.typevalue--) {
						InterpretBlock(fo.stat, variables);
					}
				}
			}

			else if (s.get(i) instanceof RepeatNode) {
				Boolean boll;
				RepeatNode rep = ((RepeatNode) s.get(i));
				// repeat the statements until bol is true
				do {
					InterpretBlock(rep.statement, variables);
					BooleanExpressionNode bol = rep.Boolean;
					//boll = EvaluateBooleanExpression(bol, variables);
					boll=resolveBoolean(bol);
				} while (boll == false);

			}
		}

	}

	public static Boolean EvaluateBooleanExpression(BooleanExpressionNode bolnod,
			HashMap<String, InterpreterDataType> variables) throws Exception {

		Node a = bolnod.Left;
		// Float fl = resolve(a);
		Node b = bolnod.Right;
		// Float fl2 = resolve (b);
		Token.Type typ = bolnod.condition;
		InterpreterDataType idtA = null;
		InterpreterDataType idtB = null;

		if (a instanceof VariableReferenceNode) {
			String str = ((VariableReferenceNode) a).getName();
			idtA = variables.get(str);

		} else if (a instanceof FloatNode) {
			float f = ((FloatNode) a).getNumber();
			idtA = new FloatDataType(f);
		}

		else if (a instanceof IntegerNode) {
			int in = ((IntegerNode) a).getNumber();
			idtA = new IntDataType(in);
		}

		if (b instanceof VariableReferenceNode) {
			String st = ((VariableReferenceNode) b).getName();
			idtB = variables.get(st);
		} else if (b instanceof FloatNode) {
			float f = ((FloatNode) b).getNumber();
			idtB = new FloatDataType(f);
		} else if (b instanceof IntegerNode) {
			int in = ((IntegerNode) b).getNumber();
			idtB = new IntDataType(in);
		}

		if (idtA instanceof IntDataType && idtB instanceof IntDataType) {
			int value = ((IntDataType) idtA).typevalue;
			int value2 = ((IntDataType) idtB).typevalue;
			
			if (typ == Token.Type.GreaterThan) {

				if (value > value2)
					return true;
			}

			if (typ == Token.Type.GreaterThanOrEqualTo) {
				if (value >= value2)
					return true;
			}
			if (typ == Token.Type.LessThan) {
				if (value < value2)
					return true;
			}

			if (typ == Token.Type.LessThanOrEqualTo) {
				if (value <= value2)
					return true;
			}

			if (typ == Token.Type.equal) {
				if (value == value2)
					return true;
			}
			if (typ == Token.Type.notEqual) {
				if (value != value2)
					return true;
			}
			return false;

		}
		if (idtA instanceof FloatDataType && idtB instanceof FloatDataType) {
			float value = ((FloatDataType) idtA).typevalue;
			float value2 = ((FloatDataType) idtB).typevalue;
			if (typ == Token.Type.GreaterThan) {
				if (value > value2)
					return true;
			}
			if (typ == Token.Type.GreaterThanOrEqualTo) {
				if (value >= value2)
					return true;
			}
			if (typ == Token.Type.LessThan) {
				if (value < value2)
					return true;
			}

			if (typ == Token.Type.LessThanOrEqualTo) {
				if (value <= value2)
					return true;
			}

			if (typ == Token.Type.equal) {
				if (value == value2)
					return true;
			}
			if (typ == Token.Type.notEqual) {
				if (value != value2)
					return true;
			}
			return false;

		}
		
		if (idtA instanceof FloatDataType && idtB instanceof IntDataType) {
			throw new Exception ("cannot have different datatypes for boolean");
		}
		if (idtA instanceof IntDataType && idtB instanceof FloatDataType) {
			throw new Exception ("cannot have different datatypes for boolean");
		}

		return false;

	}
}





	

   