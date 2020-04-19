package package_a;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.Socket;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;


public class Client implements Runnable, Printable, IpValidator, FileOperations {
	
	private Path path = Paths.get(System.getProperty("user.dir") + "\\FileLocationClient");
	private File file = path.toFile();
	
	public static void main (String[] args ) throws IOException {
		Client client = new Client();
		Thread thread = new Thread(client);
		thread.start();
	}
	
	@Override
	public void run () {
		String ip = "localhost";
		Scanner inScanner = new Scanner(System.in);
		Printable.printIpIntro(ip);
		ip = ipInputCheck(ip, inScanner);
		file = getFile(inScanner);
		
		try(Socket socket = new Socket(ip, 4999);
			DataInputStream dataInputStream = new DataInputStream(new FileInputStream(getFile()));
			DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());)
		{
			dataOutputStream.writeUTF(file.toString()); // send the name of file
			dataOutputStream.flush();
			dataOutputStream.writeLong(file.length()); // send the length of file
			dataOutputStream.flush();
					
			readAndSendFile(dataOutputStream, dataInputStream, file.length());
			System.out.println("File has been sent");
		} catch (Exception e) {
			System.err.println(e);
		}
		inScanner.close();
	}

	private String ipInputCheck(String ip, Scanner inScanner) {
			while (true) {
				String inputString = inScanner.nextLine();
				if (inputString.contains("exit")) {System.exit(0);}
				if (inputString.isEmpty()) {return ip;}
				if (IpValidator.checkIp(inputString)) { 
					ip = inputString;
					break;
					} else {
						System.out.println("Thats not valid IP adress. Please provide valid IP adress or exit.");
					}
				}
			return ip;
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

	public void intro () {
		System.out.println("Please select one of the following options:");
		System.out.println("1. choose a file");
		System.out.println("2. send a file");
		System.out.println("3. close the program");
		Printable.printFile(path.toString());
	}
	
	
}

	
