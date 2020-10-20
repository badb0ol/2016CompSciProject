import java.io.*;
import java.util.*;
import java.util.GregorianCalendar; // ggwp

public class IAProject
{
    static ArrayList<Employee> employees = new ArrayList<Employee>();
    static File employeesFile = new File("employees.txt");
    static Calendar cal = new GregorianCalendar(); // previous employees stored there
         
    public static void main(String args[]) throws IOException
    {   boolean continues = true;
    	load();
    	do
        {
            output("\n\t==== MENU ====");
            output("\nWelcome to Delta Energies!");
            output("\nThe date today is " + cal.getTime());
            output("\n[a] Add employees");
            output("[l] List all employees");
            output("[s] Sort all employees");
            output("[i] Search employee by ID");
            output("[n] Search employee by name");
            output("[q] Quit");
            char decision = inputChar("\n\tEnter one letter for your option: ");
            decision = Character.toLowerCase(decision); 
            output("");
            switch(decision)
            {    
                case 'a': 
                    add(); 
                    break;
                case 'l':
                    if(employees.size() == 0)
                        output("No employees on file, please add employees.");
                    else
                        list();
                    break;
                case 's':
                	sort();
                	break;
                case 'i':
                	searchByID();
                	break;
                case 'n':
                	search();
                	break;
                case 'q':
                    continues = false;
                    save();
                    break;
                default:
                    output("Wrong choice.");
                    break;
             }
        }   while (continues); 
         
  
    } // END MAIN
 
    static void list()
    {   output("=== Listing employees ==="); 
        for(Employee s: employees)
            System.out.println( "   > " + s + "\n");
    }

    static void add() throws IOException
    {   output("=== Adding employees ===");
        int newID = 0;
        String newName = ""; // declare and initialize data for new employee
        double newSalary = 0;
        

		do
		{	newID = inputInt("\nEnter employee id: ");
			if( foundID(newID) )
				output("Please input a unique, valid ID");
			else if(newID < 1)
				output("Please input a unique, valid integer");
		} while( foundID(newID) || newID < 1);
		
		do
		{  	newName = inputString("\nEnter a new employees name: ");
			if( newName.length() < 2 )
				output("Please input a valid, full name.");
			else if( !isAlpha(newName))
				output("Please enter a name that does not contain integers.");
		} while( newName.length() < 2 || !isAlpha(newName));
		
		do
		{	newSalary = inputDouble("\nEnter employee salary: ");
			if (newSalary < 1000)
				output("Please input a valid, monthly salary above $1k");
		} while ( newSalary < 1000 );

		Employee newEmployee = new Employee(newName, newID, newSalary);
		employees.add(newEmployee);
        sort();
        list();
        save();
    }

    static void search() throws IOException
    {   boolean found = false;
    	String name = inputString("Search for which employees name: ");
        if(name.length() != 0)
        {	name = name.toLowerCase();
			for(int j = 0; j < employees.size(); j++)
			{   String tempName = employees.get(j).getName().toLowerCase();
				if( tempName.startsWith(name) ) 
				{	found = true;
					System.out.println( employees.get(j) );
					out( "[e]- edit it  [d]- delete it  [Enter]-next hit");
				    char decision = inputChar(" --> Your choice? ");
            		decision = Character.toLowerCase(decision);
            		switch(decision)
            		{	case 'e':
            				edit(j);	
            				break;
            			case 'd':
            				delete(j);	
            				break;
            			default:
            				break;
            		}
				}
			}
		}
        
		else
		{	output( "Error - no name was input.");
		}
		
		if( !found )
		{	output( name + " not found in the system.");
		}
    }

    static boolean isAlpha(String name) {
        return name.matches("[a-zA-Z]+");
    }
    
	static boolean foundID(int id)
	{
		boolean found = false;
		for(int i = 0; i < employees.size(); i++)
		{	if( employees.get(i).getID() == id )
			{	found = true;
				break;
			}
		}
		return found;
	}
	
