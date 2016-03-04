import java.util.LinkedList;
import java.util.Random;
import java.util.Collections ;
import java.io.*;

public class IOThread_MAIN
{
	@SuppressWarnings("unchecked")
	public static void main(String[] args) throws InterruptedException, IOException 
	{
		int nodes_T	= 10 ;
		Random r1 = new Random();
		PCB	pcbRunning = null ;
		
		LinkedList<PCB> QReady	= new LinkedList<PCB>();
		
		ObjectOutputStream qrOut = null;
		
		try {
			qrOut = new ObjectOutputStream
					(	new FileOutputStream("qready.txt")	);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		for (int ii=0; ii<nodes_T; ii++)
		{
			QReady.add(new PCB());
		}
		
		try {
			qrOut.writeObject((LinkedList<PCB>)QReady);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Collections.sort(QReady);

		try {
			qrOut.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		qrOut.close();
		
		for (PCB loopI : QReady)
			System.out.printf("***@ sort main: %s\t***\n"	,loopI.showPCB()) ;
		
		while (!QReady.isEmpty())
		{
			pcbRunning	= QReady.removeFirst();

			Thread iop	= new Thread(new IOProcess(Integer.toString(pcbRunning.get_ID())
					,pcbRunning
					,QReady
					));

			iop.start();

			System.out.printf("***\tmain: thread started %s %d %s\t***\n"
					,iop.getName()
					,iop.getId()
					,iop.getState()
					);
		}
		
		while (Thread.activeCount() > 1)
		{
			System.out.printf("***\tmain: threads still running: %d\t***\n"
				,Thread.activeCount()
				);
			
			Thread.sleep(1000);
		}
		
//		for (PCB loopI : QReady)
//			System.out.printf("***main: %s\t***\n"	,loopI.showPCB()) ;
//
//		Collections.sort(QReady);
//	
//		for (PCB loopI : QReady)
//			System.out.printf("***@ sort main: %s\t***\n"	,loopI.showPCB()) ;
//
//		System.out.printf("@@@\tdone\t@@@\n");
//		
//		QReady.clear();
		
		ObjectInputStream qrIn;
		try {
			qrIn = new ObjectInputStream
					(
						new FileInputStream("qready.txt")
					);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

//		for (int ii=0; ii<nodes_T; ii++)
//		{
//			try {
//				System.out.println((String) qrIn.readObject());
//			} catch (ClassNotFoundException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		}
//
//		qrIn.close();
	}
}
