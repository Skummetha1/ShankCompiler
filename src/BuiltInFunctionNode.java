import java.util.ArrayList;

abstract class BuiltInFunctionNode {
/*BuiltInFunctions can do something that user-defined functions cannot 
 * (so far) – accept any number of parameters of any type (like read and write do).
 *  This is called variadic. C and Java both do this. Make a boolean in BuiltInFunctions to indicate if this built-in is variadic. 
 */
	
	
	Boolean variadic;
	public abstract void execute(ArrayList<InterpreterDataType> param) throws Exception;
		
}
