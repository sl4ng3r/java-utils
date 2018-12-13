package de.slfw.handling.tasks;

public class Time {

	private long milis;
	private long second;
	private int minute;
	private int hour;
	private long day;

	
	
	public Time(){
		this.second= 0;
		this.minute= 0;
		this.hour = 0;
		this.day = 0;
		this.milis=0;
	}
	
	
	public Time(long day, int hour, int minute, long second){
		
		this.second = second;
		this.minute  = minute;
		this.hour  = hour;
		this.day 	 = day;
		this.milis = 0;
		updateTime();

	}
	
	public Time(long milis){
		this.milis=milis;
		updateTime();
	}
	

	
	
	/**
	 * @return Returns the minute.
	 */
	public int getMinute() {
		return minute;
	}

	/**
	 * @param minute The minute to set.
	 */
	public void setMinute(int minute) {
		this.minute = minute;
		updateTime();
	}

	/**
	 * @return Returns the sekunde.
	 */
	public long getSecond() {
		return second;
	}

	/**
	 * @param second The sekunde to set.
	 */
	public void setSecond(long second) {
		this.second = second;
		updateTime();
	}

	/**
	 * @return Returns the hour.
	 */
	public int getHour() {
		return hour;
	}

	/**
	 * @param hour The hour to set.
	 */
	public void setHour(int hour) {
		this.hour = hour;
		updateTime();
	}

	/**
	 * @return Returns the day.
	 */
	public long getDay() {
		return day;
	}
	
	/**
	 * @param day The day to set.
	 */
	public void setDay(long day) {
		this.day = day;
	}
	
	

	/**
	 * @return the milis
	 */
	public long getMilis() {
		return milis;
	}


	/**
	 * @param milis the milis to set
	 */
	public void setMilis(long milis) {
		this.milis = milis;
		updateTime();
	}


	public long getMiliOfTime(){
		return milis + calcToMilisFromSeconds(this.second) + calcToMilisFromMinutes(this.minute)+ 
				calcToMilisFromMinutes(this.hour * 60) + calcToMilisFromMinutes(this.day * 24 * 60);
	}
	
	
	
	@Override
	public String toString() {
		return this.day + "d " + this.hour + "h " + this.minute + "m " + this.second + "s";
	}


	
	public void addTime(int day, int hour, int minute, long second, long milis) {
		this.milis = this.milis + milis;
		this.second = this.second + second;
		this.minute = this.minute + minute;
		this.hour = this.hour + hour;
		this.day = this.day + day;
		updateTime();
	}
	


	public static long calcToMilisFromMinutes(long minutes){
		return minutes * 60 * 1000;
	}
	
	public static long calcToMilisFromSeconds(long seconds){
		return seconds * 1000;
	}
	
	private void updateTime(){
		double completeSeconds = day * 24*60*60*1000 + hour * 60* 60 *1000 + minute * 60 *1000 + second *1000 + milis;
		
		day = (long) ( completeSeconds / (24*60*60*1000));
		completeSeconds = completeSeconds - day *24*60*60*1000;
		hour = (int)( completeSeconds / (60*60*1000));
		completeSeconds = completeSeconds - hour * (60* 60*1000) ;
		minute = (int)(completeSeconds / (60*1000));
		completeSeconds = completeSeconds - minute * (60 *1000);
		second = (long)(completeSeconds / (1000));
		milis = (long)completeSeconds - second * 1000; 
	}
	
	
	
	
	
}
