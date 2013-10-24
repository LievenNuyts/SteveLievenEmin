package utils;

import java.util.Scanner;

/**
 * 
 * @author Steve Houtmeyers	
 * @version 13/10/2013
 *
 */

public class DateQuiz implements Comparable<DateQuiz>{
	
	//variabelen
	
	private int dag; //tussen 1 & 31, afhankelijk van de maand
	private int maand; //tussen 1 & 12
	private int jaartal; //elk jaartal boven 0
	private String[] maanden = {"januari","februari","maart","april","mei","juni","juli","augustus","september","oktober","november","december"};
	private DateQuiz datum;
	
	//array van dagen per maand (start op nul!!)
	private static final int [] dagenPerMaand= {31,28,31,30,31,30,31,31,30,31,30,31};
	
	//getters setters
	
	public int getDag()throws IllegalArgumentException{
		if (dag > 0 && dag < (dagenPerMaand[maand]))
			return dag;
		
			
			// check schrikkeljaar
		if (maand == 2 && dag == 29 && (jaartal % 400 == 0 || (jaartal % 4 == 0 && jaartal % 100 != 0)))
		return dag;
			
		else
			throw new IllegalArgumentException("Dag klopt niet voor deze maand en jaartal");
	}
	public void setDag(int dag) {
		this.dag = dag;
	}
	public int getMaand()throws IllegalArgumentException{
		if (maand < 0 || maand > 12)throw new IllegalArgumentException("Een maand kan niet minder dan 1 of groter dan 12 zijn.");
		return maand;
	}
	public void setMaand(int maand) {
		this.maand = maand;
	}
	public int getJaartal()throws IllegalArgumentException{
		if (jaartal < 0)throw new IllegalArgumentException("Het jaartal kan niet minder dan nul zijn.");
		return jaartal;
	}
	public void setJaartal(int jaartal) {
		this.jaartal = jaartal;
	}
	
	//constructors
	
		//default verwijst naar Datum(int dag, int maand, int jaartal)
		public DateQuiz()throws IllegalArgumentException 
		{
			this(1,01,00);
		}
		//constructor op basis van object
		public DateQuiz(DateQuiz datum)throws IllegalArgumentException
		{
			this(datum.dag, datum.maand, datum.jaartal);
		}
		//constructor op basis van 3 integers
		public DateQuiz(int dag, int maand, int jaartal)throws IllegalArgumentException
		{
			setDag(dag);
			setMaand(maand);
			setJaartal(jaartal);
		}
		//constructor op basis van string
		public DateQuiz(String d)throws IllegalArgumentException
		{
			Scanner datumScanner = new Scanner(d);
			//strip string: no '/'
			datumScanner.useDelimiter("\\s*/\\s*");
			
			int _dag = datumScanner.nextInt();	
			String maandAsString = datumScanner.next();
			
			int _maand = Integer.parseInt(maandAsString);
			int _jaartal = datumScanner.nextInt();
			datumScanner.close();
			
			if ((String.valueOf(_dag).length() != 1) && (String.valueOf(_dag).length() != 2))
				throw new IllegalArgumentException ("Dag is niet correct.");
			
			if ((maandAsString.length() != 2))
				throw new IllegalArgumentException ("Maand is niet correct.");
			
			if (String.valueOf(_jaartal).length() != 4)
				throw new IllegalArgumentException ("Jaartal is niet correct.");

			setDag(_dag);
			setMaand(_maand);
			setJaartal(_jaartal);
			datum = new DateQuiz(_dag, _maand, _jaartal);
		}
		
	public DateQuiz(int dagen) {
			// TODO Auto-generated constructor stub
		}
	
	//@overrides
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + dag;
		result = prime * result + jaartal;
		result = prime * result + maand;
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
		if (dag != other.dag)
			return false;
		if (jaartal != other.jaartal)
			return false;
		if (maand != other.maand)
			return false;
		return true;
	}
	@Override
	public int compareTo(DateQuiz o) {
		return (this.jaartal + this.maand + this.dag) - (o.jaartal + o.maand + o.dag);
	}
	@Override
	public String toString(){
		return String.format("%d %s %d", getDag(), maanden [getMaand() -1], getJaartal());
	}

	//Methods
	
	public void setDatum(DateQuiz datum){
		this.datum = datum;
	}
	
	public boolean setDatum(int dag, int maand, int jaar)throws IllegalArgumentException {
		setDag(dag);
		setMaand(maand);
		setJaartal(jaar);
		return true;
}
		
	public DateQuiz getDatum(){
		return datum;
	}
	
	public String getDatumInAmerikaansFormaat() {
		return String.format("%d/%d/%d",
				datum.getJaartal(),datum.getMaand(),datum.getDag());
	}
		
	public String getDatumInEuropeesFormaat() {
		return String.format("%d/%d/%d",
				datum.getMaand(),datum.getDag(),datum.getJaartal());
	}
	
	
	public DateQuiz verschilInTijd (DateQuiz datum){
	int dagen = Math.abs(this.getDatumInDagen() - datum.getDatumInDagen());
	return new DateQuiz(dagen);
	}
	
	private int getDatumInDagen(){
		return 0;
	}

	public boolean kleinerDan (DateQuiz datum){
		//bepaalt of een 'Datum datum' object kleiner is dan huidig datumobject
		if(jaartal<datum.jaartal)return true;
		else if(jaartal==datum.jaartal && maand<datum.maand)return true;
		else if(maand==datum.maand && dag<datum.dag)return true;
		else return false;
	}
	
	public int verschilInJaren (DateQuiz d) {
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
			System.out.println(d3);
			
			//datum op basis van string
			DateQuiz d4 = new DateQuiz("15/02/2013");
			System.out.println(d4);
			
			DateQuiz d5 = new DateQuiz("21/10/2010");
			System.out.println(d5);
			
			
		}
		catch (IllegalArgumentException ex){System.out.println(ex.getMessage());}

	}
}