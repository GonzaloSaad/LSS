/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lss.solver.mathematics;

/**
 *
 * @author Gonzalo
 */
public class StringVector {

    private String[] vector;

    public StringVector(int size, String elem) {
        vector = new String[size];
        for (int i = 0; i < size; i++) {
            vector[i] = elem;
        }
    }

    public StringVector(int size) {
        this(size, " ");
    }

    public StringVector() {
        this(3);
    }

    public StringVector(String values[]) {

        vector = new String[values.length];
        System.arraycopy(values, 0, vector, 0, values.length);

    }

    public void setElem(int index, String elem) {
        vector[index] = elem;
    }

    public String getElem(int index) {
        return vector[index];
    }

    public void operation(int index1, int index2) {
        String aux = vector[index1];
        vector[index1] = vector[index2];
        vector[index2] = aux;
    }

    public int size() {
        return vector.length;
    }

    public String toString() {
        StringBuilder s = new StringBuilder();

        for (int i = 0; i < size(); i++) {

            s.append(String.format("%15s", (vector[i])));

            s.append("\n");
        }
        return s.toString();
    }
}
