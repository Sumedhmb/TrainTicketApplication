package com.casestudy.trainapplication;

import java.io.IOException;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Scanner;

public class TicketApplication {

	public static void main(String[] args) throws SQLException, ClassNotFoundException, ParseException, IOException {
		// TODO Auto-generated method stub
		Scanner s = new Scanner(System.in) ;
		//System.out.println("Menu\n 1. Enquiry\n2. Book Ticket\n3. Ticket Generated\n4. ");


		System.out.println("Enter Train No:");
		int trainNo = s.nextInt() ;
		Train train = TrainDOA.findTrain(trainNo);
		if(train == null)
			System.out.println("Train with given number does not exist");
		else 
		{
			System.out.println("Enter travel date");
			String dt = s.next() ;
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy"); 
			Date date = sdf.parse(dt) ;
			DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy");
			LocalDate ld = LocalDate.parse(dt,dateFormat );
			LocalDate today = LocalDate.now();
			if(ld.compareTo(today)<0)
			{
				System.out.println("Travel Date is before current date");
			}
			else

			{

				System.out.println("Enter number of passengers");
				int n = s.nextInt();

				Ticket t = new Ticket(date,train) ;
				for(int i = 0 ; i < n ; i++)
				{
					System.out.println("Enter passenger name");
					String name = s.next();
					System.out.println("Enter age");
					int age = s.nextInt();
					System.out.println("Enter gender");
					char gender = s.next().charAt(0);
					t.addPassenger(name, age, gender);
				}

				System.out.println("Ticket booked with PNR: " + t.generatePNR());
				t.generateTicket();
				System.out.println("Ticket should be written in a file with filename "+t.generatePNR()+".txt");
				t.writeTicket();




			}

		}


	}

}