	static boolean found(String name)
	{
		boolean found = false;
		name = name.toLowerCase();
		for(Employee s: employees)
		{	String tempName = s.getName().toLowerCase();
			if( tempName.startsWith(name) )
			{	found = true;
				break;
			}
		}
		return found;
	}
	
    static void searchByID() throws IOException
    {
    	int id;
    	boolean found = false;
    	do
    	{	id = inputInt("Enter a employee's id number you want to search: ");
        	if( id > 0)
        	{	for(int j = 0; j < employees.size(); j++)
				{   if( employees.get(j).getID() == id ) 
					{	found = true;
						System.out.println( employees.get(j) );						
						out( "[e]-edit it  [d]-delete it");
						char decision = inputChar(" --> Your choice? ");
						decision = Character.toLowerCase(decision);
						switch(decision)
						{	case 'e':
								edit(j);	break;
							case 'd':
								delete(j);	break;
							default:
								break;
						}
					}
				}
			}
			else
				System.out.println("Invalid id. Please re-enter.");
		} while( id <= 0);
    	
		if( !found )
		{	output( "The ID " + id + " was not found in the system.");
		}
    }

    static void delete(int indexToDelete) throws IOException
    {	char decision = inputChar("Do you really want to delete (y/n)? ");
		decision = Character.toLowerCase(decision);
		if( decision == 'y' )
		{	employees.remove( indexToDelete );
			list();
			save();
		} else
		{	output("No changes made.");
		}
    }

    static void load() throws IOException
    {   output("Loading employees...");
        if( !employeesFile.exists() )
        {	employeesFile.createNewFile();
        	output("\n=== Employees file not found. Creating one. ===");
        	add();
        	return;
        }
        FileReader      fr = new FileReader(employeesFile);
        BufferedReader  in = new BufferedReader(fr);
        int newID = 0;
        String newName = "";
        double newSalary = 0;
        int newLeave = 0;
        String temp;
        
        while ( in.ready() ) // reads while there is content on the file
        {   
        	temp = in.readLine();
        	newName = temp;
        	temp = in.readLine();
        	newID = Integer.parseInt(temp);
        	temp = in.readLine();
        	newSalary = Double.parseDouble(temp);
        	temp = in.readLine();
        	newLeave = Integer.parseInt(temp);
			Employee newEmployee = new Employee(newName, newID, newSalary, newLeave);
			employees.add(newEmployee);
        }
        in.close();
        output("\n==== Employees file loaded. ====");
    }

    static void save() throws IOException
    {   output("Saving employees...");
    	FileWriter  fw  = new FileWriter(employeesFile);	
        PrintWriter out = new PrintWriter(fw);	
        for (int i = 0; i < employees.size(); i++)  // saves each element of the array to the file
        {	out.println( employees.get(i).getName() );
        	out.println( employees.get(i).getID() );
        	out.println( employees.get(i).getSalary() );
        	out.println( employees.get(i).getLeave());
        }
        out.close();
        output("\n=== Employees file saved ===");
    }

    static void edit(int indexToEdit) throws IOException
    {
    	String newName = "";
    	double newSalary = 0;
    	int newLeave = 0;
    	boolean error = false;
        char decision = inputChar("Found. Do you really want to edit (y/n)? ");
		decision = Character.toLowerCase(decision);
		if( decision=='y' )
		{	System.out.println("Enter the correct employee data: \n");
			do
			{	newName = input("Enter correct name: ");
				if( !found(newName) )
				{	
					System.out.println("Error - employee name already exists");
					error = true;
					continue;
				}
				else
					employees.get(indexToEdit).setName(newName);
			} while( error );
			
			do
			{	newSalary = inputDouble("\nEnter employee's new salary: ");
					if (newSalary < 1000)
					output("Please input a valid, monthly salary above $1k");
					employees.get(indexToEdit).setSalary(newSalary);
			} while ( newSalary < 1000 );
			
			do
			{
				newLeave = inputInt("\nEnter amount of employee's leave left: ");
				if (newLeave < 5)
					output("Employee has below 5 days of leave left!");
				else if (newLeave < 1)
					output("Employee has 1 day of leave left");
				else if(newLeave < 0)
					output("Employee has no leave left");
				else
					output("Changes have been saved");
					employees.get(indexToEdit).setLeave(newLeave);
			} while(newLeave < -1);
		}		
		sort();
		list();
		save();
    }
    
