import java.util.*;

class NotE extends FunExp
{
	ExpList expList;
	
	NotE(ExpList e)
	{
		expList = e;
	}
	
	void printParseTree(String indent)
	{
		LexAnalyzer.displayln(indent + indent.length() + " <exp>");
		
		String indent1 = indent+" ";
		String indent2 = indent1+" ";
		
		LexAnalyzer.displayln(indent1 + indent1.length() + " <fun exp>");
		LexAnalyzer.displayln(indent2 + indent2.length() + "  not");
		expList.printParseTree(indent2);	
	}
	
	
	TypeVal typeEval(HashMap<String,TypeVal> paramTable, HashMap<Integer,TypeVal> paramNumTable)
	{
		TypeVal expListType = expList.typeEvalBool(paramTable, paramNumTable);
		if ( expListType != TypeVal.Error )
			return TypeVal.Boolean;
		else
		{
			LexAnalyzer.displayln("Type Error: argument of not operator has incompatible types");
			return TypeVal.Error;
		}	
	}
	
	void emitInstructions(){
		if ( expList != null){
			expList.emitInstructions();
		}
		LexAnalyzer.displayln(Interpreter.ind +"not");
	}
}