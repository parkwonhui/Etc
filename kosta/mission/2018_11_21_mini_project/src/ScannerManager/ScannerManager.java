package ScannerManager;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Scanner;

public class ScannerManager {
	static BufferedReader in;
	static {
		 in = new BufferedReader(new InputStreamReader(System.in));
	}
	 
	static public String ReadString() throws Exception{
		String str = null;
		in = new BufferedReader(new InputStreamReader(System.in));
		str = in.readLine();
		return str;
	}
	
	static public int ReadInt() throws Exception {
		int value = 0;
		in = new BufferedReader(new InputStreamReader(System.in));
		String str = in.readLine();
		value = Integer.parseInt(str);
	
		return value;
	}

}
