import java.io.Serializable;
import java.util.ArrayList;

public class Matching extends Ranking implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2924312645938707081L;
	//creates another list in addition to the original for the second column
	private ArrayList<String> matchOptions = new ArrayList<String>();
	public Matching(int num)
	{
		super(num);
		this.type = "matching";
	}
	//after setting the 1st column, prompts user to enter values for the second column.
	public void setMatch()
	{
		String temp="";
		this.prompt+=" (Response format: Left Column # Right Column # no spaces!)";
		for(int i=1; i<=this.numOptions; i++)
		{
			out.print("Add item #" + i + " to be matched.");
			temp = input.in();
			this.matchOptions.add(i+")" + temp);
		}
	}
	//returns second column
	public ArrayList<String> getMatch()
	{
		return this.matchOptions;
	}
	@Override
	//displays the first column elements tabs and then second column element
	//needs to be formatted.
	public void display()
	{
		out.print(getPrompt());
		for(int i=0; i<this.numOptions; i++)
		{
			out.print(this.choices.get(i) + "\t" + this.matchOptions.get(i));
			
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
			out.print("Would you like to modify the left column?(y/n | type n when done)");
			x=input.in().toLowerCase();
			while (!x.equals("n"))
			{
				for(int i=0; i<this.numOptions; i++)
				{
					out.print(choices.get(i));
				}
				out.print("Which option would you like to modify?");
				//check input
				choice = Integer.parseInt(input.in());
				out.print("Please enter new option: ");
				//check input
				newChoice = choice + ")" + input.in();
				choices.set(choice-1, newChoice);
				out.print("Would you like to continue modifying the left column?(y/n | type n when done)");
				x=input.in().toLowerCase();
			}
			out.print("Would you like to modify the right column?(y/n | type n when done)");
			x=input.in().toLowerCase();
			while (!x.equals("n"))
			{
				for(int i=0; i<this.numOptions; i++)
				{
					out.print(this.matchOptions.get(i));
				}
				out.print("Which option would you like to modify?");
				//check input
				choice = Integer.parseInt(input.in());
				out.print("Please enter new option: ");
				//check input
				newChoice = choice + ")" + input.in();
				this.matchOptions.set(choice-1, newChoice);
				out.print("Would you like to continue modifying the right column?(y/n | type n when done)");
				x=input.in().toLowerCase();
				
				
			}
			
		}
		else if(x.equals("n"))
		{
			out.print("Would you like to modify the left column?(y/n | type n when done)");
			x=input.in().toLowerCase();
			while (!x.equals("n"))
			{
				for(int i=0; i<this.numOptions; i++)
				{
					out.print(choices.get(i));
				}
				out.print("Which option would you like to modify?");
				//check input
				choice = Integer.parseInt(input.in());
				out.print("Please enter new option: ");
				//check input
				newChoice = choice + ")" + input.in();
				choices.set(choice-1, newChoice);
				out.print("Would you like to continue modifying the left column?(y/n | type n when done)");
				x=input.in().toLowerCase();
			}
			out.print("Would you like to modify the right column?(y/n | type n when done)");
			x=input.in().toLowerCase();
			while (!x.equals("n"))
			{
				for(int i=0; i<this.numOptions; i++)
				{
					out.print(this.matchOptions.get(i));
				}
				out.print("Which option would you like to modify?");
				//check input
				choice = Integer.parseInt(input.in());
				out.print("Please enter new option: ");
				//check input
				newChoice = choice + ")" + input.in();
				this.matchOptions.set(choice-1, newChoice);
				out.print("Would you like to continue modifying the right column?(y/n | type n when done)");
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
		boolean validi=true;
		String ans="";
		Answer a = new Answer();
		//gets the proper number of answers
		for(int i=0; i<this.numResponses;i++)
		{
			out.print("Match " + this.choices.get(i).substring(2,this.choices.get(i).length()) + " to the second column:");
			//does not check for multiple inputs because you can match multiple 1st column elements to 1 second column element.
			ans = input.in();
			//only validates the matchOptions because the user is forced to match left column elements in order.
			for(String s : this.matchOptions)
			{
				if(s.substring(0, 1).equals(ans))
				{
					validi=true;
					break;
				}
				else
					validi=false;
			}
			if(validi)
			{
				//adds the answer to the answer key
				a.setAnswer(ans);
			}
			else
			{
				out.print("Bad input!");
				return (getResponse());
			}
			
			
					
			

			
		}
		this.allUserAnswers.add(a);
		return a;
		
	}

}
