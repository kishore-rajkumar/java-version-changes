package org.kishore.java7.nio;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * File locking in Java provides a mechanism to control access to files, 
 * ensuring that multiple processes or threads do not interfere with each 
 * other's operations on a file. This can be crucial in applications that 
 * require coordination when reading, writing, or modifying files.
 * 
 * Java's FileChannel class supports file locking through the FileLock class, 
 * which allows you to acquire shared or exclusive locks on a portion of a file 
 * or the entire file. This is useful for implementing mutual exclusion, 
 * concurrency control, and avoiding race conditions when accessing files.
 * 
 * #### shared or exclusive
 * >> Exclusive Lock: 
 *    ===============
 * Prevents other processes or threads from acquiring a lock on the same portion of the file. 
 * Typically used for writing operations. 
 * 
 * >> Shared Lock: 
 *    ============
 * Allows multiple processes or threads to acquire a lock on the same portion, provided they 
 * request a shared lock. Generally used for read-only operations to allow concurrent reading.
 * 
 * In Java 7 (Java 1.7), the FileChannel class supports file locking through two primary methods:
 *  => lock(): Acquires an exclusive lock on the entire file.
 *  => tryLock(): Attempts to acquire an exclusive lock, without blocking, and returns a FileLock if successful.
 *  
 * Additionally, both methods have variants that allow locking a specific region of the file 
 * and specifying whether the lock is shared or exclusive:
 *  => lock(long position, long size, boolean shared): 
 *  	Locks a specific portion of the file. The shared parameter determines whether the lock is shared (true) 
 *      or exclusive (false).
 *  => tryLock(long position, long size, boolean shared): 
 *      Tries to lock a specific portion, with similar behavior to the lock() method.
 *      
 * Example of File Locking in Java 7 
 * ==================================
 * Here's a simple example that demonstrates how to use FileChannel to acquire an exclusive lock on a file, 
 * ensuring that only one process or thread can write to the file at a time:
 * 
 * ?? is lock required for read-only operations !!!!
 * */
public class FileLockingImprovementsExample {
	
	public static int c=0;
	
	public static void main(String[] args) {
		String file="Text-File";
		// check for multiple read & write threads
		ExecutorService pool = Executors.newFixedThreadPool(3);
		
		for(int i=0; i<10;i++) {
			pool.submit(new Runnable() {
				@Override
				public void run() {
					System.out.println((++c)+". "+this+" @ " + new Date());
					operation(c,file);
				}
			});
		}
		
		pool.shutdown();
	}

	private static void operation(int i,String file) {
		
		try(RandomAccessFile ras = new RandomAccessFile(file, "rw")){
			// Create a file channel
			FileChannel channel = ras.getChannel();
			
			// Create a lock
			FileLock lock = channel.lock();
			System.out.println(i+">>  Excl lock aqcuired! " + lock);
			
			//perform write
			ras.writeBytes("# "+i+". Hello this content is written under an exclusive file lock created from FileChannel!");
			System.out.println("content writteb! " + ras.getFilePointer());
			
			// release lock
			lock.release();
			System.out.println(i+"--  Excl lock released! " + lock);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
