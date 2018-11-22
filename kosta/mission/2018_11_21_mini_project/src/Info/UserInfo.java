package Info;

import java.io.*;
import java.nio.*;
import java.util.*;

import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


public class UserInfo {

		private String id;
		private String pass;
		
		
		public UserInfo(String id, String pass) {
			super();
			this.id = id;
			this.pass = pass;
		}
		
		public String getId() {
			return id;
		}
		public void setId(String id) {
			this.id = id;
		}
		public String getPass() {
			return pass;
		}
		public void setPass(String pass) {
			this.pass = pass;
		}

		@Override
		public String toString() {
			return "UserInfo [id=" + id + ", pass=" + pass + "]";
		}
		
		
		
}
