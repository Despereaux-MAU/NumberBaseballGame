package despereaus_numberbaseballgame.NumberBaseballGame.Level2;

import java.util.*;

public class StartGame {
    public void startGame(Scanner scanner) {
        BaseballGame baseballGame = new BaseballGame();
        boolean iscorrect = false;

        System.out.print("< 게임을 시작합니다 >");

        while (!iscorrect) {
            List<Integer> guess = new ArrayList<>();
            boolean correctInput = false;

            while (!correctInput) {

                System.out.print("세자리 숫자를 입력해 주세요 (공백으로 구분) : ");

                try {
                    Set<Integer> duplication = new HashSet<>(); // 중복된 숫자 체크
                    for (int i = 0; i < 3; i++) {

                        // 중복된 숫자가 입력되면 예외 발생
                        int input = scanner.nextInt();

                        if (!duplication.add(input)) { // Set에 추가되지 않으면 중복된 숫자
                            throw new IllegalArgumentException("중복된 숫자를 입력하셨습니다. 다시 입력해 주세요.");
                        }

                        guess.add(input);

                        //문자가 입력되면 예외 발생
                        if (!scanner.hasNextInt()) {
                            throw new InputMismatchException();
                        }
                    }

                    // 3자리수인지 자릿수 검사 및 예외 발생
                    if (guess.size() != 3) {
                        throw new IllegalArgumentException("세자리 숫자만 입력이 가능합니다. 다시 입력해 주세요.");
                    }

                    correctInput = true; // 정답이면 루프 종료

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
                System.out.println("정답입니다!");
            }
        }
    }
}

