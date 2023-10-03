package com.nusalapak.service;

import com.nusalapak.dto.response.ProductCategoryResponse;

import java.util.List;

public interface ProductCategoryService {

    List<ProductCategoryResponse> findAll();

}
