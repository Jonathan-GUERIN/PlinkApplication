package ia_interfaces;

import java.io.File;

public interface TT {
	/*
	 * Receive a raw picture to the module TT, that will be processed for machine learning
	 * @param picture jpg 256x256
	 */
	public Boolean receivePicture(File picture);
	
	/*
	 * Send a processed picture to the Neural Network
	 * @param picture jpg 256x256
	 */
	public Boolean sendPicture(File picture);
}
