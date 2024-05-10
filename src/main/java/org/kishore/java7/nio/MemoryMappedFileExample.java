package org.kishore.java7.nio;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

public class MemoryMappedFileExample {
	public static void main(String[] args) {
		String filePath = "Memory-Mapped-File.txt";

		operation(filePath);
	}

	private static void operation(String filePath) {

		// Random access file
		try (RandomAccessFile raf = new RandomAccessFile(filePath, "rw")) {
			// create file channel
			FileChannel channel = raf.getChannel();

			// define sufficient file size - allocate 1KB
			long fileSize = 1024;
			
			if(fileSize > raf.length()) {
				raf.setLength(fileSize);
			}
			
			// map first 1KB to memory
			MappedByteBuffer mappedBuffer = channel.map(FileChannel.MapMode.READ_WRITE, 0, fileSize);
			
			// write some data mapped buffer
			byte[] writeBytes = "Hello, Mapped-Buffer!".getBytes();
			mappedBuffer.put(writeBytes);
			
			// read data from buffer
			mappedBuffer.flip();
			byte[] data = new byte[(int)fileSize];
			mappedBuffer.get(data, 0, writeBytes.length);

			System.out.println("Read from memory-mapped file:"+ new String(data).trim());

		}
		catch (IOException e) {
			e.printStackTrace();
		}

	}

}
