package package_a;

import java.io.File;
import java.util.Scanner;

public interface FileOperations {
	
	public static File getFile (Scanner inScanner) {
			while (true) {
				Printable.printChoosingFile();
				String inputString = inScanner.nextLine();
				if (inputString.contains("exit")) {System.exit(0);}			
				if (new File(inputString).exists()) { return new File(inputString);}
				System.out.println("The file doesnt exist.");
			}
	}
	
	public static void sendFile () {
		
	}
	
}
