package UDP;

import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class ServidorUDP {
	
	public static int contarPalabras(String s) {
	    int contador = 1, pos;
	    s = s.trim();                              
	    if (s.isEmpty()) { 
	        contador = 0;
	    } else {
	            pos = s.indexOf(" "); 
	            while (pos != -1) {   
	                   contador++;    
	                   pos = s.indexOf(" ", pos + 1);                       
	            }                                     
	    }
	    return contador;
	}

	public static void main(String[] args) {
		
		System.out.println("---SERVIDOR---");
		try {
			DatagramSocket socketUDP = new DatagramSocket(8888);
			byte[] bufer = new byte[10000];
			while(true){
				DatagramPacket peticion = new DatagramPacket(bufer, bufer.length);
				socketUDP.receive(peticion);
				
				//DatagramPacket mensaje =  new DatagramPacket(peticion.getData(), peticion.getLength(),peticion.getAddress(),peticion.getPort());
				//socketUDP.send(mensaje);
				
				String res = new String(peticion.getData());
				String env = String.valueOf(contarPalabras(res));
				byte [] enviar = env.getBytes();
				DatagramPacket mensaje = new DatagramPacket(enviar,env.length(),peticion.getAddress(),peticion.getPort());
				socketUDP.send(mensaje);
				System.out.println(" Datos: "+new String(peticion.getData()));
				System.out.println(" Puerto del cliente: "+peticion.getPort());
				System.out.println(" Tamaño: "+peticion.getLength());	
			}
			
			
		} catch (Exception e) {
			System.out.println(e);
		}

	}

}
