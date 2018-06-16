package funcionesServidor;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.Socket;

import servidor.Peticion;

public class ReproducirAudio implements Runnable {
	private Peticion peticion;
	private Socket socket;
	
	public ReproducirAudio(Socket socket) {//, Peticion peticion) {
		//this.peticion = peticion;
		this.socket = socket;
	}

	@Override
	public void run() {
		int in;
		final String filename = "C:\\Users\\Public\\Music\\Sample Music\\Kalimba.mp3";
		final File localFile = new File(filename);
		BufferedInputStream bis = null;
		BufferedOutputStream bos = null;
		try {
			bis = new BufferedInputStream(new FileInputStream(localFile));
			bos = new BufferedOutputStream(socket.getOutputStream());
			//DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
			
			
			//dos.writeUTF(localFile.getName());
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
}
