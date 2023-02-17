import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Shank {

	// program entry point
	public static void main(String[] args) throws Exception {
		// main has only one argument
		if (args.length == 1) {
			try {
				Path filepath = Paths.get(args[0]);
				List<String> allLines = Files.readAllLines(filepath);
				System.out.println("Tokens from Lexer:");
				System.out.println("------------------");
				ArrayList<Token> finaltokens = new ArrayList<Token>();
				// reads every line in file
				for (String line : allLines) {

					ArrayList<Token> tokens = new ArrayList<Token>();
					try { // try block

						// ignores comments in this format (* *)
						String re = "\\(\\*[^()]*\\*\\)";
						Pattern p = Pattern.compile(re);
						Matcher m = p.matcher(line);
						while (m.find()) {
							line = m.replaceAll("");
							m = p.matcher(line);
						}
						Lexer lexer = new Lexer();// instantiates lexer
						tokens = lexer.lex(line);
						finaltokens.addAll(tokens);
						System.out.println(tokens.toString().replace("[", "").replace("]", "").replace(",", ""));

					} catch (Exception e) { // catch block
						System.out.println(e.getMessage());
					}

				} // end of for loop

				System.out.println();
				System.out.print("Parser Results:\n");
				System.out.println("---------------");
				// instantiates parser

				Parser parser = new Parser(finaltokens);
				// file tokens

				ArrayList<Node> ASTTree = parser.parse();
				// prints parser results

				

				// prints each individual functionnode
				System.out.println(displaynodes(ASTTree));
				
				//calling checkassignments(semantic analysis)
				SemanticAnalysis sm = new SemanticAnalysis();
				sm.CheckAssignments(ASTTree);
				
				System.out.println("\nInterpreter Results:");
				System.out.println("--------------------\n");
				// initializes interpreter

				// everytime a functionnode it adds to cal hasmap
				for (int i = 0; i < ASTTree.size(); i++) {
					if (ASTTree.get(i) instanceof FunctionNode) {
						String s = ((FunctionNode) ASTTree.get(i)).name;
						FunctionNode f = ((FunctionNode) ASTTree.get(i));
						Interpreter.getCal().put(s, f);
					}
				}
				// Interpreter interpreter = new Interpreter();

				// calls start
				if (Interpreter.getCal().containsKey("start")) {
					CallableNode k = Interpreter.getCal().get("start");
					Interpreter.InterpretFunction((FunctionNode) k, null);
				} else
					throw new Exception("couldn't find start");

				// Float a = interpreter.resolve(ASTTree);
				// prints resolve of ASTTree

				// System.out.println(a.toString());

			} catch (IOException ex) {
				// prints out exception
				System.out.println("File not found");
			}
		} else {
			System.out.println("Only one argument allowed");
		}

		{
		}

	}

	public static String displaynodes(ArrayList<Node> list) {
		String nodesinfo = "";
		for (Node v : list) {
			nodesinfo = nodesinfo + "\n" + v.ToString();
		}
		return nodesinfo;

	}

}
	


