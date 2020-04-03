package package_a;

public interface Printable {
	
	public static void printFile (String filePath) {
		System.out.println("Current file is: " + filePath.toString());
	}
	
	public static void printChoosingFile () {
		System.out.println("Please type in file path and name of the file.");
	}
	
	public static void printIp (String ip) {
		System.out.println("Selected IP is: " + ip);
	}
	
	public static void printValidId () {
		System.out.println("Please provide valid IP adress.");
	}
	
	public static void printIpIntro(String ip) {
		System.out.println("If you like to provide IP please type it in. Otherwise press enter.");
		System.out.println("If you like to close application please type in 'exit'.");
		printIp(ip);
	}
	
}
