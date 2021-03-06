import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;

class RestaurantTest {
    Restaurant restaurant;

    //REFACTOR ALL THE REPEATED LINES OF CODE

    @BeforeEach

    void refactoring_repeated_lines_of_code() {
        LocalTime openingTime = LocalTime.parse("10:30:00");
        LocalTime closingTime = LocalTime.parse("22:00:00");
        restaurant =new Restaurant("Amelie's cafe","Chennai",openingTime,closingTime);
    }


    //>>>>>>>>>>>>>>>>>>>>>>>>>OPEN/CLOSED<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
    //-------FOR THE 2 TESTS BELOW, YOU MAY USE THE CONCEPT OF MOCKING, IF YOU RUN INTO ANY TROUBLE

    @Test

    public void is_restaurant_open_should_return_true_if_time_is_between_opening_and_closing_time(){

        Restaurant spy_Restaurant = Mockito.spy(restaurant);

        LocalTime currentTime = LocalTime.parse("12:00:00");
        Mockito.when(spy_Restaurant.getCurrentTime()).thenReturn(currentTime);

        boolean output = spy_Restaurant.isRestaurantOpen();
        assertTrue(output);
    }

    @Test
    public void is_restaurant_open_should_return_false_if_time_is_outside_opening_and_closing_time(){

        Restaurant spy_Restaurant = Mockito.spy(restaurant);

        LocalTime currentTime = LocalTime.parse("23:00:00");
        Mockito.when(spy_Restaurant.getCurrentTime()).thenReturn(currentTime);

        boolean output = spy_Restaurant.isRestaurantOpen();
        assertFalse(output);

    }

    //<<<<<<<<<<<<<<<<<<<<<<<<<OPEN/CLOSED>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>


    //>>>>>>>>>>>>>>>>>>>>>>>>>>>MENU<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
    @Test
    public void adding_item_to_menu_should_increase_menu_size_by_1(){

        restaurant.addToMenu("Sweet corn soup",119);
        restaurant.addToMenu("Vegetable lasagne", 269);

        int initialMenuSize = restaurant.getMenu().size();
        restaurant.addToMenu("Sizzling brownie",319);
        assertEquals(initialMenuSize+1,restaurant.getMenu().size());
    }
    @Test
    public void removing_item_from_menu_should_decrease_menu_size_by_1() throws itemNotFoundException {

        restaurant.addToMenu("Sweet corn soup",119);
        restaurant.addToMenu("Vegetable lasagne", 269);

        int initialMenuSize = restaurant.getMenu().size();
        restaurant.removeFromMenu("Vegetable lasagne");
        assertEquals(initialMenuSize-1,restaurant.getMenu().size());
    }
    @Test
    public void removing_item_that_does_not_exist_should_throw_exception() {

        restaurant.addToMenu("Sweet corn soup",119);
        restaurant.addToMenu("Vegetable lasagne", 269);

        assertThrows(itemNotFoundException.class,
                ()->restaurant.removeFromMenu("French fries"));
    }
    //<<<<<<<<<<<<<<<<<<<<<<<MENU>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

    @Test
    public void adding_items_to_cart_should_increase_cart_size_by_1(){
        restaurant.addToMenu("Sweet corn soup",119);
        restaurant.addToMenu("Vegetable lasagne", 269);

        restaurant.addItemsToCart("Sweet corn soup");
        restaurant.addItemsToCart("Vegetable lasagne");

        int initialCartSize = restaurant.addItemsToCart("Sizzling brownie").size();
        //restaurant.addItemsToCart("Sizzling brownie");
        assertEquals(initialCartSize+1,restaurant.addItemsToCart("Sizzling brownie").size());


    }

    @Test
    public void calculating_total_value_of_the_added_items() {

        restaurant.addToMenu("Sweet corn soup",119);
        restaurant.addToMenu("Vegetable lasagne", 269);

        restaurant.addItemsToCart("Sweet corn soup");
        restaurant.addItemsToCart("Vegetable lasagne");

        int priceTotal = restaurant.orderTotalValue();
        restaurant.orderTotalValue();

        assertEquals(388, restaurant.orderTotalValue());

    }
}


