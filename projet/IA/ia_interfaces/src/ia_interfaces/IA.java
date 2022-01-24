package ia_interfaces;

import java.io.File;

public interface IA {
	/*
	 * Received processed picture from TT
	 * @param picture jpg 256x256
	 */
	public Boolean receivePicure(File picture);
	
	/*
	 * Send diagnosis to server
	 * @param diagnosis vector of probability
	 * 
	 */
	public Boolean sendDiagnosis(File diagnosis);
	
}
