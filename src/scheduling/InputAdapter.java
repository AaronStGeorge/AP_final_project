package scheduling;

public interface InputAdapter 
{
	public void changeInput(String[] equipList, Class[] classes);
	public int[][] schedule();
}
