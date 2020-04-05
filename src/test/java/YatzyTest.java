import org.junit.*;
import static org.junit.Assert.*;

public class YatzyTest {

    @Test
    public void chance_test() {
        assertEquals(15, initYatzy(2,3,4,5,1).chance());
        assertEquals(16, initYatzy(3,3,4,5,1).chance());
    }

    @Test public void yatzy_test() {
        assertEquals(50, initYatzy(4,4,4,4,4).yatzy());
        assertEquals(50, initYatzy(6,6,6,6,6).yatzy());
        assertEquals(0, initYatzy(6,6,6,6,3).yatzy());
    }

    @Test public void ones_test() {
        assertEquals(1, initYatzy(1,2,3,4,5).ones());
        assertEquals(2, initYatzy(1,2,1,4,5).ones());
        assertEquals(0, initYatzy(6,2,2,4,5).ones());
        assertEquals(4, initYatzy(1,2,1,1,1).ones());
    }

    @Test
    public void twos_test() {
        assertEquals(4, initYatzy(1,2,3,2,6).twos());
        assertEquals(10, initYatzy(2,2,2,2,2).twos());
    }

    @Test
    public void threes_test() {
        assertEquals(6, initYatzy(1,2,3,2,3).threes());
        assertEquals(12, initYatzy(2,3,3,3,3).threes());
    }

    @Test
    public void fours_test() {
        assertEquals(12, initYatzy(4,4,4,5,5).fours());
        assertEquals(8, initYatzy(4,4,5,5,5).fours());
        assertEquals(4, initYatzy(4,5,5,5,5).fours());
    }

    @Test
    public void fives_test() {
        assertEquals(10, initYatzy(4,4,4,5,5).fives());
        assertEquals(15, initYatzy(4,4,5,5,5).fives());
        assertEquals(20, initYatzy(4,5,5,5,5).fives());
    }

    @Test
    public void sixes_test() {
        assertEquals(0, initYatzy(4,4,4,5,5).sixes());
        assertEquals(6, initYatzy(4,4,6,5,5).sixes());
        assertEquals(18, initYatzy(6,5,6,6,5).sixes());
    }

    @Test
    public void onePpair_test() {
        assertEquals(6, initYatzy(3,4,3,5,6).scorePair());
        assertEquals(10, initYatzy(5,3,3,3,5).scorePair());
        assertEquals(12, initYatzy(5,3,6,6,5).scorePair());
    }

    @Test
    public void twoPair_test() {
        assertEquals(16, initYatzy(3,3,5,4,5).twoPair());
        assertEquals(16, initYatzy(3,3,5,5,5).twoPair());
    }

    @Test
    public void threeOfAKind_test() {
        assertEquals(9, initYatzy(3,3,3,4,5).threeOfAKind());
        assertEquals(15, initYatzy(5,3,5,4,5).threeOfAKind());
        assertEquals(9, initYatzy(3,3,3,3,5).threeOfAKind());
    }

    @Test
    public void fourOfAKind_test() {
        assertEquals(12, initYatzy(3,3,3,3,5).fourOfAKind());
        assertEquals(20, initYatzy(5,5,5,4,5).fourOfAKind());
        assertEquals(12, initYatzy(3,3,3,3,3).fourOfAKind());
    }

    @Test
    public void smallStraight_test() {
        assertEquals(15, initYatzy(1,2,3,4,5).smallStraight());
        assertEquals(15, initYatzy(2,3,4,5,1).smallStraight());
        assertEquals(0, initYatzy(1,2,2,4,5).smallStraight());
    }

    @Test
    public void largeStraight_test() {
        assertEquals(20, initYatzy(6,2,3,4,5).largeStraight());
        assertEquals(20, initYatzy(2,3,4,5,6).largeStraight());
        assertEquals(0, initYatzy(1,2,2,4,5).largeStraight());
    }

    @Test
    public void fullHouse_test() {
        assertEquals(18, initYatzy(6,2,2,2,6).fullHouse());
        assertEquals(0, initYatzy(2,3,4,5,6).fullHouse());
    }

    private Yatzy initYatzy(int d1, int d2, int d3, int d4, int d5) {
        return new Yatzy(d1, d2, d3, d4, d5);
    }
}
