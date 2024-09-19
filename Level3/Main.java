package despereaus_numberbaseballgame.NumberBaseballGame.Level3;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;
        StartGame game = new StartGame();

        while (!exit) {
            System.out.println("환영합니다! 원하시는 번호를 입력해주세요");
            System.out.println("1. 게임 시작하기 2. 게임 기록보기 3. 종료하기");

            try {
                int choice = scanner.nextInt();

                if (choice == 1) {
                    scanner.nextLine(); //입력 버퍼 비우기
                    game.startGame(scanner); // 게임 시작
                } else if (choice == 2) {
                    game.showGameRecords(); // 게임 기록 보기
                } else if (choice == 3) {
                    System.out.println("< 숫자 야구 게임을 종료합니다. >");
                    exit = true;
                } else {
                    System.out.println("잘못된 입력입니다. 1, 2, 3 중에 원하시는 번호를 입력해주세요");
                }
            } catch (InputMismatchException e) {
                System.out.println("잘못된 입력입니다. 숫자만 입력해주세요."); //예외 처리
                scanner.nextLine(); // 입력 버퍼 비우기
            }
        }
        scanner.close();
    }
}