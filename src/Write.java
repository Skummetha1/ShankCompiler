import java.util.ArrayList;

public class Write extends BuiltInFunctionNode{

	@Override
	public void execute(ArrayList<InterpreterDataType> params) throws Exception {
		// TODO Auto-generated method stub

		 //if no parameter throw exception
		 if (params.isEmpty() == true) {
			   throw new Exception("No parameters");
			  }
			  int i;
			  //loop to iterate through arraylist
			  for (i = 0; i < params.size(); i++ ) {
			  //prints the parameter variable
			  System.out.println(params.get(i).ToString() + " ");
			 }
			  
		            
	}
}


