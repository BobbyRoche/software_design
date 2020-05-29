import java.io.Serializable;

public class OutputFactory implements Serializable {
	/**
	 * 
	 */
	//factory created for handling different types of output. Currently only handles console
	private static final long serialVersionUID = 4700150611415438103L;

	public Output createOutputObject(String inputType)
	{
		if(inputType.contentEquals("console"))
		{
			return new ConsoleOut();
			
		}
		else 
			return null;
	}

}
