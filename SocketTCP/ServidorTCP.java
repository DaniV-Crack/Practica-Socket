package TCP;

import java.io.*;
import java.net.*;
import java.nio.channels.SocketChannel;

import javax.swing.Spring;

public class ServidorTCP {  
  public static final int PORT = 8888;
  public static void main(String[] args) throws IOException {
    // Establece el puerto en el que escucha peticiones
    ServerSocket socketServidor = null;
    try {
      socketServidor = new ServerSocket(PORT);
    } catch (IOException e) {
      System.out.println("No puede escuchar en el puerto: " + PORT);
      System.exit(-1);
    }

    Socket socketCliente = null;
    BufferedReader entrada = null;
    PrintWriter salida = null;

    System.out.println("Escuchando: " + socketServidor);
    try {
    	
    	while(true) {
	      // Se bloquea hasta que recibe alguna peticiÃ³n de un cliente
	      // abriendo un socket para el cliente
	      socketCliente = socketServidor.accept();
	      System.out.println("Conexión aceptada: "+ socketCliente);
	      System.out.println(socketCliente.getPort());
	      
	      
	      // Establece canal de entrada
	      entrada = new BufferedReader(new InputStreamReader(socketCliente.getInputStream()));
	      // Establece canal de salida
	      salida = new PrintWriter(new BufferedWriter(new 
		  OutputStreamWriter(socketCliente.getOutputStream())),true);
	      
	      boolean sw = true;
	      String cad="";
	      while (sw) {  
	        String num = entrada.readLine();
	     if(num.equals("1"))cad="Papel";
	     if(num.equals("2"))cad="Piedra";
	     if(num.equals("3"))cad="Tijera";
	     if(num.equals("4")) {
	    	 cad="Adios";
	    	 sw=false;
	     }
	     System.out.println("Cliente: " +num);
		   salida.println(cad);
         }
    }

    } catch (IOException e) {
      System.out.println("IOException: " + e.getMessage());
    }  
    salida.close();
    entrada.close();
    socketCliente.close();
    socketServidor.close();
  }
}