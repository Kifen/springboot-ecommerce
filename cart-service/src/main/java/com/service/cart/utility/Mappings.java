package com.service.cart.utility;

public final class Mappings {

    public static final String CART_API = "/api/carts";

    public static final String CREATE_CART = "/create";
    public static final String GET_CART = "/{email}";
    public static final String ADD_TO_CART = "/additem/{email}";
    public static final String EMPTY_CART = "/empty/{email}";
    public static final String IS_CART_EMPTY = "/is_empty/{email}";
    public static final String SIZE = "/{email}/size";
    public static final String REMOVE_ITEM = "/{email}/removeitem";
    public static final String CART_ITEMS = "/{email}/items";
}
