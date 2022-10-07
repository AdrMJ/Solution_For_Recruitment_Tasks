public class V_Quotes{
	private Node head = new Node(null); 
	private int size; 
	
	public V_Quotes(){
		clear();
	}
	public void clear(){
		head.setNext(null);
		size=0;
	}
	public void add(Object value){
		if (head.getNext()==null) head.setNext(new Node(value)); 
		Node last = head.getNext();
		while(last.getNext() != null) 
			last=last.getNext();
		++size;
		last.setNext(new Node(value));
	}
	public boolean delete(Object o){
		if(head.getNext() == null) return false;
		if(head.getNext().getValue().equals(o)){
			head.setNext(head.getNext().getNext());
			size--;
			return true;
		}

		Node delete = head.getNext();
		while(delete != null && delete.getNext() != null){
			if(delete.getNext().getValue().equals(o)){
				delete.setNext(delete.getNext().getNext());
                                size--;
				return true;
			}
			delete = delete.getNext();
		}
		return false;
	}
	
	public Object get(int index) throws IndexOutOfBoundsException{
		if(index<0 || index>size) throw new IndexOutOfBoundsException();
		Node find = head.getNext();
		for(int i=0; i <= index; i++)
			find = find.getNext();
		return find.getValue();
	}
	public Object set(int index, Object value) throws IndexOutOfBoundsException{
		if(index<0 || index>size) throw new IndexOutOfBoundsException();
		Node find = head.getNext();
		for(int i=0; i <= index; i++)
			find = find.getNext();
		find.setValue(value);
		return value;
	}
	
	public int size(){
		return size;
	}
	
	public boolean isEmpty(){
		return size == 0;
	}

	
	private static final class Node{
		private Object value; 
		private Node next; 
		
		public Node(Object val){
			this(val, null);
		}
		
		public Node(Object val, Node n){
			value = val;
			next = n;
		}
		
		public Object getValue(){
			return value;
		}
		
		public Node getNext(){
			return next;
		}
		
		public void setNext(Node n){
			next = n;
		}

		public void setValue(Object o){
			value = o;
		}
    }
}