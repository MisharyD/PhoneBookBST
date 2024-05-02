import java.util.Scanner;

public class PhoneBookBST {
    private static BST contactBST = new BST();
    private static LinkedListADT<Event> eventList = new LinkedListADT<Event>();
    
    public static void main(String[] args)
    {
    
        Scanner sc = new Scanner(System.in);
        String choice;
        System.out.println("Welcome to the BST Phonebook!\n");

        //menu loop
        do
        {
            System.out.println("Please choose an option:");
            System.out.println("1.Add a contact \n2.Search for a contact \n3.Delete a contact \n4.Schedule an event \n" + 
            "5.Print event details\n6.Print contacts by first name \n7.Print all events alphabetically \n8.Exit\n");
            System.out.print("Enter your choice:");
            choice = sc.nextLine().replaceAll("\\s", "");

            //for visuals
            System.out.println();

            switch(choice)
            {
                case "1"://add contact
                {
                    //take contact info
                    System.out.print("Enter the contact's name:");
                    String name = sc.nextLine();

                    //check if name is unique
                    LinkedListADT<Contact> list = contactBST.searchContact(name,1);//only 1 or no contacts are in the list
                    if(!list.empty())
                    {
                        System.out.println("name already exists!\n");
                        break;
                    }

                    System.out.print("Enter the contact's phone number:");
                    String phoneNumber = sc.nextLine(); 

                    //check if phonenumber is unique
                    list = contactBST.searchContact(phoneNumber,2);//only 1 or no contacts are in the clist
                    if(!list.empty())
                    {
                        System.out.println("Phone number already exist\n");
                        break;
                    }

                    System.out.print("Enter the contact's email address:");
                    String email = sc.nextLine();

                    System.out.print("Enter the contact's address:");
                    String address = sc.nextLine();

                    //validate 
                    System.out.print("Enter the contact's birthday:");
                    String birthday = sc.nextLine();

                    System.out.print("Enter any notes for the contact:");
                    String notes = sc.nextLine();

                    //store contact in BST, lowering case is only done when comparing
                    Contact contact = new Contact(name,phoneNumber,email,address,birthday,notes);
                    contactBST.insertContact(name, contact);

                    //for visuals
                    System.out.println("\n");

                    System.out.println("Contact added successfully!\n");
                break;
                }
    
                case "2"://search for contact and print info
                {

                    //criteria for searching
                    System.out.println("Enter search criteria:\n" + 
                            "1.Name\n" + 
                            "2.Phone Number\n" + 
                            "3.Email Address\n" + 
                            "4.Address\n" + 
                            "5.Birthday\n");

                    System.out.print("Enter your choice:");
                    choice = sc.nextLine();
                    switch(choice)
                    {
                        //5 cases for every criteria
                        
                        //by name, returns only 1 contact if it exist
                        case"1":
                        {
                            System.out.print("Enter name:");
                            String name = sc.nextLine();

                            //check if contact exist
                            LinkedListADT<Contact> contactList = contactBST.searchContact(name,1);//only 1 or no contacts are in the contactlist 
                            if(contactList.empty())
                            {
                                System.out.println("Contact does not exist!\n");
                                break;
                            }
                            
                            //returns the contact with the same name
                            Contact contact = contactList.retrieve();

                            //print contact info that have the same name
                            System.out.println("Contact was found!\n");
                            contact.printContactInfo();

                            //for visuals
                            System.out.println();

                        break;
                        }

                        //by phone number, returns only 1 contact if it exist
                        case "2":
                        {
                            System.out.print("Enter phone number:");
                            String phoneNumber = sc.nextLine();

                            LinkedListADT<Contact> contactList = contactBST.searchContact(phoneNumber,2);//only 1 or no contacts are in the contactlist 
                            //check if contact was found
                            if(contactList.empty())
                            {   
                                System.out.println("Contact does not exist\n");
                                break;
                            }

                            //returns the contact with the same number
                            Contact contact = contactList.retrieve();

                            //print contact info that have the same phonenumber
                            System.out.println("Contact was found!\n");
                            contact.printContactInfo();

                            //for visuals
                            System.out.println();

                        break;
                        }

                        //by email 
                        case "3":
                        {
                            System.out.print("Enter email:");
                            String email = sc.nextLine();

                            //returns any contact with the same email
                            LinkedListADT<Contact> contactList = contactBST.searchContact(email,3);

                            //check if no contacts were found
                            if(contactList.empty())
                            {
                                System.out.println("No contacts were found!\n");
                                break;
                            }

                            System.out.println("A contact or contacts were found\n");

                            //print contacts info that have the same email
                            contactList.findfirst();
                            while(!contactList.last())
                            {
                                contactList.retrieve().printContactInfo();
                                contactList.findnext();
                                //for visuals
                                System.out.println();
                            }
                            //last node
                            contactList.retrieve().printContactInfo();

                            //for visuals
                            System.out.println();

                        break;  
                        }

                        //by address
                        case "4":
                        {
                            System.out.print("Enter address:");
                            String address = sc.nextLine();
                            //returns any contact with the same address
                            LinkedListADT<Contact> contactList = contactBST.searchContact(address,4);

                            //check if no contacts were found
                            if(contactList.empty())
                            {
                                System.out.println("No contacts were found!\n");
                                break;
                            }

                            System.out.println("A contact or contacts were found!\n");
                            //print contacts info that have the same address
                            contactList.findfirst();
                            while(!contactList.last())
                            {
                                contactList.retrieve().printContactInfo();
                                contactList.findnext();
                                //for visuals
                                System.out.println();
                            }
                            contactList.retrieve().printContactInfo();

                            //for visuals
                            System.out.println();

                        break;  
                        }

                        //by birthday
                        case "5":
                        {
                            System.out.print("Enter birthday:");
                            String birthday = sc.nextLine();
                            //returns any contact with the same birthday
                            LinkedListADT<Contact> contactList = contactBST.searchContact(birthday,5);

                            //check if no contacts were found
                            if(contactList.empty())
                            {
                                System.out.println("No contacts were found!\n");
                                break;
                            }

                            System.out.println("A contact or contacts were found!\n");
                            //print contacts info that have the same birthday
                            contactList.findfirst();
                            while(!contactList.last())
                            {
                                contactList.retrieve().printContactInfo();
                                contactList.findnext();
                                //for visuals
                                System.out.println();
                            }
                            contactList.retrieve().printContactInfo();

                            //for visuals
                            System.out.println();

                        break;  
                        }
                        default:
                            System.out.println("Wrong input!\n");
                    }
                break;
                }
                
                case "3"://delete contact
                {
                    System.out.println("Enter delete criteria:\n"+
                    "1.Name\n"+ "2.Phone number\n");
                    System.out.print("Enter your choice:");
                    choice = sc.nextLine();

                    switch(choice)
                    {
                        //delete using name or phonenumber

                        //name
                        case "1":
                        {
                            System.out.print("Enter name:");
                            String name = sc.nextLine();
                            //returns contact with the same name
                            LinkedListADT<Contact> contactList = contactBST.searchContact(name,1);//only 1 or no contacts are in the contactlist 
                            //check if contact exist
                            if(contactList.empty())
                            {
                                System.out.println("Contact was not found.\n");
                                break;
                            }

                            Contact contact = contactList.retrieve();//only 1 contact in the contactlist because name is unique 
                            //return all events that is associated with contact
                            LinkedListADT<Event> eList = searchEvent(name,1);
                            
                            //delete the contact from any events he is associated with
                            if(!eList.empty())
                            {
                                eList.findfirst();
                                while(!eList.last())
                                {
                                    Event event = eList.retrieve();
                                    //if event is of type event, only delete the contact from the contact list of the event
                                    if(event.isEvent())
                                    {
                                        LinkedListADT<Contact> listOfContacts = event.getListOfContacts();
                                        listOfContacts.remove(contact);
                                        //if it was the last contact delete the whole event
                                        if(listOfContacts.empty())
                                            eventList.remove(event);
                                    }
                                    //if event is of type appointment delete the whole event
                                    else
                                        eventList.remove(event);

                                    eList.findnext();
                                }
                                //last element in eList
                                Event event = eList.retrieve();
                                //if event is of type event, only delete the contact from the contact list of the event
                                if(event.isEvent())
                                {
                                    LinkedListADT<Contact> listOfContacts = event.getListOfContacts();
                                    listOfContacts.remove(contact);
                                    //if it was the last contact delete the whole event
                                    if(listOfContacts.empty())
                                    eventList.remove(event);
                                }
                                //if event is of type appointment delete the whole event
                                else
                                    eventList.remove(event);
                            }
                            else
                                System.out.println("no events associated with contact!");

                            //delete contact from the BST contacts
                            contactBST.removeKey(contact.getName()); 

                            System.out.println("Contact was deleted and any events associated with it.\n");
                            break;
                        }

                        //phone number
                        case "2":
                        {
                            System.out.print("Enter phone number:");
                            String phoneNumber = sc.nextLine();
                            //returns contact with the same phone number
                            LinkedListADT<Contact> contactlist = contactBST.searchContact(phoneNumber,2);//only 1 or no contacts are in the clist 
                            //check if contact exist
                            if(contactlist.empty())
                            {
                                System.out.println("Contact was not found.\n");
                                break;
                            }
                            
                            Contact contact = contactlist.retrieve();//only 1 contact in the clist because phonenumber is unique
                            //return all events that is associated with contact
                            LinkedListADT<Event> eList = searchEvent(contact.getName(),1);
                
                            //delete the contact from any events he is associated with
                            if(!eList.empty())
                            {
                                eList.findfirst();
                                while(!eList.last())
                                {
                                    Event event = eList.retrieve();
                                    //if event is of type event, only delete the contact from the contact list of the event
                                    if(event.isEvent())
                                    {
                                        LinkedListADT<Contact> listOfContacts = event.getListOfContacts();
                                        listOfContacts.remove(contact);
                                        //if it was the last contact delete the whole event
                                        if(listOfContacts.empty())
                                            eventList.remove(event);
                                    }
                                    //if event is of type appointment delete the whole event
                                    else
                                        eventList.remove(event);

                                    eList.findnext();
                                }
                                //last element in eList
                                Event event = eList.retrieve();
                                //if event is of type event, only delete the contact from the contact list of the event
                                if(event.isEvent())
                                {
                                    LinkedListADT<Contact> listOfContacts = event.getListOfContacts();
                                    listOfContacts.remove(contact);
                                    //if it was the last contact delete the whole event
                                    if(listOfContacts.empty())
                                        eventList.remove(event);
                                }
                                //if event is of type appointment delete the whole event
                                else
                                    eventList.remove(event);
                            }
                            else
                                System.out.println("no events associated with contact!");
                            //delete contact from the BST contacts
                            contactBST.removeKey(contact.getName()); 

                            System.out.println("Contact was deleted and any events associated with it\n");
                            break;
                        }
                        default:
                            System.out.println("Wrong input!\n");
                    }
                break;
                }
                
                case "4"://schedule event
                {
                    System.out.print("Event type:\n" +"1.Event\n" + "2.Appointment\n");
                    System.out.print("Enter your choice:");
                    String type = sc.nextLine();

                    System.out.print("Enter event title:");
                    String title = sc.nextLine();
                    switch(type)
                    {
                        case "1":
                        {
                            System.out.print("Enter contacts names splitted by a comma:");
                            //an array that contains all the names
                            String[] contactName = sc.nextLine().split(",");
                            LinkedListADT<Contact> contactList = new LinkedListADT<Contact>();

                            //loop through the contact names
                            for(int i = 0;i < contactName.length;i++)
                            {
                                //tmp list to store the contact with the name in it
                                LinkedListADT<Contact> tmpList = contactBST.searchContact(contactName[i],1);
                            
                                //if tmpList is empty it means the name in contactName[i] does not exist
                                if(tmpList.empty())
                                    System.out.println(contactName[i] + " does not exist!"); 
                                else
                                    contactList.insert(tmpList.retrieve());
                            }
                            
                            //check if any of the contact names provided exist
                            if(contactList.empty())
                            {
                                System.out.println("Contacts names does not exist!\n");
                                break;
                            }
                
                            //returns the event with the title
                            LinkedListADT<Event> eList = searchEvent(title,2);//only 1 event or no event
                            //if no event with the title exist create a new event of type event
                            if(eList.empty())
                            {
                                System.out.print("Enter event date and time (MM/DD/YYYY HH:MM):");
                                String dateAndTime = sc.nextLine();
                                //check if time is already occupied
                                if(checkTimeConflict(dateAndTime))
                                {
                                    System.out.println("An event is already scheduled at this time");
                                    break;
                                }
                                System.out.print("Enter event location:");
                                String location = sc.nextLine();

                                //it is an event of type event because a list of contacts is given in the parameter
                                Event newEvent = new Event(contactList, title, dateAndTime, location);
                                insertEventAlpha(newEvent);

                                System.out.println("Event scheduled successfully!\n");
                            }
                            //if the event already exist just insert every contact in the event's list of contacts
                            else
                            {
                                Event event = eList.retrieve();
                                if(!event.isEvent())
                                {
                                    System.out.println("an appointment with the title given already exist!");
                                    break;
                                }
                                //loop through the list of contacts given
                                contactList.findfirst();
                                while(!contactList.last())
                                {
                                    event.getListOfContacts().insert(contactList.retrieve());
                                    contactList.findnext();
                                }
                                event.getListOfContacts().insert(contactList.retrieve());

                                System.out.println("Contacts has been added succefully!\n");
                            }

                            break;
                            
                        }

                        //if event of type appointment
                        case "2":
                        {
                            LinkedListADT<Event> eList = searchEvent(title,2);
                            if(!eList.empty())
                            {
                                System.out.println("An event with the title already exist!\n");
                                break;
                            }
                            //only 1 contact is scheduled with if event of type appointment 
                            System.out.print("Enter contact name:");
                            String contactName = sc.nextLine();
                            LinkedListADT<Contact> contactList = contactBST.searchContact(contactName, 1);//only 1 contact or no contact
                            //check if contact exist
                            if(contactList.empty())
                            {
                                System.out.println("Contact name does not exist\n");
                                break;
                            }
                            Contact contact = contactList.retrieve();

                            System.out.print("Enter event date and time (MM/DD/YYYY HH:MM):");
                            String dateAndTime = sc.nextLine();
                            //check if time is already occupied
                            if(checkTimeConflict(dateAndTime))
                            {
                                System.out.println("An event is already scheduled at this time");
                                break;
                            }
                            System.out.print("Enter event location:");
                            String location = sc.nextLine();
                            //it is an event of type appointment because only 1 contact is given in the parameter
                            Event newEvent = new Event(contact, title, dateAndTime, location);
                            insertEventAlpha(newEvent);

                            System.out.println("Appointment scheduled successfully!\n");

                            break;
                        }
                        default:
                        {
                            System.out.println("Wrong input!");
                            break;
                        }  
                    }
                    break;
                }
                case "5"://print event details
                {
                    System.out.println("Enter search criteria:\n" + "1.Contact name\n" + "2.Event title\n");
                    System.out.print("Enter your choice:");
                    choice = sc.nextLine();
                    switch(choice)
                    {
                        //by contact name
                        case "1":
                            {
                                System.out.print("Enter contact name:");
                                String name = sc.nextLine();
                                
                                //return any event associated with contact

                                LinkedListADT<Event> eList = searchEvent(name,1);

                                if(eList.empty())
                                {
                                    System.out.println("No events associated with the name.\n");
                                    break;
                                }
                                System.out.println("Event or events found!\n");

                                //print events info
                                eList.findfirst();
                                while(!eList.last())
                                {
                                    eList.retrieve().printEventInfo();
                                    eList.findnext();
                                    //for visuals
                                    System.out.println();
                                }
                                eList.retrieve().printEventInfo();

                                //for visuals
                                System.out.println();

                                break;
                            }

                            //by event title
                            case "2":
                            {
                                System.out.print("Enter event title:");
                                String title = sc.nextLine();
                                //return any events with the same name

                                LinkedListADT<Event> eList = searchEvent(title,2);

                                if(eList.empty())
                                {
                                    System.out.println("No events with this title.\n");
                                    break;
                                }

                                System.out.println("Event or events found!");
                                //print events info
                                eList.findfirst();
                                while(!eList.last())
                                {
                                    eList.retrieve().printEventInfo();
                                    eList.findnext();
                                    //for visuals
                                    System.out.println();
                                }
                                eList.retrieve().printEventInfo();

                                //for visuals
                                System.out.println();

                                break;
                            }
                            default:
                            {
                                System.out.println("Wrong input");
                                break;
                            }  
                    }
                         
                    break;
                }
                case "6"://print contacts by first name
                {
                    System.out.print("Enter contact's first name:");
                    String name = sc.nextLine();
                    //returns any contact that has the same first name given

                    LinkedListADT<Contact> contactList = contactBST.searchContact(name,6);
                    
                    if(contactList.empty())
                    {
                        System.out.println("No contacts were found with this name.\n");
                        break;
                    }

                    System.out.println("Contacts or contact were found !\n");
                    //print contact info 
                    contactList.findfirst();
                    while(!contactList.last())
                    {
                        contactList.retrieve().printContactInfo();
                        contactList.findnext();
                        System.out.println();
                    }
                        
                        //last node
                        contactList.retrieve().printContactInfo();

                        //for visuals
                        System.out.println("\n");

                break;
                }
                case "7"://print events alphabaticly
                {
                    //since events are already sorted print them directly
                    if(eventList.empty())
                    {
                        System.out.println("No events available\n");
                        break;
                    }
                    eventList.findfirst();
                    while(!eventList.last())
                    {
                        eventList.retrieve().printEventInfo();
                        eventList.findnext();
                        //for visuals
                        System.out.println();
                    }

                    //last node
                    eventList.retrieve().printEventInfo();

                    //for visuals
                    System.out.println();

                    break;
                }

                case "8":
                {
                    System.exit(0);
                }
                default:
                    System.out.println("Wrong input! Please enter a number from 1-8.\n");
            }

        }while(true);
    }

