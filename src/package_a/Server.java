package package_a;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Server {
	Path path = Paths.get(System.getProperty("user.dir") + "\\FileLocationServer\\");

	public static void main (String[] args ) throws IOException {
		try(	
			ServerSocket serverSocket = new ServerSocket(4999);
			Socket socket = serverSocket.accept();
			DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());
			DataOutputStream dataOutputStream = new DataOutputStream(new FileOutputStream("D:\\stazene soubory\\a.txt"));)
		{
			String fileName = dataInputStream.readUTF();
			Long fileLenght = dataInputStream.readLong();
			Long counter = 0L;
			
			byte [] byteArray = new byte [1024];
			while (fileLenght - counter > 0) {
				
				if (fileLenght - counter > byteArray.length) {
					dataInputStream.read(byteArray, 0, byteArray.length);
					dataOutputStream.write(byteArray, 0, byteArray.length);
					dataOutputStream.flush();
				} else {
					dataInputStream.read(byteArray, 0, (int) Math.subtractExact(fileLenght, counter));
					dataOutputStream.write(byteArray, 0, (int) Math.subtractExact(fileLenght, counter));
					dataOutputStream.flush();
				}
				counter += byteArray.length;
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
