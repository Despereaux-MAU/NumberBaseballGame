package despereaus_numberbaseballgame.NumberBaseballGame.Level3;

import java.util.*;

public class StartGame {
    private final despereaus_numberbaseballgame.NumberBaseballGame.Level3.GameLogic gameLogic = new GameLogic();
    private int gameCount = 0; // 총 게임 횟수
    private List<Integer> attemptsGame = new ArrayList<>(); // 각 게임의 시도 횟수 기록

    // 게임을 시작하는 메서드
    public void startGame(Scanner scanner) {
        boolean iscorrect = false;

        List<Integer> number = gameLogic.generateNumber(); //정답 생성
        gameCount++; // 게임 횟수 증가
        int attemps = 0; // 시도 횟수

        System.out.println("< 게임을 시작합니다 >");

        while (!iscorrect) {
            attemps++; // 시도 횟수 증가
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
           Result result = gameLogic.checkGuess(guess, number); // 정답과 비교

            System.out.println(result.getStrike() + " 스트라이크 " + result.getBall() + " 볼 " + result.getOut() + " 아웃 ");

            iscorrect = gameLogic.isCorrect(result); // 정답 여부 확인

            if (iscorrect) {
                System.out.println("정답입니다! 총 " + attemps + "번의 시도로 맞추셨습니다.");
            }
        }

        attemptsGame.add(attemps); // 해당 게임의 시도 횟수 기록
    }
    // 게임 기록을 보여주는 메소드
    public void showGameRecords() {
        for (int i = 0 ; i < attemptsGame.size() ; i++) {
            System.out.println((i + 1) +"번째 게임 : 시도 횟수 - " + attemptsGame.get(i));
        }
    }
}