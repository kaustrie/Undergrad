import java.util.*;

class FunDefListAndExp

{
	FunDefList funDefList;
	Exp exp;
	
	FunDefListAndExp(FunDefList f, Exp e)
	{
		funDefList = f;
		exp = e;
	}
	
	void printParseTree(String indent)
	{
		funDefList.printParseTree(indent);
		exp.printParseTree(indent);
	}

	void buildTypeTables()
	{
		funDefList.buildTypeTables();
	}

	TypeVal typeEval()
	{
		TypeVal funDefListType = funDefList.typeEval();
		TypeVal expType = exp.typeEval(null, null);

		if ( funDefListType == TypeVal.Correct && expType != TypeVal.Error )
			return TypeVal.Correct;
		else
			return TypeVal.Error;
	}
	
	void emitInstructions(){
		LexAnalyzer.displayln("goto 1");
		funDefList.emitInstructions();
		LexAnalyzer.displayln("1: ");
		exp.emitInstructions();
		
	}
}