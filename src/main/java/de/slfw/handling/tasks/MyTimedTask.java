package de.slfw.handling.tasks;

import java.util.Date;
import java.util.TimerTask;

/**
 * 
 * <p>Extends the TimerTask and adds some functionality.</p>
 * 
 * <p>The {@link TaskManager} can only bookmark {@link MyTimedTask}s</p>
 *
 * @author Alex S.
 * 
 *
 */
public abstract class MyTimedTask extends TimerTask{

	private volatile boolean canceled;
	private int id;
	protected String name;
	private Date lastRun;
	
	
	
	/**
	 * @return the canceled
	 */
	public boolean isCanceled() {
		return canceled;
	}

	/**
	 * @param canceled the canceled to set
	 */
	public void setCanceled(boolean canceled) {
		this.canceled = canceled;
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 
	 * @return the Date of the last run
	 */
	public Date getLastRun() {
		return lastRun;
	}
	
	/**
	 * The run method calls the doRun() method
	 */
	public void run() {
		lastRun=new Date();
		doRun();
	}
	
	public abstract void doRun();
	



}
