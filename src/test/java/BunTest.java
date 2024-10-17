import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import praktikum.Bun;

@RunWith(Parameterized.class)
public class BunTest {

    private String expectedName;
    private float expectedPrice;
    private Bun bun;

    public BunTest(String expectedName, float expectedPrice) {
        this.expectedName = expectedName;
        this.expectedPrice = expectedPrice;
        this.bun = new Bun(expectedName, expectedPrice);
    }

    @Parameterized.Parameters
    public static Object[][] data() {
        return new Object[][] {
                {"Пшеничная", 150.0f},
                {"Ржаная", 200.0f},
                {"Ячменная", 250.0f}
        };
    }

    @Test
    public void testGetName() {
        Assert.assertEquals("Имя булочки должно быть " + expectedName, expectedName, bun.getName());
    }

    @Test
    public void testGetPrice() {
        Assert.assertEquals("Цена булочки должна быть " + expectedPrice, expectedPrice, bun.getPrice(), 0.001);
    }
}
