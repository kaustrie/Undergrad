import java.util.*;

class GeE extends FunExp
{
	ExpList expList;
	
	GeE(ExpList e)
	{
		expList = e;
	}
	
	void printParseTree(String indent)
	{
		LexAnalyzer.displayln(indent + indent.length() + " <exp>");
		
		String indent1 = indent+" ";
		String indent2 = indent1+" ";
		
		LexAnalyzer.displayln(indent1 + indent1.length() + " <fun exp>");
		LexAnalyzer.displayln(indent2 + indent2.length() + "  >=");
		expList.printParseTree(indent2);	
	}
	
	TypeVal typeEval(HashMap<String,TypeVal> paramTable, HashMap<Integer,TypeVal> paramNumTable)
	{
		TypeVal exp1Type = expList.firstExp().typeEval(paramTable, paramNumTable);
		TypeVal exp2Type = expList.secondExp().typeEval(paramTable, paramNumTable);

		if ( (exp1Type == TypeVal.Int || exp1Type == TypeVal.Float) && (exp2Type == TypeVal.Int || exp2Type == TypeVal.Float) )
			return TypeVal.Boolean;
		else
		{
			LexAnalyzer.displayln("Type Error: one or both arguments of >= have incompatible types");
			return TypeVal.Error;
		}
	}
	
	void emitInstructions(){
		if ( expList != null){
			expList.emitInstructions();
		}
		LexAnalyzer.displayln(Interpreter.ind +"ge");
	}
}