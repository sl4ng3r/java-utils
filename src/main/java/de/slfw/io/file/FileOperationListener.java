package de.slfw.io.file;

/**
 * 
 * <p>A listener interface with an fire method witch is invoked by the <code>getNumberOfLinesWithString.</code> and <code>getAllLinesWithString</code> methods
 * of the {@link FileOperator}
 * It returns the % count witch were already finished</p>
 *
 * @author Alex S.
 * 
 *
 */
public interface FileOperationListener {
	public void fire(String msg, String type);
}
