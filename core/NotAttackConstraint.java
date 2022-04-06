package core;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.abs;

public class NotAttackConstraint implements Constraint {

    Variable var1;
    Variable var2;
    private List<Variable> scope;

    public NotAttackConstraint(Variable c1, Variable c2) {
        this.var1 = c1;
        this.var2 = c2;
        scope = new ArrayList<Variable>(2);
        scope.add(var1);
        scope.add(var2);
    }

    public List<Variable> getScope() {
        return scope;
    }

    public boolean isSatisfiedWith(Assignment assignment) {
        int var1X = Integer.parseInt(var1.toString());
        int var1Y = Integer.parseInt((String) assignment.getAssignment(var1));
        int var2X = Integer.parseInt(var2.toString());

        //be careful : var2 may be not assigned :
        Object assignement_var2 = assignment.getAssignment(var2);

        if (assignement_var2 == null) {
            return true;
        }

        //if it is assigned :
        int var2Y = Integer.parseInt((String) assignment.getAssignment(var2));

        // CONDITION ON ROWS :
        boolean cond1 = (var2Y == var1Y); //must return false for the assignment to be satisfied

        // CONDITION ON DIAGONALS :
        boolean cond2 = false;
        if(abs(var2X-var1X)==abs(var2Y-var1Y)){
            cond2 = true;
        }
        return (!cond1 && !cond2);

    }


}

