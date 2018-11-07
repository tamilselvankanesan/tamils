/* RPM Packages: ao-bkecf-web-* */
/* RPM Permissions: 444 */
/* RPM Owner: tomcat */
/* RPM Group: nobody */
/* RPM Flags: configure */

package cmecf.pdf;

import java.io.*;
import java.util.*;

/**
 * This serves as a superclass for processes that modify a PDF document. It
 * provides a way to keep track of what steps were completed successfully, and a
 * way to step back if a step in the process fails.
 * <p>
 * Output files are created internally (as oposed to being dictated by the
 * calling process). They are files in the tmp directory randomly named, except
 * that they begin with state information, and end with ".pdf". State
 * information consists of a string of capital (by convention) letters, each of
 * which represents a completed process. See the subclasses for more
 * information.
 * @see PreparePDFForDisplay
 *
 */

public class PDFModify {


	/**
	 * Holds a string of letters, each representing the step in the process that
	 * is about to run. This string is used in the name of the output file.
	 *
	 */
	private StringBuffer currentState = new StringBuffer();

	/**
	 * The file that will hold the output of the current process
	 *
	 */
	protected String outputFile	= null;

	/**
	 * The PDF file with which we are starting. At each next step this takes the
	 * value of the output file created by the previous step.
	 *
	 */
	protected String inputFile  = null;

	/**
	 * Creates a temp file that includes the current state and sets outputFile
	 * to the name of the absolute path.
	 *
	 * @exception IOException if an error occurs
	 */
	protected void startStep(char state) throws IOException {
		currentState.append(state);
		outputFile = File.createTempFile(currentState.toString() + "_end_state", ".pdf", new File("/tmp/")).getAbsolutePath();
	}

	/**
	 * Sets the inputFile for the next step to be the output file from this
	 * one.
	 *
	 */
	protected void endStep() {
		inputFile = outputFile;
	}

	/**
	 * Go back to the last known good output file, and update the current state.
	 * May be used when a step in the process fails after that step calls
	 * <code>startStep</code>.
	 *
	 */
	protected void stepBack() {
		outputFile = inputFile;
		try {
			currentState.deleteCharAt( currentState.length() - 1 );
		} catch(StringIndexOutOfBoundsException e) {}
	}


	/**
	 * Intializes the current state to the string specified.
	 *
	 * @param currentState a series of letters each representing a completed
	 * step in the process
	 */
	public void initCurrentState(String currentState) {
		this.currentState = new StringBuffer(currentState);
	}


	/**
	 * Returns the current state as a string
	 *
	 * @return the current state
	 */
	public String getCurrentState() {
		return currentState.toString();
	}


	/**
	 * Sets the document to work on
	 *
	 * @param filename the name of the document
	 */
	public void setDocument(String filename) {
		inputFile = filename;

		// if the process fails before the first step we will return the input file
		outputFile = filename;
	}


	/**
	 * <code>getOutputFile</code> returns the output file. This will be the same
	 * as the input file if the entire process failed. Otherwise it will be a
	 * file in tmp containing the state information.
	 *
	 * @return The path to the final PDF
	 */
	public String getOutputFile() {
		return outputFile;
	}


}
