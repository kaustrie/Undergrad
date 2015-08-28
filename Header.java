import java.util.*;

class Header
{
	String type;
	String funName;
	ParameterList parameterList;
	
	Header(String t, String f, ParameterList p)
	{
		type = t;
		funName = f;
		parameterList = p;
	}

	String returnType()
	{
		return type;
	}

	String funName()
	{
		return funName;
	}
	
	void printParseTree(String indent)
	{
		LexAnalyzer.displayln(indent + indent.length() + " <header>");
							  
		String indent1 = indent+" ";

		LexAnalyzer.displayln(indent1 + indent1.length() + " <type> " + type);
		LexAnalyzer.displayln(indent1 + indent1.length() + " <fun name> " + funName);
		if ( parameterList != null )
		{
			LexAnalyzer.displayln(indent1 + indent1.length() + " <parameter list>");
			parameterList.printParseTree(indent1+" ");
		}
	}

	void buildTypeTables()
	{
		ParserTypeChecker.funTypeTable.put(funName, TypeVal.toTypeVal(type));
		if ( parameterList != null )
		{
			HashMap<String,TypeVal> paramTable = new HashMap<String,TypeVal>();
			HashMap<Integer,TypeVal> paramNumTable = new HashMap<Integer,TypeVal>();

			parameterList.buildTypeTables(1, paramTable, paramNumTable);

			ParserTypeChecker.paramTypeTable.put(funName, paramTable);
			ParserTypeChecker.paramNumTypeTable.put(funName, paramNumTable);
		}			
	
	}
	void buildTable(){
		HashMap<String, Integer>pmap = new HashMap<String, Integer>();
		if(parameterList!= null){
			int l = Interpreter.L;
			parameterList.buildTable(1, pmap);
			Interpreter.paramTable.put(l, pmap);
			Collection<Integer> s =pmap.values();
			Interpreter.total_paramTable.put(l, (Integer) s.toArray()[s.size()-1]);		
		
		}
		else if (parameterList == null)
			Interpreter.total_paramTable.put(Interpreter.L, 0);
	}
}