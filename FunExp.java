import java.util.*;

abstract class FunExp extends Exp
{
	abstract TypeVal typeEval(HashMap<String,TypeVal> paramTable, HashMap<Integer,TypeVal> paramNumTable);
}