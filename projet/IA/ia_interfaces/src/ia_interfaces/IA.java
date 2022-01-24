package ia_interfaces;

import java.io.File;

public interface IA {
	/*
	 * Received processed picture from TT
	 * 
	 */
	public Boolean receivePicure(File picture);
	
	/*
	 * Send diagnosis to server
	 * @param diagnosis vector of probability
	 * 
	 */
	public Boolean sendDiagnosis(File diagnosis);
	
}
