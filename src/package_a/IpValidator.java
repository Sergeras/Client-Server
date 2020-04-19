package package_a;

import java.util.stream.Stream;

public interface IpValidator {

	public static  boolean checkIp (String ip) {
		String [] list = ip.trim().split("\\.");
		
		if (list.length != 4) {return false;}
		
		Stream <String> stream = Stream.of(list);
		try {
			return stream.filter(x -> x.length() >= 1 && x.length() <=3 )
					  	 .map(Integer::parseInt)
					  	 .filter(i -> (i >=0 && i <= 255))
					     .count()== 4;
		} catch (NumberFormatException e) {
			return false;
		}
	}
}