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
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.Map.Entry;
import java.util.Set;

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
	int maxLength = 100;
	TreeMap<Integer, String> textDataLevel = new TreeMap<>((Collections.reverseOrder()));
	String text = "";
	int normalSize = 0;
	int previousFontSize=0;
	
	/**
	 * 
	 */
	public ParseHeaderPhrasesPDF() throws IOException{
		// TODO Auto-generated constructor stub
	}

	@SuppressWarnings("resource")
	public ParseHeaderPhrasesPDF(File file, String outputFolder, String fromPageS, String toPageS) throws IOException{
		this();
		try {
			maxLength = Integer.parseInt(FAA3_GUI.comboBox_MaxLength.getSelectedItem().toString()	);
		} catch (Exception e1) {}
		
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
			
			cleanUpText();
								
			saveToFile(outputFolder + "\\" + file.getName() + ".txt");
			
		} finally {
			if (document != null) {
				document.close();
			}
		}
	}

	private void cleanUpText() {
		// to lower case
		text = text.toLowerCase();
		
		//removeShortPhase(); // remove 1 word phrases
		String[] phrases = text.split("\n");
		Set<String> phrasesSet = new LinkedHashSet<String>();
		for (String ph:phrases) {
			ph = ph.trim();
			if (ph.indexOf(" ") > 0)  // have space mean have more than 1 word
				phrasesSet.add(ph);
		}
		
		text = "";
		for (String s: phrasesSet) {
			text += s + System.lineSeparator();
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
    	
    	int fontSize=0;
    	String s;
    	Character ch;
    	boolean alreadyCheckNewLine = false;
    	boolean NoNewLine = false;
    	int changeFontCount = 0;
    	for (TextPosition text : textPositions)
        {
            /*System.out.println( "P String[" + text.getXDirAdj() + "," + text.getYDirAdj() + " fs=" + text.getFontSize() + " xscale=" +
             text.getXScale() + " height=" + text.getHeightDir() + " space=" + text.getWidthOfSpace() + " width=" + text.getWidthDirAdj() + "]" + text.getUnicode() );*/
    		
    		s = text.getUnicode();
    		ch = s.charAt(0);
    		
    		fontSize = (int) ( text.getHeightDir()*10 + 0.5); // or use Math.round(d);
    		if ((!alreadyCheckNewLine))
    			if ( (previousFontSize == fontSize)	) 
    				NoNewLine = true;	// No need a new line and continue the previous line with this one
    		
    		alreadyCheckNewLine = true;	// mark to inform that it passed the first character check already. No need to check again from the 2nd character    		
    		
    		String considerPoint = ".!?;";
    		if (considerPoint.indexOf(ch)>=0) {
    			line += System.lineSeparator();
    			continue;
    		}
    		String considerComma = ",+=/'\":()<>[]{}";
    		if (considerComma.indexOf(ch)>=0) {
    			line += System.lineSeparator();
    			continue;
    		}

    		if (	!(	Character.isLetterOrDigit(ch) || Character.isWhitespace(ch) 		)	) {
    			line += " "; // replace by a space for any other special characters
    			continue;
    		}
    		if (fontSize > maxSize) {
    			maxSize = fontSize;
    			line = s;
    			changeFontCount++;
    		} else
    			if (fontSize == maxSize) 
    			line += s;
    		previousFontSize = fontSize;
        }
    	//if (maxSize < normalSize) return;
    	line = line.trim();
    	if (line.length() > maxLength ) {	// too long will be consider normal text - Not the important title phrase
    		if (normalSize < maxSize) normalSize = maxSize;
    		return;
    	}
    	if (line.equals("") ) line += System.lineSeparator();	// start a new phrase
    	if (textDataLevel.containsKey(maxSize)) {
    		if (NoNewLine && (changeFontCount <= 1))
    			textDataLevel.put(maxSize, textDataLevel.get(maxSize) + " " + line );
    		else
    			textDataLevel.put(maxSize, textDataLevel.get(maxSize) + " " + System.lineSeparator() + line );
    	}
		else
			textDataLevel.put(maxSize, line );
    	
    	
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
		
		boolean removeNormalSize = true;
		try {
			removeNormalSize = FAA3_GUI.chckbxRemoveNormalSize.isSelected();
		} catch (Exception e1) {}
		if (removeNormalSize) {
			int max = 0;
			for (Entry<Integer,String> e : textDataLevel.entrySet()) 
				if (e.getValue().length() > max) 
					max = e.getValue().length();
			
			// Remove the entry from the normal size of text in textDataLevel Tree Map
			Iterator<Entry<Integer, String>> i2 = textDataLevel.entrySet().iterator();
			Map.Entry<Integer, String> e2;
			boolean delete = false;
			while(i2.hasNext()) {
				e2 = i2.next();
				if (delete) {
					i2.remove();
					continue;
				}
				
				if (e2.getValue().length() >= max) {
					delete = true;
					i2.remove();
				}
	        }
			
		}
		
		for (Entry<Integer, String> entry : textDataLevel.entrySet()) {
			s = entry.getValue().trim();
			
			//System.out.println("Key = " + entry.getKey() + ", Value = " + s);
			text += s + "\n\n";
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
			ParseHeaderPhrasesPDF pdf = new ParseHeaderPhrasesPDF(new File("C:\\FAA3\\data\\ILP42-Team Responsibilities.pdf") , "C:\\FAA3\\main_phrases", "1", "auto");
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
