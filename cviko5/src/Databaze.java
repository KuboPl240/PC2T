import java.util.Scanner;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Databaze {
	public Databaze(int pocetPrvku)
	{
		prvkyDatabaze=new Student[pocetPrvku];
		sc=new Scanner(System.in);
	}
	public void VypisDatabazi()throws vynimky{
		for (int i=0;i<prvkyDatabaze.length;i++){
			if (prvkyDatabaze[i]!=null)
				System.out.println(i+". Meno:"+prvkyDatabaze[i].getJmeno() +" Rok narodenia:" + prvkyDatabaze[i].getRocnik() +" Priemer:" + prvkyDatabaze[i].getStudijniPrumer());
			else
				System.out.println(i+". "+"Prazdne miesto");
		}
	}
	public boolean ulozDatabazu(String nazovSuboru)throws vynimky{
		try {
			FileWriter fw = new FileWriter(nazovSuboru);
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
			System.out.println("Subor sa neda vytvorit");
			return false;
		}
		return true;
	}
	public boolean nacitajDatabazu(String nazovSuboru) {
		FileReader fr=null;
		BufferedReader in=null;
		try {
			fr = new FileReader(nazovSuboru);
			in = new BufferedReader(fr);
			String radek=in.readLine();
			String oddelovac = "[ ]+";
			String[] castTextu = radek.split(oddelovac);
			if (castTextu.length!=2)
				return false;
			int pocetPrvkov=Integer.parseInt(castTextu[1]);
			prvkyDatabaze=new Student[pocetPrvkov];
			for (int i=0;i<pocetPrvkov;i++)
			{
				radek=in.readLine();
				castTextu = radek.split(oddelovac);
				if (castTextu.length==3)
				{
					prvkyDatabaze[i]=new Student(castTextu[0], Integer.parseInt(castTextu[1]));
					prvkyDatabaze[i].setStudijniPrumer(Float.parseFloat(castTextu[2]));
				}
			}
		}
		catch (IOException e) {
			System.out.println("Subor sa neda otvorit");
			return false;
		} 
		catch (NumberFormatException e) {
			System.out.println("Chyba formatu dat v subore");
			return false;
		} catch (vynimky e) {
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
	
	public void setStudent()
	{
		System.out.println("Zadejte jmeno studenta, rok narozeni");
		String jmeno=sc.next();
		int rok=sc.nextInt();
		prvkyDatabaze[posledniStudent++]=new Student(jmeno,rok);
	}
	
	public Student getStudent(int idx)
	{
		return prvkyDatabaze[idx];
	}
	
	public void setPrumer(int idx, float prumer) throws vynimky
	{
		prvkyDatabaze[idx].setStudijniPrumer(prumer);
	}
	
	private Scanner sc;
	private Student [] prvkyDatabaze;
	private int posledniStudent;
}