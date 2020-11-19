package de.slfw.io.file.filesearch;

import java.io.File;
import java.util.List;

@FunctionalInterface
public interface PoolFlushedListener {
	void clearPool(List<File> cachedFiles);
}
