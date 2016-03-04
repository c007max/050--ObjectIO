import java.util.Random ; 

public class CPU_event	// this class returns a result from a range by percentage
{
	private double	event ;		
	private int	rangeResult ;
	final static int pct05 = 1 ;	final static int pct10	= 2 ; 
	final static int pct15	= 3 ;	final static int pct20	= 4 ;
	final static int pct50	= 5 ;	
	
	final static double r05	= -1.645 ; 	final static double r10	=  -1.036 ;
	final static double r15 = -0.524 ;	final static double r20	= 	0 ;
	
	public void CPU_event()
	{
	
	}
	
	public int get_CPU_event()
	{
		Random random__X	= new Random();	//#010 declare random object
		
		event = random__X.nextGaussian();	
		//#020 instantiate the object and assign to: event
		
		if (event < r05)
			return pct05 ;
		else if (event < r10)
			return pct10 ;
		else if (event < r15)
			return pct15 ;
		else if (event < r20)
			return pct20 ;
		else 
			return pct50 ;
	}
}