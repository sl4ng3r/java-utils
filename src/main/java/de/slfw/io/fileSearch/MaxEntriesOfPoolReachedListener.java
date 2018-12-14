package de.slfw.io.fileSearch;

import java.io.File;
import java.util.List;

@FunctionalInterface
public interface MaxEntriesOfPoolReachedListener {
	void clearPool(List<File> cachedFiles);
}
