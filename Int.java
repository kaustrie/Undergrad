import java.util.*;

class Int extends Exp
{
	int intElem;
	
	Int(int i)
	{
		intElem = i;
	}
	
	void printParseTree(String indent)
	{
		LexAnalyzer.displayln(indent + indent.length() + " <exp>");
		String indent1 = indent+" ";
		LexAnalyzer.displayln(indent1 + indent1.length() + " " + intElem);
	}
	
	TypeVal typeEval(HashMap<String,TypeVal> paramTable, HashMap<Integer,TypeVal> paramNumTable)
	{
		return TypeVal.Int;
	}
	
	void emitInstructions()
	{
		LexAnalyzer.displayln(Interpreter.ind +"push " + intElem);
	}
}