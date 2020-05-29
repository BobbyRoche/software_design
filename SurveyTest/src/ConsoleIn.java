import java.io.Serializable;
import java.util.Scanner;

public class ConsoleIn extends Input implements Serializable {
	
	/**
	 * 
	 */
	//console input factory
	private static final long serialVersionUID = 7161304453921008367L;
	

	@Override
	public String in() 
	{
		Scanner scanner = new Scanner(System.in);
		 this.input=scanner.nextLine();
		 return this.input;
		 
		
	}

	@Override
	public String getLastInput() {
		
		return this.input;
	}
	
	

}
