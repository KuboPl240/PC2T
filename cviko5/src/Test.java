import java.util.Scanner;


public class Test {

	public static int pouzeCelaCisla(Scanner sc) 
	{
		int cislo = 0;
		try
		{
			cislo = sc.nextInt();
		}
		catch(Exception e)
		{
			System.out.println("Nastala vyjimka typu "+e.toString());
			System.out.println("zadejte prosim cele cislo ");
			sc.nextLine();
			cislo = pouzeCelaCisla(sc);
		}
		return cislo;
	}
	


	public static void main(String[] args) throws vynimky {	
		// TODO Auto-generated method stub
		Scanner sc=new Scanner(System.in);
		Databaze mojeDatabaze=new Databaze(1);
		int idx;
		float prumer;
		int volba;
		boolean run=true;
		while(run)
		{
			System.out.println("Vyberte pozadovanou cinnost:");
			System.out.println("1 .. vytvoreni nove databaze");
			System.out.println("2 .. vlozeni noveho studenta");
			System.out.println("3 .. nastaveni prumeru studenta");
			System.out.println("4 .. vypis informace o studentovi");
			System.out.println("5 .. vypis celej databazi");
			System.out.println("6 .. ulozenie do suboru");
			System.out.println("8 .. ukoncenie aplikacie");
			volba=pouzeCelaCisla(sc);
			switch(volba)
			{
				case 1:
					System.out.println("Zadejte pocet studentu");
					try {
						mojeDatabaze=new Databaze(sc.nextInt());
					}
					catch(NegativeArraySizeException e) {
						System.out.println("Zadali ste zapornu hodnota");
					}
					catch(java.util.InputMismatchException e) {
						System.out.println("zadali ste nespravny vstup");
					}
					break;
				case 2:
					try {
						mojeDatabaze.setStudent();
					}
					catch(ArrayIndexOutOfBoundsException e) {
						System.out.println("Databaza je plna");
					}
					catch(java.util.InputMismatchException e) {
						System.out.println("zadali ste nespravny vstup");
					}
					break;
				case 3:
					System.out.println("Zadejte index a prumer studenta");
					try {
						idx=pouzeCelaCisla(sc);
						prumer =sc.nextFloat();
						mojeDatabaze.setPrumer(idx,prumer);
					}
					catch(vynimky e) {
						System.out.println(e);
					}
					catch(ArrayIndexOutOfBoundsException e) {
						System.out.println("Zadali ste index mimo databazy");
					}
					catch(NullPointerException e) {
						System.out.println("nevytvorili ste databazu");
					}
					catch(java.util.InputMismatchException e) {
						System.out.println("zadali ste nespravny vstup");
					}
					break;
				case 4:
					System.out.println("Zadejte index studenta");
					try {
						idx=pouzeCelaCisla(sc);
						Student info=mojeDatabaze.getStudent(idx);
						System.out.println("Jmeno: " + info.getJmeno() + " rok narozeni: " + info.getRocnik() + " prumer: " + info.getStudijniPrumer());
			        } 
					catch(ArrayIndexOutOfBoundsException e) {
						System.out.println("Zadali ste index mimo databazy");
					}
					catch(NullPointerException e) {
						System.out.println("Nevytvorili ste databazu");
					}
					catch(java.util.InputMismatchException e) {
						System.out.println("Zadali ste nespravny vstup");
					}
					break;
				case 5:
					mojeDatabaze.VypisDatabazi();
					break;
				case 6:
					System.out.println("Zadajte nazov suboru");
					if (mojeDatabaze.ulozDatabazu(sc.next()))
						System.out.println("Databaza ulozena");
					else
						System.out.println("Databazu nebolo mozne ulozit");
					break;	
				case 7:
					System.out.println("Zadajte nazov souboru");
					if (mojeDatabaze.nacitajDatabazu(sc.next()))
						System.out.println("Databaza nacitana");
					else
						System.out.println("Databazu nebolo mozne nacitat");
					break;	
				default:
					run=false;
					break;
			}
			
		}
	}

}
