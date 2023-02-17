
public class VariableNode extends Node {
	
    //initializes values 
	private Node initialValue; 
	private String name;
	private String var;
	private DataType datatype;
	private boolean isConstant;
	
	//enum for datatype
	public enum DataType {
		integer,
		real,
		String,
		Char,
		Boolean;

	}
	
	//class constructotr 
	public VariableNode(String name, DataType datatype, Node n ) {
		this.name = name;
		this.datatype = datatype;
		this.initialValue = n;
	}
	
    //class constructor 
	public VariableNode(String name, DataType datatype) {
		this.name = name;
		this.datatype = datatype;
	}
	
	public VariableNode(String var,String name, DataType datatype) {
		this.name = name;
		this.datatype = datatype;
		this.setVar(var);
	}
	
	//method to check if variable is constant or not
	public boolean isConstant() {
	if (initialValue == null)
		return false;
	else return true;
	}
	
	//toString depending on if vatiable is constant or not
	public String ToString() {
		// TODO Auto-generated method stub
	if (var != null) {
	if (isConstant() == true)
		 return ("("+ var + " " + name + ", DataType: " + getdatatype().name() + ", Initial Value: " + initialValue.ToString() + ")");
	if (isConstant() == false)
		 return ("("+ var + " " + name + ", DataType: " + getdatatype().name()+")");
	}
	else {
		if (isConstant() == true)
			 return ("("+ name + ", DataType: " + getdatatype().name() + ", Initial Value: " + initialValue.ToString() + ")");
		if (isConstant() == false)
			 return ("("+ name + ", DataType: " + getdatatype().name()+")");
		}
	
		
	return name;
		
	}
	
	//getters and setters
	public Node getValue() {
		return initialValue;
	}
	public void setValue(Node initialValue) {
        this.initialValue = initialValue;
    }
	public String getName() {
        return name;
    }
	
	public void setName(String name) {
        this.name = name;
    }
	
	public DataType getdatatype() {
		return datatype;
	}
	public void setdatatype(DataType datatype) {
        this.datatype = datatype;
    }
	
	public boolean getisConstant() {
		return isConstant;
	}

	public String getVar() {
		return var;
	}

	public void setVar(String var) {
		this.var = var;
	}
	
}
