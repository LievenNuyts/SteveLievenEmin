package utils;

import java.util.Date;
import java.util.Scanner;

/**
 * 
 * @author Steve Houtmeyers	
 * @version 13/10/2013
 *
 */

public class DateQuiz implements Comparable<DateQuiz>{
	
	//variabelen
	
	private int day; //tussen 1 & 31, afhankelijk van de maand
	private int month; //tussen 1 & 12
	private int year; //elk jaartal boven 0
	private String[] months = {"januari","februari","maart","april","mei","juni","juli","augustus","september","oktober","november","december"};
	private DateQuiz dateQuiz;
	
	//array van dagen per maand (start op nul!!)
	private static final int [] dagenPerMaand= {31,28,31,30,31,30,31,31,30,31,30,31};
	
	//getters setters
	
	public int getDay(){
		return day;
	}
	public void setDay(int day) throws IllegalArgumentException{
		if (day <= 0 || day > (dagenPerMaand[month])) throw new IllegalArgumentException("Day is incorrect.");
		
		if (month == 2 && day == 29 && (year % 400 == 0 || (year % 4 == 0 && year % 100 != 0))) throw new IllegalArgumentException("Day doesn't fit month and year.");
		this.day = day;
	}
	public int getMonth(){
		return month;
	}
	public void setMonth(int month) throws IllegalArgumentException{
		if (month <= 0 || month > 12)throw new IllegalArgumentException("A month cannot be lesser than 1 of higher than 12.");
		this.month = month;
	}
	public int getYear(){
		return year;
	}
	public void setYear(int year) throws IllegalArgumentException{
		if (year <= 0 || year > 2999)throw new IllegalArgumentException("Year cannot be lesser than 0 or larger than 2999.");
		this.year = year;
	}
	
	//constructors
	
		//default verwijst naar Datum(int dag, int maand, int jaartal)
		public DateQuiz()throws IllegalArgumentException 
		{
			Date dateToday = new Date();

			day = dateToday.getDay();
			month = dateToday.getMonth();
			year = dateToday.getYear();
		
		}
		//constructor op basis van object
		public DateQuiz(DateQuiz date)throws IllegalArgumentException
		{
			this(date.day, date.month, date.year);
		}
		//constructor op basis van 3 integers
		public DateQuiz(int day, int month, int year)throws IllegalArgumentException
		{
			setDay(day);
			setMonth(month);
			setYear(year);
		}
		//constructor op basis van string
		public DateQuiz(String dateString)throws IllegalArgumentException
		{
			Scanner datumScanner = new Scanner(dateString);
			//strip string: no '/'
			datumScanner.useDelimiter("\\s*/\\s*");
			
			int _day = datumScanner.nextInt();	
			String monthAsString = datumScanner.next();
			
			int _month = Integer.parseInt(monthAsString);
			int _year = datumScanner.nextInt();
			datumScanner.close();
			
			if ((String.valueOf(_day).length() != 1) && (String.valueOf(_day).length() != 2))
				throw new IllegalArgumentException ("Day is not valid.");
			
			if ((monthAsString.length() != 2))
				throw new IllegalArgumentException ("Month is not valid.");
			
			if (String.valueOf(_year).length() != 4)
				throw new IllegalArgumentException ("Years are not valid.");

			setDay(_day);
			setMonth(_month);
			setYear(_year);
			dateQuiz = new DateQuiz(_day, _month, _year);
		}
		
	public DateQuiz(int days) {
			// TODO Auto-generated constructor stub
		}
	
