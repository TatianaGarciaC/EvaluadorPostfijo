/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * Universidad Ean (Bogotá - Colombia)
 * Departamento de Tecnologías de la Información y Comunicaciones
 * Licenciado bajo el esquema Academic Free License version 2.1
 * <p>
 * Proyecto Evaluador de Expresiones Postfijas
 * Fecha: Febrero 2021
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */
package universidadean.desarrollosw.postfijo;

import java.io.IOException;
import java.io.StreamTokenizer;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;

/**
 * Esta clase representa una clase que evalúa expresiones en notación polaca o
 * postfija. Por ejemplo: 4 5 +
 */
public class EvaluadorPostfijo {

    /**
     * Realiza la evaluación de la expresión postfijo utilizando una pila
     * @param expresion una lista de elementos con números u operadores
     * @return el resultado de la evaluación de la expresión.
     */
    static int evaluarPostFija(List<String> expresion) {
        Stack<Integer> pila = new Stack<>();
        
        for(String valor: expresion) {
        	
        	Integer num = validarValorNumerico(valor);
        	if(num!=null) {
        	pila.push(num);
        	} else {
        	  int val1 = pila.pop();
        	  int val2 = pila.pop();
        	  
        	  if(valor.equals("*")) {
        		 //Multiplicacion 
        		 pila.push(val1*val2);
        	  } else if(valor.equals("+")) {
        		 //Suma 
         		 pila.push(val1+val2);
        	  } else if(valor.equals("-")) {
        		 //Resta 
         		 pila.push(val2-val1);
        	  } else if(valor.equals("/")) {
        		 //Division 
         		 pila.push(val2/val1);
        	  } else if(valor.equals("%")) {
        		 //Modulo 
         		 pila.push(val2%val1);
        	  }
        	}
        } 
       
        return pila.pop();
    }

    /**
     * Programa principal
     */
    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);

        System.out.print("> ");

        String linea = teclado.nextLine();

        try {
            List<String> expresion = Token.dividir(linea);
            System.out.println(evaluarPostFija(expresion));
        }
        catch (Exception e) {
            System.err.printf("Error grave en la expresión: %s", e.getMessage());
        }

    }
    
    
    /**
     * Validar si es un numero para devolverlo en el formato correcto
     */
    public static Integer validarValorNumerico(String s) {
    	try {
    		
    		Integer val = Integer.parseInt(s);
    		return val;
    		
    	} catch (NumberFormatException e) {
    		return null;
    	}
    }
}
