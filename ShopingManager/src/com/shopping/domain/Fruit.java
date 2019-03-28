package com.shopping.domain;

import java.math.BigDecimal;

import com.shopping.exception.InvalidFruitException;

import static java.util.stream.Stream.of;

public enum Fruit {
    APPLE("Apple", BigDecimal.valueOf(2.40)),
    BANANA("Banana", BigDecimal.valueOf(1.60)),
    ORANGE("Orange", BigDecimal.valueOf(2.00)),
    PEACH("Peach", BigDecimal.valueOf(1.20)),
    LEMON("Lemon", BigDecimal.valueOf(0.80)),;

    private String name;
    private BigDecimal price;

    Fruit(String name, BigDecimal price) {
        this.name = name;
        this.price = price;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public String getName() {
        return name;
    }

	public static Fruit getFruit(String fruitName) {
		return of(Fruit.values()).filter(fruit -> fruit.getName().equalsIgnoreCase(fruitName)).findFirst()
				.orElseThrow(() -> new InvalidFruitException("Invalid Fruit " + fruitName));
	}
}
