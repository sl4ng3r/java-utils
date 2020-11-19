package de.slfw.io.file;

public class FileSearchParameter {
	
	private boolean recursiv = true;
	private String extention;
	private String directory;
	private boolean includeDirectories = false;
	
	
	public static class Builder{
		private boolean recursiv = true;
		private String extention;
		private String directory;
		private boolean includeDirectories = false;
		
		
		public FileSearchParameter build() {
			FileSearchParameter fsp = new FileSearchParameter();
			fsp.directory = this.directory;
			fsp.extention = this.extention;
			fsp.includeDirectories = this.includeDirectories;
			fsp.recursiv = this.recursiv;
			return fsp;			
		}
		
		public Builder() {
			// TODO Auto-generated constructor stub
		}
		
		
	}


	/**
	 * @return the recursiv
	 */
	public boolean isRecursiv() {
		return recursiv;
	}


	/**
	 * @param recursiv the recursiv to set
	 */
	public void setRecursiv(boolean recursiv) {
		this.recursiv = recursiv;
	}


	/**
	 * @return the extention
	 */
	public String getExtention() {
		return extention;
	}


	/**
	 * @param extention the extention to set
	 */
	public void setExtention(String extention) {
		this.extention = extention;
	}


	/**
	 * @return the directory
	 */
	public String getDirectory() {
		return directory;
	}


	/**
	 * @param directory the directory to set
	 */
	public void setDirectory(String directory) {
		this.directory = directory;
	}


	/**
	 * @return the includeDirectories
	 */
	public boolean isIncludeDirectories() {
		return includeDirectories;
	}


	/**
	 * @param includeDirectories the includeDirectories to set
	 */
	public void setIncludeDirectories(boolean includeDirectories) {
		this.includeDirectories = includeDirectories;
	}
	
	

}
