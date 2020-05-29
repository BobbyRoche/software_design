import java.io.Serializable;
import java.util.ArrayList;

public class Test extends Survey implements Serializable 
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 8889797260051389970L;
	//contains an answer key for future grading
	private AnswerSheet key = new AnswerSheet();
	
	//constructor does not change
	public Test(String name) {
		super(name);
		this.type="T";
	}

	//overrides previous addQuestion method.
	//the difference here is it calls the a function to get the correct answer after each question is filled out.
	@Override
	public void addQuestion(String qType)
	{
		//makes sure only integers are used for determining choice and response numbers
		try
		{
			int numChoices=0;
			int numResponses=0;
			//checks type of question and adds questions to the test accordingly
			switch (qType)
			{
			case "MP":
				boolean valid=true;
				out.print("Enter the number of choices you would like:");
				numChoices = Integer.parseInt(input.in());
				if(numChoices>0)
				{
					
					out.print("Enter the number of responses you would like:");
					numResponses = Integer.parseInt(input.in());
					MultipleChoice newMPQuestion = new MultipleChoice(numChoices);
					if(0<numResponses && numResponses<=numChoices)
					{
						newMPQuestion.setNumResponses(numResponses);
						newMPQuestion.setChoices();
						questions.add(newMPQuestion);
						getCorrectAnswer(newMPQuestion);
						
						
					}
					else 
					{
						out.print("Invalid number of responses! Resetting question...");
						addQuestion(qType);
					}
					
				}
				else 
				{
					out.print("Must have at least one choice! Resetting question...");
					addQuestion(qType);
				}
				
				break;
				
				
			case "TF":
				TrueFalse newTFQuestion = new TrueFalse();
				questions.add(newTFQuestion);
				getCorrectAnswer(newTFQuestion);
				break;
			case "Essay":
				Essay newEssay = new Essay();
				questions.add(newEssay);
				getCorrectAnswer(newEssay);
				break;
			case "SA":
				out.print("How many responses to this short answer would you like?");
				numResponses = Integer.parseInt(input.in());
				ShortAnswer newSA = new ShortAnswer(numResponses);
				questions.add(newSA);
				getCorrectAnswer(newSA);
				break;
			case"Ranking":
				out.print("How many items would you like to rank?");
				numChoices = Integer.parseInt(input.in());
				Ranking newRanking = new Ranking(numChoices);
				newRanking.setRanking();
				newRanking.numResponses = newRanking.numOptions;
				questions.add(newRanking);
				getCorrectAnswer(newRanking);
				break;
			case "Matching":
				out.print("How many pairs would you like to match?");
				numChoices = Integer.parseInt(input.in());
				Matching newMatch = new Matching(numChoices);
				newMatch.numResponses = newMatch.numOptions;
				newMatch.setRanking();
				newMatch.setMatch();
				questions.add(newMatch);
				getCorrectAnswer(newMatch);
				break;
			}
		}
		catch(NumberFormatException e)
		{
			//if a violation is found user is asked to redo input for the question
			out.print("Value must be an integer. Please start this question over.");
			addQuestion(qType);
		}
	}
	//function to get the correct answer
	public void getCorrectAnswer(Question q)
	{
		String ans="";
		Answer a = new Answer();
		a.numAnswers = q.numResponses;
		//prompts user for answer
		out.print("What is the correct answer(s) to this question?");
		//gets the proper number of answers
		for(int i=0; i<q.numResponses;i++)
		{
			out.print("Answer " + (i+1) + " of " + q.numResponses +":");
			ans = input.in();
			if(ans!=null)
			{
				//adds the answer to the answer key
				a.setAnswer(ans);
					
			}
			else
			{
				out.print("Answer cannot be null, please try again.");
				getCorrectAnswer(q);
			}
			
		}
		this.key.addAnswer(a);
		
	}
	@Override
	//displays questions and answers from the answer key
	public void display()
	{
		out.print(this.name+"\n");
		for(int i=0; i<this.questions.size();i++)
		{
			out.print("\nQuestion " + (i+1) + ":\n");
			this.questions.get(i).display();
			out.print("\nCorrect Answer(s):");
			this.key.displayAnswer(i);
			out.print("");
		}
	}
	public void getResponse(Question q)
	{
		String ans="";
		Answer a = new Answer();
		//gets the proper number of answers
		for(int i=0; i<q.numResponses;i++)
		{
			out.print("Response " + (i+1) + " of " + q.numResponses +":");
			ans = input.in();
			if(ans!=null)
			{
				//adds the answer to the answer key
				a.setAnswer(ans);
					
			}
			else
			{
				out.print("Answer cannot be null, please try again.");
				getResponse(q);
			}
			
		}
		this.responses.addAnswer(a);
		
	}
	
	//allows modification of a question as well as the correct answer.
	@Override
	public void modify(int num)
	{
		String res;
		Answer a = new Answer();
		String newAns;
		int count=0;
		int anum;
		Question q;
		q = this.questions.get(num);
		a.numAnswers = q.numResponses;
		q.modify();
		out.print("Would you like to change the correct answer(s)? (y/n)");
		res=input.in().toLowerCase();
		while(res.equals("y") && count<=a.numAnswers)
		{
			a = key.getAnswer(num);
			a.displayAns();
			if(a.numAnswers>1)
			{
				out.print("Which answer would you like to edit?");
				//check input
				anum = Integer.parseInt(input.in());
				out.print("Enter new correct answer:");
				newAns = input.in();
				a.modifyAns(anum, newAns);
				out.print("Would you like to change any other correct answer(s)? (y/n)");
				res=input.in().toLowerCase();
			}
			else 
			{
				out.print("Enter new correct answer:");
				newAns = input.in();
				a.setAnswer(newAns);
				res = "n";
			}
			
		}

	}
	
	//grade a students test
	public void grade()
	{
		//records correct/incorrect answers
		int correct=0;
		int incorrect =0;
		int graded=0;
		float grade=0;
		int total = this.key.getNumAnswers();
		AnswerSheet currentAs = new AnswerSheet();
		String type;
		boolean isCorrect=false;
		ArrayList<Answer> correctAnswers = this.key.sheet;
		ArrayList<Answer> userAnswers = new ArrayList<Answer>();
		out.print("Please enter the name of the student you wish to grade.");
		String sname = input.in();
		//iterates over each answer sheet in the survey
		for(AnswerSheet as : this.responseSheets)
		{
			//if the name provided matches with an answer sheet the user answer sheet is set
			if(as.name.equals(sname))
			{
				userAnswers = as.sheet;
				currentAs=as;

			}
		}
			//if it is not found, we prompt the user again.
			if(userAnswers == null) 
			{
				out.print("Name not found");
				grade();
			}
		
		for(int i=0; i<correctAnswers.size();i++)
		{
			ArrayList<String> currentCorrectAns = correctAnswers.get(i).answers;
			//check that they took the test
			ArrayList<String> currentUserAns = userAnswers.get(i).answers;
			type=this.questions.get(i).getClass().toString();
			//if it is an essay skips the question
			if(type.contains("Essay"))
				continue;
			else
			{
				for(int j=0; j<currentCorrectAns.size();j++)
				{
					//string matches answer to correct answer
					if(currentUserAns.get(j).equals(currentCorrectAns.get(j)))
					{
						isCorrect = true;
						continue;
					}
					else
					{
						isCorrect=false;
						break;
					}
					
				}
				//increments graded so we know how many problems total are graded
				graded++;
				//increments depending on right or wrong answer
				if(isCorrect) 
					correct++;
				else
					incorrect++;
			}
			
			
		}
		//prints results and calculates grade
		grade = (float)correct/(float)graded*100;
		out.print("Results for " + currentAs.name);
		out.print("Correct: " + correct);
		out.print("Incorrect: " + incorrect);
		out.print("Total (non-essays): " + graded);
		out.print("Current Grade: " + String.format("%.2f", grade));  
		total=total-graded;
		out.print(total + " essays to grade.");
	}
}
