import java.util.*;

class ExpList
{
	Exp exp;
	ExpList expList;

	ExpList(Exp e, ExpList el)
	{
		exp = e;
		expList = el;
	}

	Exp firstExp()
	{
		return exp;
	}

	Exp secondExp()
	{
		return expList.firstExp();
	}

	int numArgs()
	{
		if ( expList == null )
			return 1;
		else
			return 1 + expList.numArgs();
	}

	void printParseTree(String indent)
	{
		exp.printParseTree(indent);
		if ( expList != null )
			expList.printParseTree(indent);	
	}

	TypeVal typeEvalArith(HashMap<String,TypeVal> paramTable, HashMap<Integer,TypeVal> paramNumTable)
	{
		TypeVal expType = exp.typeEval(paramTable, paramNumTable);

		if ( expList == null )
			return expType;
		else
		{
			TypeVal expListType = expList.typeEvalArith(paramTable, paramNumTable);
			if ( expType == TypeVal.Int && expListType == TypeVal.Int )
				return TypeVal.Int;
			else if ( expType == TypeVal.Float && expListType == TypeVal.Float )
				return TypeVal.Float;
			else
				return TypeVal.Error;
		}
	}

	TypeVal typeEvalBool(HashMap<String,TypeVal> paramTable, HashMap<Integer,TypeVal> paramNumTable)
	{
		TypeVal expType = exp.typeEval(paramTable, paramNumTable);

		if ( expList == null )
			return expType;
		else
		{
			TypeVal expListType = expList.typeEvalBool(paramTable, paramNumTable);
			if ( expType == TypeVal.Boolean && expListType == TypeVal.Boolean )
				return TypeVal.Boolean;
			else
				return TypeVal.Error;
		}
	}

	TypeVal typeEvalFunCall(HashMap<String,TypeVal> paramTable, HashMap<Integer,TypeVal> paramNumTable,
			int i, HashMap<Integer,TypeVal> paramNumTypeTable)
	{
		TypeVal expType = exp.typeEval(paramTable, paramNumTable);
		TypeVal paramType = paramNumTypeTable.get(i);

		if ( expList == null )
		{
			if ( expType == paramType )
				return TypeVal.Correct;
			else
			{
				LexAnalyzer.displayln("Type Error: incompatible type for parameter # "+i);
				return TypeVal.Error;
			}
		}
		else
		{
			TypeVal expListType = expList.typeEvalFunCall(paramTable, paramNumTable, i+1, paramNumTypeTable);

			if ( expType == paramType && expListType == TypeVal.Correct )
				return TypeVal.Correct;
			else if ( expType != paramType )
			{
				LexAnalyzer.displayln("Type Error: incompatible type for parameter # "+i);
				return TypeVal.Error;
			}
			else
				return TypeVal.Error;
		}
	}

	void emitInstructions(){
		exp.emitInstructions();
		if( expList != null )
			expList.emitInstructions();
	}

}