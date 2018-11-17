package kosta;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;


public class Main {

	public static void main(String[] args) {

		InputStreamReader streamReader = null;
		BufferedReader buffReader = null;
	
		MiniDos minidos = new MiniDos(System.getProperty("user.dir"));	// 현재위치

		try {
			
			streamReader = new InputStreamReader(System.in);
			buffReader = new BufferedReader(streamReader);
					
			while(true){					
				String command = buffReader.readLine();
				int spaceIndex = command.indexOf(' ');
				String firstPath = command;
				String twoPath = null;
				if(-1 != spaceIndex){
					firstPath = command.substring(0, spaceIndex);
					twoPath = command.substring(spaceIndex+1);
				}
				if(true == firstPath.equals("ls")){
					minidos.printList();
				}else if(true == firstPath.equals("cd")){
					if(false == minidos.moveDirectory(twoPath))
						System.out.println(twoPath+"No such file or directory");
					
				}else if(true == firstPath.equals("mkdir")){
					if(false == minidos.creatDirect(twoPath))
						 System.out.println("cannot create directory"+twoPath+": No such file or directory");
					
				}else if(true == firstPath.equals("cp")){
					minidos.copyDirectory(twoPath);
				}else if(true == firstPath.equals("pwd")){
					System.out.println(minidos.getCurrentPath());
				}
			}//while
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
				try {
					if(null != buffReader )
						buffReader.close();
				} catch (IOException e) { e.printStackTrace(); }

		}
	}
}
