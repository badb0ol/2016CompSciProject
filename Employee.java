public class Employee implements Comparable<Employee>
{	private int id;
	private String name;
	private double salary;
	private int leave;

	public Employee()
	{	}

	public Employee(String name, int id, double salary)
	{	this.name = name;
		this.id = id;
		this.salary = salary;
		this.setLeave(20);
	}
	
	public Employee(String name, int id, double salary, int leave)
	{	this.name = name;
		this.id = id;
		this.salary = salary;
		this.setLeave(leave);
	}

	public int getID()
	{	return this.id;
	}

	public String getName()
	{	return this.name;
	}

	public double getSalary()
	{	return this.salary;
	}

	public int getLeave() 
	{	return leave;
	}
	
	public void setID(int id)
	{	this.id = id;
	}
	
	public void setName(String name)
	{	this.name = name;
	}

	public void setSalary(double salary)
	{	this.salary = salary;
	}
	
	public void setLeave(int leave)
	{	this.leave = leave;
	}
	
	public int compareTo(Employee employee)
	{	String employee1 = this.name;
		String employee2 = employee.getName();
		return employee1.compareTo(employee2);
	}
	
	public String toString()
	{	String output = "ID: " + this.id + "\tName: " + this.name + "\tSalary: $"+ this.salary + "\tLeave left: " + this.leave;
		return output;
	}
}

