import java.util.ArrayList;

public class SquareRoot extends BuiltInFunctionNode {

	@Override
	public void execute(ArrayList<InterpreterDataType> params)throws Exception {
		
		// TODO Auto-generated method stub
		
		//throw exception if more than 2 parameters 
			  if (params.size() != 2) {
			   throw new Exception("Invalid number of parameters");
			  }
			  
			  //if first parameter is not float type throw exception
			  if (!(params.get(1) instanceof FloatDataType)) {
			   throw new Exception("Invalid parameter type");
			  }
			  
			  //set value of second parameter to squareroot value
			  FloatDataType param1 = (FloatDataType)params.get(0);
			  Float j = param1.typevalue;
			  FloatDataType param2 = (FloatDataType)params.get(1);
			  param2.setValue((float)Math.sqrt(j));
			 }
			
}
