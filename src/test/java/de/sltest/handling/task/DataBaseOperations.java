package de.sltest.handling.task;
import de.slfw.handling.tasks.MyTimedTask;




public class DataBaseOperations extends MyTimedTask {
	
	public DataBaseOperations(String name){
		setName(name);
	}
	

	@Override
	public void doRun() {
		for(int i=0;i<20;i++){
			System.out.println("Datenbank Operationen durchgeführt");
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}	
}