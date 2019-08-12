package de.sltest.io.fileSearch;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.nio.file.Path;
import java.util.List;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import de.slfw.io.FileOperator;
import de.slfw.io.filesearch.FilePoolMaxSize;
import de.slfw.io.filesearch.PoolFlushedListener;

public class TestFilePool implements PoolFlushedListener {

	
	private static FilePoolMaxSize filePool;
	private final long POOLSIZE = 100000;
	private static Path path;
	private Integer clearCount = 0;
	private List<File> allFiles;
	
	@Before
	public void  initialize() {
		path = new File(Thread.currentThread().getContextClassLoader().getResource("testfolder_search").getFile()).toPath();
		filePool = new FilePoolMaxSize(this , POOLSIZE);
		clearCount = 0;
		allFiles = FileOperator.getAllFiles(path.toString(), true);
	}
	
	@Test
	public void testFilePool() {
		for(File file : allFiles) {
			filePool.addFile(file);
		}
		assertEquals("Amaout of Pool clears not equal", filePool.getActualPoolSize(), new Long(43012));
		filePool.flush();
		assertEquals("Amaout of Pool clears not equal", clearCount, new Integer(4));

	}
	
	@Test
	public void testFlush() {
		for(File file : allFiles) {
			filePool.addFile(file);
		}
		filePool.flush();
		assertEquals("Amaout of Pool clears not equal", clearCount, new Integer(4));
		assertEquals("The pool has to Be empty after Flush", filePool.getActualPoolSize(), new Long(0));
		assertEquals("The pool has to Be empty after Flush", filePool.getActualAmoutOfEntries(), new Integer(0));
	} 

	@Override
	public void clearPool(List<File> cachedFiles) {
		clearCount++;
		System.out.println(cachedFiles);
	}
	
	
}
