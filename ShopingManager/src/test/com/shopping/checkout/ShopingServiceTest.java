package test.com.shopping.checkout;

import com.shopping.checkout.ShopingService;
import com.shopping.checkout.ShopingServiceImpl;
import com.shopping.domain.Basket;
import com.shopping.domain.Fruit;
import com.shopping.exception.InvalidBasketException;
import com.shopping.exception.InvalidFruitException;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static java.math.BigDecimal.valueOf;
import static java.util.Collections.emptyMap;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

public class ShopingServiceTest {
	private ShopingService shopingService;
	private Basket basket;
	@Rule
	public ExpectedException expectedException = ExpectedException.none();

	@Before
	public void setUp() {
		shopingService = new ShopingServiceImpl();
	}

	/*
	 * Should return zero when given a empty basket
	 */	
	@Test
	public void givenEmptyBasketShouldReturnZero() {
		basket = new Basket(emptyMap());
		BigDecimal actualAmount = shopingService.generateBill(basket);
		assertThat(actualAmount, is(BigDecimal.ZERO));
	}

	
	/*
	 * Should Throw Exception when Checkout given Invalid Basket
	 */	
	@Test
	public void givenInvalidBasketShouldThrowException() {
		basket = new Basket(null);
		expectedException.expect(InvalidBasketException.class);
		expectedException.expectMessage("Shopping basket can not be null");
		shopingService.generateBill(basket);
	}

	/*
	 * Should Throw Exception when Checkout given a Basket With Invalid Item
	 */	
	@Test
	public void givenBasketWithInvalidItemShouldThrowException() {
		Map<String, Integer> items = new HashMap<>();
		items.put("Grapes", 1);
		basket = new Basket(items);
		expectedException.expect(InvalidFruitException.class);
		expectedException.expectMessage("Invalid Fruit Grapes");
		shopingService.generateBill(basket);
	}
	
	/*
	 * Should Throw Exception when Checkout given a Basket With Empty Item
	 */	
	@Test
	public void givenBasketWithEmptyItemShouldThrowException() {
		Map<String, Integer> items = new HashMap<>();
		items.put(" ", 1);
		basket = new Basket(items);
		expectedException.expect(InvalidFruitException.class);
		expectedException.expectMessage("Invalid Fruit");
		shopingService.generateBill(basket);
	}

	/*
	 * Should Return Amount when Checkout given a Basket With One Item
	 */	
	@Test
	public void givenBasketWithOneItemShouldReturnAmount() {
		Map<String, Integer> items = new HashMap<>();
		items.put("Apple", 1);
		basket = new Basket(items);
		BigDecimal actualAmount = shopingService.generateBill(basket);
		System.out.println("actualAmount: "+actualAmount);
		assertThat(actualAmount, is(getFormattedAmount(2.40)));
		System.out.println("actualAmount: "+actualAmount);
		//assertEquals("Basket should return amount", is(getFormattedAmount(1.20)), actualAmount);
	}

	/*
	 * Should Return Amount when Checkout given a Basket With One Item And Multiple Quantity
	 */	
	@Test
	public void givenBasketWithOneItemAndMultipleQuantityShouldReturnAmount() {
		Map<String, Integer> items = new HashMap<>();
		items.put("Orange", 5);
		basket = new Basket(items);
		BigDecimal actualAmount = shopingService.generateBill(basket);
		assertThat(actualAmount, is(getFormattedAmount(10.00)));
	}

	/*
	 * Should Return Amount when Checkout given a Basket With Multiple Items And One Quantity
	 */	
	@Test
	public void givenBasketWithMultipleItemsAndOneQuantityShouldReturnAmount() {
		Map<String, Integer> items = new HashMap<>();
		items.put("Apple", 1);
		items.put("Orange", 1);
		items.put("Peach", 1);
		items.put("Lemon", 1);
		items.put("Banana", 1);
		basket = new Basket(items);
		BigDecimal actualAmount = shopingService.generateBill(basket);
		assertThat(actualAmount, is(getFormattedAmount(8.00)));
	}

	/*
	 * Should Return Amount when Checkout given Basket With Multiple Items And Multiple Quantity
	 */
	@Test
	public void givenBasketWithMultipleItemsAndMultipleQuantityShouldReturnAmount() {

		Map<String, Integer> items = new HashMap<>();
		items.put("Apple", 2);
		items.put("Banana", 6);
		items.put("Orange", 3);
		items.put("Peach", 5);
		items.put("Lemon", 2);
		basket = new Basket(items);
		BigDecimal actualAmount = shopingService.generateBill(basket);
		assertThat(actualAmount, is(getFormattedAmount(28.00)));
	}

	/*
	 *  Should Throw Invalid Fruit Exception when givenInvalidFruit
	 */	
	@Test
	public void givenInvalidFruitShouldThrowInvalidFruitException() {	
		expectedException.expect(InvalidFruitException.class);
		expectedException.expectMessage("Invalid Fruit Pears");
		Fruit.getFruit("Pears");
	}

	/*
	 * Should Return Fruit when givenValidFruit
	 */	
	@Test
	public void givenValidFruitShouldReturnFruit() {		
		assertThat(Fruit.getFruit("Apple"), is(Fruit.APPLE));
		assertThat(Fruit.getFruit("Orange"), is(Fruit.ORANGE));
		assertThat(Fruit.getFruit("Lemon"), is(Fruit.LEMON));
		assertThat(Fruit.getFruit("Peach"), is(Fruit.PEACH));
		assertThat(Fruit.getFruit("Banana"), is(Fruit.BANANA));
	}

	private BigDecimal getFormattedAmount(double amount) {
		return valueOf(amount).setScale(2, RoundingMode.HALF_UP);
	}

}
