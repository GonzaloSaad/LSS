/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lss.solver;

import lss.solver.mathematics.Function;
import lss.solver.mathematics.Matrix;
import lss.solver.mathematics.StringVector;
import lss.solver.mathematics.Vector;

/**
 *
 * @author Gonzalo
 */
public class SolverCore {

    private final Vector X;
    private final Vector Y;
    private final Function[] FUNCTIONS;
    private double solution[];
    private String steps;
    private final Matrix A;
    private final Vector B;
    private final StringVector C;
    private final Method METHOD;
    private final Pivot PIVOT;

    public SolverCore(double x[], double y[], Function functions[], String constants[], Method method, Pivot pivot) {
        X = new Vector(x);
        Y = new Vector(y);
        FUNCTIONS = functions;
        A = new Matrix(FUNCTIONS.length);
        B = new Vector(FUNCTIONS.length);
        C = new StringVector(constants);
        PIVOT = pivot;
        METHOD = method;
        solve();
    }

    private void solve() {
        StringBuilder s = new StringBuilder();
        buildMatrices(s);
        pivot(s);
        System.out.println(s.toString());
    }

    private void buildMatrices(StringBuilder sb) {
        int N = FUNCTIONS.length;
        int M = X.size();

        Matrix R = new Matrix(M, N);

        for (int i = 0; i < N; i++) {
            R.copyVectorToColumn(i, X.operateFunction(FUNCTIONS[i]));
        }

        for (int i = 0; i < N; i++) {
            B.setElem(i, R.getColumn(i).scalarProduct(Y));
            for (int j = 0; j < N; j++) {
                A.setElem(i, j, R.getColumn(i).scalarProduct(R.getColumn(j)));
            }
        }
        sb.append("Step 1: Building the matrices.\n");
        sb.append("A:\n");
        sb.append(A);
        sb.append("B:\n");
        sb.append(B);
        sb.append("C:\n");
        sb.append(C);

    }

    private void pivot(StringBuilder sb) {

        sb.append("Part 2: Pivot");
        sb.append("\nType:\t");

        if (PIVOT.equals(Pivot.PARTIAL)) {

            if (A.needsPartialPivot()) {
                sb.append("Partial Pivot.");
                partialPivot(sb);
            } else {
                sb.append("\nNo partial pivot is needed! One less step.");
            }

        } else {
            if (A.needsTotalPivot()) {
                sb.append("Total pivot.");
                totalPivot(sb);
            } else {
                sb.append("\nNo need for total pivot! One less step!");
            }

        }

    }

    private void partialPivot(StringBuilder sb) {
        int imax = A.getIndexMaxOfColumn(0);
        int jmax = A.getIndexMaxOfRow(0);

        if (A.isSymmetrical()) {
            sb.append("\t\t\t\t\t**You could partial pivot rows or columns, since A is symmetrical.");
        }

        if (A.getElem(0, jmax) > A.getElem(imax, 0)) {

            A.colOperation(0, jmax);
            C.operation(0, jmax);

            sb.append("\nMaximum value in [").
                    append("1,")
                    .append(jmax + 1)
                    .append("]");
            sb.append("\n\tColumns:");
            sb.append("\n\t\t1)Change column 1 with column ")
                    .append(jmax + 1)
                    .append(" in 'A' matrix.");
            sb.append("\n\t\t2)Change element 1 with element ")
                    .append(jmax + 1)
                    .append(" in 'C' vector.\n");
            sb.append("A:\n");
            sb.append(A);
            sb.append("C:\n");
            sb.append(C);

        } else {
            A.rowOperation(0, imax);
            B.operation(0, imax);

            sb.append("\nMaximum value in [").
                    append(imax + 1)
                    .append(",1")
                    .append("]");
            sb.append("\n\tRows:");
            sb.append("\n\t\t1)Change row 1 with row ")
                    .append(imax + 1)
                    .append(" in 'A' matrix.");
            sb.append("\n\t\t2)Change element 1 with element ")
                    .append(imax + 1)
                    .append(" in 'B' vector.\n");
            sb.append("A:\n");
            sb.append(A);
            sb.append("B:\n");
            sb.append(B);

        }

    }

    private void totalPivot(StringBuilder sb) {
        int imax, jmax, indexes[];
        indexes = A.getMaxIndex();
        imax = indexes[0];
        jmax = indexes[1];

        sb.append("\nMaximum value in [").
                append(imax + 1)
                .append(",")
                .append(jmax + 1)
                .append("]");
        if (imax != 0) {
            A.rowOperation(0, imax);
            B.operation(0, imax);
            sb.append("\n\tRows:");
            sb.append("\n\t\t1)Change row 1 with row ")
                    .append(imax + 1)
                    .append(" in 'A' matrix.");
            sb.append("\n\t\t2)Change element 1 with element ")
                    .append(imax + 1)
                    .append(" in 'B' vector.\n");

        } else {
            sb.append("No movement required for rows!\n");
        }

        if (jmax != 0) {
            A.colOperation(0, jmax);
            C.operation(0, jmax);
            sb.append("\n\tColumns:");
            sb.append("\n\t\t1)Change column 1 with column ")
                    .append(jmax + 1)
                    .append(" in 'A' matrix.");
            sb.append("\n\t\t2)Change element 1 with element ")
                    .append(jmax + 1)
                    .append(" in 'C' vector.\n");
        } else {
            sb.append("No movement required for columns!\n");
        }

        sb.append("A:\n");
        sb.append(A);
        sb.append("B:\n");
        sb.append(B);
        sb.append("C:\n");
        sb.append(C);

    }

    private void reduce(StringBuilder sb) {

    }
}
