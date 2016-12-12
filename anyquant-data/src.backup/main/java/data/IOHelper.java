package data;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;


public class IOHelper {
	
	//filepath 文件路径   obj 要写的对�?
	public boolean writeToDisk(String filepath,Object obj) throws IOException{
		FileOutputStream fos=new FileOutputStream(filepath,false);
		ObjectOutputStream oos =new ObjectOutputStream(fos);
		oos.writeObject(obj);
		oos.close();
		return true;	
	}
	
	public Object readFromDisk(String filepath) throws IOException, ClassNotFoundException{
		FileInputStream fis=new FileInputStream(filepath);
		ObjectInputStream ois =new ObjectInputStream(fis);
		Object obj=ois.readObject();
		ois.close();
		return obj;

	}
	
}
