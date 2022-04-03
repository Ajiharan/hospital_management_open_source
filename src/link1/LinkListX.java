package link1;

public class LinkListX {
	private LinkX first;
	private LinkX ncurrent;
        private int cal_count;
	public LinkListX() {
		first=null;
                ncurrent=null;
                cal_count=0;
	}
	
	public LinkX search(String id) {
		LinkX current=first;
		while(current != null) {
			if(current.employee.getiData().equals(id)) {
				return current;
			}
			current=current.next;
		}
		return null;
	}
        
        public LinkX getFirst(){
          
            return ncurrent;
        }
	
	public void insertLast(User currrent_employee) {
		
		if(first==null) {
			insertFirst(currrent_employee);
		}
		else {
			LinkX newlink=new LinkX(currrent_employee);
			//newlink.preview=first;
			LinkX current=first;
			LinkX prev=first;
			while(current !=null) {
				
				prev=current;
				current=current.next;
			}
                        newlink.preview=prev;
			
			prev.next=newlink;
		}
	}
	
	/*public LinkX insert(int id,String name,String password,int data) {
		LinkX current=first;
	
		while(current !=null) {
			if(current.iData==id) {
				LinkX newlink=new LinkX(data,name,password);
				newlink.next=current.next;
				current.next=newlink;
				return current;
					
			}
			else {
				current=current.next;
			}
			
		}
		return null;
	}*/
	
	public LinkX delete(String id) {
		LinkX current=first;
		LinkX prev=first;
		while(current !=null) {
			if(current.employee.getiData()==id) {
				if(current == prev) {
					
                                    if(current.next != null){
                                        current.next.preview=null;
                                    }
                                    
                                    else{
                                        current.preview=null;
                                    }
                                    
					first=first.next;
					
				}
				else {
                                        if(current.next !=null){
                                             current.next.preview=prev;
                                        
                                        }
                                      
                                       
					prev.next=current.next;
                                        
				}
				return current;
			}
			else {
				prev=current;
				current=current.next;
			}
		}
		return null;
	}
	
	public void insertFirst(User current_employee) {
		LinkX newlink=new LinkX(current_employee);
		
		newlink.next=first;
		first=newlink;
                if(cal_count == 0){
                 ncurrent=first;
                 cal_count++;
                }
	}
	public void displayList() {
		LinkX current=first;
		
		while(current !=null) {
			System.out.print(current.employee.toString());
			current=current.next;
		}
	}
	
	public void deleteFirst() {
		first=first.next;
	}
	
	public LinkX deleteLast() {
		LinkX current=first;
		LinkX prev=first;
		
		while(current.next != null) {
			
			prev=current;
			current=current.next;
		}
		if(current==prev) {
			
			first=null;
			return prev;
			
		}
		else {
			prev.next=null;
			return current;
		}
		
	}
	
	public boolean isEmpty() {
		if(first == null) {
			return true;
		}
		return false;
	}

}
