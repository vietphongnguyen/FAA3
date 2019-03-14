/**
 * 
 */
package phong.FAA_GUI;

import java.io.File;
import java.io.IOException;

/**
 * @author Phong Nguyen (vietphong.nguyen@gmail.com)
 *
 */
public class ParseHeaderPhrases {

	/**
	 * 
	 */
	public ParseHeaderPhrases() {
		// TODO Auto-generated constructor stub
	}

	public ParseHeaderPhrases(File file, String outputFolder, String fromPageS, String toPageS) {
		String fileName = file.getName().toLowerCase();
		if (fileName.endsWith(".pdf")) {
			try {
				ParseHeaderPhrasesPDF mainPhrases = new ParseHeaderPhrasesPDF(file,outputFolder, fromPageS, toPageS);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
