import java.util.*;

class FunCall extends FunExp
{
	String funName;
	ExpList expList;

	FunCall(String s, ExpList e)
	{
		funName = s;
		expList = e;
	}

	void printParseTree(String indent)
	{
		String indent1 = indent+" ";
		String indent2 = indent1+" ";

		LexAnalyzer.displayln(indent + indent.length() + " <exp>");
		LexAnalyzer.displayln(indent1 + indent1.length() + " <fun exp>");
		LexAnalyzer.displayln(indent2 + indent2.length() + " " + funName);
		if ( expList != null )
			expList.printParseTree(indent2);
	}

	TypeVal typeEval(HashMap<String,TypeVal> paramTable, HashMap<Integer,TypeVal> paramNumTable)
	{
		if ( expList != null )
		{
			HashMap<Integer,TypeVal> paramNumTypeTable = ParserTypeChecker.paramNumTypeTable.get(funName);

			TypeVal expListType = expList.typeEvalFunCall(paramTable, paramNumTable, 1, paramNumTypeTable);
			if ( expListType == TypeVal.Correct )
				return ParserTypeChecker.funTypeTable.get(funName);
			else
				return TypeVal.Error;
		}
		else
			return ParserTypeChecker.funTypeTable.get(funName);
	}

	void emitInstructions(){
		int l =0;
		int n= 0;
		if ( expList != null )
			expList.emitInstructions();
		if (Interpreter.labelTable.containsKey(funName))
			l = Interpreter.labelTable.get(funName);
		if (Interpreter.total_paramTable.containsKey(l))
			n = Interpreter.total_paramTable.get(l);
		//LexAnalyzer.displayln(Interpreter.paramNumTypeTable.toString());
		LexAnalyzer.displayln(Interpreter.ind +"call "+l+", "+n);
		
	}
}