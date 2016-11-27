package com.taky.and.wendy.common;

public enum Condition {
	SUSPEND("suspend"), 
	NORMAL("normal"), 
	DELETE("delete");
	
	private String condition;

	private Condition(String condition) {
		this.condition = condition;
	}
	
	public String getCondition() {
		return condition;
	}
}
