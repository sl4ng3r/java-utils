package de.slfw.io.file.stringsearch;

import javax.annotation.Generated;

/**
 * Search Param Builder 
 * @author alex
 *
 */
public final class FileContentSearchParams {

	private String extention;
	private boolean caseSensitive;
	private String searchString;

	@Generated("SparkTools")
	private FileContentSearchParams(Builder builder) {
		this.extention = builder.extention;
		this.caseSensitive = builder.caseSensitive;
		this.searchString = builder.searchString;
	}

	public String getExtention() {
		return extention;
	}

	public void setExtention(String extention) {
		this.extention = extention;
	}

	public boolean isCaseSensitive() {
		return caseSensitive;
	}

	public void setCaseSensitive(boolean caseSensitive) {
		this.caseSensitive = caseSensitive;
	}

	
	
	public String getSearchString() {
		return searchString;
	}

	public void setSearchString(String searchString) {
		this.searchString = searchString;
	}

	/**
	 * Creates builder to build {@link FileContentSearchParams}.
	 * @return created builder
	 */
	@Generated("SparkTools")
	public static Builder builder() {
		return new Builder();
	}

	/**
	 * Builder to build {@link FileContentSearchParams}.
	 */
	@Generated("SparkTools")
	public static final class Builder {
		private String extention;
		private boolean caseSensitive;
		private String searchString;

		private Builder() {
		}

		public Builder withExtention(String extention) {
			this.extention = extention;
			return this;
		}

		public Builder withCaseSensitive(boolean caseSensitive) {
			this.caseSensitive = caseSensitive;
			return this;
		}

		public Builder withSearchString(String searchString) {
			this.searchString = searchString;
			return this;
		}

		public FileContentSearchParams build() {
			return new FileContentSearchParams(this);
		}
	}

}
