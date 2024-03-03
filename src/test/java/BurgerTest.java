import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import praktikum.*;

import static org.junit.Assert.assertNotEquals;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class BurgerTest {
    private final float PRICE_BUN = 988F;
    private final float PRICE_INGREDIENT = 3000F;
    private final float EXPECTED_PRICE = PRICE_BUN * 2 + PRICE_INGREDIENT;
    private final String NAME_BUN = "Флюоресцентная булка R2-D3";
    private final String NAME_INGREDIENT = "Мясо бессмертных моллюсков Protostomia";

    private final String EXPECTED_RECEIPT = String.format("(==== %s ====)%n= filling %s =%n(==== %s ====)%n%nPrice: %.6f%n",
            NAME_BUN, NAME_INGREDIENT, NAME_BUN, PRICE_BUN * 2 + PRICE_INGREDIENT);

    Burger burger = new Burger();

    @Mock
    Bun bun;

    @Mock
    Ingredient ingredient;

    @Test
    public void testSetBuns() {
        burger.setBuns(bun);
        Assert.assertEquals(burger.bun, bun);
    }

    @Test
    public void testAddIngredient() {
        burger.addIngredient(ingredient);
        assertNotEquals(0, burger.ingredients.size());
    }

    @Test
    public void testRemoveIngredient() {
        burger.addIngredient(ingredient);
        burger.removeIngredient(0);
        Assert.assertTrue(burger.ingredients.isEmpty());
    }

    @Test
    public void testMoveIngredient() {
        Ingredient Ingredient1 = mock(Ingredient.class);
        Ingredient Ingredient2 = mock(Ingredient.class);

        burger.addIngredient(Ingredient1);
        burger.addIngredient(Ingredient2);
        burger.moveIngredient(1, 0);
        Assert.assertEquals(Ingredient1, burger.ingredients.get(1));
    }

    @Test
    public void testGetPrice() {
        burger.setBuns(bun);
        when(bun.getPrice()).thenReturn(PRICE_BUN);
        burger.addIngredient(ingredient);
        when(ingredient.getPrice()).thenReturn(PRICE_INGREDIENT);
        Assert.assertEquals(EXPECTED_PRICE, burger.getPrice(), 0);
    }

    @Test
    public void testGetReceipt() {
        burger.setBuns(bun);
        burger.addIngredient(ingredient);
        when(bun.getName()).thenReturn(NAME_BUN);
        when(ingredient.getName()).thenReturn(NAME_INGREDIENT);
        when(bun.getPrice()).thenReturn(PRICE_BUN);
        when(ingredient.getPrice()).thenReturn(PRICE_INGREDIENT);
        when(ingredient.getType()).thenReturn(IngredientType.FILLING);
        Assert.assertEquals(EXPECTED_RECEIPT, burger.getReceipt());
    }
}