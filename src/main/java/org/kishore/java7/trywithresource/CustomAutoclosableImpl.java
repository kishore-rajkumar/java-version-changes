package org.kishore.java7.trywithresource;

import java.io.IOException;

public class CustomAutoclosableImpl implements CustomAutoClosable {

	@Override
	public void display() {
		System.out.println("This is a custome autocloseable Interface-Class!");
	}

	@Override
	public void close() throws IOException {
		System.out.println("Auto closing this resource! " + this);
//		this.close();
	}

}
