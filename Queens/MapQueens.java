package Queens;


import core.*;

public class MapQueens extends CSP {

    public MapQueens(Integer size) {

        collectVariables(size);

        //create Domain
        String[] domain = new String[size];
        for (int i = 0; i < size; i++) {
            domain[i] = Integer.toString(i);
        }
        Domain rows = new Domain(domain);

        for (Variable var : getVariables())
            setDomain(var, rows);

        for (int i = 0; i < getVariables().size(); i++) {
            for (int j = i+1 ; j < getVariables().size(); j++) {
                addConstraint(new NotAttackConstraint(getVariables().get(i), getVariables().get(j)));
            }
        }
    }


    private void collectVariables(Integer size) {
        for (int i = 0; i < size; i++) {
            addVariable(new Variable( Integer.toString(i)));
        }
    }
    public static void doPrint(Assignment sol, int boardSize){
        System.out.println(sol);
        for(int i=0; i<boardSize; i++){
            for(int j = 0; j<boardSize; j++){
                if(Integer.parseInt(sol.getAssignment(sol.getVariables().get(i)).toString()) == j ) System.out.printf("X ");
                else System.out.printf("_ ");
            }
            System.out.println();
        }
    }
}