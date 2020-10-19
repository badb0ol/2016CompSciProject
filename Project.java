public class Project1 extends IBIO 
{
    static final int MAXSTAFF = 100; // maximum number of staff to handle
    static String[]  Staff = new String[MAXSTAFF]; //to hold the names
    static int roll = 0; //number of staff added
    static File staffFile = new File("staff.txt");
    
    static void list()
    {
        output("<< Listing the Staff >>");
        for(int i = 0; i < roll; i++)
        {
            output( Staff[i] );
        }
    }

    static void add()
    {
        output("<< Adding Staff >>");
        String name = input("Enter new staff name: ");
        Staff[roll] = name;
        roll++;
    }

    static void delete()
    {
        output("<< Deleting Staff >>");
        int delete = search();
        if (delete == -1)
            output("Name not found.");
        else
    {   char decision = inputChar("Found. Do you really want to delete (y/n)? ");
        decision = Character.toLowerCase(decision);
        if (decision == 'y')
        {   String[] temp = new String[MAXSTAFF];
            int j = 0; // index of (temp) new array
            for(int i = 0; i < Staff.length; i++)
            {   if (i != delete)
            {   temp[j]= Staff[i];
                j++;
            }
            } // end for
            Staff = temp;
            roll--;
        }
        output("Staff deleted.");
    }
        return;
    }

    static void edit()
    {
        output("<< Editing Staff >>");
        int edit = search();
        if(edit == -1)
            output("name not found");
        else
        {   char decision = inputChar("Found. Do you really want to edit (y/n)? ");
            decision = Character.toLowerCase(decision);
            if (decision == 'y')
    {   String name = input("Enter the correct staff name: ");
        Staff[edit] = name;
            }
        }
    }

    static int search()
    {   String name = input("Enter a staff name you want to search: ");
        if (name.length() != 0)
        {
            for(int j = 0; j < roll; j++)
            {   if(Staff[j].startsWith(name) )
                return j;
            }
        }
        return -1;
    }

    static void load() throws IOException
    {
    output("<< Loading Staff File... File loaded successfully! >>");
    if( !staffFile.exists() )
    {   staffFile.createNewFile();
    output("Staff file not found. Creating one.");
    // add();
    return;
    }

    FileReader fr = new FileReader(staffFile);
    BufferedReader in = new BufferedReader(fr);

    roll = 0;
    while(in.ready())
    {
    Staff[roll] = in.readLine();
    roll++;
    }
    in.close();
    }

    static void save() throws IOException
    {
    output("\n" + "<< Saving Staff File... File saved! >>");
    File staffFile = new File("staff.txt");
    FileWriter fw = new FileWriter(staffFile);
    PrintWriter out = new PrintWriter(fw);
    for (int i = 0; i < roll; i++)
    out.println(Staff[i]);            //out to the file
    out.close();
    }

    static void sort()
    {

//    	output("<< Sorting Staff... >>");
//        for (int i = 0; i < roll - 1; i++)
//        {
//            for (int j = 1; j< roll; j++)
//            {
//                if (Staff[i].compareTo(Staff[j]) > 0)
//                {
//                    String temp = Staff[i];
//                    Staff[i] = Staff[j];
//                    Staff[i] = temp;
//                }
//            }
//        }

        boolean swapped = true; // sort finished when no more swaps needed
        while (swapped)
        {   swapped = false;
            for ( int j = 0; j < roll - 1; j++)
            {
                if(Staff[j].compareToIgnoreCase(Staff[j+1]) > 0)
                {
                    String temp = Staff[j];
                    Staff[j] = Staff[j+1];
                    Staff[j+1] = temp;
                    swapped = true;
                }
            }
        }
        output("Staff sorted alphabetically!");

        // http://mathbits.com/MathBits/Java/arrays/ABCSort.htm
    }
}
