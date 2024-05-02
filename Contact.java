public class Contact 
{ 
    private String name,phoneNumber,email,address,birthday,notes;


    //needed for 5 methods in phonebook
    public Contact(){}

    public Contact(String name,String phoneNumber, String email, String address, String birthday, String notes)
    {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.address = address;
        this.birthday = birthday;
        this.notes = notes;
    }
    
   public void printContactInfo()
   {
    System.out.println("Name:" + name);
    System.out.println("Phone Number:" + phoneNumber);
    System.out.println("Email address:" + email);
    System.out.println("Address:" + address);
    System.out.println("Birthday:" + birthday);
    System.out.println("Notes:" + notes);
   }

    public String getName()
    {
        return name;
    }

    public String getphoneNumber()
    {
        return phoneNumber;
    }

    public String getEmail()
    {
        return email;
    }

    public String getAddress()
    {
        return address;
    }

    public String getBirthday()
    {
        return birthday;
    }

    public String getNotes()
    {
        return notes;
    }

     
    public int compareTo(Object o) {
        Contact contact2 = (Contact) o;
        //get the min of length of the two strings to use as boundry in for loop
        int len1 = getName().replaceAll("\\s", "").length();
        int len2 = contact2.getName().replaceAll("\\s", "").length();
        int min = Math.min(len1, len2);

        //store strings as char array to iterate over the characters
        char[] ch1 = getName().toLowerCase().replaceAll("\\s", "").toCharArray();
        char[] ch2 = contact2.getName().toLowerCase().replaceAll("\\s", "").toCharArray();

        for(int i = 0; i<min; i++)
        {
            char c1 = ch1[i];
            char c2 = ch2[i];

            //if this object string is coming later in the alphabetic return 1
            if(c1 > c2)
                return 1;
             //if second string is coming first in the alphabetic return -1
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

