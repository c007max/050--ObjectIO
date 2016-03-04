import java.util.Random;
import java.util.LinkedList;

public class IOProcess extends Thread
{
	String	nameT ;
	Random	r1 =	new Random();
	private final PCB pcb;
	private final LinkedList<PCB> QReady ;
	
	public IOProcess	(String n0
						,PCB pcb0
						,LinkedList<PCB> qr0
						)
	{
		this.nameT	= n0 ;
		this.pcb	= pcb0 ;
		this.QReady	= qr0 ;
	}
	
	public void run() 
	{
		pcb.set_state("Running");
		QReady.add(pcb);
		
		System.out.printf("\t** IOProcess %s start for ID: %d**\n"	
				,this.nameT
				,pcb.get_ID()
				);
		
		try 
		{
			pcb.add_CPU_used(r1.nextInt(20)+10);
			Thread.sleep((r1.nextInt(500)+1000)) ;
		} 
		catch (InterruptedException e) 
		{
			e.printStackTrace();
		}
		
		System.out.printf("\t** IOProcess "
				+ "%s end for ID: %d"
				+ "\tCPU used: %d"
				+ "\tCPU left %d\t**\n"
				,this.nameT
				,pcb.get_ID()
				,pcb.get_CPU_used()
				,pcb.get_CPU_left()
				);
	}
}
