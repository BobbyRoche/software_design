import java.io.Serializable;
import java.util.ArrayList;

public class MultipleChoice extends Question implements Serializable {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3533109167468866321L;
	protected int numChoices;
	protected ArrayList<String> choices = new ArrayList<String>();
	protected String choice;
	
	public MultipleChoice(int numChoices)
	{
		this.numChoices = numChoices;
		this.type = "multiple-choice";
		
	}
	
	protected void setChoices()
	{
		this.choice="";
		this.choices.clear();
		//handles choices
		for(int i=1; i<=this.numChoices; i++)
		{
			out.print("Enter Choice #"+i+":");
			this.choice = input.in();
			if(this.choice.isEmpty())
			{
				out.print("Choice cannot be null");
				setChoices();
			}
			this.choices.add(i + ") " +choice);
		}
	}
	protected ArrayList<String> getChoices()
	{
		return this.choices;
	}

	@Override
	public void display() 
	{
		out.print(getPrompt());
		for(int i=0; i<numChoices; i++)
		{
			out.print(choices.get(i));
		}

		
	}
	//allows for modification of choices in addition to initial functionality
	@Override
	public void modify()
	{
		String x="";
		String newChoice = "";
		int choice;
		out.print(getPrompt());
		out.print("Would you like to modify the prompt? (y/n)");
		x=input.in().toLowerCase();
		if(x.equals("y"))
		{
			setPrompt();
			out.print("Would you like to modify the choices?(y/n | type n when done)");
			x=input.in().toLowerCase();
			while (!x.equals("n"))
			{
				for(int i=0; i<numChoices; i++)
				{
					out.print(choices.get(i));
				}
				out.print("Which choice would you like to modify?");
				//check input
				choice = Integer.parseInt(input.in());
				out.print("Please enter new choice: ");
				//check input
				newChoice = choice + ") " + input.in();
				choices.set(choice-1, newChoice);
				out.print("Would you like to continue modifying the choices?(y/n | type n when done)");
				x=input.in().toLowerCase();
				
				
			}
			
		}
		else if(x.equals("n"))
		{
			out.print("Would you like to modify the choices?(y/n | type n when done)");
			x=input.in().toLowerCase();
			while (!x.equals("n"))
			{
				for(int i=0; i<numChoices; i++)
				{
					out.print(choices.get(i));
				}
				out.print("Which choice would you like to modify?");
				//check input
				choice = Integer.parseInt(input.in());
				out.print("Please enter new choice: ");
				//check input
				newChoice = choice + ") " + input.in();
				choices.set(choice-1, newChoice);
				out.print("Would you like to continue modifying the choices?(y/n | type n when done)");
				x=input.in().toLowerCase();
				
				
			}
			
		}
		else
		{
			out.print("invalid input!");
			modify();
		}
	
	}
	@Override
	public Answer getResponse()
	{
		String ans="";
		Answer a = new Answer();
		//gets the proper number of answers
		for(int i=0; i<this.numResponses;i++)
		{
			out.print("Response " + (i+1) + " of " + this.numResponses +":");
			ans = input.in();
			if(ans!=null && Integer.parseInt(ans)>=1 && Integer.parseInt(ans)<=this.numChoices)
			{
				if(a.answers.contains(ans))
				{
					out.print("Bad input");
					return getResponse();
				}
				//adds the answer to the answer key
				a.setAnswer(ans);
					
			}
			else
			{
				out.print("Bad Input.");
				return getResponse();
			}
			
		}
		this.allUserAnswers.add(a);
		return a;
		
	}
	
	
	
	

}
