import java.util.*;

abstract class Exp
{
	abstract void printParseTree(String indent);
	abstract TypeVal typeEval(HashMap<String,TypeVal> paramTable, HashMap<Integer,TypeVal> paramNumTable);
	abstract void emitInstructions();
}