package com.Heather;

import java.util.*;

public class TicketManager {

    public static void main(String[] args) {
        LinkedList<Ticket> ticketQueue = new LinkedList<Ticket>();
        ArrayList<resolvedTicket>resolvedTickets=new ArrayList<>();
        Scanner scan = new Scanner(System.in);


        while(true){

            System.out.println("1. Enter Ticket\n2. Delete Ticket by ID\n3. Delete Ticket by Issue\n4. Search by Name\n5. Display All Tickets\n6. Quit");
            int task = Integer.parseInt(scan.nextLine());

            if (task == 1) {
                //Call addTickets, which will let us enter any number of new tickets
                addTickets(ticketQueue);

            } else if (task == 2) {
                //delete a ticket by ID
                resolvedTicket r=deleteTicket(ticketQueue);
                resolvedTickets.add(r);

            } else if (task==3){
                //delete a ticket by Issue
                System.out.println("What word or phrase are you looking for?");
                String criteria=scan.nextLine();
                ArrayList<Ticket>results=search(criteria, ticketQueue);
                for (Ticket n:results){
                    System.out.println(n);
                }
                resolvedTicket r=deleteTicket(ticketQueue);
                resolvedTickets.add(r);

            } else if (task==4){
                //search by Name
                System.out.println("What word or phrase are you looking for?");
                String criteria=scan.nextLine();
                ArrayList<Ticket>results=search(criteria, ticketQueue);
                for (Ticket n:results){
                    System.out.println(n);
                }

            } else if ( task == 6 ) {
                //Quit. Future prototype may want to save all tickets to a file
                System.out.println("Quitting program");
                String date=(String.valueOf(new Date())).replaceAll(" ","_");
                String filename="Resolved_tickets_as_of_"+date;
                /*TicketFileManager today=new TicketFileManager(filename);
                write(filename);
                read(filename);*/
                break;
            }
            else {
                //this will happen for 5 or any other selection that is a valid int
                //TODO Program crashes if you enter anything else - please fix
                //Default will be print all tickets
                printAllTickets(ticketQueue);
            }
        }

        scan.close();

    }

    protected static void printAllTickets(LinkedList<Ticket> tickets) {
        System.out.println(" ------- All open tickets ----------");

        for (Ticket t : tickets ) {
            System.out.println(t); //Write a toString method in Ticket class
            //println will try to call toString on its argument
        }
        System.out.println(" ------- End of ticket list ----------");

    }

    protected static resolvedTicket deleteTicket(LinkedList<Ticket> ticketQueue) {
        printAllTickets(ticketQueue);   //display list for user
        resolvedTicket t = null;

        if (ticketQueue.size() == 0) {    //no tickets!
            System.out.println("No tickets to delete!\n");
        }

        Scanner deleteScanner = new Scanner(System.in);
        Scanner scanr = new Scanner(System.in);
        System.out.println("Enter ID of ticket to delete");
        int deleteID = deleteScanner.nextInt();

        //Loop over all tickets. Delete the one with this ticket ID
        boolean found = false;

        for (Ticket ticket : ticketQueue) {
            int adjustedID=deleteID-1;//this is a quick and dirty solution, but I can't find the source of the actual problem (ID num entered 1 larger than actual ID num)
            if (ticket.getTicketID() == adjustedID) {
                found = true;
                System.out.println();
                System.out.println("Describe ticket resolution");
                String resolved= scanr.nextLine();
                t=new resolvedTicket(ticket.getDescription(), ticket.getPriority(), ticket.getReporter(), ticket.getDateReported(),(new Date()), resolved);
                ticketQueue.remove(ticket);
                System.out.println(String.format("Ticket %d deleted", deleteID));
                break; //don't need loop any more.
            }
        }
        if (found == false) {
            System.out.println("Ticket ID not found, no ticket deleted");
            //TODO – re-write this method to ask for ID again if not found
        }
        printAllTickets(ticketQueue);  //print updated list
        return t;
    }

    protected static void addTickets(LinkedList<Ticket> ticketQueue) {
        Scanner sc = new Scanner(System.in);
        boolean moreProblems = true;
        String description, reporter;
        Date dateReported = new Date(); //Default constructor creates date with current date/time
        int priority;

        while (moreProblems){
            System.out.println("Enter problem");
            description = sc.nextLine();
            System.out.println("Who reported this issue?");
            reporter = sc.nextLine();
            System.out.println("Enter priority of " + description);
            priority = Integer.parseInt(sc.nextLine());

            Ticket t = new Ticket(description, priority, reporter, dateReported);
            //ticketQueue.add(t);
            addTicketInPriorityOrder(ticketQueue, t);

            printAllTickets(ticketQueue);

            System.out.println("More tickets to add? Y=yes, N=no.");
            String more = sc.nextLine();
            if (more.equalsIgnoreCase("N")) {
                moreProblems = false;
            }
        }
    }

    protected static void addTicketInPriorityOrder(LinkedList<Ticket> tickets, Ticket newTicket){

        //Logic: assume the list is either empty or sorted

        if (tickets.size() == 0 ) {//Special case - if list is empty, add ticket and return
            tickets.add(newTicket);
            return;
        }

        //Tickets with the HIGHEST priority number go at the front of the list. (e.g. 5=server on fire)
        //Tickets with the LOWEST value of their priority number (so the lowest priority) go at the end

        int newTicketPriority = newTicket.getPriority();

        for (int x = 0; x < tickets.size() ; x++) {    //use a regular for loop so we know which element we are looking at

            //if newTicket is higher or equal priority than the this element, add it in front of this one, and return
            if (newTicketPriority >= tickets.get(x).getPriority()) {
                tickets.add(x, newTicket);
                return;
            }
        }

        //Will only get here if the ticket is not added in the loop
        //If that happens, it must be lower priority than all other tickets. So, add to the end.
        tickets.addLast(newTicket);
    }

    public static ArrayList<Ticket>search( String criteria, LinkedList<Ticket> ticketQueue ){
        ArrayList<Ticket>results=new ArrayList<>();
        for (Ticket t:ticketQueue){
            if(t.getDescription().contains(criteria)){
                results.add(t);
            }else if (t.getTicketID()==Integer.parseInt(criteria)){
                results.add(t);
            }
        }

        return results;
    }
    public static void resolveTicket(){

    }
}
