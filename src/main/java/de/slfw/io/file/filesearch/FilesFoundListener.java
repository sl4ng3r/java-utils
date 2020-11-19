package de.slfw.io.file.filesearch;

import java.io.File;
import java.util.List;

public interface FilesFoundListener {
	public void foundFiles(List<File> files);
}
