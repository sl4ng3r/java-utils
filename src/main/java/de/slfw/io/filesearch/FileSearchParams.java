package de.slfw.io.filesearch;

import java.nio.file.Path;

public class FileSearchParams {

	private Boolean recursive = true;
	private String extention = null;
	private Path searchDirectory;
	private long filePackageSize;

	private FileSearchParams(Builder builder) {
		this.recursive = builder.recursive;
		this.extention = builder.extention;
		this.searchDirectory = builder.searchDirectory;
		this.filePackageSize = builder.filePackageSize;
	}

	public Boolean getRecursive() {
		return recursive;
	}

	public void setRecursive(Boolean recursive) {
		this.recursive = recursive;
	}

	public String getExtention() {
		return extention;
	}

	public void setExtention(String extention) {
		this.extention = extention;
	}

	public Path getSearchDirectory() {
		return searchDirectory;
	}

	public void setSearchDirectory(Path searchDirectory) {
		this.searchDirectory = searchDirectory;
	}

	public long getFilePackageSize() {
		return filePackageSize;
	}

	public void setFilePackageSize(long filePackageSize) {
		this.filePackageSize = filePackageSize;
	}

	/**
	 * Builder to build {@link FileSearchParams}.
	 */
	public static final class Builder {
		private Boolean recursive;
		private String extention;
		private Path searchDirectory;
		private long filePackageSize;

		public Builder(Path searchDirectory, long filePackageSize) {
			this.searchDirectory = searchDirectory;
			this.filePackageSize = filePackageSize;
		}

		public Builder withRecursive(Boolean recursive) {
			this.recursive = recursive;
			return this;
		}

		public Builder withExtention(String extention) {
			this.extention = extention;
			return this;
		}

		public FileSearchParams build() {
			return new FileSearchParams(this);
		}
	}

}
