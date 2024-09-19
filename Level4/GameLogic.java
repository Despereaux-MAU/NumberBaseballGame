package despereaus_numberbaseballgame.NumberBaseballGame.Level4;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class GameLogic {

    // 랜덤 숫자(정답) 생성하기
    public List<Integer> generateNumber(int difficulty) {
        Set<Integer> numberSet = new HashSet<>(); //HashSet으로 중복 없게
        while (numberSet.size() < difficulty) {
            int randomNumber = (int) (Math.random() * 9) + 1;
            numberSet.add(randomNumber); // 중복된 값 걸러내기
        }
        return new ArrayList<>(numberSet); // HashSet을 List로 변환해서 반환
    }

    // 스트라이크, 볼, 아웃 계산하기
    public Result checkGuess(List<Integer> guess, List<Integer> number) {
        int strike = 0;
        int ball = 0;
        int out = 0;

        for (int i = 0 ; i < guess.size() ; i++) {
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
    public boolean isCorrect(Result result, int answerLength) {
        return result.getStrike() == answerLength;
    }
}
