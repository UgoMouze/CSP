package core;


public class BacktrackingStrategy extends SolutionStrategy {

    public Assignment solve(CSP csp) {
        return recursiveBackTrackingSearch(csp, new Assignment());
    }

    private Assignment recursiveBackTrackingSearch(CSP csp,
                                                   Assignment assignment) {
        Assignment result = null;
        if (assignment.isComplete(csp.getVariables())) {
            result = assignment;
        } else {
            Variable var = selectUnassignedVariable(assignment, csp);
            for (Object value : orderDomainValues(var, assignment, csp)) {
                assignment.setAssignment(var, value);
                fireStateChanged(assignment, csp);
                if (assignment.isConsistent(csp.getConstraints(var))) {
                    DomainRestoreInfo info = inference(var, assignment, csp);
                    if (!info.isEmpty())
                        fireStateChanged(csp);
                    if (!info.isEmptyDomainFound()) {
                        result = recursiveBackTrackingSearch(csp, assignment);
                        if (result != null)
                            break;
                    }
                    info.restoreDomains(csp);
                }
                assignment.removeAssignment(var);
            }
        }
        return result;
    }

    protected Variable selectUnassignedVariable(Assignment assignment, CSP csp) {
        for (Variable var : csp.getVariables()) {
            if (!(assignment.hasAssignmentFor(var)))
                return var;
        }
        return null;
    }

    protected Iterable<?> orderDomainValues(Variable var,
                                            Assignment assignment, CSP csp) {
        return csp.getDomain(var);
    }

    protected DomainRestoreInfo inference(Variable var, Assignment assignment,
                                          CSP csp) {
        return new DomainRestoreInfo().compactify();
    }
}