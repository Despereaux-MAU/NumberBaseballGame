package despereaus_numberbaseballgame.NumberBaseballGame.Level2;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;

        while (!exit) {
            System.out.println("환영합니다! 원하시는 번호를 입력해주세요");
            System.out.println("1. 게임 시작하기 2. 게임 기록보기(미구현) 3. 종료하기");

            try {
                int choice = scanner.nextInt();

                if (choice == 1) {
                    // 게임 시작
                    StartGame startGame = new StartGame();
                    startGame.startGame(scanner);
                } else if (choice == 2) {
                    System.out.println("아직 미구현된 기능입니다.");
                    exit = true;
                } else if (choice == 3) {
                    System.out.println("게임을 종료합니다.");
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