package TCP;

import java.net.*;
import java.io.*;

public class ClienteTCP {
  public static void main(String[] args)  throws IOException {
    Socket socketCliente = null;
    BufferedReader entrada = null;
    PrintWriter salida = null;

    // Creamos un socket en el lado cliente, enlazado con un
    // servidor que estÃ¡ en la misma mÃ¡quina que el cliente
    // y que escucha en el puerto 4444
    try {
      socketCliente = new Socket("localhost", 8888);
      // Obtenemos el canal de entrada
      
      entrada = new BufferedReader(new InputStreamReader(socketCliente.getInputStream()));
      
      // Obtenemos el canal de salida
      salida = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socketCliente.getOutputStream())),true);
    } catch (IOException e) {
	System.err.println("No puede establer canales de E/S para la conexiÃ³n");
        System.exit(-1);
    }
    BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
    String linea;
    
    try {
    	boolean sw = true;
      while (sw) {
    	System.out.println("    MENU   ");
    	System.out.println("1. Opción 1");
    	System.out.println("2. Opción 2");
    	System.out.println("3. Opción 3");
    	System.out.println("4. Salir");
        String num = stdIn.readLine();
        salida.println(num);
        if(num.equals("4")) sw=false;
        linea = entrada.readLine();
        System.out.println("Respuesta servidor: " + linea);
      }
    } catch (IOException e) {
	System.out.println("IOException: " + e.getMessage());
    }
 
    // Libera recursos
    salida.close();
    entrada.close();
    stdIn.close();
    socketCliente.close();
  }
}