import java.util.ArrayList;
import java.util.Random;


public class GetRandom extends BuiltInFunctionNode {

	@Override
	public void execute(ArrayList<InterpreterDataType> params) throws Exception {
		// TODO Auto-generated method stub
		
		//only one argumrnt allowed
				  if (params.size() != 1) {
				   throw new Exception("Invalid number of parameters");
				  }
				  if (params.get(0) instanceof FloatDataType) {
                   throw new Exception("parameter types don't match");
				  }

				  Random random = new Random(); 
				  //set value of parameter to random int value
				  IntDataType resultInteger = (IntDataType)params.get(0);
				  int rand = random.nextInt(100); 
				  //int rand = ((int)Math.random());
				  resultInteger.setTypevalue(rand);
				  
				 }
				
	}


