package despereaus_numberbaseballgame.Level1;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        BaseballGame baseballGame = new BaseballGame();
        Scanner scanner = new Scanner(System.in);

        boolean iscorrect = false;

        System.out.println("< 게임을 시작합니다 >");

        while (!iscorrect) {
            List<Integer> guess = new ArrayList<>();
            boolean validInput = false;

            while (!validInput) {
                System.out.print("숫자를 입력하세요.(공백으로 구분) : ");

                try {
                    Set<Integer> duplication = new HashSet<>(); // 중복된 숫자 체크
                    for (int i = 0; i < 3; i++) {
                        int input = scanner.nextInt();
                        // 중복된 숫자가 입력되면 예외 발생
                        if (!duplication.add(input)) { // Set에 추가되지 않으면 중복된 숫자
                            throw new IllegalArgumentException("중복된 숫자를 입력하셨습니다. 다시 입력해 주세요.");
                        }
                        guess.add(input); //문자가 입력되면 예외 발생
                    }
                    validInput = true; // 정답이면 루프 종료
                } catch (InputMismatchException e) {
                    System.out.println("문자를 입력하셨습니다. 숫자를 입력해주세요.");
                    scanner.nextLine(); // 잘못된 입력을 버퍼에서 제거
                    guess.clear(); // 잘못된 입력을 다시 받을 수 있도록 리스트 초기화
                } catch (IllegalArgumentException e) {
                    System.out.println(e.getMessage());
                    scanner.nextLine();
                    guess.clear();
                }
            }

            BaseballGame.Result result = baseballGame.checkGuess(guess);


            System.out.println(result.getStrike() + " 스트라이크 " + result.getBall() + " 볼 " + result.getOut() + " 아웃 ");

            iscorrect = baseballGame.isCorrect(result);

            if (iscorrect) {
                System.out.println("정답입니다! 계속하시겠습니까? [나가기 : 'exit']");
                String operation = scanner.next();
                if (operation.equalsIgnoreCase("exit")) {
                    System.out.println("게임을 종료합니다.");
                    break;
                }
            }
        }
        scanner.close();
    }
}