import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class CartTest {

   Cart cart;

    @Before
   public void setup(){
        cart = new Cart();
        cart.add(new Item("Pepsi",  1.99));
        cart.add(new Item("Crush",  1.99));
   }
   @Test
   public void itemAddTest(){
        assertTrue(cart.contains(new Item("Crush", 1.99)));
   }
   @Test
   public void removedItemTest(){
    cart.remove("Crush");
    assertFalse(cart.contains(new Item("Crush",  1.99)));
   }
   @Test
   public void subtotalIsValid(){
    assertEquals(3.98, cart.getSubtotal());
   }
   /*Making sure tax is calculated correctly. */
   @Test
   public void taxIsValid(){
    assertEquals(3.9, cart.getTax(30));
   }
   @Test
   public void totalIsValid(){
    assertEquals(96.67, cart.getTotal(85.55, 11.12));
   }
   @Test(expected = IllegalStateException.class)
   public void invalidRemoveState(){
    cart.clear();
    cart.remove("Pepsi");
   }
   @Test(expected = IllegalStateException.class)
   public void invalidCheckoutState(){
    cart.clear();
    cart.checkout(); //Throwing an illegal state exception in the case that we want to checkout an empty cart. 
   }
    
}
