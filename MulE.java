import java.util.*;

class MulE extends FunExp
{
	int count;
	ExpList expList;
	
	MulE(ExpList e)
	{
		expList = e;
	}
	
	void printParseTree(String indent)
	{
		LexAnalyzer.displayln(indent + indent.length() + " <exp>");
		
		String indent1 = indent+" ";
		String indent2 = indent1+" ";
		
		LexAnalyzer.displayln(indent1 + indent1.length() + " <fun exp>");
		LexAnalyzer.displayln(indent2 + indent2.length() + "  *");
		expList.printParseTree(indent2);	
	}
	
	TypeVal typeEval(HashMap<String,TypeVal> paramTable, HashMap<Integer,TypeVal> paramNumTable)
	{
		TypeVal expListType = expList.typeEvalArith(paramTable, paramNumTable);
		if ( expListType != TypeVal.Error )
			return expListType;
		else
		{
			LexAnalyzer.displayln("Type Error: some arguments of * operator have incompatible types");
			return TypeVal.Error;
		}	
	}
	
	void emitInstructions(){
		count = 1;
		Interpreter.pcount = count;
		if ( expList != null){
			expList.emitInstructions();
		}
		LexAnalyzer.displayln(Interpreter.ind +"mul "+Interpreter.pcount);
	}
}