import java.util.ArrayList;
import java.util.HashMap;

public class SemanticAnalysis {
	
	//checks the data types of the nodes 
	public void checkNodeType(Node n, InterpreterDataType idt) throws Exception {
		InterpreterDataType data = null;
		
		//sets datatype based on passed invalue
		if(idt instanceof IntDataType) {
			data = new IntDataType();
		}
		else if (idt instanceof FloatDataType) {
			data = new FloatDataType();
		}
		
		else if (idt instanceof StringDataType) {
			data = new StringDataType();
		}
		else if (idt instanceof CharacterDataType) {
			data = new CharacterDataType();
		}
		else if (idt instanceof BooleanDataType) {
			data = new BooleanDataType();
		}
		
	
		//if intergernode then checks to see if datatype is boolean ot int 
		if(n instanceof IntegerNode) {
			if (data instanceof IntDataType) {
				
			}
			else if(data instanceof BooleanDataType) {
				
			}
			//else throw exception 
			else throw new Exception("Invalid data type for assignment target int");
			
		}
		
		//if floatnode, checks to see if data is type boolean or float 
		else if(n instanceof FloatNode) {
			if (data instanceof FloatDataType) {
				
			}
			else if (data instanceof BooleanDataType) {
				
			}
			//else throws exception 
			
			else throw new Exception("Invalid data type for assignment target float");
			
		}
		
		//if string node, checks for string datatype or char datatype 
		else if(n instanceof StringNode) {
			if (data instanceof StringDataType) {
				
			}
			else if(data instanceof CharacterDataType) {
				
			}
			else throw new Exception("Invalid data type for assignment target string");
		}
		
		//if charnode, checks fros tring or char datatype 
		else if(n instanceof CharNode) {
			if (data instanceof CharacterDataType) {
				
			}
			else if (data instanceof StringDataType) {
				
			}
			else throw new Exception("Invalid data type for assignment target char");
		}
		
		//if booleannode checks to see if boolean datatype 
		else if((n instanceof BoolNode)) {
			if (data instanceof BooleanDataType) {
				
			}
			else throw new Exception("Invalid data type for assignment target boolean");
		}
		
		//recursively checks math operations and throws exception if left and right are not of same tye
		//the only exception is for string and char types
		//there can be interchanged
		else if (n instanceof MathOpNode) {
			Node left = ((MathOpNode)n).getOperandLeft();
			checkNodeType(left, data);
			Node right = ((MathOpNode)n).getOperandRight();
			checkNodeType(right, data);
			
			
			if(left instanceof IntegerNode && right instanceof FloatNode) {
			    throw new Exception("math operations can only happen between the same types");
			}
			else if (left instanceof FloatNode && right instanceof IntegerNode) {
				throw new Exception("math operations can only happen between the same types");
			}
			else if (left instanceof FloatNode && right instanceof StringNode) {
				throw new Exception("math operations can only happen between the same types");
			}
			else if (left instanceof StringNode && right instanceof FloatNode) {
				throw new Exception("math operations can only happen between the same types");
			}
			else if (left instanceof FloatNode && right instanceof CharNode) {
				throw new Exception("math operations can only happen between the same types");
			}
			else if (left instanceof CharNode && right instanceof FloatNode) {
				throw new Exception("math operations can only happen between the same types");
			}
			else if (left instanceof IntegerNode && right instanceof StringNode) {
				throw new Exception("math operations can only happen between the same types");
			}
			else if (left instanceof StringNode && right instanceof IntegerNode) {
				throw new Exception("math operations can only happen between the same types");
			}
			else if (left instanceof IntegerNode && right instanceof CharNode) {
				throw new Exception("math operations can only happen between the same types");
			}
			else if (left instanceof CharNode && right instanceof IntegerNode) {
				throw new Exception("math operations can only happen between the same types");
			}
			else if (left instanceof BoolNode && right instanceof IntegerNode) {
				throw new Exception("math operations can only happen between the same types");
			}
			else if (left instanceof IntegerNode && right instanceof BoolNode) {
				throw new Exception("math operations can only happen between the same types");
			}
			else if (left instanceof BoolNode && right instanceof FloatNode) {
				throw new Exception("math operations can only happen between the same types");
			}
			else if (left instanceof FloatNode && right instanceof BoolNode) {
				throw new Exception("math operations can only happen between the same types");
			}
			else if (left instanceof BoolNode && right instanceof StringNode) {
				throw new Exception("math operations can only happen between the same types");
			}
			else if (left instanceof StringNode && right instanceof BoolNode) {
				throw new Exception("math operations can only happen between the same types");
			}
			else if (left instanceof BoolNode && right instanceof CharNode) {
				throw new Exception("math operations can only happen between the same types");
			}
			else if (left instanceof CharNode && right instanceof BoolNode) {
				throw new Exception("math operations can only happen between the same types");
			}
			
			}
	}
	
