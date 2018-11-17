package kosta;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/*
>ls
=> 파일목록 출력

>cd kosta
=>디렉토리 이동

>cd..
=> 부모디렉토리 이동

>mkdir kosta
=> 새로운 디렉토리 생성

>cp /home/test
=>현재디렉토리에 /home/test에 있는 모든 파일 복사*/

public class Main {

	public static void main(String[] args) {

		InputStreamReader streamReader = null;
		BufferedReader buffReader = null;
	
		// 현재위치 구해서 나중에 넣어주자
		// D:\\study\\kosta\\
		MiniDos minidos = new MiniDos("D:\\study\\kosta\\mission\\2018_11_10_Baseball");
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
					minidos.moveDirectory(twoPath);
				}else if(true == firstPath.equals("mkdir")){
					if(false == minidos.creatDirect(twoPath)) {
						 System.out.println("cannot create directory"+twoPath+": No such file or directory");

						
					}
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
