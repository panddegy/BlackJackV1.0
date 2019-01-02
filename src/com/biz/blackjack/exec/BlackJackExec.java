package com.biz.blackjack.exec;

import java.util.Scanner;

import com.biz.blackjack.service.BlackJackService;

public class BlackJackExec {

	public static void main(String[] args) {
		
		BlackJackService bs=new BlackJackService();
		Scanner sc=new Scanner(System.in);
		
		while(true) {
			bs.startBlackJack();
			System.out.println(">> 계속 하시겠습니까?");
			System.out.print("1:예/2:아니오>> ");
			String strInput=sc.nextLine();
			try {
				int intInput=Integer.valueOf(strInput);
				if(intInput==2) break;
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
}
