package fr.lu_div.ecom.dto;

import fr.lu_div.ecom.entity.Address;
import fr.lu_div.ecom.entity.Customer;
import fr.lu_div.ecom.entity.Order;
import fr.lu_div.ecom.entity.OrderItem;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

import java.io.Serializable;
import java.util.Set;

@Data
public class Purchase {

    private Customer customer;
    private Address shippingAddress;
    private  Address billingAddress;
    private Order order;
    private Set<OrderItem> orderItems;
}
