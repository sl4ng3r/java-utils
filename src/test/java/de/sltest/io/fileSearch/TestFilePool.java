package de.sltest.io.fileSearch;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import de.slfw.io.FileOperator;
import de.slfw.io.filesearch.FilePool;
import de.slfw.io.filesearch.MaxEntriesOfPoolReachedListener;

public class TestFilePool implements MaxEntriesOfPoolReachedListener {

	
	private static FilePool filePool;
	private static final long POOLSIZE = 3;
	
	@org.junit.BeforeClass
	public static void initialize() {
		
		filePool = new FilePool((List<File>  theCachedFiles) -> System.out.println(theCachedFiles.size()) , POOLSIZE);
	}
	
	@Test
	public void testFilePool() {
		int counter = 0;
		
		for(int i = 1 ; i<=POOLSIZE+1; i++) {
			filePool.addFile(new File("").toPath(), null);
		}
		assertEquals(filePool.getActualPoolSize(), new Integer(1));
	}
	@Test
	public void testFlush() {
		filePool.flush();
		assertEquals(filePool.getActualPoolSize(), new Integer(0));
	}

	@Override
	public void clearPool(List<File> cachedFiles) {
		// TODO Auto-generated method stub
		
	}
	
}
