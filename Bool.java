import java.util.*;

class Bool extends Exp
{
	boolean boolElem;
	
	Bool(boolean b)
	{
		boolElem = b;
	}
	
	void printParseTree(String indent)
	{
		LexAnalyzer.displayln(indent + indent.length() + " <exp>");
		String indent1 = indent+" ";
		LexAnalyzer.displayln(indent1 + indent1.length() + " " + boolElem);
	}

	TypeVal typeEval(HashMap<String,TypeVal> paramTable, HashMap<Integer,TypeVal> paramNumTable)
	{
		return TypeVal.Boolean;
	}
	
	void emitInstructions()
	{
		LexAnalyzer.displayln(Interpreter.ind +"push " + boolElem);
	}
}