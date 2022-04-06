package execution;

import Queens.MapQueens;
import core.Assignment;
import core.BacktrackingStrategy;
import core.CSP;
import core.CSPStateListener;

public class ExecuteNQueens {
    public static void main(String[] args) {
        int size = 8;
        MapQueens queens = new MapQueens(size);
        BacktrackingStrategy bts = new BacktrackingStrategy();
        bts.addCSPStateListener(new CSPStateListener() {
            @Override
            public void stateChanged(Assignment assignment, CSP csp) {
                System.out.println("Assignment evolved : " + assignment);
            }

            @Override
            public void stateChanged(CSP csp) {
                System.out.println("CSP evolved : " + csp);
            }
        });

        double start = System.currentTimeMillis();
        Assignment sol = bts.solve(queens);
        double end = System.currentTimeMillis();
        MapQueens.doPrint(sol, size);
        System.out.println("Time to solve = " + (end - start));
    }
}

