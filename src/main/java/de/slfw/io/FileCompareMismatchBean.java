package de.slfw.io;

/**
 * 
 * <p>A bean with the file mismachtes. The attributes hold the paths.</p>
 *
 * @author Alex S.
 * 
 *
 */
public class FileCompareMismatchBean {
	private String directory1;
	private String directory2;
	private boolean oneSideEmpty;
	
	public FileCompareMismatchBean(String directory1, String directory2) {
		this.directory1 = directory1;
		this.directory2 = directory2;
	}

	public String getDirectory1() {
		return directory1;
	}

	public void setDirectory1(String directory1) {
		this.directory1 = directory1;
	}

	public String getDirectory2() {
		return directory2;
	}

	public void setDirectory2(String directory2) {
		this.directory2 = directory2;
	}

	/**
	 * @return the oneSideEmpty
	 */
	public boolean isOneSideEmpty() {
		return oneSideEmpty;
	}

	/**
	 * @param oneSideEmpty the oneSideEmpty to set
	 */
	public void setOneSideEmpty(boolean oneSideEmpty) {
		this.oneSideEmpty = oneSideEmpty;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "FileCompareMismatchBean [directory1=" + directory1 + ", directory2=" + directory2 + ", oneSideEmpty="
				+ oneSideEmpty + "]";
	}
	
	
	
	
}
