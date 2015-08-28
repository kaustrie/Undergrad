abstract class FunDefList
{
	abstract void printParseTree(String indent);
	abstract void buildTypeTables();
	abstract TypeVal typeEval();
	abstract void emitInstructions();
}