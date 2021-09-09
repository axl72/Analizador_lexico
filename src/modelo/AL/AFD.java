/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.AL;

import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import modelo.Sistema;
import modelo.AL.Token;

/**
 *
 * @author axell
 */
public class AFD {
    private ArrayList<Token> tokens = new ArrayList<Token>();
    
    public void validarPalabras(ArrayList<String> palabras){
        tokens = new ArrayList<Token>(palabras.size());
        
        String lexema;
        Token t;
        boolean bandera;
        int estado, contador_op, c_suma, c_resta, c_multi;
        for(int i=0; i<palabras.size(); i++){
            estado = 0; 
            contador_op = 0; 
            c_suma = 0; 
            c_resta = 0; 
            c_multi = 0;
            
            //Obtenemos la palabra i
            lexema = palabras.get(i);
            
            bandera = false;
            //for para ver si es reservada
            for(int j=0; j<Sistema.reservadas.length; j++){
           
                if(lexema.equals(Sistema.reservadas[j])){
                    
                    estado = 1;
                    bandera = true;
                    
                    if(lexema.equals("mod") || lexema.equals("or") || lexema.equals("and") || lexema.equals("not")){
                        estado = 3;
                    }else if(lexema.equals("to")){
                        estado = 8;
                        
                    }else if(lexema.equals(Sistema.reservadas[3]) || lexema.equals(Sistema.reservadas[1]) || lexema.equals(Sistema.reservadas[2]) || lexema.equals(Sistema.reservadas[0])){
                        //Estado para TIPO_DATO
                        estado = 27;
                    }
                    
                    
                }
            }
            //Si no es reservada
            if(!bandera){
                char c;
                for(int k=0; k<lexema.length(); k++){
                    c = lexema.charAt(k);
                    
                    if(c >= 'A' && c <='Z' || c >= 'a' && c<='z' || c == '_'){
                        switch(estado){
                            case 0: estado = 2;break;
                            case 2: estado = 2; break;
                            case 15: estado = 16; break;
                            case 16: estado = 16; break;
                            case 20: estado = 23; break;
                            case 23: estado = 23; break;
                            default: estado = 24; break;
                        }
                            
                    }
  
                    else if(c == ';'){
                        switch(estado){
                            case 0: estado = 26; break;
                            case 15: estado = 16; break;
                            case 16: estado = 16; break;
                            case 20: estado = 23; break;
                            case 23: estado = 23; break;
                            default: estado = 24;
                        }
                    }
                    
                    
                    
                    else if(c == '='){
                        switch(estado){
                            case 0: estado = 4; break;
                            case 3: estado = 24; break;
                            case 4: 
                                estado = 3; 
                                //contador_op++;
                                break;
                            case 15: estado = 16; break;
                            case 16: estado = 16; break;
                            case 20: estado = 23; break;
                            case 23: estado = 23; break;
                            default: estado = 24;
                        }
                    }
                    
                    
                    else if(c == '+'){
                        switch(estado){
                            case 0: 
                                estado = 3;
                                c_suma++;
                                break;
                            case 3:
                                if(c_suma == 1){
                                    estado = 3;
                                    c_suma++;
                                }else{
                                    estado = 24;
                                }
                                break;
                            case 15: estado = 16; break;
                            case 16: estado = 16; break;
                            case 20: estado = 23; break;
                            case 23: estado = 23; break;    
                            default: estado = 24;
                        }
                        
                    }else if(c == '-'){
                        switch(estado){
                            case 0: 
                                estado = 3;
                                c_resta++;
                                break;
                            case 3:
                                if(c_resta == 1){
                                    estado = 3;
                                    c_resta++;
                                }else{
                                    estado = 24;
                                }
                                break;
                            case 15: estado = 16; break;
                            case 16: estado = 16; break;
                            case 20: estado = 23; break;
                            case 23: estado = 23; break;    
                            default: estado = 24;
                        }
                    }else if(c == '*'){
                        switch(estado){
                            case 0: 
                                estado = 3;
                                c_multi++;
                                break;
                            case 3:
                                if(c_multi == 1){
                                    estado = 3;
                                    c_multi++;
                                }else{
                                    estado = 24;
                                }
                                break;
                            case 15: estado = 16; break;
                            case 16: estado = 16; break;
                            case 20: estado = 23; break;
                            case 23: estado = 23; break;    
                            default: estado = 24;
                        }
                    }
                    
                    
                    
                    else if(c == '/' || c == '>' || c == '<' || c == ':' || c == '.' || c == '#' || c == ',' || c == '!'){
                        switch(estado){
                            case 0: if(c == '.') estado = 5; else estado = 3; break;
                            case 3: estado = 24; break;
                            case 15: estado = 16; break;
                            case 16: estado = 16; break;
                            case 18: if(c == '.') estado  = 19; else estado = 24; break;
                            case 19: estado = 24; break;
                            case 20: estado = 23; break;
                            case 23: estado = 23; break;
                            case 25: if(c == '.') estado = 19; else estado = 24; break;
                            default: estado = 24;
                            
                        }
                    }
                    
                    
                    else if(c == '('){
                        switch(estado){
                            case 0: estado = 9; break;
                            case 15: estado = 16; break;
                            case 16: estado = 16; break;
                            case 20: estado = 23; break;
                            case 23: estado = 23; break;
                            default: estado = 24;
                        }
                    }
                    
                    else if(c == ')'){
                        switch(estado){
                            case 0: estado = 10; break;
                            case 15: estado = 16; break;
                            case 16: estado = 16; break;
                            case 20: estado = 23; break;
                            case 23: estado = 23; break;
                            default: estado = 24;
                        }
                    }
                    else if(c == '['){
                        switch(estado){
                            case 0: estado = 11; break;
                            case 15: estado = 16; break;
                            case 16: estado = 16; break;
                            case 20: estado = 23; break;
                            case 23: estado = 23; break;
                            default: estado = 24;
                        }
                    }
                    
                    else if(c == ']'){
                        switch(estado){
                            case 0: estado = 12; break;
                            case 15: estado = 16; break;
                            case 16: estado = 16; break;
                            case 20: estado = 23; break;
                            case 23: estado = 23; break;
                            default: estado = 24;
                        }
                    }
                    else if(c == '{'){
                        switch(estado){
                            case 0: estado = 13; break;
                            case 15: estado = 16; break;
                            case 16: estado = 16; break;
                            case 20: estado = 23; break;
                            case 23: estado = 23; break;
                            default: estado = 24;
                        }
                    }
                    
                    else if(c == '}'){
                        switch(estado){
                            case 0: estado = 14; break;
                            case 15: estado = 16; break;
                            case 16: estado = 16; break;
                            case 20: estado = 23; break;
                            case 23: estado = 23; break;
                            default: estado = 24;
                        }
                    }else if(c == '"'){
                        switch(estado){
                            case 0:estado = 15; break;
                            case 15:estado = 17;break;
                            case 16:estado = 17;break;
                            case 20: estado = 23; break;
                            case 23: estado = 23; break;
                            default: estado = 24;
                        }
                        
                        
                    }
                    else if(c == '0'){
                        switch(estado){
                            case 0: estado = 25; break;
                            case 2: estado = 2; break;
                            case 15: estado = 16; break;
                            case 16: estado = 16; break;
                            case 19: estado = 19; break;
                            case 20: estado = 23; break;
                            case 23: estado = 23; break;
                            case 25: estado = 24; break;
                            default: estado = 24;
                        }
                        
                    }
                    
                    
                    else if(c >= '1' && c <= '9'){
                        switch(estado){
                            case 0: estado = 18; break;
                            case 2: estado = 2; break;
                            case 15: estado = 16; break;
                            case 16: estado = 16; break;
                            case 18: estado =18; break;
                            case 19: estado = 19; break;
                            case 20: estado = 23; break;
                            case 23: estado = 23; break;
                            case 25: estado = 24; break;
                            default: estado = 24; break;
                        }
                    }
                    
                    else if( c == '%'){
                        switch(estado){
                            case 0: estado = 20;break;
                            case 15: estado = 16; break;
                            case 16: estado = 16; break;
                            case 20: estado = 21;break;
                            case 23: estado = 21;break;
                            default: estado = 24;
                        }
                    }else if(c == ' '){
                        switch(estado){
                            case 15: estado = 16; break;
                            case 16: estado = 16; break;
                            case 20: estado = 23; break;
                            case 23: estado = 23; break;
                            default: estado = 24;
                        }
                    }else if(c == 9){
                        switch(estado){
                            case 15: estado = 16; break;
                            case 16: estado = 16; break;
                            case 20: estado = 23; break;
                            case 23: estado = 23; break;
                            default: estado = 24;
                        }
                    }
                    else{
                        if(estado != 16 && estado != 15)
                            estado = 24;
                    }
                    //System.out.println("Lexema a validar: " + lexema + "Caracter: " + c + "   estado: " + estado);
                }
            }
            
            
            if(estado != 24){
                if(estado != 15 && estado != 17 && estado != 16 && estado != 21 && estado != 20 && estado != 23 ){
                    t = new Token(lexema, estado);
                    tokens.add(t);
                }else if(estado < 20){
                    t = new Token("\"", 15);
                    tokens.add(t);
                    if( estado == 16 || estado == 17){
                        try{
                            t = new Token(lexema.split("\"")[1], 16);
                            tokens.add(t);
                        }catch(Exception e){
                            //System.out.println("Se capturo la excepcion cuando la cadena esta vacia");
                        }
                        
                        
                        if(estado == 17){
                            t = new Token("\"", 17);
                            tokens.add(t);
                        }
                    }
                }else{
                    t = new Token(lexema, 23);
                    tokens.add(t);

                }  
            }else{
                //System.out.println("Modo panico");
                t = new Token(lexema, estado);
                if(isGramatic(lexema))
                    modoPanico(t);
                else{
                    tokens.add(t);
                    //JOptionPane.showMessageDialog(null, "Tokens invalidos");
                    System.err.println("Token invalido");
                }
            }
        }
    /*for(int i=0; i<tokens.size(); i++){
        System.out.println(tokens.get(i));
    }*/    
    }

