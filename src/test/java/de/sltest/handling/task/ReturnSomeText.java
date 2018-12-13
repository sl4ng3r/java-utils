package de.sltest.handling.task;

import de.slfw.handling.tasks.MyTimedTask;

public class ReturnSomeText extends MyTimedTask {


	
	private String msg;
	public ReturnSomeText(String msg, String name){
		this.msg = msg;
		setName(name);
		
	}
	
	
	
	@Override
	public void doRun() {
		System.out.println(msg);
	}

}
