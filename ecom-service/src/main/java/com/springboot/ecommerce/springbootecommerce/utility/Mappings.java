package com.springboot.ecommerce.springbootecommerce.utility;

public final class Mappings {

    //Root product URI
    public static final String API_PRODUCTS = "/api/products";
    //Product id URI
    public static final String PRODUCT_ID = "/{id}";
    public static final String QTY_STOCK = PRODUCT_ID+"/instock";

    //Root order URI
    public static final String API_ORDERS = "/api/orders";
    //Order id URI
    public static final String ORDER_ID = "/{id}";
    //Order status URI
    public static final String ORDER_STATUS = ORDER_ID+"/status";
    //cancel order URI
    public static final String CANCEL_ORDER = ORDER_ID+"/cancel";
    //delete order URI
    public static final String DELETE_ORDER = ORDER_ID+"/delete";
    public static final String CREATE_ORDER = "/neworder";
    public static final String ORDER_VALUE = ORDER_ID+"/value";
}
