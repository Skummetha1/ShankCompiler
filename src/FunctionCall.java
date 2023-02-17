import java.util.ArrayList;

public class FunctionCall extends StatementNode {

	String name;
	ArrayList<ParameterNode> param = new ArrayList<>();
	
	//class constructor
	public FunctionCall(String name, ArrayList<ParameterNode> param) {
		this.name = name;
		this.param = param;
	}


	 // toString method
		public String ToString() {
			return "Function Call(Name: " + name.toString() + ", Parameters: " + displaystatementnodes(param) + ")";
		}
		
		//method to apply tostring to each index of arraylist
		public String displaystatementnodes(ArrayList<ParameterNode> list) {
			String statementnodesinfo = "";
			for (ParameterNode v : list) {
				statementnodesinfo = statementnodesinfo  + v.ToString() + ", ";
			}
			return statementnodesinfo;

		}
		
		//setter and getters
	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public ArrayList<ParameterNode> getParam() {
		return param;
	}


	public void setParam(ArrayList<ParameterNode> param) {
		this.param = param;
	}
	
	
	
}
