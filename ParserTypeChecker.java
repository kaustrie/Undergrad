import java.io.*;
import java.util.*;

public abstract class ParserTypeChecker extends Parser
{
	public static HashMap<String,TypeVal> funTypeTable = new HashMap<String,TypeVal>(); 
		// records declared return types of functions

	public static HashMap<String,HashMap<String,TypeVal>> paramTypeTable = new HashMap<String,HashMap<String,TypeVal>>();
		// for each function name, records declared types of parameters

	public static HashMap<String,HashMap<Integer,TypeVal>> paramNumTypeTable = new HashMap<String,HashMap<Integer,TypeVal>>();
		// for each function name, records declared types of i-th parameters, indexed by i

	public static void main(String argv[])
	{
		/*Scanner read = new Scanner(System.in);
		String in = "";
		String out=""; 
		System.out.println("Please enter name of input file.");
		in = read.next();
		System.out.println("Please enter name of output file.");
		out = read.next();*/
		setLex( "input1.txt", "out.txt" );

		
		// argv[0]: source program
		// argv[1]: all output including error messages

		getToken();
		FunDefList funDefList = funDefList(); // build a parse tree		                    
		if ( ! t.isEmpty() )
			displayln(t + "  -- unexpected symbol");
		else if ( ! syntaxErrorFound )
		{
			funDefList.buildTypeTables(); // build the three type tables

			{
				displayln("Display return types of functions:");
				displayln("");
				displayln(funTypeTable.toString());
				displayln("");
				displayln("Display parameter types of functions:");
				displayln("");
				displayln(paramTypeTable.toString());
				displayln("");
				displayln("Display parameter types of functions by position:");
				displayln("");
				displayln(paramNumTypeTable.toString());
				displayln("");

				TypeVal funDefListType = funDefList.typeEval(); // perform type checking
				if ( funDefListType == TypeVal.Correct )
					displayln("All function definitions have passed type checking.");
			}
		}

		closeIO();
	}
}