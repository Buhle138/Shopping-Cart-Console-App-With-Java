import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;

public class CheckoutTest{

    
    public void subtotalIsValid(){

        assertEquals(19.2, MainUnit.getSubtotal());


    }

    @Test
    public void taxIsValid(){
        assertEquals(3.9, MainUnit.getTax(30));
    }

}

