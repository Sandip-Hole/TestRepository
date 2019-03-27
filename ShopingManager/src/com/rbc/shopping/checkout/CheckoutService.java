package com.rbc.shopping.checkout;

import com.rbc.shopping.domain.Basket;

import java.math.BigDecimal;

public interface CheckoutService {
    BigDecimal generateBill(Basket basket);
}