	static void sort()
	{		
		for(int i = 0; i < employees.size(); i++)
		{
			for(int j = i + 1; j < employees.size(); j++)
			{
				if(employees.get(i).compareTo(employees.get(j))<0)
				{	Employee temp = employees.get(i);
					employees.set(i, employees.get(j));
					employees.set(j, temp );
				}
			}
		}
		final String ANSI_CLS = "\u001b[2J";
	    final String ANSI_HOME = "\u001b[H";
	    System.out.print(ANSI_CLS + ANSI_HOME);
	    System.out.flush();
		System.out.println("Employees sorted!");
	}

// -IBIO--IBIO--IBIO--IBIO--IBIO--IBIO--IBIO--IBIO--IBIO--IBIO--IBIO--IBIO-
    static void out(String info)
    {   System.out.print(info);
    }
 
    static void out(char info)
    {   System.out.print(info);
    }
 
    static void out(byte info)
    {   System.out.print(info);
    }
 
    static void out(int info)
    {   System.out.print(info);
    }
 
    static void out(long info)
    {   System.out.print(info);
    }
 
    static void out(double info)
    {   System.out.print(info);
    }
 
    static void out(boolean info)
    {   System.out.print(info);
    }
 
    static void output(String info)
    {   System.out.println(info);
    }
 
    static void output(char info)
    {   System.out.println(info);
    }
 
    static void output(byte info)
    {   System.out.println(info);
    }
 
    static void output(int info)
    {   System.out.println(info);
    }
 
    static void output(long info)
    {   System.out.println(info);
    }
 
    static void output(double info)
    {   System.out.println(info);
    }
 
    static void output(boolean info)
    {   System.out.println(info);
    }
 
    static String input(String prompt)
    {   String inputLine = "";
        System.out.print(prompt);
        try
        {   inputLine = (new java.io.BufferedReader(
                new java.io.InputStreamReader(System.in))).readLine();}
        catch (Exception e)
        {   String err = e.toString();
            System.out.println(err);
            inputLine = "";
        }
        return inputLine;
    }
 
    static String inputString(String prompt)
    {   return input(prompt);
    }
 
    static String input()
    {   return input("");
    }
 
    static int inputInt()
    {   return inputInt(""); }
 
    static double inputDouble()
    {   return inputDouble(""); }
 
    static char inputChar(String prompt)
    {   char result=(char)0;
        try{result=input(prompt).charAt(0);}
        catch (Exception e){result = (char)0;}
        return result;
    }
 
    static byte inputByte(String prompt)
    {   byte result=0;
        try{result=Byte.valueOf(input(prompt).trim()).byteValue();}
        catch (Exception e){result = 0;}
        return result;
    }
 
    static int inputInt(String prompt)
    {   int result=0;
        try{result=Integer.valueOf(
                input(prompt).trim()).intValue();}
        catch (Exception e){result = 0;}
        return result;
    }
 
    static long inputLong(String prompt)
    {   long result=0;
        try{result=Long.valueOf(input(prompt).trim()).longValue();}
        catch (Exception e){result = 0;}
        return result;
    }
 
    static double inputDouble(String prompt)
    {   double result=0;
        try{result=Double.valueOf(
                input(prompt).trim()).doubleValue();}
        catch (Exception e){result = 0;}
        return result;
    }
 
    static boolean inputBoolean(String prompt)
    {   boolean result=false;
        try{result=Boolean.valueOf(
                input(prompt).trim()).booleanValue();}
        catch (Exception e){result = false;}
        return result;
    } // end IBIO class
} // end class
