package org.kishore.java7.trywithresource;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
/*
 * Class to test & learn try-with-resource
 * 
 * A try-with-resource statement in Java is a structure that ensures
 * that resources (such as files, streams, or database connections) 
 * are properly closed after they are used, even if an exception occurs. 
 * This structure helps to avoid resource leaks by automatically closing 
 * the resource when the block is exited.
 * 
 * */
public class TryWithResourceExample {

	public static void main(String[] args) {
		
//		tryWithResourceWithStandardInterface();
		
		tryWithResourceWithCustomInterface();

	}

	private static void tryWithResourceWithCustomInterface() {
		
		try(CustomAutoClosable cas = new CustomAutoclosableImpl()){
			cas.display();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static void tryWithResourceWithStandardInterface() {
		String file ="Text-File"; 
		// Try with resource construct
		try(BufferedReader br = new BufferedReader(new FileReader(file))){
			String line;
			
			while((line=br.readLine())!=null) {
				System.out.println(line);
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
