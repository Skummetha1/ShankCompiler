import java.util.ArrayList;
import java.util.Scanner;

public class Read extends BuiltInFunctionNode {

	@Override
	public void execute(ArrayList<InterpreterDataType> param) throws Exception {
		Scanner s = new Scanner(System.in);
		// if no parameters throwo exception
		if (param.isEmpty() == true) {
			throw new Exception("No parameters");
		}

		int i;
		// iterate through list and get value of each index
		for (i = 0; i < param.size(); i++) {
			if ((param.get(i) instanceof IntDataType)) {
				System.out.print("Enter an int value: ");
				IntDataType parameter = (IntDataType) param.get(i);
				parameter.FromString(s.nextLine());
				Integer f = parameter.getTypevalue();
				parameter.setTypevalue(f);
			} 
			
			else if ((param.get(i) instanceof FloatDataType)) {
				System.out.print("Enter a float value: ");
				FloatDataType parameter = (FloatDataType) param.get(i);
				parameter.FromString(s.nextLine());
				Float f = parameter.getValue();
				parameter.setValue(f);

			}
			else if ((param.get(i) instanceof StringDataType)) {
				System.out.print("Enter a String value: ");
				StringDataType parameter = (StringDataType) param.get(i);
				parameter.FromString(s.nextLine());
				String f = parameter.getValue();
				parameter.setValue(f);

			}
			
			else if ((param.get(i) instanceof CharacterDataType)) {
				System.out.print("Enter one Character value: ");
				CharacterDataType parameter = (CharacterDataType) param.get(i);
				parameter.FromString(s.nextLine());
				char f = parameter.getValue();
				parameter.setValue(f);

			}
			
			else if ((param.get(i) instanceof BooleanDataType)) {
				System.out.print("Enter a boolean value: ");
				BooleanDataType parameter = (BooleanDataType) param.get(i);
				parameter.FromString(s.nextLine());
				boolean f = parameter.getValue();
				parameter.setValue(f);

			}
			

		}
	}
}
		  
		 



			
	

