/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.AL;

import modelo.Sistema;
import static modelo.Sistema.cantidadIDs;

/**
 *
 * @author axell
 */
public class Token {
    private String tipo;
    private String lexema;
    private int id;
    private int orden;
    private static int cantTokens = 0;
    public static int buffer = 1000;
    
    public Token(String lexema, int estado){
        this.lexema = lexema;
        this.id = estado;
        this.orden = retornarVal(estado);
        this.tipo = generarTipo(estado);
    }
    
    private String generarTipo(int estado){
        return Sistema.terminales[estado];
    }

    public String getLexema() {
        return lexema;
    }
    
    
    
    public String toString(){
        return "\n" + lexema + "    " + tipo + "    " + (id*buffer + orden);
    }
    
    private int retornarVal(int estado){
          cantidadIDs[estado] = cantidadIDs[estado]+1;
          return cantidadIDs[estado];
    }

    public String getTipo() {
        return tipo;
    }

    public String getPalabra() {
        return lexema;
    }

    public int getId() {
        return id;
    }

    public int getOrden() {
        return orden;
    }

    public static int getCantTokens() {
        return cantTokens;
    }
    
    public static void main(String[] args){
        Token t = new Token("juan", 3);
        t.getLexema().split("");
    }
    
    
    
    
}
