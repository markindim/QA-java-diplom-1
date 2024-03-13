import org.junit.Assert;
import org.junit.Test;
import praktikum.Database;

public class DatabaseTest {

    private final Database db = new Database();

    @Test
    public void availableBunsTest() {
        Assert.assertFalse(db.availableBuns().isEmpty());
    }

    @Test
    public void availableIngredientsTest() {
        Assert.assertFalse(db.availableIngredients().isEmpty());
    }
}