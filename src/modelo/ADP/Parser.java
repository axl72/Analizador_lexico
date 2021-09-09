package modelo.ADP;

import java.io.File;
import java.util.ArrayList;
import modelo.Sistema;
import modelo.util.Excel;

public class Parser {
    Queue <String> entrada;
    Stack<String> stack;
    TAS tabla;
    public Parser(Queue<String> entrada){
        this.entrada = entrada;
        stack = new Stack();
        tabla = new TAS();
    }

    public boolean analizar(){
        Sistema.producciones = new ArrayList<Object[]>();
        int contador=0;
        String [] produccion = null;
        
        boolean error = false;
        //Inicializacion de los primeros componentes lexicos
        stack.push("$");
        stack.push("programa");
        
        //cimaPila apunta al primer componenete léxico
        String cimaPila = stack.getLast(), primero;
        
        //Hasta que X = $
        while(!(stack.getLast()).equalsIgnoreCase("$") && !error){
            contador++;
            Object []salida = new Object[4];
            primero = entrada.getFirst();
            salida[0] = contador;
            salida[1] = "" + this.stack.toString();
            salida[2] = "" + entrada.toString();
            cimaPila =(String) stack.pop();
            
            //Si X pertener a los Vt
            if(cimaPila.equals(primero)){
                salida[3] = "";
                
                /*
                sacar X de la Pila está implicito ya
                que cada vez que le pedimos un elemento a la Pila este 
                ya sale de la Pila
                */
                //Avanzar sim
                entrada.dequeue();
            
            //Sino si M[X, a] = --> Y1, Y2 ... Yk
            }else{
                
                String p;
                
                try{
                    p = tabla.getProduction(cimaPila, primero);
                    /*
                    sacar X de la Pila está implicito ya
                    que cada vez que le pedimos un elemento a la Pila este 
                    ya sale de la Pila
                    */
                    
                    produccion = p.split("\\|");
                    salida[3] = "";
                    
                    //meter Yk Yk-1 ... Y1 con Yi en la cima
                    for(int i=0; i<produccion.length; i++){
                        salida[3] += produccion[i] + "  ";
                    }
                    for(int i=produccion.length-1 ; i>=0 && !produccion[i].equalsIgnoreCase("&"); i--)
                    stack.push(produccion[i]);
                /*
                    En caso no pueda lograr esto se producira una excepcion con lo cual no es necesario
                    crear una condicional más como está en el algoritmo, sino solo debemos tratar la 
                    Excepcion generada.
                    */
                
                //Este bloque catch simboliza:
                //sino Error
                }catch(Exception e){
                    error = true;
                    System.out.println(e);
                }
                
                
            }
            
        Sistema.producciones.add(salida);
        
        }
        String []salida = new String[4];
        salida[0] = "" + (contador+1);
        salida[1] = "" + this.stack;
        salida[2] = "" + entrada;
        salida[3] = stack.getLast().equalsIgnoreCase(entrada.getFirst()) ? "Aceptacion" : "No aceptacion";
        Sistema.producciones.add(salida);
                
        return stack.getLast().equalsIgnoreCase("$") && entrada.getFirst().equalsIgnoreCase("$");
    }
    
    public void setTas(String path){
        Excel xlsx = new Excel("TAS", new File("C:\\Users\\axell\\Desktop"));
        tabla.setMatrix(xlsx.cargarExcel(path));
        
    }

}
