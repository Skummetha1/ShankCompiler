import java.util.ArrayList;

public class RealToInteger extends BuiltInFunctionNode {
	//IntegerToReal someInteger, var someReal
	//someReal = someInteger  (so if someInteger = 5, someReal = 5.0)


	@Override
	public void execute(ArrayList<InterpreterDataType> params)throws Exception {
		// TODO Auto-generated method stub
		
		//throw exception if more than 2 parameters
			  if (params.size() != 2) {
			   throw new Exception("Invalid number of parameters");
			  }
			  
			//if first parameter is not float type throw exception
			  if (!(params.get(0) instanceof FloatDataType)) {
			   throw new Exception("Invalid parameter type");
			  }
			  
			  //set value of second parameter to int value
			  FloatDataType param = (FloatDataType)params.get(0);
			  IntDataType parameter = (IntDataType)params.get(1);
			  int a = (int)(param.getValue());
			  parameter.setTypevalue(a);
			  
			 }
	}

	
