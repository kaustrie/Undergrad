import java.util.*;

class Floatp extends Exp
{
	float floatElem;
	
	Floatp(float f)
	{
		floatElem = f;
	}
	
	void printParseTree(String indent)
	{
		LexAnalyzer.displayln(indent + indent.length() + " <exp>");
		String indent1 = indent+" ";
		LexAnalyzer.displayln(indent1 + indent1.length() + " " + floatElem);
	}

	TypeVal typeEval(HashMap<String,TypeVal> paramTable, HashMap<Integer,TypeVal> paramNumTable)
	{
		return TypeVal.Float;
	}
	
	void emitInstructions()
	{
		LexAnalyzer.displayln(Interpreter.ind +"push " + floatElem);
	}
}