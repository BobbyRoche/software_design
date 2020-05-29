import java.io.Serializable;
import java.util.ArrayList;

public abstract class Question implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6076583280958153994L;
	protected String prompt;
	protected int numResponses = 1;
	protected InputFactory IF = new InputFactory();
	protected OutputFactory f = new OutputFactory();
	protected Input input = this.IF.createInputObject("console");
	protected Output out = this.f.createOutputObject("console");
	protected String type;
	public ArrayList<Answer> allUserAnswers = new ArrayList();
	
	

	
	public Question()
	{
		setPrompt();
	}
	protected void setPrompt() 
	{
		out.print("Please enter the prompt for your question:");
		this.prompt = input.in();
		if(this.prompt.isEmpty())
		{
			out.print("Prompt cannot be empty");
			setPrompt();
		}
	}
	public String getPrompt()
	{
		return this.prompt;
	}
	public void display()
	{
		out.print(getPrompt());
	}
	public void setNumResponses(int num)
	{
		this.numResponses = num;
	}
	public void modify()
	{
		String x="";
		out.print(getPrompt());
		out.print("Would you like to modify the prompt? (y/n)");
		x=input.in().toLowerCase();
		if(x.equals("y"))
		{
			setPrompt();
		}
		else if(x.equals("n"))
		{
			
		}
		else
		{
			out.print("invalid input!");
			modify();
		}
	
	}
	//overridden when necessary. only not modified for essay or short answer
	public Answer getResponse()
	{
		String ans="";
		Answer a = new Answer();
		//gets the proper number of answers
		for(int i=0; i<this.numResponses;i++)
		{
			out.print("Response " + (i+1) + " of " + this.numResponses +":");
			ans = input.in();
			if(ans!=null)
			{
				//adds the answer to the answer key
				a.setAnswer(ans);
					
			}
			else
			{
				out.print("Answer cannot be null, please try again.");
				getResponse();
			}
			
		}
		this.allUserAnswers.add(a);
		return a;
		
	}

	
		
		
	
		
	

}
