package JaCoCo.TestExe;

import JaCoCo.JaCoCoCoffeeMaker;
import JaCoCo.JaCoCoExceptions.InventoryException;
import JaCoCo.JaCoCoExceptions.RecipeException;
import JaCoCo.JaCoCoRecipe;
import coffeemaker.CoffeeMaker;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

public class JaCoCoCoffeeMakerTest {
    private JaCoCoCoffeeMaker coffeeMaker;

    // Sample recipes to use in testing.
    private JaCoCoRecipe recipe1;
    private JaCoCoRecipe recipe2;
    private JaCoCoRecipe recipe3;
    private JaCoCoRecipe recipe4;

    /**
     * Initializes some recipes to test with and the {@link CoffeeMaker}
     * object we wish to test.
     *
     * @throws RecipeException  if there was an error parsing the ingredient
     * 		amount when setting up the recipe.
     */
    @Before
    public void setUp() throws RecipeException {
        coffeeMaker = new JaCoCoCoffeeMaker();
        //Set up for r1
        recipe1 = new JaCoCoRecipe();
        recipe1.setName("Coffee");
        recipe1.setAmtChocolate("0");
        recipe1.setAmtCoffee("3");
        recipe1.setAmtMilk("1");
        recipe1.setAmtSugar("1");
        recipe1.setPrice("50");

        //Set up for r2
        recipe2 = new JaCoCoRecipe();
        recipe2.setName("Mocha");
        recipe2.setAmtChocolate("20");
        recipe2.setAmtCoffee("3");
        recipe2.setAmtMilk("1");
        recipe2.setAmtSugar("1");
        recipe2.setPrice("75");

        //Set up for r3
        recipe3 = new JaCoCoRecipe();
        recipe3.setName("Latte");
        recipe3.setAmtChocolate("0");
        recipe3.setAmtCoffee("3");
        recipe3.setAmtMilk("3");
        recipe3.setAmtSugar("1");
        recipe3.setPrice("100");

        //Set up for r4
        recipe4 = new JaCoCoRecipe();
        recipe4.setName("Hot Chocolate");
        recipe4.setAmtChocolate("4");
        recipe4.setAmtCoffee("0");
        recipe4.setAmtMilk("1");
        recipe4.setAmtSugar("1");
        recipe4.setPrice("65");
    }


    /**
     * Given a coffee maker with the default inventory
     * When we add inventory with well-formed quantities
     * Then we do not get an exception trying to read the inventory quantities.
     *
     * @throws InventoryException  if there was an error parsing the quanity
     * 		to a positive integer.
     */
    @Test
    public void testAddInventory() throws InventoryException {
        coffeeMaker.addInventory("4","7","0","9");
    }

    /**
     * Given a coffee maker with the default inventory
     * When we add inventory with malformed quantities (i.e., a negative
     * quantity and a non-numeric string)
     * Then we get an inventory exception
     *
     * @throws InventoryException  if there was an error parsing the quanity
     * 		to a positive integer.
     */
    @Test(expected = InventoryException.class)
    public void testAddInventoryException() throws InventoryException {
        coffeeMaker.addInventory("4", "-1", "-6", "3");
    }

    /**
     * Given a coffee maker with one valid recipe
     * When we make coffee, selecting the valid recipe and paying more than
     * 		the coffee costs
     * Then we get the correct change back.
     */
    @Test
    public void testMakeCoffee() {
        coffeeMaker.addRecipe(recipe1);
        assertEquals(25, coffeeMaker.makeCoffee(0, 75));
    }


    @Test
    public void testAddRecipe1()
    {
        assertEquals(true,coffeeMaker.addRecipe(recipe1));
        assertEquals(true,coffeeMaker.addRecipe(recipe2));
        assertEquals(true,coffeeMaker.addRecipe(recipe3));
        assertEquals(true,coffeeMaker.addRecipe(recipe4));
    }
    @Test
    public void testMakeCoffee1() {
        coffeeMaker.addRecipe(recipe1);
        coffeeMaker.addRecipe(recipe2);
        coffeeMaker.addRecipe(recipe3);
        coffeeMaker.addRecipe(recipe4);
        assertEquals(5, coffeeMaker.makeCoffee(2, 5));
    }
    @Test
    public void testDeleteRecipe4()
    {   coffeeMaker.addRecipe(recipe1);
        coffeeMaker.addRecipe(recipe2);
        coffeeMaker.addRecipe(recipe3);
        coffeeMaker.addRecipe(recipe4);
        assertEquals("Coffee",coffeeMaker.deleteRecipe(0));
        assertEquals("Mocha",coffeeMaker.deleteRecipe(1));
        assertEquals("Latte",coffeeMaker.deleteRecipe(2));
        assertEquals("Hot Chocolate",coffeeMaker.deleteRecipe(3));
    }
}
