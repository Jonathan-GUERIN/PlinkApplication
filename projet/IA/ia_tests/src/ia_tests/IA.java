package ia_tests;

import java.io.File;

public class IA implements IAInterface{
	
	public IA() {
		super();
	}

	@Override
	public File generateDiagnosis(File picture) {
		// TODO Auto-generated method stub
		return new File("diagnosis");
	}

}
