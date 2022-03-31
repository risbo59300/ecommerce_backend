package fr.lu_div.ecom.services;

import fr.lu_div.ecom.dto.Purchase;
import fr.lu_div.ecom.dto.PurchasseResponse;

public interface CheckoutService {

    PurchasseResponse placeOrder (Purchase purchase);

}
