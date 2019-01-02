package com.biz.blackjack.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

import com.biz.blackjack.vo.BlackJackVO;

public class BlackJackService {

	// BlackJack Project에서 사용할 List 선언
	List<BlackJackVO> deckList;
	List<BlackJackVO> dealerList;
	List<BlackJackVO> userList;
	
	Scanner sc;
	
	public BlackJackService() {
		
		deckList=new ArrayList<>();
		dealerList=new ArrayList<>();
		userList=new ArrayList<>();
		sc=new Scanner(System.in);
		this.makeDeck();
	}
	
	public void makeDeck() {
		
		String[] shapes= {"Diamond","Spade","Clover","Heart"};
		for(String s:shapes) {
			int cardVal=1;
			addDeckList(s,cardVal);
		}
		
	}
	
	public void addDeckList(String shape, int cardVal) {
		
		String[] nums= {"A","2","3","4","5","6","7","8","9","10","J","Q","K"};
		for(String s:nums) {
			BlackJackVO vo=new BlackJackVO();
			vo.setCardShape(shape);
			vo.setCardNum(s);
			if(cardVal>10) cardVal=10;
			vo.setCardVal(cardVal++);
			deckList.add(vo);
		}
	}
	
	public void shuffleDeck() {
		
		Collections.shuffle(deckList);
		
	}
	
	public void startBlackJack() {
		
		menu();
		String strMenu=sc.nextLine();
		System.out.println();
		try {
			int intMenu=Integer.valueOf(strMenu);
			if(intMenu==2) return;
			if(intMenu==1) playBlackJack();
			if(intMenu>2) {
				System.out.println(">> 입력이 잘못되었습니다.");
				System.out.println();
				this.startBlackJack();
			}
		} catch (NumberFormatException e) {
			System.out.println(">> 입력이 잘못되었습니다.");
			System.out.println();
			this.startBlackJack();
		}
	}
	
	public void playBlackJack() {
		
		int index=0;
		this.shuffleDeck();
		System.out.println(">> Start Game!!");
		index=getCards(index);
		index=this.getMoreCard(index);
		while(true) {
			System.out.println(">> 카드를 확인하시겠습니까?");
			System.out.print("1:예/2:아니오>> ");
			String strInput=sc.nextLine();
			try {
				int intInput=Integer.valueOf(strInput);
				if(intInput==1) {
					checkResult();
					break;
				}
				if(intInput==2) index=this.getMoreCard(index);
				if(intInput>2) {
					System.out.println(">> 입력이 잘못되었습니다.");
					System.out.println();
					continue;
				}
			} catch (NumberFormatException e) {
				System.out.println(">> 입력이 잘못되었습니다.");
				System.out.println();
				continue;
			}
		}
	}
	
	public void checkResult() {
		
		int dealerResult=0;
		int userResult=0;
		this.checkDealerCard();
		this.checkUserCard();
		for(BlackJackVO vo:dealerList) {
			dealerResult+=vo.getCardVal();
		}
		for(BlackJackVO vo:userList) {
			userResult+=vo.getCardVal();
		}
		if(userResult>21) {
			System.out.println(">> 게임에서 패배하셨습니다.");
			System.out.println();
			return;
		}
		if(dealerResult>21) {
			System.out.println(">> 게임에서 승리하셨습니다.");
			System.out.println();
			return;
		}
		if(userResult==dealerResult) {
			System.out.println(">> 게임에서 비기셨습니다.");
			System.out.println();
			return;
		}
		if(userResult>dealerResult) {
			System.out.println(">> 게임에서 승리하셨습니다.");
		} else {
			System.out.println(">> 게임에서 패배하셨습니다.");
		}
		System.out.println();
		
	}
	
	public int getMoreCard(int index) {
		
		int t_index=index;
		while(true) {
			this.getDealerCard(t_index++);
			System.out.println(">> 카드를 더 뽑으시겠습니까?");
			System.out.print("1:예/2:아니오>> ");
			String strInput=sc.nextLine();
			System.out.println();
			try {
				int intInput=Integer.valueOf(strInput);
				if(intInput==2) break;
				if(intInput==1) this.getUserCard(t_index++);
				if(intInput>2) {
					System.out.println(">> 입력이 잘못되었습니다.");
					System.out.println();
					continue;	
				}
			} catch (NumberFormatException e) {
				System.out.println(">> 입력이 잘못되었습니다.");
				System.out.println();
				continue;
			}
		}
		index=t_index;
		return index;
	}
	
	public int getUserCard(int index) {
		
		BlackJackVO user=deckList.get(index++);
		userList.add(user);
		System.out.println(">> 유저가 카드를 한장받았습니다.");
		checkUserCard();
		return index;
		
	}
	
	public int getDealerCard(int index) {
		
		while(true) {
			int dealerCheck=0;
			for(BlackJackVO vo:dealerList) {
				dealerCheck+=vo.getCardVal();
			}
			if(dealerCheck<17) {
				BlackJackVO dealer=deckList.get(index++);
				dealerList.add(dealer);
				System.out.println(">> 딜러가 카드를 한장받았습니다.");
			} else {
				break;
			}
		}
		return index;
	}
	
	public int getCards(int index) {
		
		for(int i=0; i<2; i++) {
			BlackJackVO dealer=deckList.get(index++);
			BlackJackVO user=deckList.get(index++);
			dealerList.add(dealer);
			System.out.println(">> 딜러가 카드를 한장받았습니다.");
			userList.add(user);
			System.out.println(">> 유저가 카드를 한장받았습니다.");
			System.out.println();
			checkUserCard();
		}
		return index;
	}
	
	public void checkDealerCard() {
		
		System.out.println();
		System.out.print(">> 딜러카드 : ");
		for(BlackJackVO vo:dealerList) {
			System.out.print(vo.getCardShape()+vo.getCardNum()+" ");
		}
		System.out.println();
	}

	public void checkUserCard() {
		
		System.out.print(">> 유저카드 :  ");
		for(BlackJackVO vo:userList) {
			System.out.print(vo.getCardShape()+vo.getCardNum()+" ");
		}
		System.out.println();
		System.out.println();
	}
	
	public void menu() {
		
		System.out.println("========================================");
		System.out.println("             BlackJack v1.0");
		System.out.println("========================================");
		System.out.print("1:시작/2:종료>> ");
	}
	
	public void viewDeck() {
		
		int i=0;
		for(BlackJackVO vo:deckList) {
			System.out.println(vo);
			i++;
		}
		System.out.println(i);
	}
}
















