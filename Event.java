public class Event implements Comparable 
{

    private Contact c;
    private String title,dateAndtime,location;
    private boolean event;
    private LinkedListADT<Contact> listOfContacts;

    //for an apoointment
    public Event(Contact c, String title,String dateAndtime, String location)
    {
        this.c = c;
        this.title = title;
        this.dateAndtime = dateAndtime;
        this.location = location;
    
        event = false;     
    }

    public Event(LinkedListADT<Contact> listOfContacts, String title,String dateAndtime, String location)
    {
        this.title = title;
        this.dateAndtime = dateAndtime;
        this.location = location;

        event = true;

        this.listOfContacts = new LinkedListADT<Contact>();
        listOfContacts.findfirst();
        while(!listOfContacts.last())
        {
            this.listOfContacts.insert(listOfContacts.retrieve());
            listOfContacts.findnext();
        }
        this.listOfContacts.insert(listOfContacts.retrieve());
        
       
    }
    
    public void printEventInfo()
    {
        System.out.println("Event title:" + title);
        if(isEvent())
        {
            System.out.println("Contacts names:");
            listOfContacts.findfirst();
            while(!listOfContacts.last())
            {
                System.out.print(listOfContacts.retrieve().getName() + ", ");
                listOfContacts.findnext();
            }
            System.out.println(listOfContacts.retrieve().getName());
        }
        else
            System.out.println("Contact name:" + c.getName());
        System.out.println("Event date and time:" + dateAndtime);
        System.out.println("Event location:" + location);
       
    }

    public Contact getContact()
    {
        return c;
    }

    public String getTitle()
    {
        return title;
    }

    public String getDateAndtime()
    {
        return dateAndtime;
    }

    public String getLocation()
    {
        return location;
    }

    public boolean isEvent()
    {
        return event == true;
    }

    public LinkedListADT<Contact> getListOfContacts()
    {
        return listOfContacts;
    }
    @Override
    public int compareTo(Object o) {
        Event e2 = (Event) o;
       //get the min of length of the two strings to use as boundry in for loop
        int len1 = getTitle().replaceAll("\\s", "").length();
        int len2 = e2.getTitle().replaceAll("\\s", "").length();
        int min = Math.min(len1, len2);

        //store strings as char array to iterate over the characters
        char[] ch1 = getTitle().toLowerCase().replaceAll("\\s", "").toCharArray();
        char[] ch2 = e2.getTitle().toLowerCase().replaceAll("\\s", "").toCharArray();

        for(int i = 0; i<min; i++)
        {
            char c1 = ch1[i];
            char c2 = ch2[i];

            //if this object string is coming later in the alphabetic return 1
            if(c1 > c2)
                return 1;
             //if seccond string is coming first in the alphabetic return -1
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
