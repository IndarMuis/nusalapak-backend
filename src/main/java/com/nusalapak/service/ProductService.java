package com.nusalapak.service;

import com.nusalapak.dto.request.ProductCreateRequest;
import com.nusalapak.dto.response.ProductCreateResponse;
import com.nusalapak.dto.response.ProductResponse;

import java.util.List;

public interface ProductService {

    ProductCreateResponse addProduct(ProductCreateRequest request);

    List<ProductResponse> findAll();

}
