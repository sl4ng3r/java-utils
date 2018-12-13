package de.sltest.bean;

public class MyBean {

	private Integer myId;
	private String name;
	
	public MyBean() {
		// TODO Auto-generated constructor stub
	}
	
	
	
	
	public MyBean(Integer myId, String name) {
		super();
		this.myId = myId;
		this.name = name;
	}




	/**
	 * @return the myId
	 */
	public Integer getMyId() {
		return myId;
	}
	/**
	 * @param myId the myId to set
	 */
	public void setMyId(Integer myId) {
		this.myId = myId;
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
	
	
	
	
}
