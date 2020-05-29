import java.io.Serializable;

public class TrueFalse extends MultipleChoice implements Serializable {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7997055159530215469L;
	//constructs a multiple choice question with 2 options True and False
	public TrueFalse() 
	{
		super(2);
		choices.add("T");
		choices.add("F");
		this.type = "true/false";
	
	}
	@Override
	public void modify()
	{
		String ans;
		out.print("Would you like to change the prompt? (y/n)");
		ans=input.in().toLowerCase();
		if(ans.equals("y"))
			setPrompt();
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
			ans = input.in().toUpperCase();
			if(ans!=null &&(ans.equals("T")||ans.equals("F")))
			{
				//adds the answer to the answer key
				a.setAnswer(ans);
					
			}
			else
			{
				out.print("Response must be T or F, please try again.");
				getResponse();
			}
			
		}
		this.allUserAnswers.add(a);
		return a;
		
	}


}

