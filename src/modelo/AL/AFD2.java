/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.AL;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author axell
 */
public class AFD2 {
    private ArrayList<Token> tokens = new ArrayList<Token>();
    public void validarPalabras(){
        
        Pattern er = Pattern.compile("main");
        Matcher mat = er.matcher("main");
        System.out.println(mat.matches());
    }
    
    public static void main(String [] args){
        AFD2 afd = new AFD2();
        afd.validarPalabras();
    }
}
