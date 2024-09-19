package despereaus_numberbaseballgame.NumberBaseballGame.Level4;

//결과를 저장하는 클래스
public class Result {
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
