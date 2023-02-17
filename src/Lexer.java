import java.util.ArrayList;
import java.util.HashMap;

/*
 * uses lexical analysis to iterate over the input string and create appropriate
 * Tokens returns a collection (array or list) of Tokens 
 */

public class Lexer {

	// ATTRIBUTES
	private String State = "Start";
	private ArrayList<Token> tokens = new ArrayList<Token>();
	private String lexeme = "";

	//HashMap  (String, TokenType) of reserved words
	HashMap<String, Token.Type> reservedWords = new HashMap<String, Token.Type>();
	{
		reservedWords.put("integer", Token.Type.integer);
		reservedWords.put("real", Token.Type.real);
		reservedWords.put("begin", Token.Type.begin);
		reservedWords.put("end", Token.Type.end);
		reservedWords.put("variables", Token.Type.variables);
		reservedWords.put("constants", Token.Type.constants);
		reservedWords.put("define", Token.Type.define);
		reservedWords.put("if", Token.Type.IF);
		reservedWords.put("then", Token.Type.then);
		reservedWords.put("else", Token.Type.ELSE);
		reservedWords.put("elsif", Token.Type.elsif);
		reservedWords.put("for", Token.Type.FOR);
		reservedWords.put("from", Token.Type.from);
		reservedWords.put("to", Token.Type.to);
		reservedWords.put("while", Token.Type.WHILE);
		reservedWords.put("repeat", Token.Type.repeat);
		reservedWords.put("until", Token.Type.until);
		reservedWords.put("mod", Token.Type.mod);
		reservedWords.put("var", Token.Type.var);
		reservedWords.put("true", Token.Type.TRUE);
		reservedWords.put("false", Token.Type.FALSE);
		reservedWords.put("char", Token.Type.CHAR);
		reservedWords.put("string", Token.Type.STRING);
		reservedWords.put("boolean", Token.Type.BOOLEAN);
	}

	
	// lex method to generate tokens from string
	public ArrayList<Token> lex(String line) throws Exception {
		// iterating through string
		for (int i = 0; i < line.length(); i++) {
			// calls Statemachine method
			StateMachine(line.charAt(i));
		}
		StateMachine('\0');

		// Return the list of tokens
		return tokens;

	}
	
	//public boolean peekChar(char a) {
	//	if ()
	//	return false;
		
	//}
	
