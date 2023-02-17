import java.util.ArrayList;

public class Substring extends BuiltInFunctionNode {
	// Substring someString, index, length, var resultString
	// ResultString = length characters from someString, starting at index


	@Override
	public void execute(ArrayList<InterpreterDataType> param) throws Exception {
			// TODO Auto-generated method stub
			if(param.size() != 4) {
				throw new Exception ("Invalid number of parameters");
			}
			
			if(!(param.get(0) instanceof StringDataType)) {
				throw new Exception("Invalid parameter type: needs String Data Type for first argument");
			}
			
			if(!(param.get(1) instanceof IntDataType)) {
				throw new Exception("Invalid parameter type: needs Int Data Type for second argument");
			}
			if(!(param.get(2) instanceof IntDataType)) {
				throw new Exception("Invalid parameter type: needs Int Data Type for second argument");
			}
			if(!(param.get(3) instanceof StringDataType)) {
				throw new Exception("Invalid parameter type: needs String Data Type for third argument");
			}
			
			//gets parameters and sets typevalues 
			StringDataType strdata = (StringDataType)param.get(0);
			String str = strdata.typevalue;
			IntDataType intdata = (IntDataType)param.get(1);
			int index = intdata.typevalue;
			IntDataType intdata2 = (IntDataType)param.get(2);
			int length = intdata2.typevalue;
			StringDataType strdata2 = (StringDataType)param.get(3);
			String temp = str.substring(index, index + length);
			//sets value of last var variable to temp value 
			strdata2.setValue(temp);
		
	}
	}


