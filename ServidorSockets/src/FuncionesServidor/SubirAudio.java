package funcionesServidor;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.Socket;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;
import servidor.Peticion;

public class SubirAudio implements Runnable {

	private Peticion peticion;
	private Socket socket;

	public SubirAudio(Socket socket) {// , Peticion peticion) {
		// this.peticion = peticion;
		this.socket = socket;
	}

	@Override
	public void run() {
		byte[] receivedData;
		int in;
		String file;
		while (true) {
			receivedData = new byte[1024];
			BufferedInputStream bis;
			try {
				bis = new BufferedInputStream(socket.getInputStream());

				DataInputStream dis = new DataInputStream(socket.getInputStream());
				// ObjectOutputStream salidaObjeto = new
				// ObjectOutputStream(connection.getOutputStream());
				// Peticion peticion = new Peticion("descargar",0);
				// salidaObjeto.writeObject(peticion);
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
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		}
	}

}
