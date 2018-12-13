package de.slfw.handling.tasks;


import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Timer;
import java.util.Vector;

public class TaskManager  {

	private Timer taskmanager;
	private Vector<MyTimedTask> tasks = new Vector<MyTimedTask>();
	private int id;
	
	public TaskManager(){
		taskmanager = new Timer();
		id=1;
	}
	
	public void runOnce(MyTimedTask task, Time leftTime) throws Exception{	
		try{
			taskmanager.schedule(task, new Date(GregorianCalendar.getInstance().getTimeInMillis() + leftTime.getMiliOfTime()));
			addTask(task);
		} catch (Exception e) {
			throw e;
		}
	}
	
	public void runOnce(MyTimedTask task, Date date) throws Exception{		
		try{
			taskmanager.schedule(task, date);
			addTask(task);
		} catch (Exception e) {
			throw e;
		}	
	}
	
	public void runAtFixTime(MyTimedTask task, Time intervall, Time leftTime) throws Exception{
	
		try{
			taskmanager.schedule(task, new Date(GregorianCalendar.getInstance().getTimeInMillis() + leftTime.getMiliOfTime()), intervall.getMiliOfTime());
			addTask(task);
		} catch (Exception e) {
			throw e;
		}
	}
	
	public void runAtFixTime(MyTimedTask task, Time intervall, Date date) throws Exception {
		try{
			taskmanager.schedule(task, date, intervall.getMiliOfTime());
			addTask(task);
		} catch (Exception e) {
			throw e;
		}
	}
	
	public void runWithIntervall(MyTimedTask task, Time intervall) throws Exception{
		try {
			taskmanager.schedule(task, new Date(GregorianCalendar.getInstance().getTimeInMillis()), intervall.getMiliOfTime());	
			addTask(task);
		} catch (Exception e) {
			throw e;
		}
	}

	private void addTask(MyTimedTask task){
		task.setCanceled(false);
		task.setId(id);
		tasks.add(task);
		id++;
	}
	
	public boolean cancelTaskById(int id){
		MyTimedTask task = getTask(id);
		return cancelTask(task);
	}
	
	public boolean cancelTaskByTask(MyTimedTask task){
		return cancelTask(task);
	}
	
	private boolean cancelTask(MyTimedTask task){
		if(task!=null){
			boolean success = task.cancel();
			if(!task.isCanceled()){
				task.setCanceled(success);
			}
			return success;
		}
		return false;		
	}
	
	
	public MyTimedTask getTask(int id){
		for(MyTimedTask task : tasks ){
			if(task.getId()==id){
				return task;
			}
		}
		return null;
	}
	
	
	
	
	public synchronized List<String> getOutputLines(){

		ArrayList<String> entries = new ArrayList<String>();
		entries.add("Folgende " + tasks.size() + " Tasks sind zur Zeit registriert:");
		
		StringBuffer output;

		for(MyTimedTask task : tasks ){
			output = new StringBuffer();
			output.append("ID:");
			output.append(task.getId());
			output.append(", Name:");
			output.append(task.getName());
			output.append(", canceled:");
			output.append(task.isCanceled());
			entries.add(output.toString());
		}
		return entries;
	}
	
	public void removeCanceledTasks(){
		int index=0;
		for(int i=0, size=tasks.size(); i<size; i++){	
			if(tasks.get(index).isCanceled()){
				tasks.remove(index);
			}
			else
			{
				index++;
			}
		}
	}
	
	public synchronized void cancleAllTasks(){
		for(MyTimedTask task : tasks ){
			cancelTaskByTask(task);
		}
		taskmanager.purge();
		//tasks.clear();
	}

	public void closeTaskmanager() {
		cancleAllTasks();
		taskmanager.cancel();		
	}
}
