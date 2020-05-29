import java.io.Serializable;

public class Essay extends Question implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -352755228528016325L;
	protected String ans="";
	//simplest of all questions.
	public Essay()
	{
		this.type = "essay";
	}
	//simply takes in strings
	public void writeEssay()
	{
		this.ans+=input.in();
		
	}
	//returns the essay
	public String getEssay()
	{
		return this.ans;
	}


}
