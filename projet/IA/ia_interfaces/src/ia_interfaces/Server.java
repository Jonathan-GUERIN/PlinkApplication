package ia_interfaces;

import java.io.File;

public interface Server {
	/*
	 * send a picture to the module TT
	 * 
	 */
	public int sendPicture(File picture);
	/*
	 * Receive a dignosis (text file or json file) from the neural network
	 * @param diagnosis vector of probability
	 */
	public Boolean receiveDiagnosis(File diagnosis);
}