    public ArrayList<Token> getTokens() {
        return tokens;
    }

    
    public  void modoPanico(Token t){
        //System.out.println("Modo Panico");
        String p = t.getPalabra();
        String actual = "";
        String anterior;
        char c = '\0';
        int contador = 0;
        boolean isC = false, isN = false;
        for(int i=0; i<p.length(); i++){
            c = p.charAt(i);
            anterior = actual;
            actual += c;
            
            if(c >= 97  && c <= 122 || c >= 65 && c <= 90 || c == '_'){
                isC = true;
                if(isN){
                   if(!isReal(actual)){
                       int e = 19;
                       if(isEntero(anterior))
                           e = 18;
                        Token t1 = new Token(anterior, e);
                        tokens.add(t1);
                        actual = "" + c;
                        isN = false;
                    } 
                }
                
                for(String s: Sistema.reservadas){
                    if(s.equals(actual)){
                        Token t1 = new Token(actual, 1);
                        this.tokens.add(t1);
                        actual = "";
                    }
                }
                if(i == p.length() -1 && actual.length() > 1){
                    Token t1 = new Token(actual, 2);
                    this.tokens.add(t1);
                }
                
                
                
            }
            else if(c== '=' || c == '+' || c == '-' || c == '*' || c == '/' || c == '<' || c == '>' || c == '!'){
                contador++;
                if(contador == 2){
                    Token t1 = new Token(actual, 3);
                    this.tokens.add(t1);
                    actual = "";
                    contador = 0;
                }
            }
            else if(c >= 48 && c <= 57 || c == '.'){
                isN = true;
                //System.out.println(i + "  " +actual);
                if(isC){
                    Token t1 = new Token(anterior, 2);
                    this.tokens.add(t1);
                    actual = "" + c;
                    isC = false;
                    
                }else{
                    //System.out.println("entramos" + isReal(actual));
                    if(!isReal(actual) || i == p.length()-1){
                        if(!isReal(actual) && anterior.length() > 0){
                            Token t1 = new Token(anterior, 19);
                            this.tokens.add(t1);
                            //System.out.println("435AGREGAMOS" + anterior);
                            actual = "" + c;
                            //System.out.println("agregamos" + anterior);
                            //System.out.println(actual);
                            isN = false;
                            try{
                                if((p.charAt(i+1) >= 'a' && p.charAt(i+1) <= 'z') || p.charAt(i+1) >= 'A' && p.charAt(i+1) <= 'Z'){
                                    Token t2 = new Token(actual, 5);
                                    this.tokens.add(t2);
                                    actual = "";
                                }
                            }catch(Exception e){
                                
                            }
                        }else if(i == p.length()-1){
                            //actual = "" + c;
                            int e = 19;
                            if(isEntero(actual))
                                e = 18;
                            Token t1 = new Token(actual, e);
                            //System.out.println("Se agrego: " + actual + "cuando: " + (i == p.length()-1));
                            this.tokens.add(t1);
                            actual = "";
                        }
                        
                    }
                    
                }
            }    
        }
        if(actual.length() == 1)
            if(c == '.'){
                Token t1 = new Token("" + c, 5);
                this.tokens.add(t1);
            }else if((c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z') || c == '_'){
                Token t1 = new Token("" + c, 2);
                this.tokens.add(t1);
            }else if(c >= '0' && c <= '9'){
                Token t1 = new Token("" + c, 18);
                this.tokens.add(t1);
            }else if(c== '=' || c == '+' || c == '-' || c == '*' || c == '/' || c == '<' || c == '>'){
                int estado = 3;
                if(c == '=')
                    estado = 4;
                Token t1 = new Token("" + c, estado);
                this.tokens.add(t1);
            }
        
    }
    
    private boolean isReal(String s){
        int estado = 0;
        for(int i=0; i<s.length(); i++){
            char c = s.charAt(i);
            if(c >= 49 && c <= 57){
                switch(estado){
                    case 0: estado = 1; break;
                    case 1: estado = 1; break;
                    case 3: estado = 3; break;
                    case 4: estado = 3; break;
                    default: estado = 5;
                }
            }else if(c == '0'){
                switch(estado){
                    case 0: estado = 2; break;
                    case 1: estado = 1; break;
                    case 3: estado = 3; break;
                    case 4: estado = 3; break;
                    default: estado = 5;
                }
            }else if(c == '.'){
                switch(estado){
                    case 0: estado = 4; break;
                    case 1: estado = 3; break;
                    case 2: estado = 3; break;
                    case 3: estado = 5; break;
                    default: estado = 5;
                }
            }else{
                estado = 5;
            }
        }
        return estado == 3 || estado == 1 || estado == 2;
    }
    public boolean isEntero(String s){
        int estado = 0;
        for(int i=0; i<s.length(); i++){
            char c = s.charAt(i);
            if(c >= 49 && c <= 57){
                switch(estado){
                    case 0: estado = 1; break;
                    case 1: estado = 1; break;
                    default: estado = 5;
                }
            }else if(c == '0'){
                switch(estado){
                    case 0: estado = 3; break;
                    case 1: estado = 1; break;
                    default: estado = 4;
                }
            
            }else{
                estado = 5;
            }
        }
        return estado == 3 || estado == 1;
    }
    
    public DefaultTableModel getTabla(){
        DefaultTableModel df = null;
        Object [] columns = {"TIPO TOKEN","TOKEN", "REFERENCIAS"};
        Object [][]data;
        
        ArrayList<Object[]> aux = new ArrayList<Object[]>();
        
        for(int i=0; i<tokens.size(); i++){
            Object [] row = {tokens.get(i).getTipo(), tokens.get(i).getId()*Token.buffer, tokens.get(i).getPalabra()};
            if(aux.size() == 0){
                aux.add(row);
                
            }else{
                boolean agregado = false;
                boolean iguales = false;
                for(int j=0; j<aux.size(); j++){
                    Object [] a = (Object[]) aux.get(j);
                    if(a[0].equals(row[0])){
                        if(!a[2].equals(row[2])){
                            a[2] = a[2] + " | " + row[2];
                            agregado = true;
                            break;
                        }else{
                            iguales = true;
                        }
                    }
                }
                if(!agregado && !iguales){
                    aux.add(row);
                }
            }
        }
        
        data = new Object[aux.size()][3];
        for(int i=0; i<data.length; i++){
            data[i] = aux.get(i);
        }
        df = new DefaultTableModel(data, columns);
        return df;
    }
    
    private boolean isGramatic(String palabra){
        boolean result = false;
        for(int i=0; i<palabra.length(); i++){
            char c = palabra.charAt(i);
            if((c >= 48 && c <= 57 || c == '.' || c >= 'a' && c <= 'z' || c >= 'A' && c <= 'Z' || c== '=' || c == '+' || c == '-' || c == '*' || c == '/' || c == '<' || c == '>' || c == '_')){
                result = true;
                break;
            }
        }
        
        return result;
    }
    
    
}
