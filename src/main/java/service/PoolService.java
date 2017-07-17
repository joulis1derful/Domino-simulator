package service;

import model.Bar;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class PoolService {

    public static final int BARS_AMOUNT = 7;
    public static final int MAX_DIGIT_BAR_SIDE = 6;

    private List<Bar> poolBars;
    private List<Bar> userBars;

    public PoolService() {
        poolBars = new ArrayList<>();
        userBars = new ArrayList<>();
        poolBars = createBarSet();
    }

    private List<Bar> createBarSet() {
        List<Bar> bars = new ArrayList<>();
        int firstSideCounter = 0;
        int additionalCounter = 0;
        while (firstSideCounter <= MAX_DIGIT_BAR_SIDE) {
            int secondSideCounter = additionalCounter;
            while (secondSideCounter <= MAX_DIGIT_BAR_SIDE) {
                bars.add(new Bar(firstSideCounter, secondSideCounter));
                secondSideCounter++;
            }
            additionalCounter++;
            firstSideCounter++;
        }
        return bars;
    }

    public List<Bar> getStartPackDomino() {
        List<Integer> randomDigits = new ArrayList<>();
        while (randomDigits.size() < BARS_AMOUNT) {
            int randomDigit = (int) (Math.random() * 28);
            if (!randomDigits.contains(randomDigit)) {
                randomDigits.add(randomDigit);
            }
        }
        for (int i = 0; i < BARS_AMOUNT; i++) {
            userBars.add(poolBars.get(randomDigits.get(i)));
        }
        for (Bar userBar : userBars) {
            for (int j = 0; j < poolBars.size(); j++) {
                if (userBar.equals(poolBars.get(j))) {
                    poolBars.remove(j);
                }
            }
        }
        return userBars;
    }

    public List<Bar> getPoolBars() {
        return poolBars;
    }

    public List<Bar> getUserBars() {
        return userBars;
    }

    public List<Bar> showUserBars(PoolService poolService) {
        poolService = new PoolService();
        userBars = poolService.getStartPackDomino();
        return userBars;
    }
}
