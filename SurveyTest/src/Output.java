import java.io.Serializable;

public abstract class Output implements Serializable {

		/**
	 * 
	 */
	//abstract output
	private static final long serialVersionUID = -6520318896773849266L;
		protected String output;
		
		public abstract void print(String m);
		public abstract String getLastOutput();

}
