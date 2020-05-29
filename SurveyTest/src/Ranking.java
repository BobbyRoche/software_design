import java.io.Serializable;
import java.util.ArrayList;

public class Ranking extends Question implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2387976443239857058L;
	//list of choices to be ranked
	protected ArrayList<String> choices = new ArrayList<String>();
	protected int numOptions;
	public Ranking(int num)
	{
		this.numOptions= num;
		this.numResponses = numOptions;
		this.type = "ranking";	
	}
	//allows user to set items to be ranked
	protected void setRanking()
	{
		String temp="";
		for(int i=1; i<=this.numOptions; i++)
		{
			out.print("Add item #" + i);
			temp = input.in();
			this.choices.add(i+")" + temp);
		}
	}
	//returns rankings
	public ArrayList<String> getRanking()
	{
		return this.choices;
	}
	//displays the rankings in a column
	@Override
	public void display()
	{
		out.print(getPrompt());
		for(int i=0; i<this.numOptions; i++)
		{
			out.print(this.choices.get(i));
		}
	}
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
			out.print("Would you like to modify the options?(y/n | type n when done)");
			x=input.in().toLowerCase();
			while (!x.equals("n"))
			{
				for(int i=0; i<this.numOptions; i++)
				{
					out.print(choices.get(i));
				}
				out.print("Which option would you like to modify?");
				try
				{
					choice = Integer.parseInt(input.in());
					out.print("Please enter new choice: ");
					newChoice = choice + ") " + input.in();
					choices.set(choice-1, newChoice);
					out.print("Would you like to continue modifying the options?(y/n | type n when done)");
					x=input.in().toLowerCase();
				}
				catch(NumberFormatException e)
				{
					out.print("Value must be an integer.");
					modify();
				}
				catch(ArrayIndexOutOfBoundsException e)
				{
					out.print("Number given exceeds amount of options.");
					modify();
				}
				
				
			}
			
		}
		else if(x.equals("n"))
		{
			out.print("Would you like to modify the options?(y/n | type n when done)");
			x=input.in().toLowerCase();
			while (!x.equals("n"))
			{
				for(int i=0; i<this.numOptions; i++)
				{
					out.print(choices.get(i));
				}
				out.print("Which option would you like to modify?");
				try
				{
					choice = Integer.parseInt(input.in());
					out.print(choice + ") "+choices.get(choice));
					out.print("Please enter new option: ");
					newChoice = input.in();
					choices.set(choice-1, newChoice);
					out.print("Would you like to continue modifying the options?(y/n | type n when done)");
					x=input.in().toLowerCase();
				}
				catch(NumberFormatException e)
				{
					out.print("Value must be an integer.");
					modify();
				}
				catch(ArrayIndexOutOfBoundsException e)
				{
					out.print("Number given exceeds amount of options.");
					modify();
				}
				
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
			try
			{
				if(Integer.parseInt(ans)>=1 && Integer.parseInt(ans)<=this.numResponses )
				{
					if(a.answers.contains(ans))
					{
						out.print("Bad input");
						return getResponse();
					}
					a.setAnswer(ans);
						
				}
				else
				{
					out.print("Answer out of bounds, please try again..");
					return getResponse();
				}

			}
			catch(NumberFormatException e)
			{
				out.print("Must be an integer!");
				return getResponse();
			}
		}
		this.allUserAnswers.add(a);
		return a;
		
	}

}
