package com.shopping.checkout;

import com.shopping.domain.Basket;
import com.shopping.exception.InvalidBasketException;

import java.math.BigDecimal;
import java.util.Map;
import java.util.function.BiFunction;

import static com.shopping.domain.Fruit.getFruit;
import static java.math.BigDecimal.*;
import static java.util.Objects.isNull;

public class ShopingServiceImpl implements ShopingService {

	private BiFunction<Map<String, Integer>, String, BigDecimal> calculatePrice = (items, fruit) -> getPrice(fruit)
			.multiply(valueOf(items.get(fruit)));

	@Override
	public BigDecimal generateBill(Basket basket) {
		validateBasket(basket);
		return getBasketPrice(basket.getItems());
	}

	private BigDecimal getBasketPrice(Map<String, Integer> items) {
		return items.keySet().stream().map(fruit -> calculatePrice.apply(items, fruit)).reduce(ZERO, BigDecimal::add);
	}

	private static void validateBasket(Basket basket) {
		if (isNull(basket.getItems())) {
			throw new InvalidBasketException("Shopping basket can not be null");
		}
	}

	private BigDecimal getPrice(String fruit) {
		return getFruit(fruit).getPrice().setScale(2, ROUND_HALF_UP);
	}
}
