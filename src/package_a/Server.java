package package_a;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
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
	Path path = Paths.get(System.getProperty("user.dir") + "\\FileLocationServer");
	File file = path.toFile();

	public static void main (String[] args ) throws IOException {
		Client client = new Client();
		client.intro();
		
		try(	
			ServerSocket serverSocket = new ServerSocket(4999);
			Socket socket = serverSocket.accept();
			InputStream inputStream = new BufferedInputStream(new FileInputStream(client.getFile()));
			OutputStream outputStream = new BufferedOutputStream(new FileOutputStream(client.getFile()));)
		{
			
			
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

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

}
