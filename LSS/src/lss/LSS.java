/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lss;

import lss.solver.Method;
import lss.solver.Pivot;
import lss.solver.SolverCore;
import lss.solver.mathematics.Function;

/**
 *
 * @author Gonzalo
 */
public class LSS {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        double[] x = new double[]{-1, 3, 15};
        double[] y = new double[]{0,20,0};

        
        Function f1 = new Function("f(x)=x^2");
        Function f2 = new Function("f(x)=1");
        Function f3 = new Function("f(x)=x^3");
        Function f[] = new Function[]{ f1, f2,f3};
        String c[] = new String[]{"C1", "C2","C3"};

        SolverCore s = new SolverCore(x, y, f, c, Method.GAUSS_JORDAN, Pivot.TOTAL);

    }

}
