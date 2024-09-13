package despereaus_numberbaseballgame.Level1;

import java.util.HashSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class BaseballGame {
    private List<Integer> number;

    public BaseballGame() {
        generateNumber();
    }

    // 랜덤 숫자 생성하기
    private void generateNumber() {
        Set<Integer> numberSet = new HashSet<>(); //HashSet으로 중복 없게
        while (numberSet.size() < 3) {
            int randomNumber = (int) (Math.random() * 9) + 1;
            numberSet.add(randomNumber); // 중복된 값 걸러내기
        }
        number = new ArrayList<>(numberSet); // HashSet을 List로 변경
    }

    // 스트라이크, 볼, 아웃 계산하기
    public Result checkGuess(List<Integer> guess) {
        int strike = 0;
        int ball = 0;
        int out = 0;

        for (int i = 0 ; i < 3 ; i++) {
            if (guess.get(i).equals(number.get(i))) {
                strike++;
            } else if (number.contains(guess.get(i))) {
                ball++;
            } else {
                out++;
            }
        }

        return new Result(strike, ball, out);
    }

    // 정답 여부 확인
    public boolean isCorrect(Result result) {
        return result.getStrike() == 3;
    }

    // 결과(스트라이크, 볼)를 저장하는 클래스
    class Result {
        private int strike;
        private int ball;
        private int out;

        public Result(int strike, int ball, int out) {
            this.strike = strike;
            this.ball = ball;
            this.out = out;
        }

        public int getStrike() {
            return strike;
        }

        public int getBall() {
            return ball;
        }

        public int getOut() {
            return out;
        }
    }
}