/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lss.solver.entryparser.lexer;

import java.util.ArrayList;

/**
 *
 * @author estre
 */
public class Lexer {
    private ArrayList<ArrayList<Lexeme>> lexemes;
    private final String string;
    
    public Lexer(String input){
        string = input;
        lexemes = new ArrayList<>();
    }
    
    private Token scan(ArrayList<Lexeme> lexemes,int size,StringIterator chars){
        
        if (size==0){
            return scanMultiple(lexemes,chars);
        }
        String c = chars.getChars(size);
        
        Token t = null;
        if (c.equals(StringIterator.NONE)){
            return t;
        }
        for (Lexeme l: lexemes){
            if (c.matches(l.getPat())){
                t = new Token(l.getType(),c);
                break;
            }
        }
        
        if (t!=null){
            chars.move(size);
        }
        return t;
    }
    
    private Token scanMultiple(ArrayList<Lexeme> lexemes, StringIterator chars){
       for (Lexeme l: lexemes){
           int step =1;
           boolean found = false;
           String c = chars.getChars(step);
           
           while (!c.equals(StringIterator.NONE)){
               if (!c.matches(l.getPat())){
                   break;
               }
               found = true;
               step++;
               c = chars.getChars(step);
           }
           if (found){
               c = chars.getChars(step-1);
               chars.move(step-1);
               return new Token(l.getType(),c);
           }
       }
       return null;
    }
}
