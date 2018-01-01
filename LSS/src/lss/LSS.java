/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lss;


import lss.solver.Method;
import lss.solver.Pivot;
import lss.solver.SolverCore;
import lss.solver.entryparser.lexer.Lexeme;


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
//        double[] x = new double[]{-1, 3, 15};
//        double[] y = new double[]{0, 20, 0};
//        String input = "A1*sin(x)+A2*cos(x)*log(x+4)+3*A3*x";
//        
//
//        SolverCore s = new SolverCore(x, y, input, Method.GAUSS, Pivot.PARTIAL);
//        System.out.println(s.getSteps());
        
        Lexeme l = new Lexeme("test","\\(\\(");
        System.out.println(l.size());
        System.out.println(l);
        
        
    }

}
