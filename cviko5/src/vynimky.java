
public class vynimky extends java.lang.Exception  {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public vynimky(){
		super("priemer ma byt od 1 do 5");
	}
	public vynimky(String a){
		super(a);
	}
}
