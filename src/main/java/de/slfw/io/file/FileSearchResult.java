package de.slfw.io.file;

public class FileSearchResult {

	private String path;
	private String fileType;

	
	public static class Builder{
	
		
		private String path;
		private String fileType;
		
		
		public FileSearchResult build(){
			FileSearchResult fileSearchResult = new FileSearchResult();
			fileSearchResult.path = this.path;
			fileSearchResult.fileType = this.fileType;
			return fileSearchResult;
		}
		
		public Builder setPath(String path) {
			this.path = path;
			return this;
		}
		
		public Builder setFileType(String fileType) {
			this.fileType = fileType;
			return this;
		}
		
	}
	
	private FileSearchResult() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @return the path
	 */
	public String getPath() {
		return path;
	}

	public String getFileType() {
		return fileType;
	}
	

	public static FileSearchResult buildFileSearchResult() {
		return new FileSearchResult();
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "FileSearchResult [path=" + path + ", fileType=" + fileType + "]";
	}

	
	
}
