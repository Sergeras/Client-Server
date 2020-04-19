package package_a;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Server implements FileOperations, Runnable {
	Path path = Paths.get(System.getProperty("user.dir") + "\\FileLocationServer\\");
	
	public static void main (String[] args ) throws IOException {
		Server server = new Server();
		Thread thread = new Thread(server);
		thread.run();
	}
	
	@Override
	public void run () {
		DataOutputStream dataOutputStream = null;
		DataInputStream dataInputStream = null;
		
		try(	
			ServerSocket serverSocket = new ServerSocket(4999);
			Socket socket = serverSocket.accept();)
		{
			dataInputStream = new DataInputStream(socket.getInputStream());
			String fileName = dataInputStream.readUTF(); 
			Long fileLenght = dataInputStream.readLong(); 
			
			createFolder(new File(getPath().toString())); // check for folder and if missing create it
			dataOutputStream = new DataOutputStream(new FileOutputStream(getPath() + getNameFromPath(fileName)));
			readAndSendFile(dataOutputStream, dataInputStream, fileLenght);
			System.out.println("File has been received");
		} catch (Exception e) {
			System.err.println(e);
		} 
	}
	
	public Path getPath() {
		return path;
	}

	public void setPath(Path path) {
		this.path = path;
	}


}
