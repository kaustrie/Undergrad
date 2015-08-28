import java.util.*;

class If extends Exp
{
	Exp exp1;
	Exp exp2;
	Exp exp3;
	
	If(Exp e1, Exp e2, Exp e3)
	{
		exp1 = e1;
		exp2 = e2;
		exp3 = e3;
	}
	
	void printParseTree(String indent)
	{
		LexAnalyzer.displayln(indent + indent.length() + " <exp>");
		
		String indent1 = indent+" ";
		String indent2 = indent1+" ";
		
		LexAnalyzer.displayln(indent1 + indent1.length() + " if");		
		exp1.printParseTree(indent2);
		LexAnalyzer.displayln(indent1 + indent1.length() + " then");
		exp2.printParseTree(indent2);
		LexAnalyzer.displayln(indent1 + indent1.length() + " else");
		exp3.printParseTree(indent2);
	}

	TypeVal typeEval(HashMap<String,TypeVal> paramTable, HashMap<Integer,TypeVal> paramNumTable)
	{
		TypeVal exp1Type = exp1.typeEval(paramTable, paramNumTable);
		TypeVal exp2Type = exp2.typeEval(paramTable, paramNumTable);
		TypeVal exp3Type = exp3.typeEval(paramTable, paramNumTable);

		if ( exp1Type == TypeVal.Boolean )
		{
			if ( exp2Type == TypeVal.Int && exp3Type == TypeVal.Int )
				return TypeVal.Int;
			else if ( exp2Type == TypeVal.Float && exp3Type == TypeVal.Float )
				return TypeVal.Float;
			else if ( exp2Type == TypeVal.Boolean && exp3Type == TypeVal.Boolean )
				return TypeVal.Boolean;
			else
			{
				LexAnalyzer.displayln("Type Error: incompatible types found in conditional expression");
				return TypeVal.Error;
			}
		}
		else
		{
			LexAnalyzer.displayln("Type Error: non-boolean expression found after if");
			return TypeVal.Error;
		}		
	}

	void emitInstructions() {
		exp1.emitInstructions(); //instructions for 'if'
		Interpreter.L++;
		LexAnalyzer.displayln(Interpreter.ind +"if_f goto "+Interpreter.L); //label for else
		int elseNum = Interpreter.L;
		Interpreter.L++;
		LexAnalyzer.displayln(Interpreter.ind +"goto " +Interpreter.L );
		exp2.emitInstructions(); // instructions for 'then'
		
		LexAnalyzer.displayln(elseNum+" :"); //code for else
		exp3.emitInstructions();
		
		//Interpreter.L++;
		LexAnalyzer.displayln(Interpreter.L+" :"); //code for out
		
	}
}