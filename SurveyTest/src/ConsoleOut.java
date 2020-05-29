import java.io.Serializable;

public class ConsoleOut extends Output implements Serializable {

	
	
	/**
	 * 
	 */
	//console output factory
	private static final long serialVersionUID = -1565131342640369801L;

	@Override
	public void print(String m) {
		this.output = m;
		System.out.println(this.output);
	}


	@Override
	public String getLastOutput() {
		return this.output;
	}
	
	

}
