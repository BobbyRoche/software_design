import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

public class Survey implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6865093333332259823L;
	//all surveys and test have these elements
	protected String name;
	//survey is composed of questions
	protected ArrayList<Question> questions = new ArrayList<>();
	//IO factories
	protected OutputFactory f = new OutputFactory();
	protected Output out = f.createOutputObject("console");
	protected InputFactory IF = new InputFactory();
	protected Input input = IF.createInputObject("console");
	protected AnswerSheet responses;
	protected ArrayList<AnswerSheet> responseSheets = new ArrayList<AnswerSheet>();
	public String type ="S";
	
	//only constructs a name
	public Survey(String name)
	{
		this.name = name;
		
	}
	
	//adds a question based on menu selection
	public void addQuestion(String qType)
	
	{
		try
		{
			int numChoices=0;
			int numResponses=0;
			switch (qType)
			{
			//ensures proper input for all cases.
			case "MP":
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
				break;
			case "Essay":
				Essay newEssay = new Essay();
				questions.add(newEssay);
				break;
			case "SA":
				out.print("How many responses to this short answer would you like?");
				numResponses = Integer.parseInt(input.in());
				ShortAnswer newSA = new ShortAnswer(numResponses);
				questions.add(newSA);
				break;
			case"Ranking":
				out.print("How many items would you like to rank?");
				numChoices = Integer.parseInt(input.in());
				Ranking newRanking = new Ranking(numChoices);
				newRanking.setRanking();
				newRanking.numResponses = newRanking.numOptions;
				questions.add(newRanking);
				break;
			case "Matching":
				out.print("How many pairs would you like to match?");
				numChoices = Integer.parseInt(input.in());
				Matching newMatch = new Matching(numChoices);
				newMatch.numResponses = newMatch.numOptions;
				newMatch.setRanking();
				newMatch.setMatch();
				questions.add(newMatch);
				break;
			}
		}
		catch(NumberFormatException e)
		{
			out.print("Value must be an integer. Please start this question over.");
			addQuestion(qType);
		}
	}
	//displays all questions in the survey
	public void display()
	{
		out.print(this.name+"\n");
		for(int i=0; i<this.questions.size();i++)
		{
			out.print("\nQuestion " + (i+1) + ":\n");
			questions.get(i).display();
		}
	}
	public void take()
	{
		
		//instantiates new response so that it is not always referencing the same location because Java is evil
		this.responses=new AnswerSheet();
		//sets a username for each survey/test filled out
		String username="";
		out.print("Please enter your name: ");
		username=input.in();
		this.responses.setName(username);
		//prints the name of the test
		out.print(this.name);
		//for loop displays each question and then asks for a response after the question is displayed
		for(int i=0; i<this.questions.size();i++)
		{
			out.print("\nQuestion: " + (i+1));
			questions.get(i).display();
			//response is returned from the question as an answer and stored in the answer sheet
			this.responses.addAnswer(questions.get(i).getResponse());		
		}
		//the answer sheet is added to a list of answer sheets
		this.responseSheets.add(this.responses);
	}

	//allows for question modification
	public void modify(int num)
	{
		Question q;
		q = this.questions.get(num);
		//Question has a modify method overidden by necessary question types
		q.modify();

	}
	//get the statistics of each question
	public void tabulate()
	{
		//makes sure responses exist
		if(this.responseSheets.size()==0)
		{
			out.print("No surevys/tests have been filled out.");
			return;
		}
		else
		{
			//iterates over each question in the survey
			for(int i=0;i<this.questions.size();i++)
			{
				Question q = this.questions.get(i);
				out.print("\nQuestion: " + (i+1));
				q.display();
				out.print("\nResponses");
				//makes a "Dictionary" like object to associate the answers to a value
				HashMap<ArrayList<String>,Integer> m = new HashMap<ArrayList<String>, Integer>();
				//iterates over all answers recorded for each question
				for(Answer a : q.allUserAnswers)
				{
					ArrayList<String> answer = a.answers;
					//if the answer exists it increments the value
					if(m.containsKey(answer))
					{
						Integer num = m.get(answer)+1;
						m.put(answer, num);
					}
					//if it does not exist it is added and given a value of 1
					else
					{
						m.put(answer, 1);
					}
					
				}
				//displays the HashMap
				for(HashMap.Entry<ArrayList<String>,Integer> entry: m.entrySet())
				{
					ArrayList<String> res = entry.getKey();
					Integer num = entry.getValue();
					out.print(res.toString().replaceAll("[\\[\\],]","") +": " + num.toString());
				}
				
				
			}
		}
	}

}
