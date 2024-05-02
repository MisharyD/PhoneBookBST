public class BST
{
    public class BSTNode 
    {
        public String key;
        public Contact data;
        public BSTNode left, right;
        
        /** Creates a new instance of BSTNode */
        public BSTNode(String k, Contact val) {
            key = k;
            data = val;
            left = right = null;
        }
        
        public BSTNode(String k, Contact val, BSTNode l, BSTNode r) {
            key = k;
            data = val;
            left = l;
            right = r;
        }
    }
    public enum Relative {Root, Parent, LeftChild, RightChild};

	BSTNode root, current;
	
	/** Creates a new instance of BST */
	public BST() {
		root = current = null;
	}
	
	public boolean empty() {
		return root == null;
	}
	
	public boolean full() {
		return false;
	}
	
	public Contact retrieve () {
		return current.data;
	}

    private BSTNode findparent(BSTNode p, BSTNode t) {
		// Stack is used to store the right pointers of nodes
		LinkedStack<BSTNode> stack = new LinkedStack<BSTNode>();
		BSTNode q = t;
		while(q.right != p && q.left != p) {
			if(q.right != null)
				stack.push(q.right);
			
			if(q.left != null)
				q = q.left;
			else
				q = stack.pop(); // Go right here
		}
		return q;
	}

    public boolean find(Relative rel){
		switch (rel) {
		   case Root:	// Easy case
			current = root;
			return true;
		   case Parent:
			if(current == root) return false;
			current = findparent(current, root);
			return true;
		   case LeftChild:
			if(current.left == null) return false;
			current = current.left;
			return true;
		   case RightChild:
			if(current.right == null) return false;
			current = current.right;
			return true;
		   default:
			return false;
		}
	}

    public void deleteSubtree(){
		if(current == root){
			current = root = null;
		}
		else {
			BSTNode p = current;
			find(Relative.Parent);
			if(current.left == p)
				current.left = null;
			else 
				current.right = null;
			current = root;
		}
	}

    public boolean findkey(String key) {
		BSTNode p = root, q = root;
				
		if(empty())
			return false;
		
		Contact pContact = p.data;
		while(p != null) {
			q = p;
			pContact = p.data;
			int result = compareTo(pContact.getName(), key);
			if(result == 0) {
				current = p;
				return true;
			}
			else if(result == 1)
				p = p.left;
			else
				p = p.right;
		}
		
		current = q;
		return false;
	}

	public boolean insertContact(String key,Contact data)
	{
		BSTNode p, q = current;
		
		if(findkey(key)) {
			current = q;  // findkey() modified current
			return false; // key already in the BST
		}
		
		p = new BSTNode(key, data);
		if (empty()) {
			root = current = p;
			return true;
		}
		else {
			Contact currentContact = current.data; 
			int result = compareTo(currentContact.getName(),key);
			//current is pointing to parent of the new key
			if (result == 1)
				current.left = p;
			else
				current.right = p;
			current = p;
			return true;
		}
	}
																								
    public boolean removeKey(String key) 
    {  
        BSTNode p = root; 
        BSTNode q = null; // Parent of p 
        while (p != null) 
        {
			int result = compareTo(p.key, key); 
            if(result == 1) 
            {
                q = p;     
                p = p.left;
            }
            else if(result == -1) 
            {   
                q = p;      
                p = p.right;
            }
            else //Found the key, Check the three cases   
            {   
                if((p.left != null) && (p.right != null)) 
                {
                    // Case 3: search for min in the right subtree       
                    BSTNode min = p.right;       
                    q = p;
                    while (min.left != null) 
                    {          
                        q = min;        
                        min = min.left;      
                    }
                    p.key = min.key;        
                    p.data = min.data;      
                    String k1 = min.key;        
                    p = min;
                }
                // Now fall back to either case 1 or 2
                // The subtree rooted at p will change here
                if(p.left != null) // One child       
                    p = p.left;
                else // One or no children        
                    p = p.right;
                if (q == null)//No parent for p, root must change       
                    root = p;
                else
				{
					result = compareTo(q.key, key);       
                	if (result == 1)           
                        q.left = p;         
                    else            
                    q.right = p;
				}
                current = root;      
                return true;
            }
        }
        return false;
    }

 	public LinkedListADT<Contact> searchContact(String string, int nb)
	{
		LinkedListADT<Contact> contactList = new LinkedListADT<Contact>();
		search(root, contactList, string, nb);
		return contactList;
	}

	//1 for name 2 for phonenmber 3 for email 4 for address 5,for birthday, 6 for first names,inOrder travesral for 2,3,4,5,6
 	private void search(BSTNode root,LinkedListADT<Contact> contactList, String string,int nb)
	{
		switch(nb)
		{
			//search for name
			case 1:
			{
				//if name exists in tree, insert it in the linked list, other wise just return
				if(findkey(string))
				{
					contactList.insert(current.data);
					return;
				}
			}

			//search for phoneNumber
			case 2:
			{
				//if the list is not empty it means we found the contact with the phonenumber, since the phonenumber is unique stop searching
				if(!contactList.empty())
					return;

				//search using inOrder 
				if(root != null)
				{
					search(root.left,contactList,string,nb);

					if(root.data.getphoneNumber().replaceAll("\\s", "").equals(string.replaceAll("\\s", "")))
						contactList.insert(root.data);

					search(root.right,contactList,string,nb);
				}
				break;
			}

			//search for email
			case 3:
			{
				if(root != null)
				{
					search(root.left,contactList,string,nb);

					if(root.data.getEmail().replaceAll("\\s", "").toLowerCase().equals(string.replaceAll("\\s", "").toLowerCase()))
						contactList.insert(root.data);

					search(root.right,contactList,string,nb);
				}
				break;
			}

			//search for address
			case 4:
			{
				if(root != null)
				{
					search(root.left,contactList,string,nb);

					if(root.data.getAddress().replaceAll("\\s", "").toLowerCase().equals(string.replaceAll("\\s", "").toLowerCase()))
						contactList.insert(root.data);
						
					search(root.right,contactList,string,nb);
				}
				break;
			}

			//search for birthday
			case 5:
			{
				if(root != null)
				{
					search(root.left,contactList,string,nb);

					if(root.data.getBirthday().replaceAll("\\s", "").toLowerCase().equals(string.replaceAll("\\s", "").toLowerCase()))
						contactList.insert(root.data);
						
					search(root.right,contactList,string,nb);
				}
				break;
			}

			//search for first name only
			case 6:
			{
				if(root != null)
				{
					search(root.left,contactList,string,nb);

					String[] firstName = (root.data).getName().toLowerCase().stripLeading().split(" ");
					if(firstName[0].equals(string.toLowerCase().replaceAll("\\s", "")))
						contactList.insert(root.data);
						
					search(root.right,contactList,string,nb);
				}
				break;
			}
		}
	}

	public int compareTo(String s1,String s2) {
       
        //get the min of length of the two strings to use as boundry in for loop
        int len1 = s1.replaceAll("\\s", "").length();
        int len2 = s2.replaceAll("\\s", "").length();
        int min = Math.min(len1, len2);

        //store strings as char array to iterate over the characters
        char[] ch1 = s1.toLowerCase().replaceAll("\\s", "").toCharArray();
        char[] ch2 = s2.toLowerCase().replaceAll("\\s", "").toCharArray();

        for(int i = 0; i<min; i++)
        {
            char c1 = ch1[i];
            char c2 = ch2[i];

            //if s1 is coming later in the alphabetic return 1
            if(c1 > c2)
                return 1;
             //if s2 coming later in the alphabetic return -1
            else if(c2 > c1)
                return -1;
        }
		if(len1 > len2)//example: s1 = zz, s2 = z
			return 1;
		else if(len2 > len1)//example: s1 = z, s2 = zz
			return -1;
		//if they are the same word return 0
		else
        	return 0;
    }

    
}

