package com.medbillpro.constants;

public class URLMapping {
    public static final String ADMIN_API_BASE = "/api/admin";
    
    public static final String ADMIN_LOGIN = "/login";
    public static final String ADMIN_DETAILS = "/adminDetails";
    public static final String GET_ADMIN_DETAILS = "/getadmindetails";

    public static final String CREATE_INVOICE ="/save-Invoice";
    public static final String GET_INVOICE_BY_ID="/getInvoiceBy/{id}";
    public static final String GET_ALL_INVOICE = "/getAll-Invoice";
    public static final String UPDATE_INVOICE = "/update/{id}";
    public static final String DELETE_INVOICE= "/delete/{id}";
    
    //seller Class urls
    public static final String SELLER_API_BASE ="/api/sellers";
    public static final String CREATE_SELLER ="/save-new-SellerDetails";
    public static final String  UPDATE_SELLER="/update-SellerDetail/{id}";
    public static final String GET_ALL_SELLER = "/getSellerDetails";
    public static final String GET_SELLER_BY_ID = "/getSellerDetail/{id}";
    public static final String DELETE_SELLER= "/delete-SellerDetail/{id}";
    
    public static final String PRODUCT_API_BASE = "/api/products";
    public static final String CREATE_PRODUCT ="/save-new-ProductDetails";
    public static final String GET_PRODUCT_BY_ID="/getProductDetail/{id}";
    public static final String GET_ALL_PRODUCT="/getProductDetails";
    public static final String DELETE_PRODUCT="/delete-ProductDetail";
    public static final String UPDATE_PRODUCT="/update-ProductDetail/{id}";
    
    public static final String BUYER_API_BASE ="/api/buyers/";
    public static final String CREATE_BUYERDETAILS ="/save-new-BuyerDetails";
    public static final String GET_BUYERDETAILS_BY_ID="/getBuyerDetail/{id}";
    public static final String GET_ALL_BUYERDETAILS="/getBuyerDetails";
    public static final String DELETE_BUYERDETAILS="/delete-BuyerDetail/{id}";
    public static final String UPDATE_BUYERDETAILS="/update-BuyerDetail/{id}";
}
