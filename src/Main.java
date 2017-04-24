import java.io.File;
import java.util.ArrayList;

/**
 * The Main class takes the file paths of the text file containing the image file paths, 
 * and calls the methods to train the network and test corrupted patterns.
 * 
 * ***REMOVED***
 * @version 1.0
 */
public class Main {
	
	static PatternProcessor patternProcessor;
	static Hopfield hopfield;
	static ImageProcessor imageProcessor;

	public static void main(String[] args) {

		//String storedPatternsPath = args[0];
		//String corruptedPatternsPath = args[1];
		
		// Process the default text files for debugging
		String storedPatternsPath = "example_images/100_example_stored.txt";
		String corruptedPatternsPath = "example_images/100_example_corrupt.txt";
		
		patternProcessor = new PatternProcessor();
		
		ArrayList<ArrayList<Integer>> storedPatterns = patternProcessor.processImageToPatterns(storedPatternsPath);
		ArrayList<ArrayList<Integer>> corruptedPatterns = patternProcessor.processImageToPatterns(corruptedPatternsPath);
		
		hopfield = new Hopfield();
		
		hopfield.train(storedPatterns);
		ArrayList<ArrayList<Integer>> outputPatterns = hopfield.corruptedPatterns(corruptedPatterns);

		int size = patternProcessor.getInputImageSize();
		ImageProcessor.convertPatternsToImages(outputPatterns, size);	
		
	}

}