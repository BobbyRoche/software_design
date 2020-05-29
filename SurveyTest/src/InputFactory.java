import java.io.Serializable;

public class InputFactory implements Serializable {
	
	/**
	 * 
	 */
	//input facotry
	private static final long serialVersionUID = 271929543044353831L;

	public Input createInputObject(String inputType)
	{
		if(inputType.contentEquals("console"))
		{
			return new ConsoleIn();
			
		}
		else 
			return null;
	}
	
	

}
