package Australia;


import core.CSP;
import core.Domain;
import core.NotEqualConstraint;
import core.Variable;

public class MapCSP extends CSP {
    public static final Variable NSW = new Variable("NSW");
    public static final Variable NT = new Variable("NT");
    public static final Variable Q = new Variable("Q");
    public static final Variable SA = new Variable("SA");
    public static final Variable T = new Variable("T");
    public static final Variable V = new Variable("V");
    public static final Variable WA = new Variable("WA");
    public static final String RED = "RED";
    public static final String GREEN = "GREEN";
    public static final String BLUE = "BLUE";

    public MapCSP() {
        addVariable(NSW);
        addVariable(WA);
        addVariable(NT);
        addVariable(Q);
        addVariable(SA);
        addVariable(V);
        addVariable(T);

        Domain colors = new Domain(new Object[]{RED, GREEN, BLUE});

        for (Variable var : getVariables())
            setDomain(var, colors);

        addConstraint(new NotEqualConstraint(WA, NT));
        addConstraint(new NotEqualConstraint(WA, SA));
        addConstraint(new NotEqualConstraint(NT, SA));
        addConstraint(new NotEqualConstraint(NT, Q));
        addConstraint(new NotEqualConstraint(SA, Q));
        addConstraint(new NotEqualConstraint(SA, NSW));
        addConstraint(new NotEqualConstraint(SA, V));
        addConstraint(new NotEqualConstraint(Q, NSW));
        addConstraint(new NotEqualConstraint(NSW, V));
    }
}
