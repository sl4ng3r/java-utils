package de.sltest.io.file.stringSearch;

import java.io.IOException;
import java.nio.file.Paths;

import javax.enterprise.inject.se.SeContainer;
import javax.enterprise.inject.se.SeContainerInitializer;

import org.jboss.weld.environment.se.Weld;
import org.jboss.weld.environment.se.WeldContainer;

import com.sun.glass.ui.Application;

import de.slfw.io.file.stringSearch.FilePool;
import de.slfw.io.file.stringSearch.FileVisitor;



public class TestStringSearch {

	public static void main(String[] args) throws IOException {
		TestStringSearch myClass = new TestStringSearch();
		myClass.start();
	}

	private void start() throws IOException {
//		 SeContainerInitializer containerInit = SeContainerInitializer.newInstance();
//	     SeContainer container = containerInit.initialize();
//	     
	     
	     SeContainer container = SeContainerInitializer.newInstance()
	             .disableDiscovery()
	             .addBeanClasses(FilePool.class, FileVisitor.class)
	             .initialize();

	     
	 
	     FileVisitor fw = new FileVisitor(Paths.get("blubb"));
	     fw.visitFile(null, null);
	     
	     
	     container.close();
		
	}
	
}
