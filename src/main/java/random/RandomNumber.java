package random;

public class RandomNumber {
    public static int getRandomIntegerBetweenRange(int min, int max){
        int x = (int) ((Math.random()*((max-min)+1))+min);
        return x;
    }
}