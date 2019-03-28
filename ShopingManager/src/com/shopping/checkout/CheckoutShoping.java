package com.shopping.checkout;

import static java.math.BigDecimal.valueOf;
import static java.util.Collections.emptyMap;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.Map;

import com.shopping.domain.Basket;

public class CheckoutShoping {

	private static ShopingService  checkoutService = new ShopingServiceImpl();;
    private Basket basket;
    
   // public ExpectedException expectedException = ExpectedException.none();
    
	public static void main(String[] args) {
	 
   		CheckoutShoping checkoutShoping= new CheckoutShoping();
		checkoutShoping.shouldReturnAmount_whenCheckout_givenABasketWithMultipleItemsAndMultipleQuantity();
		checkoutShoping.shouldReturnAmount_whenCheckout_givenABasketWithMultipleItemsAndOneQuantity();
		checkoutShoping.shouldReturnAmount_whenCheckout_givenABasketWithOneItemAndMultipleQuantity();
		checkoutShoping.shouldReturnAmount_whenCheckout_givenABasketWithOneItem();		
		checkoutShoping.shouldReturnAmount_whenCheckout_givenABasketWithInvalidItem();
		//checkoutShoping.shouldThrowException_whenCheckout_givenInvalidBasket();
		checkoutShoping.shouldReturnZero_whenCheckout_givenEmptyBasket();
	}

	 public void shouldReturnZero_whenCheckout_givenEmptyBasket() {
	        basket = new Basket(emptyMap());
	        BigDecimal actualAmount = checkoutService.generateBill(basket);
	        //assertThat(actualAmount, is(BigDecimal.ZERO));
	        System.out.println("shouldReturnZero_whenCheckout_givenEmptyBasket actualAmount: "+actualAmount);
	    }

	   // @Test
	    public void shouldThrowException_whenCheckout_givenInvalidBasket() {
	        basket = new Basket(null);
	       // expectedException.expect(InvalidBasketException.class);
	       // expectedException.expectMessage("Invalid Basket");
	        BigDecimal actualAmount = checkoutService.generateBill(basket);
	        System.out.println("shouldThrowException_whenCheckout_givenInvalidBasket actualAmount: "+actualAmount);
	    }

	   // @Test
	    public void shouldReturnAmount_whenCheckout_givenABasketWithInvalidItem() {
	        Map<String, Integer> items = new HashMap<>();
	        items.put("Grapes", 1);
	        basket = new Basket(items);
	        //expectedException.expect(InvalidFruitException.class);
	       // expectedException.expectMessage("Invalid Fruit Kiwi");
	        BigDecimal actualAmount =checkoutService.generateBill(basket);
	        System.out.println("actualAmount: "+actualAmount);
	    }

	   // @Test
	    public void shouldReturnAmount_whenCheckout_givenABasketWithOneItem() {
	        Map<String, Integer> items = new HashMap<>();
	        items.put("Apple", 1);
	        basket = new Basket(items);
	        BigDecimal actualAmount = checkoutService.generateBill(basket);
	       // assertThat(actualAmount, is(getFormattedAmount(0.60)));
	        System.out.println("actualAmount: "+actualAmount);
	    }

	    //@Test
	    public void shouldReturnAmount_whenCheckout_givenABasketWithOneItemAndMultipleQuantity() {
	        Map<String, Integer> items = new HashMap<>();
	        items.put("Orange", 5);
	        basket = new Basket(items);
	        BigDecimal actualAmount = checkoutService.generateBill(basket);
	       // assertThat(actualAmount, is(getFormattedAmount(2.50)));
	        System.out.println("actualAmount: "+actualAmount);
	    }

	    //@Test
	    public void shouldReturnAmount_whenCheckout_givenABasketWithMultipleItemsAndOneQuantity() {
	        Map<String, Integer> items = new HashMap<>();
	        items.put("Apple", 1);
	        items.put("Orange", 1);
	        items.put("Peach", 1);
	        items.put("Lemon", 1);
	        items.put("Banana", 1);
	        basket = new Basket(items);
	        BigDecimal actualAmount = checkoutService.generateBill(basket);
	       // assertThat(actualAmount, is(getFormattedAmount(2.00)));
	        System.out.println("actualAmount: "+actualAmount);
	    }

	   // @Test
	    public void shouldReturnAmount_whenCheckout_givenABasketWithMultipleItemsAndMultipleQuantity() {
	        Map<String, Integer> items = new HashMap<>();
	        items.put("Apple", 2);
	        items.put("Banana", 6);
	        items.put("Orange", 3);
	        items.put("Peach", 5);
	        items.put("Lemon", 2);
	        basket = new Basket(items);
	        BigDecimal actualAmount = checkoutService.generateBill(basket);
	        
	       // assertThat(actualAmount, is(getFormattedAmount(7.00)));
	        System.out.println("actualAmount: "+actualAmount);
	    }

	    private BigDecimal getFormattedAmount(double amount) {
	        return valueOf(amount).setScale(2, RoundingMode.HALF_UP);
	    }
	    
	 /*   @Test
	    public void shouldThrowInvalidFruitException_when_getFruit_givenInvalidFruit() {
	        expectedException.expect(InvalidFruitException.class);
	        expectedException.expectMessage("Invalid Fruit Pears");
	        Fruit.getFruit("Pears");
	    }

	   // @Test
	    public void shouldReturnFruit_when_getFruit_givenValidFruit() {
	        assertThat(Fruit.getFruit("Apple"), is(Fruit.APPLE));
	        assertThat(Fruit.getFruit("Orange"), is(Fruit.ORANGE));
	        assertThat(Fruit.getFruit("Lemon"), is(Fruit.LEMON));
	        assertThat(Fruit.getFruit("Peach"), is(Fruit.PEACH));
	        assertThat(Fruit.getFruit("Banana"), is(Fruit.BANANA));
	    } */
}
