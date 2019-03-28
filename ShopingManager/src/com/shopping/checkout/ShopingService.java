package com.shopping.checkout;

import java.math.BigDecimal;

import com.shopping.domain.Basket;

public interface ShopingService {
    BigDecimal generateBill(Basket basket);
}
