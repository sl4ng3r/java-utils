package de.slfw.io.fileSearch;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;

@ApplicationScoped
public class FilePool {

	private MaxEntriesOfPoolReachedListener maxEntriesOfPoolReachedListener;
	private long maxPoolSizeByte;
	private List<File> cachedFiles;
	private long actualCashSize;
	
	
	public FilePool(MaxEntriesOfPoolReachedListener filePoolMaxEntriesReachedListener, long maxPoolSize) {
		super();
		this.maxEntriesOfPoolReachedListener = filePoolMaxEntriesReachedListener;
		this.maxPoolSizeByte = maxPoolSize;
		this.actualCashSize = 0;
		clearPool();
	}



	public synchronized void addFile(Path filePath, BasicFileAttributes attrs) {
		if(actualCashSize >= maxPoolSizeByte) {
			flush();
		}
		File file = filePath.toFile();
		cachedFiles.add(file);
		actualCashSize = actualCashSize + file.length();
	}
	
	/**
	 * Flushes the remaining files
	 */
	public synchronized void flush() {
		maxEntriesOfPoolReachedListener.clearPool(cachedFiles);
		clearPool();
	}
	
	private void clearPool() {
		actualCashSize = 0;
		cachedFiles = new ArrayList<File>();
	}
	
	public Integer getActualPoolSize() {
		return cachedFiles.size();
	}
	
}
