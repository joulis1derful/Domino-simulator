package service;

import model.Bar;
import org.apache.log4j.Logger;

import java.util.Iterator;
import java.util.List;

import static service.PoolService.BARS_AMOUNT;

public class ChainService {
    private static final Logger log = Logger.getLogger(ChainService.class);

    private boolean isAdded;

    public List<Bar> makeChain(List<Bar> chainBars, List<Bar> userBars) {
        cleanDuplicates(chainBars, userBars);
        makeLongestChain(chainBars, userBars, chainBars.size() - 1);

        return chainBars;
    }

    public void placeFirstBar(List<Bar> cb, List<Bar> ub) {
        int max = 0;
        int index = 0;
        int additional = 0;
        // searching for doubles
        for (int i = 0; i < ub.size(); i++) {
            if (ub.get(i).getSide1() == 6 && ub.get(i).getSide2() == 6) {
                index = i;
                break;
            } else if (ub.get(i).getSide1() == 5 && ub.get(i).getSide2() == 5) {
                additional = 17;
                if (max < additional) {
                    index = i;
                    max = additional;
                }
            } else if (ub.get(i).getSide1() == 4 && ub.get(i).getSide2() == 4) {
                additional = 16;
                if (max < additional) {
                    index = i;
                    max = additional;
                }
            } else if (ub.get(i).getSide1() == 3 && ub.get(i).getSide2() == 3) {
                additional = 15;
                if (max < additional) {
                    index = i;
                    max = additional;
                }
            } else if (ub.get(i).getSide1() == 2 && ub.get(i).getSide2() == 2) {
                additional = 14;
                if (max < additional) {
                    index = i;
                    max = additional;
                }
            } else if (ub.get(i).getSide1() == 1 && ub.get(i).getSide2() == 1) {
                additional = 13;
                if (max < additional) {
                    index = i;
                    max = additional;
                }
            } else if (ub.get(i).getSide1() == 0 && ub.get(i).getSide2() == 0) {
                continue;
            }
            // if no doubles place first with the biggest sum sides
            else {
                if (ub.get(i).sumSides() > max) {
                    max = ub.get(i).sumSides();
                    index = i;
                }
            }
        }
        cb.add(ub.get(index));
    }

    private void makeLongestChain(List<Bar> cb, List<Bar> ub, int indexChainBar) {
        isAdded = false;
        //finding doubles to place in right chain
        for (int indexUserBar = 0; indexUserBar < ub.size(); indexUserBar++) {
            if (cb.get(indexChainBar).getSide2() == ub.get(indexUserBar).getSide1() &&
                    cb.get(indexChainBar).getSide2() == ub.get(indexUserBar).getSide2()) {
                cb.add(ub.get(indexUserBar));
                isAdded = true;
            }
            if (isAdded) {
                cleanDuplicates(cb, ub);
                break;
            }
        }

        //finding appropriate digits to place in right chain
        for (int indexUserBar = 0; indexUserBar < ub.size(); indexUserBar++) {
            if (cb.get(indexChainBar).getSide2() == ub.get(indexUserBar).getSide1()) {
                cb.add(ub.get(indexUserBar));
                indexUserBar++;
                indexChainBar++;
                isAdded = true;
            } else if (cb.get(indexChainBar).getSide2() == ub.get(indexUserBar).getSide2()) {
                ub.get(indexUserBar).revertBar();
                cb.add(ub.get(indexUserBar));
                indexUserBar++;
                indexChainBar++;
                isAdded = true;
            }
            cleanDuplicates(cb, ub);
        }

        //finding doubles to place in left chain
        for (int indexUserBar = 0; indexUserBar < ub.size(); indexUserBar++) {
            if (cb.get(0).getSide1() == ub.get(indexUserBar).getSide1() &&
                    cb.get(0).getSide1() == ub.get(indexUserBar).getSide2()) {
                cb.add(0, ub.get(indexUserBar));
                isAdded = true;
            }
            cleanDuplicates(cb, ub);
        }

        //finding appropriate digits to place in left chain
        for (int indexUserBar = 0; indexUserBar < ub.size(); indexUserBar++) {
            if (cb.get(0).getSide1() == ub.get(indexUserBar).getSide1()) {
                ub.get(indexUserBar).revertBar();
                cb.add(0, ub.get(indexUserBar));
                indexUserBar++;
                isAdded = true;
            } else if (cb.get(0).getSide1() == ub.get(indexUserBar).getSide2()) {
                cb.add(0, ub.get(indexUserBar));
                indexUserBar++;
                isAdded = true;
            }
            cleanDuplicates(cb, ub);
        }

        //if new bar was added into chain - recursive call
        //with pointer(index) on last bar of chain
        if (isAdded) {
            makeLongestChain(cb, ub, cb.size() - 1);
        }
    }

    public void cleanDuplicates(List<Bar> cb, List<Bar> ub) {
        Iterator<Bar> iterator = ub.iterator();
        while (iterator.hasNext()) {
            Bar bar = iterator.next();
            for (Bar stackBar : cb) {
                if (stackBar.equals(bar) || (stackBar.getSide1() == bar.getSide2() &&
                        stackBar.getSide2() == bar.getSide1())) {
                    iterator.remove();
                }
            }
        }
    }

    public String convertChain(List<Bar> cb) {
        StringBuilder sb = new StringBuilder(5 * BARS_AMOUNT);
        for (Bar bar : cb) {
            sb.append(bar.getString());
            sb.append(" ");
        }
        return sb.toString();
    }

    public void cleanLists(List<Bar> chainBars, List<Bar> userBars) {
        chainBars.clear();
        userBars.clear();
    }

    public List<Bar> makeCustomChain(List<Bar> cb, List<Bar> ol,
                                     int param) {
        if (param < 0 || param > ol.size() - 1) {
            log.error("Select another digit (from 1 to amount of possible variants).");
            log.error("Probably no items possible variants left.");
            return cb;
        }
        if (ol.get(param).getSide1() == cb.get(cb.size() - 1).getSide2()) {
            cb.add(ol.get(param));
        } else if (ol.get(param).getSide2() == cb.get(cb.size() - 1).getSide2()) {
            ol.get(param).revertBar();
            cb.add(ol.get(param));
        } else if (ol.get(param).getSide2() == cb.get(0).getSide1()) {
            cb.add(0, ol.get(param));
        } else if (ol.get(param).getSide1() == cb.get(0).getSide1()) {
            ol.get(param).revertBar();
            cb.add(0, ol.get(param));
        }
        return cb;
    }

    public List<Bar> findOccurences(int side1, int side2, List<Bar> ub, List<Bar> ol) {
        for (Bar userBar : ub) {
            if (side1 == userBar.getSide2() || side2 == userBar.getSide1()) {
                ol.add(userBar);
            } else if (side1 == userBar.getSide1() || side2 == userBar.getSide2()) {
                userBar.revertBar();
                ol.add(userBar);
            }
        }
        return ol;
    }

}
