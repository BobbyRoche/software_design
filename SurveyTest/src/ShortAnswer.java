import java.io.Serializable;
import java.util.ArrayList;

public class ShortAnswer extends Essay implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8739795540393828673L;
	private ArrayList <String> answers = new ArrayList<String>();

	//same as essay accept takes multiple answers
	public ShortAnswer(int num)
	
	{
		this.numResponses = num;
		this.type = "short answer";
		
	}
	public void setAnswers()
	{
		for(int i=0;i<=this.numResponses;i++)
		{
			this.answers.add(input.in());
		}
	}
	public ArrayList<String> getAnswers()
	{
	
		return this.answers;
	}
	

	

}
