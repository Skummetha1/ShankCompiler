import java.util.ArrayList;


public class IntegerToReal extends BuiltInFunctionNode {
	//RealToInteger someReal, var someInt
	//someInt = truncate someReal (so if someReal = 5.5, someInt = 5)

	@Override
	public void execute(ArrayList<InterpreterDataType> params)throws Exception {
		//throw exception if more than 2 parameters
			  if (params.size() != 2) {
			   throw new Exception("Invalid number of parameters");
			  }
			//if first parameter is not int type throw exception
			  if (!(params.get(0) instanceof IntDataType)) {
			   throw new Exception("Invalid parameter type");
			  }
			  //set value of second parameter to float value
			  IntDataType parameter = (IntDataType)params.get(0);
			  FloatDataType param = (FloatDataType)params.get(1);
			  float a = (parameter.getTypevalue().floatValue());
			  param.setValue(a);
			 }
			
		// TODO Auto-generated method stub
		
	}


