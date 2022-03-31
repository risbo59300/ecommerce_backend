package fr.lu_div.ecom.Controller;

import fr.lu_div.ecom.dto.Purchase;
import fr.lu_div.ecom.dto.PurchasseResponse;
import fr.lu_div.ecom.services.CheckoutService;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("http://localhost:4200")
@RestController
@RequestMapping("/api/checkout")
public class CheckoutController {


    CheckoutService checkoutService;

    public CheckoutController(CheckoutService checkoutService) {
        this.checkoutService = checkoutService;
    }

    @PostMapping("/purchase")
    public PurchasseResponse placeOrder(@RequestBody Purchase purchase) {

        PurchasseResponse purchasseResponse = checkoutService.placeOrder(purchase);

        return purchasseResponse;
    }





}
