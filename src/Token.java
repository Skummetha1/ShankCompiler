
public class Token {

	// enum
	public enum Type {
		// States
		NUMBERINT, PLUS, MINUS, TIMES, DIVIDE, EndOfLine, LPAREN, RPAREN, NUMBERFLOAT,Identifier,
		Assignment, define, integer, real, begin, end, semicolon, colon, equal, comma, variables, 
		constants, IF, then, ELSE, elsif, FOR, from, to, WHILE, repeat, until, mod, GreaterThan,
		LessThan, GreaterThanOrEqualTo, LessThanOrEqualTo, notEqual, var,TRUE,FALSE, charContents, stringContents, 
		STRING, CHAR, BOOLEAN;

	}

	// The type of the token
	private Type type;

	// The value of token
	private String value;

	// Methods and Constructors

	// Default constructor
	public Token() {

	}

	// type constructor
	public Token(Type type) {
		this.type = type;
	}

	// Full constructor
	public Token(Type type, String value) {
		this.type = type;
		this.value = value;
	}

	// Getters and setters

	public Type getType() {
		return type;
	}

	public String getValue() {
		return value;
	}

	public void setType(Type type) {
		this.type = type;
	}

	public void setValue(String value) {
		this.value = value;
	}

	@Override
	// toString method to print number token
	public String toString() {
		if (type == Type.NUMBERINT)
			return type.toString() + "(" + value + ")";
		if (type ==Type.NUMBERFLOAT)
			return type.toString() + "(" + value + ")";
		if (type == Type.Identifier)
			return type.toString() + "(" + value + ")";
		if (type == Type.stringContents)
			return type.toString() + "(" + value + ")";
		if (type == Type.charContents)
			return type.toString() + "(" + value + ")";

		return type.toString();
	}

}
