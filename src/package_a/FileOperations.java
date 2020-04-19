package package_a;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public interface FileOperations {
	// loop for sending the file over the socket in size of array
	public default void readAndSendFile(DataOutputStream dataOutputStream, DataInputStream dataInputStream, Long fileLenght)
			throws IOException {
		byte [] byteArray = new byte [1024];
		Long counter = 0L;
		
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
	}
	
	public default void readAndSendFile(DataOutputStream dataOutputStream, DataInputStream dataInputStream, Long fileLenght, int arraySize)
			throws IOException {
		byte [] byteArray = new byte [arraySize];
		Long counter = 0L;
		
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
	}
	
	public default File getFile (Scanner inScanner) {
			while (true) {
				Printable.printChoosingFile();
				String inputString = inScanner.nextLine();
				if (inputString.contains("exit")) {System.exit(0);}			
				if (new File(inputString).exists()) { return new File(inputString);}
				System.out.println("The file doesnt exist.");
			}
	}
	
	public default String getNameFromPath (String path) {
		return path.substring(path.lastIndexOf("\\"), path.length());
	}
	
	public default void createFolder (File directory) {
		if (!directory.exists()) {
			directory.mkdir();
		}
	}
}
