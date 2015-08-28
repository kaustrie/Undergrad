import java.util.*;

class FunDef extends FunDefList
{
	Header header;
	Exp exp;
	
	FunDef(Header h, Exp e)
	{
		header = h;
		exp = e;
	}
	
	void printParseTree(String indent)
	{
		String indent1 = indent + " ";
		
		LexAnalyzer.displayln(indent + indent.length() + " <fun def>");		
		header.printParseTree(indent1);
		exp.printParseTree(indent1);
	}

	void buildTypeTables()
	{
		header.buildTypeTables();
	}

	TypeVal typeEval()
	{
		String returnType = header.returnType();
		String funName = header.funName();

		HashMap<String,TypeVal> paramTable = ParserTypeChecker.paramTypeTable.get(funName);
		HashMap<Integer,TypeVal> paramNumTable = ParserTypeChecker.paramNumTypeTable.get(funName);

		TypeVal expType = exp.typeEval(paramTable, paramNumTable);

		if ( returnType.equals(expType.toString()) )
			return TypeVal.Correct;
		else
		{
			LexAnalyzer.displayln("Type Error: incompatible return type and body expression type in function "+funName );
			return TypeVal.Error;
		}
	}
	
	void emitInstructions()
	{
		Interpreter.labelTable.put( header.funName,Interpreter.L++);
		LexAnalyzer.displayln(Interpreter.L+":");
		header.buildTable();
		//LexAnalyzer.displayln(Interpreter.L++ +":");
		exp.emitInstructions();
		LexAnalyzer.displayln(Interpreter.ind + "return" );	
		
	}
}