package com.redhat.microsaga.order.repository;

import com.redhat.microsaga.order.domain.Order;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class OrderRepository implements PanacheRepositoryBase<Order, String> {
}
