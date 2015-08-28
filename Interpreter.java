import java.util.HashMap;


public class Interpreter extends ParserTypeChecker{
	public static int L = 2
			;
	public static int tot = 0;
	public static int countp = 0;
	public static int pcount = 0;
	public static String ind = "       "; 

	public static HashMap<String,Integer> labelTable = new HashMap<String,Integer>(); 
	// records declared return types of functions

	public static HashMap<Integer,Integer> total_paramTable = new HashMap<Integer,Integer>();
	// for each function name, records declared types of parameters

	public static HashMap<Integer,HashMap<String,Integer>> paramTable = new HashMap<Integer,HashMap<String,Integer>>();
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
		FunDefListAndExp funDefList = funDefListAndExp(); // build a parse tree		                    
		if ( ! t.isEmpty() )
			displayln(t + "  -- unexpected symbol");
		else if ( ! syntaxErrorFound )
		{
			funDefList.emitInstructions(); // build the three type tables
		}

		closeIO();
	}

}
