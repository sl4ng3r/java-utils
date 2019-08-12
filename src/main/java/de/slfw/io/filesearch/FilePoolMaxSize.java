package de.slfw.io.filesearch;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;

@ApplicationScoped
public class FilePoolMaxSize extends AbstractFilePool {

	
	private long maxPoolSizeByte;

	private long actualCashSize;
	
	
	public FilePoolMaxSize(PoolFlushedListener poolFlushedListener, long maxPoolSize) {
		super();
		this.setPoolFlushListener(poolFlushedListener);
		this.maxPoolSizeByte = maxPoolSize;
		this.actualCashSize = 0;
		clearPool();
	}


	public synchronized void addFile(File file, BasicFileAttributes attrs) {
		long fileLength = file.length();
		
		if(actualCashSize + fileLength  >= maxPoolSizeByte && cachedFiles.size() > 0) {
			flush();
		}
		cachedFiles.add(file);
		actualCashSize = actualCashSize + file.length();
	}

	
	private void filePoolMaxEntriesReached() {
		flush();
	}

	
	public void clearPool() {
		actualCashSize = 0;
		cachedFiles = new ArrayList<File>();
	}
	
	
	
	
	
	

	
}
