package de.sltest.io.fileSearch;

import de.slfw.io.FileOperator;
import de.slfw.io.filesearch.FilePoolMaxSize;
import de.slfw.io.filesearch.PoolFlushedListener;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.nio.file.Path;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestFilePool implements PoolFlushedListener {

	
	private static FilePoolMaxSize filePool;
	private final long POOLSIZE = 100000;
	private static Path path;
	private Integer clearCount = 0;
	private List<File> allFiles;
	
	@BeforeEach
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
		//TODO hier muss nochmal gecheckt werden
		//assertEquals(43012, filePool.getActualPoolSize(), "Amount of Pool clears not equal");
		filePool.flush();
		assertEquals(clearCount, new Integer(4),"Amaout of Pool clears not equal");

	}
	
	@Test
	public void testFlush() {
		for(File file : allFiles) {
			filePool.addFile(file);
		}
		filePool.flush();


		assertEquals(clearCount, 4,"Amaout of Pool clears not equal");
		assertEquals( filePool.getActualPoolSize(), 0,"The pool has to Be empty after Flush");
		assertEquals( filePool.getActualAmoutOfEntries(), 0,"The pool has to Be empty after Flush");
	} 

	@Override
	public void clearPool(List<File> cachedFiles) {
		clearCount++;
		System.out.println(cachedFiles);
	}
	
	
}
