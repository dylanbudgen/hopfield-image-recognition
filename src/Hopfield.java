import java.util.ArrayList;
import java.util.Arrays;

/**
 *  Hopfield is a class that contains the hopfield network.
 * 
 * ***REMOVED***
 * @version 1.2
 */
public class Hopfield {
	
	// The constant N
	private static final double N = 0.01;
	
	private double[][] weights;
	private int numberOfNeurons;
	private int numberOfPatterns;
	

	public Hopfield() {
		
	} 
	
	/**
	 * Train the hopfield network with a set of patterns, using the Hebbian learning rule. 
	 *
	 * @param storedPatterns the stored patterns
	 * @return true, if successful
	 */
	public boolean train(ArrayList<ArrayList<Integer>> storedPatterns) {
		
		numberOfNeurons = storedPatterns.get(0).size();
		int numberOfPatterns = storedPatterns.size();
		
		// Check if the patterns are learnable
		double learnable = (double) numberOfPatterns / (double) numberOfNeurons;
	
		if (learnable >= 0.138) {
			System.out.println("The patterns are unlearnable");
			return false;
		}	

		// Calculate weights
		weights = new double[numberOfNeurons][numberOfNeurons];
		
		for(int i = 0; i < numberOfNeurons; i++) {
			for(int j = 0; j < numberOfNeurons; j++) {
			
				if (i == j) {
					// do nothing because the neuron is pointing to itself
				} else {
					double weight = 0;
					
					for (int k = 0; k < numberOfPatterns; k++) {
						weight += storedPatterns.get(k).get(i) * storedPatterns.get(k).get(j);
					}
					weight = weight * N;
					weights[i][j] = weight;
				}
			}
		}	
		return true;
	}
	
	
	/**
	 * Calculate the output of multiple corrupted patterns, by updating the pattern asynchronously.
	 * 
	 * @param corruptedPatterns the corrupted patterns
	 * @return the output patterns
	 */
	public ArrayList<ArrayList<Integer>> corruptedPatterns(ArrayList<ArrayList<Integer>> corruptedPatterns) {

		int qtyOfPatterns = corruptedPatterns.size();
		
		ArrayList<ArrayList<Integer>> outputPatterns = new ArrayList<ArrayList<Integer>>();
		
		for (int k = 0; k < qtyOfPatterns; k++) {
			outputPatterns.add(corruptedPattern(corruptedPatterns.get(k)));
		}
		
		return outputPatterns;
	}
	
	
	/**
	 * Calculate the output of a single corrupted pattern, by updating the pattern asynchronously.
	 *
	 * @param corruptedPattern the corrupted pattern
	 * @return the output pattern
	 */
	public ArrayList<Integer> corruptedPattern(ArrayList<Integer> corruptedPattern) {

			ArrayList<Integer> output = new ArrayList<>();
			
			for(int i = 0; i < numberOfNeurons; i++) {

				double a = 0; 

				for (int j = 0; j < numberOfNeurons; j++) {
					a+= weights[i][j] * corruptedPattern.get(j);
				}
				
				if (a >= 0) {	
					output.add(i, 1);
				} else {
					output.add(i, -1);
				}
			}
			
			// check for stability
			if(corruptedPattern.equals(output)) {
				//ArrayPrinter.printArrayList(output);
				return output;
			}
			// recursion for stability check 
			return corruptedPattern(output);
		}
	
	
		
	/**
	 * Prints the weights.
	 */
	public void printWeights() {
		System.out.println("");
		
		int n = 0;
		
		for(int i = 0; i < weights.length; i++) {
			for(int j = 0; j < weights.length; j++) {
				
				System.out.print(weights[i][j]);
				System.out.print(" ");
				n++;
			}
			
			// print next line
			if (n % numberOfNeurons == 0) {
				System.out.println("");
			}
		}	
		System.out.println("");
	}
	


}