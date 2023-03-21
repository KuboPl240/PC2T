
public class Student {
	public Student(String jmeno, int rocnik)
	{
		this.jmeno=jmeno;
		this.rocnik=rocnik;
	}
	
	public String getJmeno()
	{
		return jmeno;
	}
	
	public int getRocnik()
	{
		return rocnik;
	}
	
	public float getStudijniPrumer()throws vynimky{
		if(studijniPrumer==0)throw new vynimky("priemerNenastaveny"); 
		return studijniPrumer;
	}

	public void setStudijniPrumer(float studijniPrumer) throws vynimky{
		if((studijniPrumer<1) || (studijniPrumer>5)) {
			throw new vynimky(); 
		}
		this.studijniPrumer = studijniPrumer;
	}

	private String jmeno;
	private int rocnik;
	private float studijniPrumer;
}