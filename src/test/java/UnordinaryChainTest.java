import model.Bar;
import org.junit.Before;
import org.junit.Test;
import service.ChainService;
import service.PoolService;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class UnordinaryChainTest {
    ChainService chainService = new ChainService();
    List<Bar> userbars = new ArrayList<>();
    List<Bar> chainbars = new ArrayList<>();

    @Before
    public void setUp2() {
        userbars.clear();
        Bar bar1 = new Bar(4, 4);
        Bar bar2 = new Bar(3, 4);
        Bar bar3 = new Bar(5, 5);
        Bar bar4 = new Bar(0, 0);
        Bar bar5 = new Bar(4, 5);
        Bar bar6 = new Bar(2, 4);
        Bar bar7 = new Bar(2, 2);
        userbars.add(bar1);
        userbars.add(bar2);
        userbars.add(bar3);
        userbars.add(bar4);
        userbars.add(bar5);
        userbars.add(bar6);
        userbars.add(bar7);
        chainService.placeFirstBar(chainbars, userbars);
    }

    @Test
    public void shouldMakeMaxSizeChain() {
        assertEquals(4, chainService.makeChain(chainbars, userbars).size());
    }
}
