package package_a;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInput;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Server {
	Path path = Paths.get(System.getProperty("user.dir") + "\\FileLocationServer\\");

	public static void main (String[] args ) throws IOException {
		Client client = new Client();
		//client.intro();
		
		try(	
			ServerSocket serverSocket = new ServerSocket(4999);
			Socket socket = serverSocket.accept();
			InputStream inputStream = socket.getInputStream();
			DataInputStream dataInputStream = new DataInputStream(inputStream);
			OutputStream outputStream = new BufferedOutputStream(new FileOutputStream("D:\\stazene soubory\\a.txt"));)
		{
			String fileName = dataInputStream.readUTF();
			System.out.println(fileName);
			byte [] byteArray = new byte [1024];
			while (inputStream.read() != -1) {
				inputStream.read(byteArray, 0, byteArray.length);
				outputStream.write(byteArray, 0, byteArray.length);
				outputStream.flush();
			}
			
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	public Path getPath() {
		return path;
	}

	public void setPath(Path path) {
		this.path = path;
	}


}
