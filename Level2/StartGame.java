package despereaus_numberbaseballgame.NumberBaseballGame.Level2;

import java.util.*;

public class StartGame {
    GameLogic gameLogic = new GameLogic();

    // 게임을 시작하는 메서드
    public void startGame(Scanner scanner) {
        boolean iscorrect = false;

        System.out.println("< 게임을 시작합니다 >");

        while (!iscorrect) {
            List<Integer> guess = new ArrayList<>();
            boolean correctInput = false;

            while (!correctInput) {

                System.out.print("1-9까지의 세자리 숫자를 입력해 주세요 (공백으로 구분) : ");

                try {
                    String inputLine = scanner.nextLine();
                    String[] inputs = inputLine.split(" "); // 공백을 기준으로 입력 분리

                    if (inputs.length != 3) {
                        throw new IllegalArgumentException("세자리 숫자가 아닙니다. 다시 입력해 주세요.");
                    }

                    Set<Integer> duplication = new HashSet<>(); // 중복된 숫자 체크 Set

                    for (String input : inputs) {
                        int num;

                        try {
                            num = Integer.parseInt(input); // 문자열을 숫자로 변환
                        } catch (NumberFormatException e) {
                            throw new IllegalArgumentException("1-9 사이의 숫자만 입력이 가능합니다. 다시 입력해 주세요.");
                        }

                        // 중복된 숫자(Set에 추가되지 않음)가 입력되면 예외 발생
                        if (!duplication.add(num)) {
                            throw new IllegalArgumentException("중복된 숫자를 입력하셨습니다. 다시 입력해 주세요.");
                        }
                        // 1-9 사이의 숫자만 입력이 가능
                        if (num < 1 || num > 9) {
                            throw new IllegalArgumentException("숫자는 1부터 9까지 입력이 가능합니다. 다시 입력해 주세요.");
                        }

                        guess.add(num);
                    }
                    correctInput = true; // 정답이면 루프 종료

                } catch (IllegalArgumentException e) {
                    System.out.println(e.getMessage());
                }
            }
           Result result = gameLogic.checkGuess(guess);

            System.out.println(result.getStrike() + " 스트라이크 " + result.getBall() + " 볼 " + result.getOut() + " 아웃 ");

            iscorrect = gameLogic.isCorrect(result);

            if (iscorrect) {
                System.out.println("정답입니다!");
            }
        }
    }
}

