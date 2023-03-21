import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Databaze {
	public Databaze(int pocetPrvku)
	{
		prvkyDatabaze=new Student[pocetPrvku];
		sc=new Scanner(System.in);
	}
	
	public void setStudent()
	{
		System.out.println("Zadejte jmeno studenta, rok narozeni");
		String jmeno=sc.next();
		int rok=Test.pouzeCelaCisla(sc);
		prvkyDatabaze[posledniStudent++]=new Student(jmeno,rok);
	}
	
	public Student getStudent(int idx) 
	{
		return prvkyDatabaze[idx];
	}
	
	public void setPrumer(int idx, float prumer) throws prumerException
	{
		prvkyDatabaze[idx].setStudijniPrumer(prumer);
	}
	
	public boolean ulozDatabazi(String jmenoSouboru)
	{
		try {
			FileWriter fw = new FileWriter(jmenoSouboru);
			BufferedWriter out = new BufferedWriter(fw);
			out.write(new String("Pocet " + prvkyDatabaze.length));
			out.newLine();
			for (int i=0;i<prvkyDatabaze.length;i++){
				if (prvkyDatabaze[i]!=null)
					out.write(prvkyDatabaze[i].getJmeno() +" " + prvkyDatabaze[i].getRocnik() +" " + prvkyDatabaze[i].getStudijniPrumer());
				else
					out.write("null");
				out.newLine();
			}
			out.close();
			fw.close();
		}
		catch (IOException e) {
			System.out.println("Soubor nelze vytvorit");
			return false;
		}
		return true;
	}
	
	public boolean nactiDatabazi(String jmenoSouboru) {
		FileReader fr=null;
		BufferedReader in=null;
		try {
			fr = new FileReader(jmenoSouboru);
			in = new BufferedReader(fr);
			String radek=in.readLine();
			String oddelovac = "[ ]+";
			String[] castiTextu = radek.split(oddelovac);
			if (castiTextu.length!=2)
				return false;
			int pocetPrvku=Integer.parseInt(castiTextu[1]);
			prvkyDatabaze=new Student[pocetPrvku];
			for (int i=0;i<pocetPrvku;i++)
			{
				radek=in.readLine();
				castiTextu = radek.split(oddelovac);
				if (castiTextu.length==3)
				{
					prvkyDatabaze[i]=new Student(castiTextu[0], Integer.parseInt(castiTextu[1]));
					prvkyDatabaze[i].setStudijniPrumer(Float.parseFloat(castiTextu[2]));
				}
			}
		}
		catch (IOException e) {
			System.out.println("Soubor  nelze otevøít");
			return false;
		} 
		catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			System.out.println("Chyba integrity dat v souboru");
			return false;
		} catch (prumerException e) {
			// TODO Auto-generated catch block
			System.out.println("Nespravna data v souboru");
			return false;
		}
		finally
		{
			try
			{	if (in!=null)
				{
					in.close();
					fr.close();
				}
			}
			catch (IOException e) {
				System.out.println("Soubor  nelze zavrit");
				return false;
			} 
		}
		
		return true;
	}
	
	public void vypisDatabazi()
	{
		for (int i=0;i<prvkyDatabaze.length;i++){
			if (prvkyDatabaze[i]!=null)
				System.out.println(i+". "+prvkyDatabaze[i].getJmeno() +" " + prvkyDatabaze[i].getRocnik() +" " + prvkyDatabaze[i].getStudijniPrumer());
			else
				System.out.println(i+". "+"null");
		}
	}
	
	private Scanner sc;
	private Student [] prvkyDatabaze;
	private int posledniStudent;
}