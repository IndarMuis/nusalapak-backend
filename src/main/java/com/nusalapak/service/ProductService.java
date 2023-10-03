package com.nusalapak.service;

import com.nusalapak.dto.request.ProductCreateRequest;
import com.nusalapak.dto.response.ProductCreateResponse;

public interface ProductService {

    ProductCreateResponse addProduct(ProductCreateRequest request);

}
