/**
 * 
 */
package com.sist.user.domain;

/**
 * 등급: BASIC,SILVER,GOLD
 * @author sist_
 *
 */
public enum Level {
	//생성자 코딩시 최종Level 순선대로 작성 
	GOLD(3,null),SILVER(2,GOLD),BASIC(1,SILVER);
	
	private final int value;
	private final Level next;//다음단계 Level
	
	public Level getNext() {
		return next;
	}

	public int getValue() {
		return value;
	}

	Level(int value,Level next){
		this.value = value;
		this.next = next;
	}
	
	public static Level valueOf(int value) {
		switch(value) {
			case 1: return BASIC;
			case 2: return SILVER;
			case 3: return GOLD;
			default:
			throw new AssertionError("UnKnown value:"+value);
		}
		
	}
	
	
}
