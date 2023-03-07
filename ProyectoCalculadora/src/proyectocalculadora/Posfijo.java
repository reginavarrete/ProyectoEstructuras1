/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyectocalculadora;

import proyectocalculadora.PilaA;
import proyectocalculadora.PilaADT;

/**
 *
 * @author fernando.puron
 */
public class Posfijo {
    private String cadena;
    public Posfijo(){
    }    
    
    public Posfijo(String cadena){
        this.cadena = cadena;
        
    }
    public String convierte_Posfijo(){
        int i,n;
        char caracter;
        String postfija;
        postfija = "";
        i =0;
        n = cadena.length();
        PilaADT<Character> pila = new PilaA();
        while (i<n){
            caracter = cadena.charAt(i);
            if (caracter == '(')
                pila.push(caracter);
            else
                if (caracter == ')'){ // Es paréntesis derecho
                    while (!pila.peek().equals('('))
                        postfija = postfija +pila.pop();
                    pila.pop();
                }
                else
                    if (noEsOperador(caracter)) // Es un operando
                        postfija = postfija + caracter;
                        
                    
                    else{  // Es un operador
                        while (!pila.isEmpty() && prioridad(pila.peek()) > prioridad(caracter))
                            postfija = postfija + pila.pop();
                            
                        
                        pila.push(caracter);
                        }
                     
            i++;
        }
        while (!pila.isEmpty())
            postfija = postfija + pila.pop();
        
    
        return postfija;
        
    }
    private boolean noEsOperador(char dato){
        return dato !='+' && dato !='-' && dato != '*' && dato !='/';
    }
    
    /* Método auxiliar para el manejo de las prioridades de los operadores. Regresa 0,
     * el valor más pequeño, cuando el dato dado es un "(". De esta manera
     * el "(" sólo se saca de la pila cuando se encuentre un ")".
     */
    private int prioridad(char dato){
        int resultado = 0; // En caso de que el dato sea un paréntesis izquierdo
        
        switch (dato){
            case '+':
            case '-': resultado = 1;
                break;
            case '*':
            case '/': resultado = 2;
        }
        return resultado;
    }
    
}
