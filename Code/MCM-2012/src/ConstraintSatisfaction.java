/**
 * The heavy lifting class.  Runs the primary search algorithm, heuristic
 * assesments, and various other high level solution processes.
 */
public class ConstraintSatisfaction {


    /**
     * The constructor just initializes an instance of this class and takes
     * in some general items necessary to finding a problem solution.
     *@param variable list
     **/
    public ConstraintSatisfaction(){

    }

    /**
     * Attempts a backtracking search to satisfy the problem passed in
     * through the constructor.
     *@return a string specifying the result of the constraint satisfaction
     **/
    public String satisfy(){
	return "This is supposed to be your solution to the problem.";
    }

    /**
     * A general solution to backtracking depth first for solving the
     * constraint satisfaction problem passed in.
     *@param ass the current assignment
     *@param csp the constraint satisfaction problem
     **/
    public void backtrackingRecurse(Assignment ass, CSP csp){
	if(ass.isComplete()){
	    return ass;
	}Variable var = selectUnassignedVar(variables(csp), ass, csp);
	for(Value val: orderDomainValues(var, ass, csp)){
	    if(val.isConsistent(ass, csp)){
		ass.add(var,val);
		Result res = backtrackingRecurse(ass, csp);
		if(!res.failure()){
		    return res;
		}ass.remove(var,val);
	    }
	}return null;
    }
}
