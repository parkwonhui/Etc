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
	
	// 파일목록 출력
	public void printList(){
		System.out.println("name\t\t\\t\tt d/f \t\t modify");
		File[] arr = getCurrentFile().listFiles();
		for(int i = 0; i < arr.length; ++i) {
			System.out.printf("%-40s",arr[i].getName());
			if(true == arr[i].isDirectory())
				System.out.printf("%-10s","directory");
			else
				System.out.printf("%-10s","file");

			SimpleDateFormat sf = new SimpleDateFormat();			
			System.out.printf("%-20s",sf.format(getCurrentFile().lastModified()));
			System.out.println();
		}
	}
	
	// 디렉토리 이동
	public boolean moveDirectory(final String movePath){
		
		String tempMovePath = movePath;
		while(true == isMoveParentDirectory(tempMovePath)){			 // 상위폴더 이동
			tempMovePath = MoveParentDirectory(tempMovePath);
			if(null == tempMovePath)
				return false;
		}
		
		File file = new File(getCurrentPath(), tempMovePath);
		if(false == file.exists())
			return false;
		
		updateCurrentFile(file, file.getPath());
		
		return true;
	}
	
	// 새로운 디렉토리 생성
	public boolean creatDirect(final String newDirectoryName){
		String tempMovePath = newDirectoryName;
		while(true == isMoveParentDirectory(tempMovePath)){			 // 상위폴더 이동
			tempMovePath = MoveParentDirectory(tempMovePath);
			if(null == tempMovePath)
				return false;
		}

		File f = new File(getCurrentPath(),tempMovePath);
		return f.mkdir();
	}
	
	// 현재 디렉토리로 모든 파일 복사 하기
	public void copyDirectory(String copyPath) throws Exception{
		file = new File(getCurrentPath());
		File copyFile = new File(copyPath);
		
		System.out.println("copyFile:"+copyFile.getPath());
		copyFile.mkdir();
		copyFile(file, copyFile);
	}
	
	public void copyFile(File ori, File copy) throws Exception{
		// 여기도 ../체크해야할듯?
		File[] fileList = getCurrentFile().listFiles();
		
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

	
	protected void updateCurrentFile(File currentFile, String path){
		file = currentFile;
		currentPath = path;
	}
	
	private String MoveParentDirectory(String tempMovePath) {
		File moveFile = getMoveParentDirectoryFile(tempMovePath);
		if(null == moveFile)
			return null;
		
		tempMovePath = removeStartPath(tempMovePath, '/');
		if(null == tempMovePath)
			return null;
	
		updateCurrentFile(moveFile, moveFile.getPath());
		return tempMovePath;
	}
	
	private boolean isMoveParentDirectory(final String path) {		// 상위폴더로 이동하는가?
		Pattern pattern = Pattern.compile("^[../]");
		Matcher matcher = pattern.matcher(path);
		return matcher.find();		
	}
	
	private File getMoveParentDirectoryFile(String movePath) {
		int lastPathIndex = getCurrentPath().lastIndexOf("\\");		// 마지막 \\을 찾는다
		if(-1 == lastPathIndex){
			// 파일을 찾을 수 없습니다
			return null;
		}
		String tempPath = getCurrentPath();
		tempPath = getCurrentPath().substring(0, lastPathIndex);		// 마지막 폴더 자르기
		
		File moveFile = new File(tempPath);
		if(false == moveFile.exists()){
			System.out.println("파일을 찾을 수 없습니다");
			return null;
		}
		
		return moveFile;
	}
	
	private String removeStartPath(final String path, final char findWord) {
		int index = path.indexOf(findWord);
		if(-1 == index)
			return null;
		
		return path.substring(index+1);
	}
	
	public String getCurrentPath(){
		return currentPath;
	}
	
	public File getCurrentFile() {
		return file;
	}
	
}
