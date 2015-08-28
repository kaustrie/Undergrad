import java.util.*;

class ParameterList
{
	String type;
	String id;
	ParameterList parameterList;

	ParameterList(String t, String s, ParameterList p)
	{
		type = t;
		id = s;
		parameterList = p;
	}

	void printParseTree(String indent)
	{
		LexAnalyzer.displayln(indent + indent.length() + " " + type + " " + id);
		if ( parameterList != null )
			parameterList.printParseTree(indent);
	}

	void buildTypeTables(int i, HashMap<String,TypeVal> paramTable, HashMap<Integer,TypeVal> paramNumTable)
	{
		if ( paramTable.get(id) != null )
			LexAnalyzer.displayln("parameter "+id+" already declared");
		else
		{
			paramTable.put(id, TypeVal.toTypeVal(type));
			paramNumTable.put(i, TypeVal.toTypeVal(type));
		}
		if ( parameterList != null )
			parameterList.buildTypeTables(i+1, paramTable, paramNumTable);
	}

	void buildTable(int i, HashMap<String,Integer> pmap){
		if (!pmap.containsKey(id)){
		pmap.put(id, i);
		//LexAnalyzer.displayln(Interpreter.ind +"push #"+i);
		i++;
		}
		if(parameterList != null)			
			parameterList.buildTable(i, pmap);
		Interpreter.tot = i;
	}
	
}