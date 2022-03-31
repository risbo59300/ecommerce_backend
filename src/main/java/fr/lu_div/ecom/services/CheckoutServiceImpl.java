package fr.lu_div.ecom.services;

import fr.lu_div.ecom.dao.CustomerRepository;
import fr.lu_div.ecom.dto.Purchase;
import fr.lu_div.ecom.dto.PurchasseResponse;
import fr.lu_div.ecom.entity.Customer;
import fr.lu_div.ecom.entity.Order;
import fr.lu_div.ecom.entity.OrderItem;
import org.springframework.beans.factory.annotation.Autowired;

import javax.transaction.Transactional;
import java.util.Set;
import java.util.UUID;

public class CheckoutServiceImpl implements CheckoutService{

    @Autowired
    private CustomerRepository customerRepos;

    @Override
    @Transactional
    public PurchasseResponse placeOrder(Purchase purchase) {

        // retrieve the order info from dto
        Order order = purchase.getOrder();

        // generate tracking number
        String orderTrackingNumber = generateOrderTrackingNumber();
        order.setOrderTrackingNumber(orderTrackingNumber);

        // populate order with orderItems
        Set<OrderItem> orderItems = purchase.getOrderItems();
        orderItems.forEach(item -> order.add(item));

        //populate order with billingAddress and shippingAddress
        order.setBillingAddress(purchase.getBillingAddress());
        order.setShippingAddress(purchase.getShippingAddress());

        // populate customer with order
        Customer customer = purchase.getCustomer();
        customer.add(order);

        // save to the database
        customerRepos.save(customer);

        // return a response
        return new PurchasseResponse(orderTrackingNumber);
    }

    private String generateOrderTrackingNumber() {

        // generate a random UUID number (UUID version-4)
        // For details see: https://en.wikipedia.org/wiki/University_unique_identifier
        //
        return UUID.randomUUID().toString();
    }
}