import java.util.*;

class Id extends Exp
{
	String id;
	
	Id(String s)
	{
		id = s;
	}
	
	void printParseTree(String indent)
	{
		LexAnalyzer.displayln(indent + indent.length() + " <exp>");
		String indent1 = indent+" ";
		LexAnalyzer.displayln(indent1 + indent1.length() + " " + id);
	}
	
	TypeVal typeEval(HashMap<String,TypeVal> paramTable, HashMap<Integer,TypeVal> paramNumTable)
	{
		TypeVal idType = paramTable.get(id);
		if ( idType != null )
			return idType;
		else
		{
			LexAnalyzer.displayln( "Error: variable "+id+" not declared as parameter" );
			return null;			
		}

	}
	
	void emitInstructions()
	{
		LexAnalyzer.displayln(Interpreter.ind +"push #param");
	}
}