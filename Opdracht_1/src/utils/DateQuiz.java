package utils;

import java.util.Calendar;
import java.util.Date;
import java.util.InputMismatchException;
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
	//private DateQuiz dateQuiz;
	
	//array van dagen per maand (start op nul!!)
	private static final int [] dagenPerMaand= {31,28,31,30,31,30,31,31,30,31,30,31};
	
	//getters setters
	
	public int getDay(){
		return day;
	}
	public void setDay(int day) throws IllegalArgumentException{
		if ((this.year % 400 == 0) || ((this.year % 4 == 0) && (this.year % 100 != 0))){
			if (month == 2){
				if (day <= 0 || day > 29) throw new IllegalArgumentException("Day is incorrect.");
			}
		}
		else {
			if (day <= 0 || day > (dagenPerMaand[month - 1])) throw new IllegalArgumentException("Day is incorrect.");
		}
		
		if (month == 2 && day == 29 && (year % 4 != 0)) throw new IllegalArgumentException("Day doesn't fit month and year.");
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
			this.setMonth(Calendar.MONTH);
			this.setDay(Calendar.DAY_OF_MONTH);
			this.setYear(Calendar.YEAR);
		}
		//constructor op basis van object
		public DateQuiz(Date date)throws IllegalArgumentException
		{
			Calendar cal = Calendar.getInstance();
		    cal.setTime(date);

		    this.setMonth(cal.get(Calendar.MONTH));
		    this.setDay(cal.get(Calendar.DAY_OF_MONTH));
		    this.setYear(cal.get(Calendar.YEAR));
		}
		//constructor op basis van 3 integers
		public DateQuiz(int day, int month, int year)throws IllegalArgumentException
		{
			this.setYear(year);
			this.setMonth(month);
			this.setDay(day);
		}
		//constructor op basis van string
		public DateQuiz(String dateString)throws IllegalArgumentException
		{
			try
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

				this.setYear(_year);
				this.setMonth(_month);
				this.setDay(_day);

				//dateQuiz = new DateQuiz(_day, _month, _year);
			}

			catch (IllegalArgumentException argEx) {
				throw new IllegalArgumentException("Date is not valid.");
			}
			
			catch (InputMismatchException argEx) {
				throw new InputMismatchException("Date is not valid.");
			}
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
	public int compareTo(DateQuiz o) throws IllegalArgumentException {
		if(o == null)throw new IllegalArgumentException("Datum is null");
		
		return (this.year + this.month + this.day) - (o.year + o.month + o.day);
	}
	@Override
	public String toString(){
		return String.format("%02d/%02d/%04d", getDay(), getMonth(), getYear());
	}

	public String toString2(){
		return String.format("%02d %s %04d", getDay(), months [getMonth()-1], getYear());
	}
	
	//Methods
	
	public String getDateInAmericanFormat() {
		return String.format("%02d/%02d/%04d", this.getMonth(), this.getDay(), this.getYear());
	}
		
	public String getDateInEuropeanFormat() {
		return String.format("%02d/%02d/%04d", this.getDay(),this.getMonth(),this.getYear());
	}

	public boolean smallerThan (DateQuiz date){
		if(date == null)throw new IllegalArgumentException("Datum is null");
		
		if(year < date.year)return true;
		else if( year == date.year && month < date.month)return true;
		else if(month == date.month && day < date.day)return true;
		else return false;
	}
	
	public int differenceInYears (DateQuiz date) throws IllegalArgumentException {
		if(date == null)throw new IllegalArgumentException("Datum is null");
		
		int years = 0;
		int dMonth = this.month + this.day;
		int thisMonth = date.month + date.day;

		if(date.smallerThan(this)){
			years = this.year - date.year;
			if (dMonth < thisMonth && this.year != date.year){
				years--;
			}
		}
		else{
			years = date.year - this.year;
			if ((thisMonth < dMonth && this.year != date.year) || (this.year != date.year && this.month == date.month && date.day <this.day)){
				years--;
			}
		}
		return years;
	}
		//bepaalt het verschil in volledige jaren tussen datum d en huidig datumobject  (vb 01032007 en 03012009 -> 1 jaar)

	
	public int differenceInMonths (DateQuiz d) throws IllegalArgumentException {
		if(d == null)throw new IllegalArgumentException("Datum is null");
		
		int months = 0;
		int thisMonth = this.year * 12 + this.month;
		int dMonth = d.year * 12 + d.month;

		if (thisMonth < dMonth) {
			months = dMonth - thisMonth;
		}
		else if (dMonth < thisMonth) {
			months = thisMonth - dMonth;
		}
		else {
			months = 0;
		}

		return months;
	}
	
	public int differenceInDays (DateQuiz d) throws IllegalArgumentException {
		if(d == null)throw new IllegalArgumentException("Datum is null");
		
		int days = 0;
        DateQuiz date1 = new DateQuiz();
        DateQuiz date2 = new DateQuiz();
        if(this.smallerThan(d)){
                date1= this;
                date2 = d;
        }
        else{
                date1= d;
                date2= this;
        }
        
        while(!date1.equals(date2)){
                days++;
                if(date1.day == dagenPerMaand[date1.month - 1]){
                        date1.day = 1;
                        if(date1.month == 12){
                                date1.month = 1;
                                date1.year++;
                        }
                        else{
                                date1.month++;
                        }
                }
                else{
                        date1.day++;
                }
        }
        return days;
}

	//verhoogt of verlaagt de datum met een aantal dagen
	
	public void changeDate (int nDays) {
		while(nDays > 0){

			if(this.day == dagenPerMaand[month - 1]){
				this.day = 1;
				if(this.month == 12){
					this.month = 1;
					this.year++;
				}
				else{
					this.month++;
				}
			}
			else{
				this.day++;
			}
			nDays--;
		}

		while(nDays < 0){

			if(this.day == 1){
				if(this.month == 1){
					this.year--;
					this.month = 12;
				}
				else{
					this.month--;
				}
				this.day = dagenPerMaand[month - 1];
				
				if(this.month == 2)
					if((this.year % 400 == 0) || ((this.year % 4 == 0) && (this.year % 100 != 0)))
						this.day++;
			}
			else{
				this.day--;
			}
			nDays++;
		}
	}
	
	//geeft een niew Datum object terug dat gelijk is aan het originele datum object verhoogt of verlaagt met een aantal dagen
	
    public DateQuiz changeDate2 (int nDays) {
    	DateQuiz date = this;
        while(nDays > 0){

                if(date.day == dagenPerMaand[date.month - 1]){
                	date.day = 1;
                        if(date.month == 12){
                        	date.month = 1;
                        	date.year++;
                        }
                        else{
                        	date.month++;
                        }
                }
                else{
                	date.day++;
                }
                nDays--;
        }
        while(nDays < 0){
                if(date.day == 1){
                        if(date.month == 1){
                        	date.year--;
                        	date.month = 12;
                        }
                        else{
                        	date.month--;
                        }
                        day = dagenPerMaand[date.month - 1];
                        
                        if(this.month == 2)
        					if((this.year % 400 == 0) || ((this.year % 4 == 0) && (this.year % 100 != 0)))
        						this.day++;
                }
                else{
                	date.day--;
                }
                nDays++;
        }
                return date;
	}
}