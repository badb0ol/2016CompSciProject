public class Student
{
    int id;
    String name;
    boolean ibdp;
    
    public Student()
    {
        
    }
    
    public Student(int id, String name, boolean ibdp)
    {
        this.id = id;
        this.name = name;
        this.ibdp = ibdp;
    }
    
    public int getId() //accessor method
    {
        return this.id;
    }
    
    public String getName()
    {
        return this.name;
    }
    
    public String getIbdp()
    {
        String output = "";
        if(this.ibdp == true)
            return "Yes";
        else
            return "No";
    }
    
    public void setId(int id)
    {
        if (id >= 0)       // validating input
            this.id = id;
        else
            this.id = id * -1;
    }
    
    public void setName(String name)
    {
        this.name = name;
    }
    
    public void setIbdp(boolean ibdp)
    {
        this.ibdp = ibdp;
    }
    
    public String toString() // Polymorphism
    {
        String output = "";
        return "Student: " + getName() + "\n" + "ID number is: " + getId() + "\n" + "Does " + getName() + " take IB Diploma? " + getIbdp() + "\n";
    }
}