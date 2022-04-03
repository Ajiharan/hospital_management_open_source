package link1;

public class LinkX {
	public LinkX next;
        public LinkX preview;
	public User employee;
	
	
	public LinkX(User current_employee) {
		next=null;
                preview=null;
		this.employee=current_employee;
	}
	
	public void display()  {
		
		System.out.println(employee);
		
		
	}

   

}
