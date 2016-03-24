/*
package com.Heather;

import java.io.*;
import java.util.*;

*/
/**
 * Created by cryst on 3/24/2016.
 *//*

public class TicketFileManager {
    //save resolved tickets
    String filename1;
    String filename2;

    TicketFileManager(String filename1, String filename2) {
        write(filename1);
        write(filename2);
    }

    String date=(String.valueOf(new Date())).replaceAll(" ","_");
    String filename="Resolved_tickets_as_of_"+date;
    write(filename);
    read(filename);


    private static void read(String filename) throws IOException {
        FileReader reader = new FileReader(filename);
        BufferedReader bufReader = new BufferedReader(reader);
        try {
            String line = bufReader.readLine();
            while (line != null) {
                System.out.println(line);
                line = bufReader.readLine();
            }
        }catch(IOException io){
            System.out.println("There was an error while reading "+filename+".");
        }
        bufReader.close();   //This closes the inner FileReader too
    }
    private static void write(String filename) throws IOException {

        FileWriter writer = new FileWriter(filename);
        BufferedWriter bufWriter = new BufferedWriter(writer);
        try {
            bufWriter.write("My name is Heather.\n");
            bufWriter.write("My favorite color is blue.\n");
            bufWriter.write("I am "+Integer.toString(33) + " years old.\n");
            bufWriter.close();
        }catch(IOException io){
            System.out.println("There was an error writing to the file "+filename+".");
        }

    }


}
*/