	//@overrides
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + day;
		result = prime * result + year;
		result = prime * result + month;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DateQuiz other = (DateQuiz) obj;
		if (day != other.day)
			return false;
		if (year != other.year)
			return false;
		if (month != other.month)
			return false;
		return true;
	}
	@Override
	public int compareTo(DateQuiz o) {
		return (this.year + this.month + this.day) - (o.year + o.month + o.day);
	}
	@Override
	public String toString(){
		return String.format("%02d/%02d/%04d", getDay(), getMonth(), getYear());
	}

	public String toString2(){
		return String.format("%02d %s %04d", getDay(), months [getMonth() -1], getYear());
	}
	
	//Methods
	
	public void setDatum(DateQuiz datum){
		this.dateQuiz= datum;
	}
	
	public boolean setDatum(int dag, int maand, int jaar)throws IllegalArgumentException {
		setDay(dag);
		setMonth(maand);
		setYear(jaar);
		return true;
}
		
	public DateQuiz getDate(){
		return dateQuiz;
	}
	
	public String getDatumInAmerikaansFormaat(DateQuiz dateQuiz) {
		return String.format("%04d/%d/%d",
				dateQuiz.getYear(),dateQuiz.getMonth(),dateQuiz.getDay());
	}
		
	public String getDatumInEuropeesFormaat(DateQuiz dateQuiz) {
		return String.format("%d/%d/%04d",
				dateQuiz.getDay(),dateQuiz.getMonth(),dateQuiz.getYear());
	}
	
	
	public DateQuiz verschilInTijd (DateQuiz datum){
	int days = Math.abs(this.getDateInDays() - datum.getDateInDays());
	return new DateQuiz(days);
	}
	
	private int getDateInDays(){
		return 0;
	}

	public boolean smallerThan (DateQuiz datum){
		//bepaalt of een 'Datum datum' object kleiner is dan huidig datumobject
		if(year<datum.year)return true;
		else if(year==datum.year && month<datum.month)return true;
		else if(month==datum.month && day<datum.day)return true;
		else return false;
	}
	
	public int verschilInJaren (DateQuiz d) throws Exception {
		try {
			int years = 0;
			int monthX = this.month + this.day;
			int monthY = d.month + d.day;

			if(d.smallerThan(this)){
				years = this.year - d.year;
				if (monthX < monthY && this.year != d.year){
					years -= 1;
				}
			}
			return years;


		} catch (Exception e) {
			// TODO: handle exception
		}
		//bepaalt het verschil in volledige jaren tussen datum d en huidig datumobject  (vb 01032007 en 03012009 -> 1 jaar)
		return 0;
	}
	
	public int verschilInMaanden (DateQuiz d) {
		//: bepaalt het verschil in volledige maanden tussen datum d en huidig datumobject (vb 01032007 en 03012009 -> 22 maanden)
		return 0;
	}
	
	public int verschilInDagen (DateQuiz d) {
		//: bepaalt het verschil in dagen tussen datum d en huidig datumobject 
		return 0;
	}
	
	
	public void veranderDatum (int aantalDagen) {
		//: verhoogt of verlaagt de datum met een aantal dagen
    }
    public DateQuiz veranderDatum2 (int aantalDagen) {
    	DateQuiz datum = new DateQuiz();
		//: geeft een niew Datum object terug dat gelijk is aan het originele datum object verhoogt of verlaagt met een aantal dagen.
		return datum;
	}
	
	
	public static void main(String[] args) {
		try 
		{
			//datum met integers
			DateQuiz d1 = new DateQuiz(15,8,2004);
			System.out.println(d1);
			
			//datum zonder parameters
			DateQuiz d2 = new DateQuiz();
			System.out.println(d2);
			
			//datum op basis van object
			DateQuiz d3 = new DateQuiz(d1);
			System.out.println(d3.toString2());
			
			//datum op basis van string
			DateQuiz d4 = new DateQuiz("3/02/2000");
			System.out.println(d4);
			
			DateQuiz d5 = new DateQuiz("21/02/2010");
			System.out.println(d5);
			System.out.println(d5.toString2());
			
			System.out.println(d5.getDatumInAmerikaansFormaat(d5));
			
			int year = 1955;
			int month = 5;
			int day = 26;
			String american = Integer.toString(year) + "/" + Integer.toString(month) + "/" + Integer.toString(day);
			System.out.println(american);
			DateQuiz date = new DateQuiz(day, month, year);
			System.out.println(date);
			System.out.println(date.getDatumInAmerikaansFormaat(date));
			
		}
		catch (IllegalArgumentException ex){System.out.println(ex.getMessage());}

	}
}