    //inserts event alphabeticly based on title
    public static void insertEventAlpha(Event e)
    {
        Event e2;
        
        //if list is empty insert at head 
        if(eventList.empty())               
        {
            eventList.insert(e); 
            return;
        }

        int result;
        eventList.findfirst();
        while(!eventList.last())
        {
            e2 = eventList.retrieve();
            result = e.compareTo(e2);
            /*if current node is coming later in alphabetic insert before it,
            insertBefore method was used instead of normal insert in case of inserting before first element.
            in case of same word it will be inserted after*/
            if(result == -1)
            {
                eventList.insertBefore(e);
                return;
            }
            eventList.findnext();
        }

        //last node
        e2 = eventList.retrieve();
        result = e.compareTo(e2);
        //if last node is coming later in the alphabetic insert before it
        if(result == -1)
            eventList.insertBefore(e);
        //if same word or last node is coming first in the alphabetic insert after 
        else
            eventList.insert(e);
    }


    //return true if conflict exist
    private static boolean checkTimeConflict(String eventDateAndtime)
    {
        String dateAndTime = eventDateAndtime.toLowerCase().replaceAll("\\s", "");
        if(eventList.empty())
            return false;

        //search for event with the same time and return true if it exist
        eventList.findfirst();
        while(!eventList.last())
        {
            if(eventList.retrieve().getDateAndtime().toLowerCase().replaceAll("\\s", "").equals(dateAndTime))
                return true;
            eventList.findnext();
        }

        //last node
        if(eventList.retrieve().getDateAndtime().toLowerCase().replaceAll("\\s", "").equals(dateAndTime))
                return true;

        //no conflict was found
        return false;
    }