	//creates a hashmap to store datatypes of variables in function 
	static HashMap<String, InterpreterDataType> Symbol = new HashMap<>();
	public static HashMap<String, InterpreterDataType> getSymbol() {
		return Symbol;
	}
	
	
	//checks each assignment in function and checks data types 
	public void CheckAssignments(ArrayList<Node> functions) throws Exception {
		
		int i;
		//adding parameters to the hashmap 
		//adds data type to hashmap
		for (i = 0; i < functions.size(); i++) {
			FunctionNode f = (FunctionNode) functions.get(i);
			int k;
			//looping throw each parameter
			for(k = 0; k< f.Params.size(); k++ ) {
				VariableNode vari = f.Params.get(k);
				String str = vari.getName().toString();
				VariableNode.DataType va = vari.getdatatype();
				
				if (va == VariableNode.DataType.integer) {
					Symbol.put(str, new IntDataType());
				}
				if (va == VariableNode.DataType.real) {
					Symbol.put(str, new FloatDataType());
				}
				if (va == VariableNode.DataType.Char) {
					Symbol.put(str, new CharacterDataType());
				}
				if (va == VariableNode.DataType.String) {
					Symbol.put(str, new StringDataType());
				}
				if (va == VariableNode.DataType.Boolean) {
					Symbol.put(str, new BooleanDataType());
				}
			}
			
			//adding local variables to the hashmap 
			//adds data type to hashmap
			//looping throw each variable and constant
			for(k = 0; k<f.Vars.size(); k++ ) {
				VariableNode vari = f.Vars.get(k);
				String str = vari.getName().toString();
				VariableNode.DataType va = vari.getdatatype();
				
				if (va == VariableNode.DataType.integer) {
					Symbol.put(str, new IntDataType());
				}
				if (va == VariableNode.DataType.real) {
					Symbol.put(str, new FloatDataType());
				}
				if (va == VariableNode.DataType.Char) {
					Symbol.put(str, new CharacterDataType());
				}
				if (va == VariableNode.DataType.String) {
					Symbol.put(str, new StringDataType());
				}
				if (va == VariableNode.DataType.Boolean) {
					Symbol.put(str, new BooleanDataType());
				}
			}
			
			
			int j;
			InterpreterDataType idt = null;
			//looping throw function statements 
			for (j = 0; j < f.stat.size(); j++) {
				//if assignment then look at the right hand side and get datatype 
				if (f.stat.get(j) instanceof AssignmentNode) {
					String vrn = ((AssignmentNode) f.stat.get(j)).target.ToString();
					if(Symbol.containsKey(vrn)) 
					 idt = Symbol.get(vrn);
					Node n = ((AssignmentNode) f.stat.get(j)).expression;
					
					//if right hand side datatype is integer 
					if(idt instanceof IntDataType) {
						
						if((n instanceof IntegerNode)) {
							
						}
						//if it's a mathOp then recursively check each side of the Node by calling checkNodeType
						else if (n instanceof MathOpNode) {
						Node left = ((MathOpNode)n).getOperandLeft();
						checkNodeType(left, new IntDataType());
						Node right = ((MathOpNode)n).getOperandRight();
						checkNodeType(right, new IntDataType());
						
						}
						
						//if anyother node then throw exception 
						else throw new Exception("Invalid data type for assignment target int");
					}
					
					//if float data type for target 
					else if (idt instanceof FloatDataType) {
						if((n instanceof FloatNode)) {
							
						}
						//if it's a mathOp then recursively check each side of the Node by calling checkNodeType
						else if (n instanceof MathOpNode) {
							Node left = ((MathOpNode)n).getOperandLeft();
							checkNodeType(left, new FloatDataType());
							Node right = ((MathOpNode)n).getOperandRight();
							checkNodeType(right, new FloatDataType());
							}
						//if anyother node then throw exception 
						else throw new Exception("Invalid data type for assignment target float");
					}
					
					
					else if (idt instanceof StringDataType) {
						if((n instanceof StringNode)) {
							
						}
						//if it's a mathOp then recursively check each side of the Node by calling checkNodeType
						else if (n instanceof MathOpNode) {
							Node left = ((MathOpNode)n).getOperandLeft();
							checkNodeType(left, new StringDataType());
							Node right = ((MathOpNode)n).getOperandRight();
							checkNodeType(right, new StringDataType());
							}
						//if anyother node then throw exception 
						else throw new Exception("Invalid data type for assignment target String");
					}
					
					else if (idt instanceof CharacterDataType) {
						if((n instanceof CharNode)) {
							
						}
						//if it's a mathOp then recursively check each side of the Node by calling checkNodeType
						else if (n instanceof MathOpNode) {
							Node left = ((MathOpNode)n).getOperandLeft();
							checkNodeType(left, new CharacterDataType());
							Node right = ((MathOpNode)n).getOperandRight();
							checkNodeType(right, new CharacterDataType());
							}
						//if anyother node then throw exception 
						else throw new Exception("Invalid data type for assignment target char");
					}
					
					
					else if (idt instanceof BooleanDataType) {
						
						if(n instanceof BoolNode) {
							
						}
						
						//if it's a booleanExpressionNode then recursively check each side of the Node by calling checkNodeType
						else if(n instanceof BooleanExpressionNode) {
							Node left = ((BooleanExpressionNode)n).getLeft();
							checkNodeType(left, new BooleanDataType());
							Node right = ((BooleanExpressionNode)n).getRight();
							checkNodeType(right, new BooleanDataType());
						}
						//if it's a mathOpNode then recursively check each side of the Node by calling checkNodeType
						else if (n instanceof MathOpNode) {
							Node left = ((MathOpNode)n).getOperandLeft();
							checkNodeType(left, new BooleanDataType());
							Node right = ((MathOpNode)n).getOperandRight();
							checkNodeType(right, new BooleanDataType());
							}
						
						else throw new Exception("Invalid data type for assignment target boolean");
					}
				}
				
				//if statement is ifnode, whilenode, repeatnode, or fornode then call checkBlock to recursively check for 
				//assignment operators in the satatements of these nodes
				if (f.stat.get(j) instanceof IfNode) {
					IfNode node = ((IfNode)f.stat.get(j));
					//int e;
					checkBlock(node.statement);
				}
				if (f.stat.get(j) instanceof WhileNode) {
					WhileNode node = ((WhileNode)f.stat.get(j));
					checkBlock(node.statement);

				}
				if (f.stat.get(j) instanceof RepeatNode) {
					RepeatNode node = ((RepeatNode)f.stat.get(j));
					checkBlock(node.statement);
				}
				if (f.stat.get(j) instanceof ForNode) {
					ForNode node = ((ForNode)f.stat.get(j));
					checkBlock(node.stat);
					
				}

			}

		}
	}

	
	//if statement is not assignment node then it is passed into this block 
	public void checkBlock(ArrayList<StatementNode> stat) throws Exception {
		InterpreterDataType idt = null;
		int p;
		//loops over the statements of ifnode, fornode, whilenode...ect...
		for (p = 0; p < stat.size(); p++) {
			
			//block same as checkAssignments 
			if (stat.get(p) instanceof AssignmentNode) {
				String vrn = ((AssignmentNode) stat.get(p)).target.ToString();
				if(Symbol.containsKey(vrn)) 
				 idt = Symbol.get(vrn);
				Node n = ((AssignmentNode) stat.get(p)).expression;
				
				if(idt instanceof IntDataType) {
					if((n instanceof IntegerNode)) {
						
					}
					else if (n instanceof MathOpNode) {
					Node left = ((MathOpNode)n).getOperandLeft();
					checkNodeType(left, new IntDataType());
					Node right = ((MathOpNode)n).getOperandRight();
					checkNodeType(right, new IntDataType());
					}
					
					else throw new Exception("Invalid data type for assignment target int");
				}
				
				else if (idt instanceof FloatDataType) {
					if((n instanceof FloatNode)) {
						
					}
					else if (n instanceof MathOpNode) {
						Node left = ((MathOpNode)n).getOperandLeft();
						checkNodeType(left, new FloatDataType());
						Node right = ((MathOpNode)n).getOperandRight();
						checkNodeType(right, new FloatDataType());
						}
					else throw new Exception("Invalid data type for assignment target float");
				}
				
				else if (idt instanceof StringDataType) {
					if((n instanceof StringNode)) {
						
					}
					else if (n instanceof MathOpNode) {
						Node left = ((MathOpNode)n).getOperandLeft();
						checkNodeType(left, new StringDataType());
						Node right = ((MathOpNode)n).getOperandRight();
						checkNodeType(right, new StringDataType());
						}
					else throw new Exception("Invalid data type for assignment target String");
				}
				
				else if (idt instanceof CharacterDataType) {
					if((n instanceof CharNode)) {
						
					}
					else if (n instanceof MathOpNode) {
						Node left = ((MathOpNode)n).getOperandLeft();
						checkNodeType(left, new CharacterDataType());
						Node right = ((MathOpNode)n).getOperandRight();
						checkNodeType(right, new CharacterDataType());
						}
					else throw new Exception("Invalid data type for assignment target char");
				}
				else if (idt instanceof BooleanDataType) {
					
					if(n instanceof BoolNode) {
						
					}
					
					else if(n instanceof BooleanExpressionNode) {
						Node left = ((BooleanExpressionNode)n).getLeft();
						checkNodeType(left, new BooleanDataType());
						Node right = ((BooleanExpressionNode)n).getRight();
						checkNodeType(right, new BooleanDataType());
					}
						
					else if (n instanceof MathOpNode) {
						Node left = ((MathOpNode)n).getOperandLeft();
						checkNodeType(left, new BooleanDataType());
						Node right = ((MathOpNode)n).getOperandRight();
						checkNodeType(right, new BooleanDataType());
						}
					
					else throw new Exception("Invalid data type for assignment target boolean");
				}
			}
			
			if (stat.get(p) instanceof IfNode) {
				IfNode node = ((IfNode)stat.get(p));
				checkBlock(node.statement);
			
			}
			if (stat.get(p) instanceof WhileNode) {
				WhileNode node = ((WhileNode)stat.get(p));
				checkBlock(node.statement);

			}
			if (stat.get(p) instanceof RepeatNode) {
				RepeatNode node = ((RepeatNode)stat.get(p));
				checkBlock(node.statement);
			}
			if (stat.get(p) instanceof ForNode) {
				ForNode node = ((ForNode)stat.get(p));
				checkBlock(node.stat);
				
				
			}
	}
		}
	}
	

