package org.kishore.java7.nio;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousFileChannel;
import java.nio.channels.CompletionHandler;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

/*
 * An AsynchronousChannel is part of the java.nio.channels package and 
 * is used to perform asynchronous I/O operations in Java. 
 * 
 * It is often used in network programming or file handling 
 * where non-blocking I/O is required. 
 * 
 * The primary benefit of asynchronous channels is that 
 * they allow operations to proceed without blocking the thread, 
 * enabling more scalable and responsive applications.
 * */
public class AsynchronousFileChannelExample {

	public static void main(String[] args) {
		Path path = Paths.get("Text-File");
		operation(path);
	}

	private static void operation(Path filePath) {
		try {
			
			//Open async file channel to read a file
			AsynchronousFileChannel channel = AsynchronousFileChannel
					.open(filePath, StandardOpenOption.READ);
			
			//buffer to hold data read from the file
			ByteBuffer buffer = ByteBuffer.allocate(1024);
			
			// async read operation with completion handler
			channel.read(buffer, 0,buffer, new CompletionHandler<Integer, ByteBuffer>() {
				
				@Override
				public void completed(Integer result, ByteBuffer attachment) {
					System.out.println("Read " + result + " bytes");
					System.out.println();

					attachment.flip();
					
					while(attachment.hasRemaining()) {
						System.out.print((char)attachment.get());
					}
					
                    System.out.println();
				}

				@Override
				public void failed(Throwable exc, ByteBuffer attachment) {
					System.err.println("Failed read operation"+ exc.getMessage());
				}
			});
			
			Thread.sleep(1000);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException  ie) {
			// TODO Auto-generated catch block
			ie.printStackTrace();
		}
		
	}

}
