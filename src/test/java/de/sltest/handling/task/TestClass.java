package de.sltest.handling.task;
import java.util.GregorianCalendar;
import java.util.List;

import de.slfw.handling.tasks.TaskManager;
import de.slfw.handling.tasks.Time;
import de.slfw.io.Terminal;

public class TestClass {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		

		boolean breakbool = true;
		TaskManager manager = null;
		GregorianCalendar cal = new GregorianCalendar();

		manager = new TaskManager();
		
			while(breakbool){
				msg2();
				try {
					switch(Terminal.readInt()){
					case 1:
						manager.runWithIntervall(new ReturnSomeText("Erster Task", "E-mails abrufen"), new Time(0,0,0,15) );
						System.out.println("\nTask gestartet!\n");
						break;
						
					case 2:
						//manager.runWithIntervall(new ReturnSomeText("Erster Task", "E-mails abrufen"), new Time(0,0,0,2) );
						manager.runAtFixTime(new DataBaseOperations("Zweiter Task"),new Time(0,0,0,35), new Time(0,0,0,10));
						System.out.println("\nTask gestartet!\n");
						break;
						
					case 3:
						List<String> l =  manager.getOutputLines();
						for(String s :l){
							System.out.println(s);
						}
						break;
					case 4:
						System.out.println("Welcher Task soll beendet werden(ID): ");
						manager.cancelTaskById(Terminal.readInt());
						System.out.println("Task beendet!");
						

						break;
					
					case 5:
						Time thetime = new Time(0,23,59,90);
						thetime.addTime(0,23,59,30,0);
						System.out.println(thetime.toString() );
						
						System.out.println("\nMilis " + cal.getInstance().getTimeInMillis() );
					
					break;
					
					case 6:
						manager.removeCanceledTasks();
							
						break;
					
					
					case 7:
						manager.cancleAllTasks();
						break;
						
					case 8:
						System.out.println("Programm beendet");
						manager.closeTaskmanager();
						breakbool = false;
						
						break;
					default:
						
						break;

					}
					
				} 
				catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} 
			}
		 
		

	}


	
	private static void msg2(){
		System.out.print("Bitte auswaehlen:\n" +
				"1. Ersten Task Starten\n" +
				"2. Zweiten Task starten\n"+
				"3. Uebersicht\n" +
				"4. Task beenden\n" +
				"5. Test\n" +
				"6. Inaktive Tasks entfernen\n" +
				"7. Alle Tasks beenenden\n" +
				"8. Programm schlieﬂen\n" +
				"Eingabe:");
	}
	
}
