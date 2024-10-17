import praktikum.Ingredient;
import praktikum.IngredientType;

import org.hamcrest.MatcherAssert;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertEquals;

public class IngredientTest {

    private Ingredient ingredient;

    @Before
    public void setUp() {
        ingredient = new Ingredient(IngredientType.FILLING, "Котлета", 0.5f);
    }

    @Test
    public void testGetName() {
        assertThat(ingredient.getName(), is("Котлета"));
    }

    @Test
    public void testGetPrice() {
        assertThat((double) ingredient.getPrice(), is(closeTo(0.5, 0.01)));
    }

    @Test
    public void testGetType() {
        assertThat(ingredient.getType(), is(IngredientType.FILLING));
    }
}
