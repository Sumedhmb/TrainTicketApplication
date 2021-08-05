package com.casestudy.trainapplication;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.TreeMap;

public class Ticket {
	private static int counter = 99  ;
	private String pnr ;
	private Date travelDate ;
	private Train train ;
	Map<Passenger, Integer> passenger ;
 Ticket(Date travelDate, Train train) {
		super();
		passenger = new TreeMap<>() ;
		//addPassenge();
		this.travelDate = travelDate;
		this.train = train;
		setPnr();
	}
	public static int getCounter() {
		return counter;
	}

	public String getPnr() {
		return generatePNR().toString();
	}
	public void setPnr() {

		this.pnr = generatePNR().toString();
	}
	public Date getTravelDate() {
		return travelDate;
	}
	public void setTravelDate(Date travelDate) {
		this.travelDate = travelDate;
	}
	public Train getTrain() {
		return train;
	}
	public void setTrain(Train train) {
		this.train = train;
	}

	public StringBuilder generatePNR()
	{
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd"); 
		StringBuilder pnr = new StringBuilder() ;
		pnr.append( train.getSource().charAt(0));
		pnr.append(train.getDestination().charAt(0));
		pnr.append('_');
		pnr.append(sdf.format(travelDate));
		pnr.append('_').append(counter);
		return pnr;
	}

	public void addPassenger(String name, int age , char gender)
	{
		Passenger p = new Passenger(name,age,gender);
		int fare = (int)calcPassengerFare(p) ;
		counter++;
		passenger.put(p,fare);
	}

	public double TotalTicketPrice() {
		double total = 0 ;
		for( Map.Entry<Passenger, Integer> e : passenger.entrySet())
		{
			total += calcPassengerFare(e.getKey());
		}
		return total ;
	}
	
	public double calcPassengerFare(Passenger p )

	{
		double fare ;
		int age = p.getAge();
		if(age<=12)
			fare = train.getTicketPrice()*0.5 ;
		else if(age >= 60)
			fare = train.getTicketPrice()*0.6 ; 	
		else
		{
			char gender = p.getGender();
			if(gender == 'F')
				fare = train.getTicketPrice()*0.75;
			else
				fare = train.getTicketPrice();
		}
		return fare ;
	}
	
	public StringBuilder generateTicket() {
		
		StringBuilder s  = new StringBuilder() ;
		s.append(String.format("%-15s","Pnr"));
		s.append(" : ");
		s.append(generatePNR());
		s.append("\n");
		s.append(String.format("%-15s","Train no"));
		s.append(" : ");
		s.append(train.getTrainNo());
		s.append("\n");
		s.append(String.format("%-15s","Train Name"));
		s.append(" : ");
		s.append(train.getTrainName());
		s.append("\n");
		s.append(String.format("%-15s","From"));
		s.append(" : ");
		s.append(train.getSource());
		s.append("\n");
		s.append(String.format("%-15s","To"));
		s.append(" : ");
		s.append(train.getDestination());
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy"); 
		s.append("\n");
		s.append(String.format("%-15s","Travel Date"));
		s.append(" : ");
		s.append(sdf.format(travelDate));
		s.append("\n\nPassengers :\n");
		s.append(String.format("%-20s", "Name"));
		s.append(String.format("%-10s", "Age"));
		s.append(String.format("%-10s", "Gender"));
		s.append(String.format("%-10s", "Fare"));
		s.append("\n");
		for( Map.Entry<Passenger, Integer> e : passenger.entrySet())
		{
			
			s.append(String.format("%-20s", e.getKey().getName()));
			s.append(String.format("%-10s", e.getKey().getAge()));
			s.append(String.format("%-10s", e.getKey().getGender()));
			s.append(String.format("%-10s", e.getValue()));
			s.append("\n");
		}
		s.append("\nTotal Price :");
		s.append(TotalTicketPrice());
		s.append("\nTotal Passenger:");
		s.append(counter - 99) ;
		return s ;
	}
	
	public void writeTicket() throws IOException
	{
		File dir = new File("D:\\Ticket") ;
		dir.mkdir();
		OutputStream file = new FileOutputStream (new File(dir,generatePNR().toString().concat(".txt")));
		BufferedOutputStream out = new BufferedOutputStream(file) ;
		byte [] info = generateTicket().toString().getBytes();
		out.write(info);
		out.close();
	}
}



