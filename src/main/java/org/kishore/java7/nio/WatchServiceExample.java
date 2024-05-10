package org.kishore.java7.nio;

import java.io.IOException;
import java.net.URI;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardWatchEventKinds;
import java.nio.file.WatchEvent;
import java.nio.file.WatchEvent.Kind;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;
import java.util.List;

/**
The WatchService in Java is used to monitor changes in the file system.
It allows you to watch a directory for events such as file creation, 
modification, or deletion. This feature is particularly useful for building file watchers, 
where you want to respond to changes in real-time.

To use WatchService, you create a WatchService instance, register a directory to be monitored, 
and then wait for events. You can choose which types of events to watch (e.g., create, modify, delete), 
and handle them when they occur.

Key Components
==============
WatchService: A service to monitor file system events.
WatchKey: Represents a registration of a directory with a WatchService and holds events when they occur.
WatchEvent: Represents a specific event, such as file creation, modification, or deletion.
WatchEvent.Kind: Specifies the type of event (e.g., ENTRY_CREATE, ENTRY_MODIFY, ENTRY_DELETE).

To implement a WatchService, you typically:

1. Create a WatchService instance.
2. Register one or more directories with the WatchService.
3. Wait for events and handle them appropriately.

Explanation
===========
The program creates a WatchService and registers a directory with it, 
monitoring for ENTRY_CREATE, ENTRY_MODIFY, and ENTRY_DELETE events.

It then enters an infinite loop, waiting for watch keys to be available. 
The watchService.take() method blocks until a watch key is signaled, 
indicating that an event has occurred.

Once a watch key is available, the code iterates through all WatchEvents, 
handling them according to their type. It uses the kind to determine the type of event 
and the context to get the related path.

After processing all events, the watch key is reset using watchKey.reset(). If the reset fails, 
it means the watch key is no longer valid, possibly indicating that the watched directory has been removed.

Use cases
=========
1. Real-Time File Syncing:
--------------------------
In environments where files need to be synchronized across different locations or systems, 
WatchService can monitor a directory and trigger synchronization tasks. This use case is common 
in cloud-based services, backup applications, and collaborative platforms.

# Example Scenario: 
A team shares a folder on a network drive. A background service uses WatchService to detect new files, 
modifications, or deletions. When a change is detected, the service automatically synchronizes the 
updated content with a remote server or other shared locations, ensuring all team members have the latest files.

2. Automated Task Triggers:
---------------------------
Developers often create automated build systems or test pipelines that rely on detecting changes 
in source code or configuration files. WatchService can be used to trigger these automation tasks when 
specific events occur in a monitored directory.

# Example Scenario: 
A developer maintains a project with a continuous integration/continuous delivery (CI/CD) pipeline. 
A WatchService monitors the source code directory. When code is added, modified, or deleted, the 
service triggers a build process and runs automated tests. This use case helps maintain code quality 
and streamline development workflows.

3. Security and Compliance Monitoring:
--------------------------------------
In security-sensitive environments, detecting unauthorized file changes is crucial. 
WatchService can be used to monitor key directories for unexpected modifications, helping 
to identify potential security breaches or compliance violations.

# Example Scenario: 
A system administrator sets up a monitoring service using WatchService to watch 
critical system directories, such as /etc in Linux systems or C:\Windows\System32 in Windows. 
If a file is unexpectedly modified or deleted, an alert is triggered, allowing the administrator to 
investigate and take corrective action. This use case helps maintain system integrity and comply 
with security policies.
**/
public class WatchServiceExample {
	
	public static void main(String[] args) {
		String path="watch-service-directory";
		try {
			WatchServiceExample.monitor(path);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public static void monitor(String directoryPath) throws IOException, InterruptedException {
		
		Path dirPath = Paths.get(directoryPath);
		
		// 1. Create a WatchService instance.
		WatchService ws=FileSystems.getDefault().newWatchService();
		
		// 2. Register one or more directories with the WatchService for for create, modify, and delete events.
		dirPath.register(ws,StandardWatchEventKinds.ENTRY_CREATE,
				StandardWatchEventKinds.ENTRY_MODIFY,StandardWatchEventKinds.ENTRY_DELETE);
		
		// 3. Wait for events and handle them appropriately.
		   
	    // Infinite loop to continuously watch for events
		while(true) {
			// Wait for a watch key to be available
			WatchKey key = ws.take();
			
			// Process all events for this watch key
			List<WatchEvent<?>> watchEvents = key.pollEvents();
			
			for(WatchEvent<?> event: watchEvents) {
				Kind<?> kind = event.kind();
				
				Path path = (Path) event.context();
				
				if(kind==StandardWatchEventKinds.ENTRY_CREATE) {
					System.out.println("created: " + path.toString());
				}else if(kind==StandardWatchEventKinds.ENTRY_MODIFY) { // check this step: double message printed!
					System.out.println("modified: " + path.toString());
				}else if(kind==StandardWatchEventKinds.ENTRY_DELETE) {
					System.out.println("deleted: " + path.toString());
				}
				
			}
			
			// Reset the watch key and check if it is still valid
            boolean valid = key.reset();
            if(!valid) {
            	System.out.println("Watch key is no longer valid!");
            	break;
            }
            
		}
		
		
		
		
	}

}
