package despereaus_numberbaseballgame.NumberBaseballGame.Level4;

import java.util.*;

public class StartGame {
    private final GameLogic gameLogic = new GameLogic();
    private int gameCount = 0; // 총 게임 횟수
    private List<Integer> attemptsGame = new ArrayList<>(); // 각 게임의 시도 횟수 기록
    private int difficulty = 3; // 기본 난이도는 3

    // 게임을 시작하는 메서드
    public void startGame(Scanner scanner) {
        boolean iscorrect = false;
        List<Integer> number = gameLogic.generateNumber(difficulty); //(난이도에 따른)정답 생성
        gameCount++; // 게임 횟수 증가
        int attempts = 0; // 시도 횟수

        System.out.println("< 게임을 시작합니다 >");

        while (!iscorrect) {
            attempts++; // 시도 횟수 증가
            List<Integer> guess = new ArrayList<>();
            boolean correctInput = false;

            while (!correctInput) {

                System.out.print("1-9까지의 " + difficulty + "자릿수 숫자를 입력해 주세요 (공백으로 구분) : ");

                try {
                    String inputLine = scanner.nextLine();
                    String[] inputs = inputLine.split(" "); // 공백을 기준으로 입력 분리

                    if (inputs.length != difficulty) {
                        throw new IllegalArgumentException(difficulty + "자리 숫자가 아닙니다. 다시 입력해 주세요.");
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

            iscorrect = gameLogic.isCorrect(result, difficulty); // 정답 여부 확인

            if (iscorrect) {
                System.out.println("정답입니다! 총 " + attempts + "번의 시도로 맞추셨습니다.");
            }
        }

        attemptsGame.add(attempts); // 해당 게임의 시도 횟수 기록
    }

    // 게임 기록을 보여주는 메소드
    public void showGameRecords() {
        for (int i = 0 ; i < attemptsGame.size() ; i++) {
            System.out.println((i + 1) +"번째 게임 : 시도 횟수 - " + attemptsGame.get(i));
        }
    }

    // 난이도를 조절하는 메소드
    public void adjustDifficulty(Scanner scanner) {
        boolean correctInput = false;

        while (!correctInput) {
            System.out.println("설정하고자 하는 자리수를 입력하세요.(3-5)");
            try {
                int newDifficulty = scanner.nextInt();
                if (newDifficulty >= 3 && newDifficulty <= 5) {
                    difficulty = newDifficulty;
                    System.out.println(difficulty + "자리수 난이도로 설정되었습니다.");
                    correctInput = true;
                } else {
                    System.out.println("3, 4, 5 중에서 선택하세요.");
                }
            } catch (InputMismatchException e) {
                System.out.println("잘못된 입력입니다. 숫자만 입력하세요.");
                scanner.nextLine(); // 입력 버퍼 비우기
            }
        }
    }
}