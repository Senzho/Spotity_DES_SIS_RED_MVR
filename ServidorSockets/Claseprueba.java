package cliente;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;

public class Main {

	public static void main(String[] args) {
		byte[] receivedData;
		int in;
		String file;

		try {
			Socket connection = new Socket("localhost",7000); 
			while (true) {
				receivedData = new byte[1024];
				BufferedInputStream bis = new BufferedInputStream(connection.getInputStream());
				DataInputStream dis = new DataInputStream(connection.getInputStream());
				//file = dis.readUTF();
				//file = file.substring(file.indexOf('\\') + 1, file.length());
				
				
				//BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(file));
				while ((in = bis.read(receivedData)) != -1) {
					bos.write(receivedData, 0, in);
				}
				//FileInputStream f2 = new FileInputStream(file);
				Player apl;
				try {
					apl = new Player(f2);
					System.out.println("reproduciendo...");
					apl.play();
				} catch (JavaLayerException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				bos.close();
				dis.close();
			}
		} catch (Exception e) {
			System.err.println(e);
		}
		
	}

}
/*esto es para bajar audio
 * byte[] receivedData;
		int in;
		String file;

		try {
			Socket connection = new Socket("localhost",7000); 
			while (true) {
				receivedData = new byte[1024];
				BufferedInputStream bis = new BufferedInputStream(connection.getInputStream());
				DataInputStream dis = new DataInputStream(connection.getInputStream());
				//ObjectOutputStream salidaObjeto = new ObjectOutputStream(connection.getOutputStream());
				//Peticion peticion = new Peticion("descargar",0);
				//salidaObjeto.writeObject(peticion);
				file = dis.readUTF();
				file = file.substring(file.indexOf('\\') + 1, file.length());
				BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(file));
				while ((in = bis.read(receivedData)) != -1) {
					bos.write(receivedData, 0, in);
				}
				FileInputStream f2 = new FileInputStream(file);
				Player apl;
				try {
					apl = new Player(f2);
					System.out.println("reproduciendo...");
					apl.play();
				} catch (JavaLayerException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				bos.close();
				dis.close();
			}
		} catch (Exception e) {
			System.err.println(e);
		}*/


 //esto es para subir audio
/*Socket socket = null;
try {
socket = new Socket("localhost",7000);
} catch (IOException e1) {
// TODO Auto-generated catch block
e1.printStackTrace();
} 
int in;
final String filename = "C:\\Users\\Public\\Music\\Sample Music\\Kalimba.mp3";
final File localFile = new File(filename);
BufferedInputStream bis = null;
BufferedOutputStream bos = null;
try {
bis = new BufferedInputStream(new FileInputStream(localFile));
bos = new BufferedOutputStream(socket.getOutputStream());
DataOutputStream dos = new DataOutputStream(socket.getOutputStream());


dos.writeUTF(localFile.getName());
// Enviamos el fichero
byte[] byteArray = new byte[8192];
while ((in = bis.read(byteArray)) != -1) {
	bos.write(byteArray, 0, in);
}
} catch (FileNotFoundException e) {
// TODO Auto-generated catch block
e.printStackTrace();
} catch (IOException e) {
// TODO Auto-generated catch block
e.printStackTrace();
}

try {
bis.close();
bos.close();
} catch (IOException e) {
// TODO Auto-generated catch block
e.printStackTrace();
}
	
}
*/