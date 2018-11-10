package baseball;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class BufferReader {

	BufferedReader reader;

	public BufferReader() {
		reader = new BufferedReader(new InputStreamReader(System.in));
	}
		
	public int Input() {
		int value = 0;
		try {
			value = Integer.parseInt(reader.readLine());

		}catch(Exception e) {
			return -1;
		}
		
		return value;
	}
}
