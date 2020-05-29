import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class Menu implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2167419315957300117L;
	//Lists that hold the contents of the two menus. 
	private String []SurveyMenu = {"Survey Menu", "1) Create New Survey", "2) Display a Survey", 
									"3) Load a Survey", "4) Save a Survey", "5) Modify Survey","6) Take Survey", "7) Tabulate","8) Quit" };
	private String []TestMenu = {"Test Menu", "1) Create New Test", "2) Display a Test", 
			"3) Load a Test", "4) Save a Test","5) Modify Test","6) Take Test", "7) Grade Test","8) Tabulate","9) Quit" };
	private String[] QuestionsMenu = {"1)Add Multiple Choice","2)Add True/Flase",
									"3)Add Short Answer","4)Add Essay","5)Add Ranking","6)Add Matching","7)Go back"};
	
	public Survey currentSurvey;
	//protected Test currentTest;
	private String type;
	
	//input/output factory in use
	protected OutputFactory f = new OutputFactory();
	protected Output out = f.createOutputObject("console");
	protected InputFactory IF = new InputFactory();
	protected Input input=this.IF.createInputObject("console");
	
	
	
	//Displays the first menu
	public void displayMenu1()
	{
		out.print("Welcome Bobby's Survey/Test system!");
		out.print("Please Select one of the following options (1, or 2):");
		out.print("\n1)Survey\t2)Test");
		switch (input.in())
		{
		case "1":
			this.type = "survey";
			displaySurveyMenu();
			break;
		case "2":
			this.type="test";
			displayTestMenu();  
			break;
		default:
			out.print("Not an available option");
			displayMenu1();
			
		}
		
	}
	//displays survey menu
	private void displaySurveyMenu()
	{
	for (int i = 0; i<SurveyMenu.length; i++)
		{
		out.print(SurveyMenu[i]);
		}
	this.type="S";
	surveyMenu();
	}
	//displays test menu
	private void displayTestMenu()
	{
		for (int i = 0; i<TestMenu.length; i++)
		{
			out.print(TestMenu[i]);
		}
		this.type="T";
		testMenu();
		
	}
	//survey menu handler
	private void surveyMenu()
	{
	switch (input.in())
	{
		case "1":
			createNewSurvey();
			break;
		case "2":
			if(this.currentSurvey!=null)
			{
				this.currentSurvey.display();
			}
			else
			{
				out.print("NO SURVEY OR TEST SELECTED, PLEASE LOAD OR CREATE A SURVEY/TEST!");
				displayMenu1();
			}
			break;
		case "3":
			String outFile;
			out.print("Please enter the file name you would like to load this survey or test under (no spaces): ");
			outFile=input.in();
			load(outFile,this.type);
	
			break;
		case "4":
			if(this.currentSurvey!=null)
			{
				String inFile;
				out.print("Please enter the file name you would like to save this survey or test under (no spaces): ");
				inFile=input.in();
				save(inFile);
			}
			else
			{
				out.print("NO SURVEY OR TEST SELECTED, PLEASE LOAD OR CREATE A SURVEY/TEST!");
				displayMenu1();
			}
			break;
		case "5":
			if(this.currentSurvey!=null)
			{
				int num =0;
				out.print("Please enter the question number you would like to modify.");
				try
				{
				num = Integer.parseInt(input.in())-1;
				this.currentSurvey.modify(num);
				}
				catch(NumberFormatException e)
				{
					out.print("Invalid input. Question number must be an integer.");
				}
			}
			else
			{
				out.print("No survey or test selected. Please load or create a survey or test.");
				displayMenu1();
			}
			
			break;
		case "6":
			if(this.currentSurvey!=null)
			{
				this.currentSurvey.take();
			}
			else
			{
				out.print("No survey or test currently selected. Please load or create a survey/test.");
				displayMenu1();
			}
			break;
		case "7":
			if(this.currentSurvey!=null)
				this.currentSurvey.tabulate();
			else
			{
				out.print("No Survey Selected\n");
			}
			break;
		case "8":
			System.exit(0);
		default:
			out.print("Not an available option");
			break;
	
	}
	displaySurveyMenu();
	}
	//test menu handler
	private void testMenu()
	{
	switch (input.in())
	{
		case "1":
			createNewTest();
			break;
		case "2":
			if(this.currentSurvey!=null)
			{
				this.currentSurvey.display();
			}
			
			else
			{
				out.print("NO SURVEY OR TEST SELECTED, PLEASE LOAD OR CREATE A SURVEY/TEST!");
				displayMenu1();
			}
			break;
		case "3":
			String outFile;
			out.print("Please enter the file name you would like to load this survey or test under (no spaces): ");
			outFile=input.in();
			load(outFile,this.type);
			break;
		case "4":
			if(this.currentSurvey!=null)
			{
				String inFile;
				out.print("Please enter the file name you would like to save this survey or test under (no spaces): ");
				inFile=input.in();
				save(inFile);
			}
			else
			{
				out.print("NO SURVEY OR TEST SELECTED, PLEASE LOAD OR CREATE A SURVEY/TEST!");
				displayMenu1();
			}
			break;
		case "5":
			if(this.currentSurvey!=null)
			{
				int num =0;
				out.print("Please enter the question number you would like to modify.");
				try
				{
				num = Integer.parseInt(input.in())-1;
				this.currentSurvey.modify(num);
				}
				catch(NumberFormatException e)
				{
					out.print("Invalid input. Question number must be an integer.");
				}
			}
			else
			{
				out.print("No survey or test selected. Please load or create a survey or test.");
				displayMenu1();
			}
			
			break;
		case "6":
			if(this.currentSurvey!=null)
			{
				this.currentSurvey.take();
			}
			else
			{
				out.print("No survey or test currently selected. Please load or create a survey/test.");
				displayMenu1();
			}
			break;
		case "7":
			if(this.currentSurvey==null)
			{
				out.print("No test selected, please create or load a test.");
			}
			else 
			{
				((Test) this.currentSurvey).grade();
			}
			break;
			
		case "8":
			if(this.currentSurvey!=null)
				this.currentSurvey.tabulate();
			else
			{
				out.print("No Test Selected");
			}
			break;
		case "9":
			System.exit(0);
		default:
			out.print("Not an available option");
			break;
	
	}
	displayTestMenu();
		
	}
	//creates new survey object
	private void createNewSurvey()
	{
		String name;
		out.print("Please give the name of the survey or test:");
		name = input.in();
		if(name.length()>0)
		{
			this.currentSurvey = new Survey(name);
			questionMenu();	
		}
		else
		{
			out.print("Must name Survey!");
			createNewSurvey();
		}
		
	}
	//creates new test object
	private void createNewTest()
	{
		String name;
		out.print("Please give the name of the survey or test:");
		name = input.in();
		if(name.length()>0)
		{
			this.currentSurvey = new Test(name);
			questionMenu();	
		}
		else
		{
			out.print("Must name Test!");
			createNewTest();
		}

	}
	//menu for adding questions
	public void questionMenu()
	{
		String qchoice;
		out.print("Add questions: ");
	
		for(int i=0; i<QuestionsMenu.length;i++)
		{
			out.print(QuestionsMenu[i]);
		}
		qchoice = input.in();
		switch (qchoice)
		{
		case "1":
			this.currentSurvey.addQuestion("MP");
			questionMenu();
			break;
		case "2":
			this.currentSurvey.addQuestion("TF");
			questionMenu();
			break;
		case "3":
			this.currentSurvey.addQuestion("SA");
			questionMenu();
			break;
		case "4":
			this.currentSurvey.addQuestion("Essay");
			questionMenu();
			break;
		case "5":
			this.currentSurvey.addQuestion("Ranking");
			questionMenu();
			break;
		case "6":
			this.currentSurvey.addQuestion("Matching");
			questionMenu();
			break;
		case "7":
			if(this.type.equals("S"))
				displaySurveyMenu();
			
			else if(this.type.equals("T"))
				displayTestMenu();
			break;
		default:
			out.print("Not a given option");
			questionMenu();
		
	}
	}
	//serialization methods
	protected void save(String filename)
	{
		ObjectOutputStream outS;
		try 
		{
			outS = new ObjectOutputStream(new FileOutputStream(filename));
			outS.writeObject(this.currentSurvey);
			outS.close();
			out.print("Saved current survey/test!");
			if(type.equals("T"))
			{
				displayTestMenu();
			}
			else if(type.equals("S"))
			{
				displaySurveyMenu();
			}
		} 
		catch (IOException e) 
		{
			out.print("Improper input!\n");
			if(type.equals("T"))
			{
				displayTestMenu();
			}
			else if(type.equals("S"))
			{
				displaySurveyMenu();
			}
		}
	}
	protected void load(String filename, String type)
	{
		try 
		{
			ObjectInputStream inS = new ObjectInputStream(new FileInputStream (filename));
			this.currentSurvey = (Survey)inS.readObject();
			inS.close();
			//if you load a test file from survey menu or survey from test menu, it will switch to the proper menu
			if(this.currentSurvey.getClass().toString().contains("Test"))
			{
				displayTestMenu();
			}
			else if(this.currentSurvey.getClass().toString().contains("Survey"))
			{
				displaySurveyMenu();
			}
			
			
			
		}
		catch (IOException e) 
		{
			out.print("File error. Wrong name or corrupted file.");
			if(type.equals("T"))
			{
				displayTestMenu();
			}
			else if(type.equals("S"))
			{
				displaySurveyMenu();
			}
		} 
		catch (ClassNotFoundException e) {
			out.print("File not found. Please try again\n");
			if(type.equals("T"))
			{
				displayTestMenu();
			}
			else if(type.equals("S"))
			{
				displaySurveyMenu();
			}
		}
		
	}

}
