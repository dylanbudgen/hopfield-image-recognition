import java.io.File;
import java.util.*;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

/**
 * PatternProcessor is a class which translates images to patterns, and vice versa. 
 * 
 * ***REMOVED*** Methods from other authors are referenced. 
 * @version 1.2
 */
public class PatternProcessor {
	
	int imageSize;
	private final String IMAGE_FORMAT = "png";
	
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
                imageSize = getImageSize(line);
                patterns.add(convertImageToPattern(line));
                
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
	
	
	/**
     * Read an image file from disk and return it as an image. This method
     * can read JPG and PNG file formats. In case of any problem (e.g the file 
     * does not exist, is in an undecodable format, or any other read error) 
     * this method returns null.
     * 
     * @author  	Michael Kölling and David J. Barnes.
     * @param imageFile  The image file to be loaded.
     * @return           The image object or null is it could not be read.
     */
    public OFImage loadImage(File imageFile)
    {
        try {
            BufferedImage image = ImageIO.read(imageFile);
            if(image == null || (image.getWidth(null) < 0)) {
                return null;
            }
            return new OFImage(image);
        }
        catch(IOException exc) {
            return null;
        }
    }
    
    
    /**
     * Convert a pattern into an image file.
     *
     * @param filename the filename
     * @return the array list
     */
    public ArrayList<Integer> convertImageToPattern(String filename) {
    
		File file = new File(filename);
		OFImage currentImage = loadImage(file);
		
	
		if(currentImage != null) {
            int width = currentImage.getWidth();
            int height = currentImage.getHeight();

            int n = 0; 
            ArrayList<Integer> pixels = new ArrayList<>();
            
            // copy pixel data into new image
            for(int y = 0; y < height; y++) {
                for(int x = 0; x < width; x++) {
                    Color col = currentImage.getPixel(x, y);
                    
                    if (col.getRGB() < -1) {
                    	pixels.add(n, -1);
                    } else {
                    	pixels.add(n, 1);
                    }

                    n++;
                }
            }
            
            //ArrayPrinter.printArrayList(pixels);
            return pixels;
	            
	     } else {
	        return null;
	     }
	        
    }
	        
    public Integer getImageSize(String filename) {
        
		File file = new File(filename);
		OFImage currentImage = loadImage(file);
		
		if(currentImage != null) {

			if (currentImage.getWidth() == currentImage.getHeight()) {
				return currentImage.getHeight();
			} else {
				System.out.println("Error: Input images must have the same height and width");
				return null;
			}
		} else {
			return null;
		}
	        
    }    
    
    /**
     * Convert patterns to images and save the files.
     *
     * @param array  the patterns to convert
     */
    public void convertPatternsToImages(ArrayList<ArrayList<Integer>> patterns, int size) {
    	
    	int outputNumber = 0;
    	
    	for (ArrayList<Integer> pattern : patterns) {
    	
            OFImage image = new OFImage(size, size);

            int n = 0;
            // copy pixel data into new image
            for(int y = 0; y < size; y++) {
                for(int x = 0; x < size; x++) {
                	
                	if (pattern.get(n) == -1) {
                		image.setPixel(x, y, Color.BLACK);
                		
                	} else {
                		image.setPixel(x, y, Color.WHITE);
                	}
                	n++;

                }
            }
           
            String filename = Integer.toString(outputNumber) + ".png";
        	File file = new File(filename);
        	saveImageFile(image, file);  
        	outputNumber++;
    	}

    }
    

    /**
     * Convert pattern to image.
     *
     * @param array  pattern to convert
     * @return the OF image
     */
    public OFImage convertPatternToImage(ArrayList<Integer> array)
    {
            OFImage newImage = new OFImage(16, 16);

            int n = 0;
            // copy pixel data into new image
            for(int y = 0; y < 16; y++) {
                for(int x = 0; x < 16; x++) {
                	
                	if (array.get(n) == -1) {
                		newImage.setPixel(x, y, Color.BLACK);
                		
                	} else {
                		newImage.setPixel(x, y, Color.WHITE);
                	}
                	n++;

                }
            }
            
            return newImage;

    }
    

    /**
     * Write an image file to disk. The file format is JPG. In case of any 
     * problem the method just silently returns.
     * 
     * @author  	Michael Kölling and David J. Barnes.
     * @param image  The image to be saved.
     * @param file   The file to save to.
     */
    public void saveImageFile(OFImage image, File file)
    {
        try {
            ImageIO.write(image, IMAGE_FORMAT, file);
        }
        catch(IOException exc) {
            return;
        }
    }
	
	
}
