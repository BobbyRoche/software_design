import java.io.Serializable;
import java.util.ArrayList;

public class Answer implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8025808766333816585L;
	public int numAnswers;
	//an answer is a list of strings. Based on how many responses will determine the size of the ArrayList.
	public ArrayList<String> answers = new ArrayList<String>();
	protected OutputFactory f = new OutputFactory();
	protected Output out=f.createOutputObject("console");
	

	public void setNumAnswers(int num)
	{
		this.numAnswers = num;
	}
	//adds strings to the answer
	public void setAnswer(String ans)
	{
			this.answers.add(ans);

	}
	public ArrayList<String> getAnswer()
	{
		return answers;
	}
	public void modifyAns(int num, String newAns)
	{
		this.answers.set(num, newAns);
	}
	public void displayAns()
	{
		//displays all sub-answers in the answer
		for(String s:this.answers)
		{
			out.print(s);
		}
	}

}