    //1 by name , 2 by title
    private static LinkedListADT<Event> searchEvent(String string, int nb) 
    {   
        string = string.toLowerCase().replaceAll("\\s", "");
        //to store events with the condition
        LinkedListADT<Event> eList = new LinkedListADT<Event>();
        if(eventList.empty())
            return eList;

        //if by name, search for the contact first then search for events asscoiated with the contact 
        if(nb == 1)
        {
            Contact contact;

            
            if(contactBST.findkey(string))
                contact = contactBST.retrieve();
            //contact does not exist
            else
                return eList;
            
            //search for events assoiated with contact
            eventList.findfirst();
            int length = eventList.length();
            Event currentEvent;
            for(int i = 0; i<length; i++)
            {
                currentEvent = eventList.retrieve();
                //if event is of type event
                if(currentEvent.isEvent())
                {
                    LinkedListADT<Contact> tmpContacts = currentEvent.getListOfContacts();
                    int tmpLength = tmpContacts.length();
                    //loop through the currentEvent's contacts to search for the contact
                    tmpContacts.findfirst();
                    for(int j = 0; j<tmpLength; j++)
                    {
                        /*if the currentEvent's contacts has the contact we are searching for insert the currentEvent
                        to the list of events that will be returned by the method*/
                        if(tmpContacts.retrieve().getName().toLowerCase().replaceAll("\\s", "").equals(string))
                        {
                            eList.insert(currentEvent);
                            break;
                        }           
                        tmpContacts.findnext();
                    }
                }
                //if event is of type appointment
                else
                {
                    /*if the currentEvent contact has the contact we are searching for insert the currentEvent
                    to the list of events that will be returned by the method*/
                    if(currentEvent.getContact().getName().toLowerCase().replaceAll("\\s", "").equals(string))
                        eList.insert(currentEvent);
                }
                eventList.findnext();
            }
        }
        //if by event title
        if(nb == 2)
        {
            int length = eventList.length();
            eventList.findfirst();
            for(int i=0; i<length; i++)
            {
                Event currentEvent = eventList.retrieve();
                /*if currentEvent's title is equal to the given title then insert the currentEvent
                to the list of events that will be returned by the method*/
                if(currentEvent.getTitle().toLowerCase().replaceAll("\\s", "").equals(string))
                    eList.insert(currentEvent);       
                eventList.findnext();
            }    
        }
        return eList;
    }

}

