import java.util.ArrayList;
import java.io.Serializable;
public class AnswerSheet implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2411996953543347266L;
	//Contains answers
	String name;
	public ArrayList<Answer> sheet = new ArrayList<Answer>();
	Answer displayAns = new Answer();
	
	//adds an answer object to the list
	public void addAnswer(Answer a)
	{
		this.sheet.add(a);
	}
	public void setName(String n)
	{
		this.name = n;
	}
	public String getName()
	{
		if(this.name == null)
		{
			return "This is a correct answer sheet.";
		}
		else
		{
			return this.name;
		}
	}
	//displays all answers together
	public void displaySheet()
	{
		for(Answer a: this.sheet)
		{
			a.displayAns();
		}
	}
	//displays answers on a per question basis
	public void displayAnswer(int i)
	{
		this.displayAns=this.sheet.get(i);
		this.displayAns.numAnswers=this.sheet.size();
		this.displayAns.displayAns();
	}
	public Answer getAnswer(int num)
	{
		Answer a = new Answer();
		a = this.sheet.get(num);
		return a;
	}
	public int getNumAnswers()
	{
		return this.sheet.size();
	}
	public void empty()
	{
		this.sheet.clear();
	}

}
