package kosta;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MiniDos {
	private String currentPath;
	private File file;
	//private FileInputStream fileInputStream;
	//private FileOutputStream fileOutputStream;

	
	public MiniDos(String currentPath){
		this.currentPath = currentPath;
		file = new File(this.currentPath);
	}
	
	public String getCurrentPath(){
		return currentPath;
	}
	
	// 파일목록 출력
	public void printList(){
		System.out.println("name\t\t\t d/f \t\t modify");
		File[] arr = file.listFiles();
		for(int i = 0; i < arr.length; ++i) {
			System.out.printf("%-20s",arr[i].getName());
			if(true == arr[i].isDirectory())
				System.out.printf("%-10s","directory");
			else
				System.out.printf("%-10s","file");

			SimpleDateFormat sf = new SimpleDateFormat();			
			System.out.printf("%-20s",sf.format(file.lastModified()));
			System.out.println();
		}
	}
	
	// 디렉토리 이동(정규표현식 써야할 듯?)
	public boolean moveDirectory(String movePath){
		Pattern pattern = Pattern.compile("^[../]");
		Matcher matcher = pattern.matcher(movePath);
		
		
		if(true == matcher.find()){ // 상위폴더 이동
			
			int index = movePath.indexOf("/");
			System.out.println("index:"+index);
			
			String path = movePath.substring(index+1);	// 남은 위치 가져옴
			
			int lastPathIndex = currentPath.lastIndexOf("\\");				// 마지막 \\을 찾는다
			if(-1 == lastPathIndex){
				// 파일을 찾을 수 없습니다
				return false;
			}
			String tempPath = currentPath;
			tempPath = currentPath.substring(0, lastPathIndex);
			System.out.println("tempPath:"+tempPath+" movePath:"+movePath);
			File moveFile = new File(tempPath, path);
			if(false == moveFile.exists()){
				System.out.println("파일을 찾을 수 없습니다");
				return false;
			}

			UpdateCurrentFile(moveFile, moveFile.getPath());
			
		}else{
			System.out.println("else");

			File file = new File(currentPath, movePath);
			if(false == file.exists())
				return false;
			
			UpdateCurrentFile(file, file.getPath());
		}
		
		return true;
	}
	
	// 새로운 디렉토리 생성
	public boolean creatDirect(String newDirectoryName){
		File f = new File(currentPath,newDirectoryName);
		return f.mkdir();
	}
	
	// 현재 디렉토리로 모든 파일 복사 하기
	public void copyDirectory(String copyPath) throws Exception{
		file = new File(currentPath);
		File copyFile = new File(copyPath);
		
		System.out.println("copyFile:"+copyFile.getPath());
		copyFile.mkdir();
		copyFile(file, copyFile);
	}
	
	public void copyFile(File ori, File copy) throws Exception{
		// 여기도 ../체크해야할듯?
		File[] fileList = file.listFiles();
		
		for(int i = 0; i < fileList.length; ++i){
			file = fileList[i];
			//System.out.println("file:"+file);
			if(true == file.isDirectory()){
				//폴더 생성
				File newFile = new File(copy, file.getName());
				//System.out.println("newCopy:"+newCopy.getPath());
				if(false == newFile.mkdirs()){
					throw new Exception("아 에러남;");
				}
				
				copyFile(file, newFile);
				
			}else{
				File newFile = new File(copy, file.getName());
				copy(file, newFile);
			}
		}
	}
	
	// 파일복사 D:\ex
	// copy 함수에서만 문제있음..
	public void copy(File ori, File copy){
		FileInputStream fileInputStream = null;
		FileOutputStream fileOutputStream = null;
		//System.out.println("ori:"+ori.getPath());
		//System.out.println("copy:"+copy.getPath());

		try{
		fileInputStream = new FileInputStream(ori);
		fileOutputStream = new FileOutputStream(copy);
		byte[] data = new byte[50];
		while(true){
			int value = fileInputStream.read(data);			
			if(-1 == value)
				break;
			
			fileOutputStream.write(data, 0, value);			// 얼마만큼 복사하는지 명시해야한다. 안그러면 파일복사가 제대로 이루어지지 않는다
		}
		
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			try {
				if(null != fileInputStream)
					fileInputStream.close();
				
				if(null != fileOutputStream)
					fileOutputStream .close();
				
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	protected void UpdateCurrentFile(File currentFile, String path){
		file = currentFile;
		currentPath = path;		
	}
}
