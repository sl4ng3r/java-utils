package de.slfw.io.filesearch;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;

public abstract class AbstractFilePool {
	private PoolFlushedListener poolFlushListener;
	protected List<File> cachedFiles;
	
	
	public synchronized void addFile(Path filePath) {
		addFile(filePath.toFile(), null);
	}
	
	public synchronized void addFile(File file) {
		addFile(file, null);
	}
	
	public synchronized void addFile(Path filePath, BasicFileAttributes attrs) {
		addFile(filePath.toFile(), attrs);
	}
	
	
	
	public void setPoolFlushListener(PoolFlushedListener poolFlushListener) {
		this.poolFlushListener = poolFlushListener;
	}
	/**
	 * Flushes the remaining files
	 */
	public synchronized void flush() {
		poolFlushListener.clearPool(cachedFiles);
		clearPool();
	}
	
	public Integer getActualAmoutOfEntries() {
		return cachedFiles.size();
	}
	
	public Long getActualPoolSize() {
		Long poolSize = 0L;
		for(File file : cachedFiles) {
			poolSize = poolSize + file.length();
		}
		return poolSize;
	}
	
	
	
	public abstract void addFile(File file, BasicFileAttributes attrs);
	public abstract void clearPool();
	
}
