package patterns.creational.singleton;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class EnumBasedSingletonDemo {

	static void saveToFile(EnumBasedSingleton singleton, String filename) throws Exception {
		try (FileOutputStream fileOut = new FileOutputStream(filename);
				ObjectOutputStream out = new ObjectOutputStream(fileOut)) {
			out.writeObject(singleton);
		}
	}

	static EnumBasedSingleton readFromFile(String filename) throws Exception {
		try (FileInputStream fileIn = new FileInputStream(filename);
				ObjectInputStream in = new ObjectInputStream(fileIn)) {
			return (EnumBasedSingleton) in.readObject();
		}
	}

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		String filename = "myfile.bin";
		EnumBasedSingleton singleton = EnumBasedSingleton.INSTANCE;
		singleton.setValue(111);
		saveToFile(singleton, filename);
		
		EnumBasedSingleton singleton2 = readFromFile(filename);
		System.out.println(singleton2.getValue());
	}

}

enum EnumBasedSingleton{
	INSTANCE;
	
	private int value;
	
	EnumBasedSingleton(){
		value = 42;
	}
	
	public int getValue() {
		return value;
	}
	public void setValue(int value) {
		this.value = value;
	}
	
	
}