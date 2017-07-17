import model.Bar;
import org.junit.Before;
import org.junit.Test;
import service.ChainService;
import service.PoolService;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

public class BarSetCreationTest {
    PoolService poolService = new PoolService();
    ChainService chainService = new ChainService();
    List<Bar> chainBars = new ArrayList<>();

    @Test
    public void sizeIsCorrect() {
        assertEquals(28, poolService.getPoolBars().size());
    }

    @Test
    public void shouldPlaceFirst() {
        Bar bar00 = poolService.getPoolBars().get(0);
        poolService.getUserBars().add(bar00);
        poolService.getUserBars().add(poolService.getPoolBars().get(2));
        assertEquals(0, poolService.getUserBars().get(1).getSide1());
    }



}
