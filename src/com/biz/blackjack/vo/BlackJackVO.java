package com.biz.blackjack.vo;

// BlackJack Project에서 사용할 VO Class 생성
public class BlackJackVO {
	
	private String cardShape;	// 카드모양		
	private String cardNum;		// 카드숫자
	private int cardVal;		// 카드 값
	
	// 외부에서 접근할 Getter와 Setter Method 생성
	public String getCardShape() {
		return cardShape;
	}
	public void setCardShape(String cardShape) {
		this.cardShape = cardShape;
	}
	public String getCardNum() {
		return cardNum;
	}
	public void setCardNum(String cardNum) {
		this.cardNum = cardNum;
	}
	public int getCardVal() {
		return cardVal;
	}
	public void setCardVal(int cardVal) {
		this.cardVal = cardVal;
	}
	// 입력된 값을 확인하기 위한 toString Override
	@Override
	public String toString() {
		return "BlackJackVO [cardShape=" + cardShape + ", cardNum=" + cardNum + ", cardVal=" + cardVal + "]";
	}
	
	

}