    //statemachine
	private void StateMachine(char c) throws Exception {

		switch (State) {
		
        //starts here
		case "Start":

			if (c == '-') {
				lexeme = "-";
				State = "Minus";
			}
			
			else if (Character.isDigit(c)) {
				//charater accumulation
				lexeme += Character.toString(c);
				State = "NumberInt";
			} 
			else if (c == '(') {
				lexeme = "(";
				State = "LParen";
			}

			else if (c == ' ') {
				State = "Space";
			} 
			else if (c == '\'') {
				State = "single";
			}
			
			else if (c == '\"') {
				State = "double";
			}
			
			else if (Character.isLetter(c)) {
				//character accumulation
				lexeme += Character.toString(c);
				State = "Word";
			} 
			

			else if (c == '\0') // if char is null then add end of line token
			{
				//tokens.add(new Token(Token.Type.EndOfLine));
				
			} 
			else // throws exception
			{
				throw new Exception("Invalid");
			}
			break;

			//if charater is operator, space, or null--add number int token and go to coooresponding state
		case "NumberInt":
			if (c == '+') {
				tokens.add(new Token(Token.Type.NUMBERINT, lexeme));
				lexeme = "+";
				State = "Plus";
			} 
			else if (c == '-') {
				tokens.add(new Token(Token.Type.NUMBERINT, lexeme));
				lexeme = "-";
				State = "Minus";
			} 
			else if (c == '*') {
				tokens.add(new Token(Token.Type.NUMBERINT, lexeme));
				lexeme = "*";
				State = "Times";
			} 
			else if (c == '/') {
				tokens.add(new Token(Token.Type.NUMBERINT, lexeme));
				lexeme = "/";
				State = "Divide";
			} 
			else if (c == '>') {
				tokens.add(new Token(Token.Type.NUMBERINT, lexeme));
				lexeme = ">";
				State = "Greater";
			} 
			else if (c == '<') {
				tokens.add(new Token(Token.Type.NUMBERINT, lexeme));
				lexeme = "<";
				State = "Less";
			} 
			else if (c == '=') {
				tokens.add(new Token(Token.Type.NUMBERINT, lexeme));
				lexeme = "=";
				State = "Equal";
			} 
			else if (c == '\'') {
				tokens.add(new Token(Token.Type.NUMBERINT, lexeme));
				lexeme = "\'";
				State = "single";
			} 
			else if (c == '\"') {
				tokens.add(new Token(Token.Type.NUMBERINT, lexeme));
				lexeme = "\"";
				State = "double";
			} 
			
			
			//if charater is digit go to number state
			else if (Character.isDigit(c)) {
				//charater accumulation
				lexeme += Character.toString(c);
				State = "NumberInt";
			} 
			
			else if (Character.isLetter(c)) {
				tokens.add(new Token(Token.Type.NUMBERINT, lexeme));
				//charater accumulation
				lexeme = "";
				lexeme += Character.toString(c);
				State = "Word";
			} 
			//if charater is decimal point go to decimal state
			else if (c == '.') {
				lexeme += Character.toString(c);
				State = "Decimal";
			} 
			else if (c == '(') {
				lexeme = "(";
				State = "LParen";
				// tokens.add(new Token(Token.Type.LPAREN));
			} 
			else if (c == ')') {
				lexeme = ")";
				State = "RParen";
				// tokens.add(new Token(Token.Type.RPAREN));
			} 
			else if (c == ' ') {
				tokens.add(new Token(Token.Type.NUMBERINT, lexeme));
				lexeme = "";
				State = "Space";
			} 
			else if (c == ',') {
				tokens.add(new Token(Token.Type.NUMBERINT, lexeme));
				lexeme = "";
				State = "Comma";
			} 
			
			else if (c == '\0') {
				// if char is null then add number token and go to endOfLine state
				tokens.add(new Token(Token.Type.NUMBERINT, lexeme));
				lexeme = "";
				tokens.add(new Token(Token.Type.EndOfLine));
			} 
			else {
				// if unexpected char throw exception
				throw new Exception("Invalid");
			}
			break;
		
		case "Decimal":

			if (Character.isDigit(c)) {
				// accumulate numbers
				lexeme += Character.toString(c);
				State = "NumberDecimal";
			} else {
				throw new Exception("Invalid");

			}
			break;

		case "NumberDecimal":
			if (c == '+') {
				// add token with token type and value of the lexeme string
				tokens.add(new Token(Token.Type.NUMBERFLOAT, lexeme));
				lexeme = "+";
				State = "Plus";
			} else if (c == '-') {
				tokens.add(new Token(Token.Type.NUMBERFLOAT, lexeme));
				lexeme = "-";
				State = "Minus";
			} else if (c == '*') {
				tokens.add(new Token(Token.Type.NUMBERFLOAT, lexeme));
				lexeme = "*";
				State = "Times";
			} else if (c == '/') {
				tokens.add(new Token(Token.Type.NUMBERFLOAT, lexeme));
				lexeme = "/";
				State = "Divide";
			} 
			else if (c == '>') {
				tokens.add(new Token(Token.Type.NUMBERFLOAT, lexeme));
				lexeme = ">";
				State = "Greater";
			} 
			else if (c == '<') {
				tokens.add(new Token(Token.Type.NUMBERFLOAT, lexeme));
				lexeme = "<";
				State = "Less";
			} 
			else if (c == '=') {
				tokens.add(new Token(Token.Type.NUMBERFLOAT, lexeme));
				lexeme = "=";
				State = "Equal";
			} 
			else if (c == ',') {
				tokens.add(new Token(Token.Type.NUMBERFLOAT, lexeme));
				lexeme = "";
				State = "Comma";
			} 
			else if (c == '\'') {
				tokens.add(new Token(Token.Type.NUMBERFLOAT, lexeme));
				lexeme = "";
				State = "single";
			} 
			else if (c == '\"') {
				tokens.add(new Token(Token.Type.NUMBERFLOAT, lexeme));
				lexeme = "";
				State = "double";
			} 
			
			else if (Character.isDigit(c)) {
				lexeme += Character.toString(c);
				State = "NumberInt";
				

			} 
			else if (Character.isLetter(c)) {
				tokens.add(new Token(Token.Type.NUMBERFLOAT, lexeme));
				//charater accumulation
				lexeme = "";
				lexeme += Character.toString(c);
				State = "Word";
			} 
			
			
			else if (c == ')') {
				lexeme = ")";
				State = "RParen";
				// tokens.add(new Token(Token.Type.RPAREN));
			} else if (c == ' ') {
				tokens.add(new Token(Token.Type.NUMBERFLOAT, lexeme));
				lexeme = "";
				State = "Space";
			} else if (c == '\0') {
				// if char is null then add number token and go to endOfLine state
				tokens.add(new Token(Token.Type.NUMBERFLOAT, lexeme));
				lexeme = "";
				// State = "ENDOFLINE";
				tokens.add(new Token(Token.Type.EndOfLine));
			} else {
				// if unexpected char throw exception
				throw new Exception("Invalid");
			}
			break;

		case "Minus":

			if (c == '-') {
				tokens.add(new Token(Token.Type.MINUS));
				lexeme = "-";
				State = "NumberInt";

			} else if (Character.isDigit(c)) {
				// accumulate numbers onto lexeme string
				lexeme += Character.toString(c);
				State = "NumberInt";

			} else if (c == '(') {
				lexeme = "(";
				State = "Parenthasis";
				tokens.add(new Token(Token.Type.MINUS));

			} else if (c == ' ') {
				tokens.add(new Token(Token.Type.MINUS));
				lexeme = "";
				State = "Space";

			} else if (c == '\'') {
				tokens.add(new Token(Token.Type.MINUS));
				lexeme = "";
				State = "single";

			}else if (c == '\"') {
				tokens.add(new Token(Token.Type.MINUS));
				lexeme = "";
				State = "double";

			}else {
				// if unexpected char throw exception
				throw new Exception("Invalid");
			}
			break;

		case "Plus":

			if (c == '-') {
				tokens.add(new Token(Token.Type.PLUS));
				lexeme = "-";
				State = "Operation";

			}

			else if (Character.isDigit(c)) {
				tokens.add(new Token(Token.Type.PLUS));
				lexeme = "";
				// accumulate numbers onto lexeme string
				lexeme += Character.toString(c);
				State = "NumberInt";

			}
			
			

			else if (Character.isLetter(c)) {
				tokens.add(new Token(Token.Type.PLUS));
				lexeme = "";
				// accumulate numbers onto lexeme string
				lexeme += Character.toString(c);
				State = "Word";

			}

			else if (c == '(') {
				lexeme = "(";
				State = "Parenthasis";
				tokens.add(new Token(Token.Type.PLUS));

			}else if (c == '\'') {
				lexeme = "";
				State = "single";
				tokens.add(new Token(Token.Type.PLUS));

			} else if (c == '\"') {
				lexeme = "";
				State = "double";
				tokens.add(new Token(Token.Type.PLUS));

			}else if (c == ' ') {
				tokens.add(new Token(Token.Type.PLUS));
				lexeme = "";
				State = "Space";

			} else {
				// if unexpected char throw exception
				throw new Exception("Invalid");
			}
			break;

		case "Times":

			if (Character.isDigit(c)) {
				tokens.add(new Token(Token.Type.TIMES));
				lexeme = "";
				// accumulate numbers onto lexeme string
				lexeme += Character.toString(c);
				State = "NumberInt";

			}

			else if (c == '(') {
				lexeme = "(";
				State = "LParen";
				tokens.add(new Token(Token.Type.TIMES));

			}

			else if (c == ' ') {
				tokens.add(new Token(Token.Type.TIMES));
				lexeme = "";
				State = "Space";

			} else {
				// if unexpected char throw exception
				throw new Exception("Invalid");
			}
			break;

		case "Divide":

			if (Character.isDigit(c)) {
				tokens.add(new Token(Token.Type.DIVIDE));
				lexeme = "";
				// accumulate numbers onto lexeme string
				lexeme += Character.toString(c);
				State = "NumberInt";

			}

			else if (c == '(') {
				lexeme = "(";
				State = "Parenthasis";
				tokens.add(new Token(Token.Type.DIVIDE));

			}

			else if (c == ' ') {
				tokens.add(new Token(Token.Type.DIVIDE));
				lexeme = "";
				State = "Space";

			} else {
				// if unexpected char throw exception
				throw new Exception("Invalid");
			}
			break;
			
			//if charater is acceptable charater then add rparen token and go to cooresponding state
		case "LParen":

			if (Character.isDigit(c)) {
				tokens.add(new Token(Token.Type.LPAREN));
				// accumulate numbers onto lexeme string
				lexeme += Character.toString(c);
				State = "NumberInt";

			}

			else if (Character.isLetter(c)) {
				tokens.add(new Token(Token.Type.LPAREN));
				lexeme += Character.toString(c);
				State = "Word";

			} else if (c == ' ') {
				tokens.add(new Token(Token.Type.LPAREN));
				State = "Space";

			
			}
		   else if (c == ')') {
			tokens.add(new Token(Token.Type.LPAREN));
			State = "RParen";

		   }else if (c == '\'') {
				tokens.add(new Token(Token.Type.LPAREN));
				State = "single";

		   }else if (c == '\"') {
				tokens.add(new Token(Token.Type.LPAREN));
				State = "double";

		    }
			
		
			else {
				// if unexpected char throw exception
				throw new Exception("Invalid");
			}
			
			break;

			//if charater is acceptable charater then add rparen token and go to cooresponding state
		case "RParen":

			if (c == ' ') {
				tokens.add(new Token(Token.Type.RPAREN));
				lexeme = "";
				State = "Space";
			} else if (c == '+') {
				lexeme = "+";
				tokens.add(new Token(Token.Type.RPAREN));
			} else if (c == '*') {
				lexeme = "*";
				tokens.add(new Token(Token.Type.RPAREN));
			} else if (c == '/') {
				lexeme = "/";
				tokens.add(new Token(Token.Type.RPAREN));
			} else if (c == '-') {
				lexeme = "-";
				tokens.add(new Token(Token.Type.RPAREN));
			}else if (c == '\'') {
				lexeme = "";
				tokens.add(new Token(Token.Type.RPAREN));
				State = "single";
			}else if (c == '\"') {
				lexeme = "";
				tokens.add(new Token(Token.Type.RPAREN));
				State = "double";
			} else if (c == '\0') {
				lexeme = "";
				tokens.add(new Token(Token.Type.RPAREN));
				tokens.add(new Token(Token.Type.EndOfLine));
			}

			else {
				// if unexpected char throw exception
				throw new Exception("Invalid");
			}
			break;

			//if c is equal to operator or punctuation, lexeme is null and go to cooresponding state
		case "Space":

			if (c == '+') {
				lexeme = "";
				State = "Plus";
			} else if (c == '(') {
				lexeme = "";
				State = "LParen";
			} else if (c == ')') {
				lexeme = "";
				State = "RParen";
			} else if (c == '-') {
				lexeme = "";
				State = "Minus";
			} else if (c == '/') {
				lexeme = "";
				State = "Divide";
			} else if (c == '*') {
				lexeme = "";
				State = "Times";
			} else if (c == ';') {
				lexeme = "";
				State = "Semicolon";
			} else if (c == ':') {
				lexeme = "";
				State = "Colon";
			} else if (c == ',') {
				lexeme = "";
				State = "Comma";
			} else if (c == '=') {
				lexeme = "";
				State = "Equal";
			} else if (c == '<') {
				lexeme = "";
				State = "Less";
			}else if (c == '>') {
				lexeme = "";
				State = "Greater";
			}else if (c == '\'') {
				lexeme = "";
				State = "single";
			}else if (c == '\"') {
				lexeme = "";
				State = "double";
			}
			else if (Character.isDigit(c)) {
				//if lexeme is digit go to number state 
				lexeme += Character.toString(c);
				State = "NumberInt";
			} else if (Character.isLetter(c)) {
				//if lexeme is letter go to word state
				lexeme += Character.toString(c);
				State = "Word";
			} else if (c == ' ') {
				lexeme = "";
				State = "Space";
			} else if (c == '\0') {
				//if lexeme is null then add endofline token
				lexeme = "";
				State = "ENDOFLINE";
				tokens.add(new Token(Token.Type.EndOfLine));
			} else {
				// if unexpected char throw exception
				throw new Exception("Invalid");
			}
			break;

		case "Word":

			if (Character.isLetter(c)) {
				lexeme += Character.toString(c);
				State = "Word";
			}

			else if (Character.isDigit(c)) {
				// accumulate numbers onto lexeme string
				
				if (reservedWords.containsKey(lexeme)) {
					tokens.add(new Token(reservedWords.get(lexeme)));
					lexeme = "";
					lexeme += Character.toString(c);
					State = "NumberInt";
				} 
				//otherwise use the identifier token
				else {
					tokens.add(new Token(Token.Type.Identifier, lexeme));
					lexeme = "";
					lexeme += Character.toString(c);
					State = "NumberInt";
				}
			}
				
			
			else if (c == ';') {
				//When your lexer completes a word, look in the HashMap; 
				//if it is in there, create a Token using that TokenType
				if (reservedWords.containsKey(lexeme)) {
					tokens.add(new Token(reservedWords.get(lexeme)));
					lexeme = ";";
					State = "Semicolon";
				} 
				//otherwise use the identifier token
				else {
					tokens.add(new Token(Token.Type.Identifier, lexeme));
					lexeme = ";";
					State = "Semicolon";
				}
			}
			else if (c == '\"') {
				//When your lexer completes a word, look in the HashMap; 
				//if it is in there, create a Token using that TokenType
					//lexeme = "";
					State = "double";
				} 
			
			else if (c == '+') {
				//When your lexer completes a word, look in the HashMap; 
				//if it is in there, create a Token using that TokenType
				if (reservedWords.containsKey(lexeme)) {
					tokens.add(new Token(reservedWords.get(lexeme)));
					lexeme = ";";
					State = "Plus";
				} 
				//otherwise use the identifier token
				else {
					tokens.add(new Token(Token.Type.Identifier, lexeme));
					lexeme = ";";
					State = "Plus";
				}
			}
			
			else if (c == '*') {
				//When your lexer completes a word, look in the HashMap; 
				//if it is in there, create a Token using that TokenType
				if (reservedWords.containsKey(lexeme)) {
					tokens.add(new Token(reservedWords.get(lexeme)));
					lexeme = ";";
					State = "Minus";
				} 
				//otherwise use the identifier token
				else {
					tokens.add(new Token(Token.Type.Identifier, lexeme));
					lexeme = ";";
					State = "Minus";
				}
			}

			
			else if (c == '/') {
				//When your lexer completes a word, look in the HashMap; 
				//if it is in there, create a Token using that TokenType
				if (reservedWords.containsKey(lexeme)) {
					tokens.add(new Token(reservedWords.get(lexeme)));
					lexeme = ";";
					State = "Divide";
				} 
				//otherwise use the identifier token
				else {
					tokens.add(new Token(Token.Type.Identifier, lexeme));
					lexeme = ";";
					State = "Divide";
				}
			}
			
			else if (c == '-') {
				//When your lexer completes a word, look in the HashMap; 
				//if it is in there, create a Token using that TokenType
				if (reservedWords.containsKey(lexeme)) {
					tokens.add(new Token(reservedWords.get(lexeme)));
					lexeme = ";";
					State = "Minus";
				} 
				//otherwise use the identifier token
				else {
					tokens.add(new Token(Token.Type.Identifier, lexeme));
					lexeme = ";";
					State = "Minus";
				}
			}
			else if (c == ')') {
				//When your lexer completes a word, look in the HashMap; 
				//if it is in there, create a Token using that TokenType
				if (reservedWords.containsKey(lexeme)) {
					tokens.add(new Token(reservedWords.get(lexeme)));
					lexeme = ")";
					State = "RParen";
				} 
				//otherwise use the identifier token
				else {
					tokens.add(new Token(Token.Type.Identifier, lexeme));
					lexeme = ")";
					State = "RParen";
				}

			} 
			//When your lexer completes a word, look in the HashMap; 
			//if it is in there, create a Token using that TokenType
			else if (c == ':') {
				if (reservedWords.containsKey(lexeme)) {
					tokens.add(new Token(reservedWords.get(lexeme)));
					lexeme = ":";
					State = "Colon";
				} 
				//otherwise use the identifier token
				else {
					tokens.add(new Token(Token.Type.Identifier, lexeme));
					lexeme = ":";
					State = "Colon";
				}

			} 
			//When your lexer completes a word, look in the HashMap; 
			//if it is in there, create a Token using that TokenType
			else if (c == '=') {
				if (reservedWords.containsKey(lexeme)) {
					tokens.add(new Token(reservedWords.get(lexeme)));
					lexeme = "=";
					State = "Equal";
				} 
				//otherwise use the identifier token
				else {
					tokens.add(new Token(Token.Type.Identifier, lexeme));
					lexeme = "=";
					State = "Equal";
				}

			} 
			
			else if (c == '>') {
				if (reservedWords.containsKey(lexeme)) {
					tokens.add(new Token(reservedWords.get(lexeme)));
					lexeme = ">";
					State = "Greater";
				} 
				//otherwise use the identifier token
				else {
					tokens.add(new Token(Token.Type.Identifier, lexeme));
					lexeme = ">";
					State = "Greater";
				}

			} 
			
			else if (c == '<') {
				if (reservedWords.containsKey(lexeme)) {
					tokens.add(new Token(reservedWords.get(lexeme)));
					lexeme = "<";
					State = "Less";
				} 
				//otherwise use the identifier token
				else {
					tokens.add(new Token(Token.Type.Identifier, lexeme));
					lexeme = "<";
					State = "Less";
				}

			} 
			//When your lexer completes a word, look in the HashMap; 
			//if it is in there, create a Token using that TokenType
			else if (c == ',') {
				if (reservedWords.containsKey(lexeme)) {
					tokens.add(new Token(reservedWords.get(lexeme)));
					lexeme = ",";
					State = "Comma";
				} 
				//otherwise use the identifier token
				else {
					tokens.add(new Token(Token.Type.Identifier, lexeme));
					lexeme = ",";
					State = "Comma";
				}

			}

			else if (c == ' ') {
				//When your lexer completes a word, look in the HashMap; 
				//if it is in there, create a Token using that TokenType
				if (reservedWords.containsKey(lexeme)) {
					tokens.add(new Token(reservedWords.get(lexeme)));
					lexeme = "";
					State = "Space";
				} 
				//otherwise use the identifier token
				else {
					tokens.add(new Token(Token.Type.Identifier, lexeme));
					lexeme = "";
					State = "Space";
				}
			}

			else if (c == '\0') {
				//When your lexer completes a word, look in the HashMap; 
				//if it is in there, create a Token using that TokenType
				if (reservedWords.containsKey(lexeme)) {
					tokens.add(new Token(reservedWords.get(lexeme)));
					lexeme = "";
					tokens.add(new Token(Token.Type.EndOfLine));
					break;
				} 
				//otherwise use the identifier token
				else {
					tokens.add(new Token(Token.Type.Identifier, lexeme));
					lexeme = "";
					tokens.add(new Token(Token.Type.EndOfLine));
					break;
				}
			} else {
				throw new Exception("Invalid");
			}
			break;

		case "Semicolon":
			//if charater is letter add semicolon token and go to word state 
			if (Character.isLetter(c)) {
				tokens.add(new Token(Token.Type.semicolon));
				lexeme = "";
				lexeme += Character.toString(c);
				State = "Word";
			}
			//if charater is space add semicolon token and go to space state 
			else if (c == ' ') {
				tokens.add(new Token(Token.Type.semicolon));
				lexeme = "";
				State = "Space";
			} 
			
			else {
				//throw exception 
				throw new Exception("Invalid");
			}
			break;

		case "Colon":
			//if charater is letter add colon token and go to word state 
			if (Character.isLetter(c)) {
				tokens.add(new Token(Token.Type.colon));
				lexeme = "";
				lexeme += Character.toString(c);
				State = "Word";
				
			//if charater is space add colon token and go to space state 
			} else if (c == ' ') {
				tokens.add(new Token(Token.Type.colon));
				lexeme = "";
				State = "Space";

			} 
			else if (c == '=') {
				lexeme = "";
				lexeme += Character.toString(c);
				State = "Assignment";
			}
			
			else {
				//throw exception
				throw new Exception("Invalid");
			}
			break;
			
		case "Assignment":
			if (c == ' ') {
				tokens.add(new Token(Token.Type.Assignment));
				lexeme = "";
				State = "Space";
			} 
			else if (Character.isDigit(c)) {
				tokens.add(new Token(Token.Type.Assignment));
				lexeme = "";
				// accumulate numbers onto lexeme string
				lexeme += Character.toString(c);
				State = "NumberInt";
			} 
			
			else if (Character.isLetter(c)) {
				tokens.add(new Token(Token.Type.Assignment));
				lexeme = "";
				// accumulate numbers onto lexeme string
				lexeme += Character.toString(c);
				State = "Word";
			} 
			
			else if (c == '-') {
				tokens.add(new Token(Token.Type.Assignment));
				lexeme = "-";
				State = "NumberInt";
			}
			
			else if (c == '\'') {
				tokens.add(new Token(Token.Type.Assignment));
				lexeme = "";
				State = "single";
			}
			else if (c == '\"') {
				tokens.add(new Token(Token.Type.Assignment));
				lexeme = "";
				State = "double";
			}
			
			else {
				//throws exception
				throw new Exception("Invalid");
			}
			
			break;

		//if charater is letter add comma token and go to word state 
		case "Comma":
			if (Character.isLetter(c)) {
				tokens.add(new Token(Token.Type.comma));
				lexeme = "";
				lexeme += Character.toString(c);
				State = "Word";
			} 
			
			//if charater is space add comma token and go to space state 
			else if (c == ' ') {
				tokens.add(new Token(Token.Type.comma));
				lexeme = "";
				State = "Space";
			} 
			
			else {
				//throw exception
				throw new Exception("Invalid");
			}
			break;

			
		case "Equal":
			//if charater is letter add equal token and go to word state 
			if (Character.isLetter(c)) {
				tokens.add(new Token(Token.Type.equal));
				lexeme = "";
				lexeme += Character.toString(c);
				State = "Word";
			}
			//if charater is space add equal token and go to space state 
			else if (c == ' ') {
				tokens.add(new Token(Token.Type.equal));
				lexeme = "";
				State = "Space";

			} 
			//if charater is number add equal token and go to number state 
			else if (Character.isDigit(c)) {
				tokens.add(new Token(Token.Type.equal));
				lexeme = "";
				// accumulate numbers onto lexeme string
				lexeme += Character.toString(c);
				State = "NumberInt";
			} 
			else if (c == '\'') {
				tokens.add(new Token(Token.Type.equal));
				lexeme = "";
				// accumulate numbers onto lexeme string
				//lexeme += Character.toString(c);
				State = "single";
			} 
			else if (c == '\"') {
				tokens.add(new Token(Token.Type.equal));
				lexeme = "";
				// accumulate numbers onto lexeme string
				//lexeme += Character.toString(c);
				State = "double";
			} 
			else {
				//throws exception
				throw new Exception("Invalid");
			}
			break;
			
			
		case "Greater":
			if (Character.isLetter(c)) {
				tokens.add(new Token(Token.Type.GreaterThan));
				lexeme = "";
				lexeme += Character.toString(c);
				State = "Word";
			}
			//if charater is space add equal token and go to space state 
			else if (c == ' ') {
				tokens.add(new Token(Token.Type.GreaterThan));
				lexeme = "";
				State = "Space";

			} 
			//if charater is number add equal token and go to number state 
			else if (Character.isDigit(c)) {
				tokens.add(new Token(Token.Type.GreaterThan));
				lexeme = "";
				// accumulate numbers onto lexeme string
				lexeme += Character.toString(c);
				State = "NumberInt";
			} 
			else if (c == '=') {
				lexeme = ">";
				lexeme += Character.toString(c);
				State = "GreaterThanOrEqual";
				
			}
			
			else {
				//throws exception
				throw new Exception("Invalid");
			}
			break;
			
		case "Less":
			//>, <, >=, <=, =, <> (not equal)
			if (Character.isLetter(c)) {
				tokens.add(new Token(Token.Type.LessThan));
				lexeme = "";
				lexeme += Character.toString(c);
				State = "Word";
			}
			//if charater is space add equal token and go to space state 
			else if (c == ' ') {
				tokens.add(new Token(Token.Type.LessThan));
				lexeme = "";
				State = "Space";

			} 
			//if charater is number add equal token and go to number state 
			else if (Character.isDigit(c)) {
				tokens.add(new Token(Token.Type.LessThan));
				lexeme = "";
				// accumulate numbers onto lexeme string
				lexeme += Character.toString(c);
				State = "NumberInt";
			} 
			else if (c == '=') {
				lexeme = "<";
				lexeme += Character.toString(c);
				State = "LessThanOrEqual";
				
			}
			else if (c=='>') {
				lexeme = ">";
				lexeme += Character.toString(c);
				State = "NotEqual";

			}
			else {
				//throws exception
				throw new Exception("Invalid");
			}
			break;
			
			
		case "GreaterThanOrEqual":
			//>, <, >=, <=, =, <> (not equal)
			
			if (c == ' ') {
				tokens.add(new Token(Token.Type.GreaterThanOrEqualTo));
				lexeme = "";
				State = "Space";
			} 
			else if (Character.isDigit(c)) {
				tokens.add(new Token(Token.Type.GreaterThanOrEqualTo));
				lexeme = "";
				// accumulate numbers onto lexeme string
				lexeme += Character.toString(c);
				State = "NumberInt";
			} 
			else if (c == '-') {
				tokens.add(new Token(Token.Type.GreaterThanOrEqualTo));
				lexeme = "-";
				State = "NumberInt";
			}
			else if (Character.isLetter(c)) {
				tokens.add(new Token(Token.Type.GreaterThanOrEqualTo));
				lexeme = "";
				lexeme += Character.toString(c);
				State = "Word";
			}
			
			else {
				//throws exception
				throw new Exception("Invalid");
			}
			break;
			
		case "LessThanOrEqual":
			//>, <, >=, <=, =, <> (not equal)
			if (c == ' ') {
				tokens.add(new Token(Token.Type.LessThanOrEqualTo));
				lexeme = "";
				State = "Space";
			}  
			
			else if (Character.isDigit(c)) {
				tokens.add(new Token(Token.Type.LessThanOrEqualTo));
				lexeme = "";
				// accumulate numbers onto lexeme string
				lexeme += Character.toString(c);
				State = "NumberInt";
			} 
			else if (c == '-') {
				tokens.add(new Token(Token.Type.LessThanOrEqualTo));
				lexeme = "-";
				State = "NumberInt";
			}
			else if (Character.isLetter(c)) {
				tokens.add(new Token(Token.Type.LessThanOrEqualTo));
				lexeme = "";
				lexeme += Character.toString(c);
				State = "Word";
			}
			else {
				//throws exception
				throw new Exception("Invalid");
			}
			break;
			
		case "NotEqual":
			//>, <, >=, <=, =, <> (not equal)
			if (c == ' ') {
				tokens.add(new Token(Token.Type.notEqual));
				lexeme = "";
				State = "Space";
			} 
			else if (Character.isLetter(c)) {
				tokens.add(new Token(Token.Type.notEqual));
				lexeme = "";
				lexeme += Character.toString(c);
				State = "Word";
			}
			else if (Character.isDigit(c)) {
				tokens.add(new Token(Token.Type.notEqual));
				lexeme = "";
				// accumulate numbers onto lexeme string
				lexeme += Character.toString(c);
				State = "NumberInt";
			} 
			else if (c == '-') {
				tokens.add(new Token(Token.Type.notEqual));
				lexeme = "-";
				State = "NumberInt";
			}
			
			else {
				//throws exception
				throw new Exception("Invalid");
			}
			break;
			
		case "single":
			
			if (Character.isLetter(c)) {
				lexeme = "";
				lexeme = Character.toString(c);
				State = "char";
			}
			
			else if (c == ' ') {
				lexeme = "";
				lexeme = Character.toString(c);
				State = "char"; 
			}
			else {
				//throws exception
				throw new Exception("Invalid");
			}
			break;
			
		case "char":
			//can only have one character for char 
			if (c == '\'') {
				tokens.add(new Token(Token.Type.charContents, lexeme));
				State = "Space";	
			}
			else {
				throw new Exception("Invalid");
			}
			break;
			
		case "double":
			
			//accumulation 
			if (Character.isLetter(c)) {
				lexeme += Character.toString(c);
				State = "Word";
			}
			
			else if (c == '\"')
			{
				tokens.add(new Token(Token.Type.stringContents, lexeme));
			}
			else if (c == ' ')
			{
				tokens.add(new Token(Token.Type.stringContents, lexeme));
				State = "Space";
			}
			
			else if (c == '\0') {
				tokens.add(new Token(Token.Type.stringContents, lexeme));
				tokens.add(new Token(Token.Type.EndOfLine));
				break;
			}
			
			else {
				//throws exception
				throw new Exception("Invalid");
			}
			break;
		}
		}
		}
	

	

