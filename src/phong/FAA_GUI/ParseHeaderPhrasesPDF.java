/**
 * 
 */
package phong.FAA_GUI;

import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.Map.Entry;

import org.apache.commons.io.FileUtils;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.encryption.InvalidPasswordException;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.pdfbox.text.TextPosition;


/**
 * @author Phong Nguyen (vietphong.nguyen@gmail.com)
 *
 */
public class ParseHeaderPhrasesPDF extends PDFTextStripper{

	int levelOfExtraction = 10;
	int maxNoCharacters = 100000;
	TreeMap<Integer, String> textDataLevel = new TreeMap<>((Collections.reverseOrder()));
	String text = "";
	int normalSize = 0;
	
	/**
	 * 
	 */
	public ParseHeaderPhrasesPDF() throws IOException{
		// TODO Auto-generated constructor stub
	}

	@SuppressWarnings("resource")
	public ParseHeaderPhrasesPDF(File file, String outputFolder, String fromPageS, String toPageS) throws IOException{
		this();
		

		int fromPage=1, toPage=20;
		try {
			fromPage = Integer.parseInt(fromPageS);
			toPage = Integer.parseInt(toPageS);
		} catch (NumberFormatException e2) {}
		
		PDDocument document = null;
		try {
			document = PDDocument.load(file);
			if (document.isEncrypted()) {
                try {
                	document = PDDocument.load(file, "");
                	document.setAllSecurityToBeRemoved(true);
                } catch (InvalidPasswordException e) {
                    System.out.println("Error: Document is encrypted with a password.");
                    return;
                }
            }
			
			setSortByPosition(true);
			
			setStartPage(fromPage-1);
			setEndPage( Math.min(toPage,document.getNumberOfPages()) );
			
			Writer dummy = new OutputStreamWriter(new ByteArrayOutputStream());
			writeText(document, dummy);
			
			sortedTextDataLevel();
			
			FileUtils.deleteDirectory(new File(outputFolder )); // Delete the old file in this directory
			if (new File(outputFolder).mkdir()) { 
				// Make new folder successful 
			} 					
			
			saveToFile(outputFolder + "\\" + file.getName() + ".txt");
			
		} finally {
			if (document != null) {
				document.close();
			}
		}
	}

	private void saveToFile(String fileName) {
		if (text.length()<= 0) return;
		
		try {
			FileOutputStream outputStream = new FileOutputStream(fileName);
			OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream, "UTF-8");
			BufferedWriter bufferedWriter = new BufferedWriter(outputStreamWriter);

			bufferedWriter.write(text);
			bufferedWriter.close();
		} catch (IOException e2) {
			e2.printStackTrace();
		}
	}

	/**
     * Override the default functionality of PDFTextStripper.
     * The writeString method will be called many time in the extraction of the PDF file. After reading a new line
     * number of called time = number of line
     */
    @Override
    protected void writeString(String string, List<TextPosition> textPositions) throws IOException
    {
    	String line="";
    	int maxSize = 0;
    	
    	int fontSize;
    	String s;
    	Character ch;
    	for (TextPosition text : textPositions)
        {
            /*System.out.println( "P String[" + text.getXDirAdj() + "," +
                    text.getYDirAdj() + " fs=" + text.getFontSize() + " xscale=" +
                    text.getXScale() + " height=" + text.getHeightDir() + " space=" +
                    text.getWidthOfSpace() + " width=" +
                    text.getWidthDirAdj() + "]" + text.getUnicode() );*/
    		
    		s = text.getUnicode();
    		ch = s.charAt(0);
    		
    		String considerPoint = ".!?;";
    		if (considerPoint.indexOf(ch)>=0) {
    			line += ". ";
    			continue;
    		}
    		String considerComma = ",+=/'\":";
    		if (considerComma.indexOf(ch)>=0) {
    			line += ", ";
    			continue;
    		}
    		String helpExplanation = "()<>[] {}";
    		if (helpExplanation.indexOf(ch)>=0) {
    			line += ch;
    			continue;
    		}
    		if (	!(	Character.isLetterOrDigit(ch) || Character.isWhitespace(ch) || (ch == '.')|| (ch == ',')	 		)	) {
    			line += " "; // replace by a space for any other special characters
    			continue;
    		}
    		fontSize = (int) ( text.getHeightDir()*10 + 0.5); // or use Math.round(d);
    		if (fontSize > maxSize) 
    			maxSize = fontSize;
    		line += s;
    		
        }
    	if (maxSize < normalSize) return;
    	line = line.trim();
    	if (line.length() > 60 ) {	// too long will be consider normal text - Not the important title phrase
    		if (normalSize < maxSize) normalSize = maxSize;
    		return;
    	}
    	if (textDataLevel.containsKey(maxSize))
			textDataLevel.put(maxSize, textDataLevel.get(maxSize) + line );
		else
			textDataLevel.put(maxSize, line );
    	
    	// Put a space at the end of each line to separate with text in the next line 
    	for (Entry<Integer, String> entry : textDataLevel.entrySet()) {
    		int key = entry.getKey();
    		String value = entry.getValue().trim() + " \n";
    		textDataLevel.put(key, value);
    	}
    }
    
    private void sortedTextDataLevel() {

		// Display the TreeMap which is naturally sorted
		int level = 0;
		String s;
		text ="";

		// Remove the entry with no Letter in its value in textDataLevel Tree Map
		Iterator<Entry<Integer, String>> i = textDataLevel.entrySet().iterator();
		Map.Entry<Integer, String> me;
		while(i.hasNext()) {
			me = i.next();
            if ( TextProcessing.containNoLetter((String) me.getValue()	)	) {
                i.remove();
            }
        }
		
		for (Entry<Integer, String> entry : textDataLevel.entrySet()) {
			s = entry.getValue().trim();
			
			//System.out.println("Key = " + entry.getKey() + ", Value = " + s);
			text += s + ".\n\n";
			level++;
			if (level >= levelOfExtraction)
				break;
		}
		
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			ParseHeaderPhrasesPDF pdf = new ParseHeaderPhrasesPDF(new File("C:\\FAA3\\data\\ILP01-En Route Qualification Training Overview.pdf") , "C:\\FAA3\\main_phrases", "1", "auto");
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
