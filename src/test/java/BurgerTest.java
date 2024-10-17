import org.hamcrest.MatcherAssert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import praktikum.Bun;
import praktikum.Burger;
import praktikum.Ingredient;
import praktikum.IngredientType;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class BurgerTest {

    private Burger burger;

    @Mock
    private Bun bun;

    @Mock
    private Ingredient ingredient1;

    @Mock
    private Ingredient ingredient2;

    @Before
    public void setUp() {
        burger = new Burger();

        // Настройка моков
        Mockito.when(bun.getName()).thenReturn("Ржаная");
        Mockito.when(bun.getPrice()).thenReturn(1.5f);
        Mockito.when(ingredient1.getName()).thenReturn("Кетчуп");
        Mockito.when(ingredient1.getPrice()).thenReturn(0.5f);
        Mockito.when(ingredient1.getType()).thenReturn(IngredientType.SAUCE);
        Mockito.when(ingredient2.getName()).thenReturn("Котлета");
        Mockito.when(ingredient2.getPrice()).thenReturn(0.7f);
        Mockito.when(ingredient2.getType()).thenReturn(IngredientType.FILLING);
        burger.setBuns(bun);
    }

    @Test
    public void testAddIngredient() {
        burger.addIngredient(ingredient1);
        assertEquals(1, burger.ingredients.size());
        assertEquals(ingredient1, burger.ingredients.get(0));
    }

    @Test
    public void testRemoveIngredient() {
        burger.addIngredient(ingredient1);
        burger.addIngredient(ingredient2);
        burger.removeIngredient(0);
        assertEquals(1, burger.ingredients.size());
        assertEquals(ingredient2, burger.ingredients.get(0));
    }

    @Test
    public void testRemoveIngredientInvalidIndex() {
        burger.addIngredient(ingredient1);
        assertEquals(1, burger.ingredients.size());
        try {
            burger.removeIngredient(1);
        } catch (IndexOutOfBoundsException e) {
            // Ожидаемое исключение }
            assertEquals(1, burger.ingredients.size());
        }
    }

    @Test
    public void testMoveIngredient() {
        burger.addIngredient(ingredient1);
        burger.addIngredient(ingredient2);
        burger.moveIngredient(0, 1);

        assertEquals(ingredient2, burger.ingredients.get(0));
        assertEquals(ingredient1, burger.ingredients.get(1));
    }

    @Test
    public void testMoveIngredientInvalidIndex() {
        burger.addIngredient(ingredient1);
        burger.addIngredient(ingredient2);
        assertEquals(2, burger.ingredients.size());
        try {
            burger.moveIngredient(0, 1);
        } catch (IndexOutOfBoundsException e) {
            // Ожидаемое исключение }
            assertEquals(1, burger.ingredients.size());
        }
    }

    @Test
    public void testGetPrice() {
        burger.addIngredient(ingredient1);
        burger.addIngredient(ingredient2);

        float expectedPrice = bun.getPrice() * 2 + ingredient1.getPrice() + ingredient2.getPrice();
        assertEquals(expectedPrice, burger.getPrice(), 0.01);
    }

    @Test
    public void testGetReceipt() {
        burger.addIngredient(ingredient1);
        burger.addIngredient(ingredient2);

        String expectedReceipt = String.format("(==== %s ====)%n", bun.getName()) +
                String.format("= %s %s =%n", ingredient1.getType().toString().toLowerCase(), ingredient1.getName()) +
                String.format("= %s %s =%n", ingredient2.getType().toString().toLowerCase(), ingredient2.getName()) +
                String.format("(==== %s ====)%n", bun.getName()) +
                String.format("%nPrice: %f%n", burger.getPrice());
        MatcherAssert.assertThat(burger.getReceipt(), equalTo(expectedReceipt));
    }
}
