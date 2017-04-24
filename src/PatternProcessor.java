import java.awt.Color;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.*;

/**
 * PatternProcessor is a class which translates images to patterns. 
 * 
 * ***REMOVED***
 * @version 1.2
 */
public class PatternProcessor {
	
	int imageSize;
	
	public PatternProcessor() {

	}	
	
	/**
	 * Process images from text file to patterns.
	 *
	 * @param filepath the filepath of the image
	 * @return the resulting patterns
	 */
	public ArrayList<ArrayList<Integer>> processImageToPatterns(String filepath) {
		
        try {
            File file = new File(filepath);
            Scanner scanner = new Scanner(file);

            ArrayList<ArrayList<Integer>> patterns = new ArrayList<ArrayList<Integer>>();

            while (scanner.hasNextLine()) {            	
            	
                String line = scanner.nextLine();   
                imageSize = ImageProcessor.getImageSize(line);
                patterns.add(ImageProcessor.convertImageToPattern(line));
                
            }
            scanner.close();
            
            return patterns;

	    } catch (Exception ex) {
	        ex.printStackTrace();
	        System.out.println("Error: File not found");
	    }
		return null;
	}
	
	
	public int getInputImageSize() {
		
		return imageSize;
		
	}
	
}
