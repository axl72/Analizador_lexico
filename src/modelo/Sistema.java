/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import modelo.ADP.Queue;
import java.io.File;
import java.util.ArrayList;
import modelo.AL.Token;

/**
 *
 * @author axell
 */
public class Sistema {
    public static String [] reservadas = {"integer", "flot", "boolean", "String", "FALSE", "TRUE", "true", "also", "false", "switch", "while", "loop", "print", "input", "error", "select", "set", "func", "Abstract", "self", "private", "public", "static", "main", "break", "to", "mod", "or", "and", "case", "not", "return"};
    public static String [] terminales = {"q0", "RESERVADA", "ID", "OPERADOR", "OP_ASIGNACION", "OP_MIEMBRO", "OP_MACRO", "OP_SEPARADOR", "OP_CASTING", "APERTURA_PARENTESIS", "CLAUSURA_PARENTESIS", "APERTURA_CORCHETE", "CLAUSURA_CORCHETE", "INICIO_BLOQUE", "FIN_BLOQUE", "INICIO_CADENA", "CADENA", "FIN_CADENA", "CONSTANTE_ENTERO", "CONSTANTE_REAL", "INICIO_COMENTARIO", "FIN_COMENTARIO", "", "COMENTARIO","ERROR", "CONSTANTE_NEUTRA", "FINALIZADOR", "TIPO_DATO"};
    public static int [] cantidadIDs;
    public static int [] buffers = {100, 100, 100, 100};
    public static Object[] columns = {"TIPO TOKEN", "LEXEMA", "TOKEN"};
    public static ArrayList<Token> tokens;
    public static String rutaTabla;
    public static String [] columnas = {"N°", "PILA", "ENTRADA", "SALIDA"};
    public static ArrayList<Object[]> producciones;
    
    private Sistema(){
    }
    
    
    
    public static void reservar(){
        int[] vector = new int[terminales.length];
        for(int i=0; i<vector.length; i++){
            vector[i] = 0;
        }
        cantidadIDs = vector;
    }
    
    public static Queue getCola(){
        Queue<String> result = new Queue();
        for(Token t: tokens){
            if(t.getTipo().equalsIgnoreCase("CONSTANTE_REAL")){
                result.enqueue("constante real");
            }else if(t.getTipo().equalsIgnoreCase("CONSTANTE_ENTERO")){
                result.enqueue("constante entero");
            }else if(t.getTipo().equalsIgnoreCase("CONSTANTE_NEUTRA")){
                result.enqueue("constante neutra");
               
            }else if(t.getTipo().equalsIgnoreCase("CADENA")){
                result.enqueue("cadena");
            }else if(t.getTipo().equalsIgnoreCase("ID")){
                result.enqueue("identificador");
            }
            else{
                result.enqueue(t.getLexema());
            }
            
        }
        result.enqueue("$");
        return result;
    }
    
}
