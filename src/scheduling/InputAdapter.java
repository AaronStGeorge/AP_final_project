package scheduling;
/*
 * This is the interface for the input adapters
 * created by Matt Detrick
 * 12/2/14
 */
public interface InputAdapter 
{
	public void changeInput(String[] equipList, Class[] classes);
	public int[][] schedule();
}
