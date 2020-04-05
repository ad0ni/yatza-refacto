import java.util.Arrays;

public class Yatzy {

    protected int[] dice;

    public Yatzy(int d1, int d2, int d3, int d4, int d5) {
        dice = new int[5];
        dice[0] = d1;
        dice[1] = d2;
        dice[2] = d3;
        dice[3] = d4;
        dice[4] = d5;
    }

    /**
     * Implementation of the chance score.
     * @return Sum of values.
     */
    public int chance() {
        return Arrays.stream(dice).sum();
    }

    /**
     * Check if a value is diced 5 times. 5 same dice will give a score of 50.
     * @return 50 or 0 as default value.
     */

    public int yatzy() {
        return CheckIfANumberWasDiceEnoughTime(initDiceCounts(), 5) == 0 ? 0 : 50;
    }

    /**
     * Check if a number is diced 5 times. If it's the case the score is its value multiply by 5.
     * @param numberDice number to check
     * @return the score value or 0 as default value.
     */
    private int handleFiveSameDice(int numberDice) {
        return Arrays.stream(dice)
                .filter(d -> d == numberDice)
                .sum();
    }

    /**
     * Check if the number 1 was diced 5 times. In this case the score is 5.
     * @return 5 if all are 1 otherwise 0.
     */
    public int ones() {
        return handleFiveSameDice(1);
    }

    /**
     * Check if the number 2 was diced 5 times. In this case the score is 10.
     * @return 10 if all are 2 otherwise 0.
     */
    public int twos() {
        return handleFiveSameDice(2);
    }

    /**
     * Check if the number 3 was diced 5 times. In this case the score is 15.
     * @return 15 if all are 3 otherwise 0.
     */
    public int threes() {
        return handleFiveSameDice(3);
    }

    /**
     * Check if the number 4 was diced 5 times. In this case the score is 20.
     * @return 20 if all are 4 otherwise 0.
     */
    public int fours() {
        return handleFiveSameDice(4);
    }

    /**
     * Check if the number 5 was diced 5 times. In this case the score is 25.
     * @return 25 if all are 5 otherwise 0.
     */
    public int fives() {
        return handleFiveSameDice(5);
    }

    /**
     * Check if the number 6 was diced 5 times. In this case the score is 30.
     * @return 30 if all are 6 otherwise 0.
     */
    public int sixes() {
        return handleFiveSameDice(6);
    }

    /**
     * Check dice to see if a number was dice at least 2 times (= pair).
     * If there is more than 1 pair, the biggest pair is use to calculate the score
     * which is equal to the pair value multiply by 2.
     * @return score value or 0 if there is no pair.
     */
    public int scorePair() {
        int[] counts = initDiceCounts();
        for (int at = 0; at != 6; at++) {
            if (counts[6-at-1] >= 2) {
                return (6-at)*2;
            }
        }
        return 0;
    }

    /**
     * Initialize an array with dice count.
     * For each index of the array, the corresponding value represent how many times the number equal to index+1 was diced.
     * @return an array of size 6.
     */
    private int[] initDiceCounts() {
        int[] counts = new int[6];
        Arrays.stream(dice)
                .filter(die -> die >= 1)
                .forEach(die -> counts[die - 1]++);
        return counts;
    }

    /**
     * Check dice to see if there is 2 numbers which appears at least 2 times.
     * The score is then the sum of each number multiply by 2.
     * @return score if there is 2 pair otherwise 0.
     */
    public int twoPair() {
        int[] counts = initDiceCounts();
        int n = 0, score = 0;

        for (int i = 0; i < 6; i += 1) {
            if (counts[6-i-1] >= 2) {
                n++;
                score += (6-i);
            }
        }
        if (n == 2)
            return score * 2;
        else
            return 0;
    }

    /**
     * Check dice to see if a number was dices 3 times.
     * If it's the case the score is its value multiply by 3.
     * @return the score in case of 3 same dice otherwise 0.
     */
    public int threeOfAKind() {
        return CheckIfANumberWasDiceEnoughTime(initDiceCounts(), 3) * 3;
    }

    /**
     * Check dice to see if a number was dices 4 times.
     * If it's the case the score is its value multiply by 4.
     * @return the score in case of 3 same dice otherwise 0.
     */
    public int fourOfAKind() {
        return CheckIfANumberWasDiceEnoughTime(initDiceCounts(), 4) * 4;
    }

    /**
     * Check in dice if a value was dice at least a number of time.
     * @param counts array with dice counts
     * @param numberOfDice number of dice to find
     * @return the number dice input numberOfDice times
     */
    private int CheckIfANumberWasDiceEnoughTime(int[] counts, int numberOfDice) {
        for (int i = 0; i < 6; i++) {
            if (counts[i] >= numberOfDice) {
                return (i + 1);
            }
        }
        return 0;
    }

    /**
     * Check if 4 first dice was consecutive numbers (= small straight).
     * @return 15 if there is small straight otherwise 0.
     */
    public int smallStraight() {
        return checkForStraight(initDiceCounts(), 0, 5) ? 15: 0;
    }

    /**
     * Check dice counts to see if 4 consecutive numbers (= straight) was diced.
     * @param counts array containing info about dice
     * @param firstPosition first position for the consecutive check
     * @param lastPosition last position for the consecutive check
     * @return true if there is a straight
     */
    private boolean checkForStraight(int[] counts, int firstPosition, int lastPosition) {
        for(int i = firstPosition; i < lastPosition; i++) {
            if(counts[i] != 1) {
                return false;
            }
        }
        return true;
    }

    /**
     * Check if 4 last dice was consecutive numbers (= large straight).
     * @return 20 if there is small straight otherwise 0.
     */
    public int largeStraight() {
        return checkForStraight(initDiceCounts(), 1, 6) ? 20: 0;
    }

    /**
     * Check if 1 number was diced 2 times and another was diced 3 times (= full house).
     * In this case the score is the first multiply by 2 and the second multiply by 3.
     * @return the score in case of full house otherwise 0.
     */
    public int fullHouse()
    {
        boolean twoFound = false, threeFound = false;
        int pairValue = 0, threeValue = 0;

        int[] counts = initDiceCounts();

        for (int i = 0; i != 6; i += 1) {
            if (counts[i] == 2) {
                twoFound = true;
                pairValue = i + 1;
            }

            if (counts[i] == 3) {
                threeFound = true;
                threeValue = i + 1;
            }
        }

        if (twoFound && threeFound)
            return pairValue * 2 + threeValue * 3;
        else
            return 0;
    }
}



