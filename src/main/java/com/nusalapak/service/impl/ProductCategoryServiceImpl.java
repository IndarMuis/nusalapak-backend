package com.nusalapak.service.impl;

import com.nusalapak.dto.response.ProductCategoryResponse;
import com.nusalapak.entity.ProductCategory;
import com.nusalapak.repository.ProductCategoryRepository;
import com.nusalapak.repository.ProductRepository;
import com.nusalapak.service.ProductCategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductCategoryServiceImpl implements ProductCategoryService {

    private final ProductCategoryRepository productCategoryRepository;
    private final ProductRepository productRepository;

    @Override
    public List<ProductCategoryResponse> findAll() {

        List<ProductCategory> categories = productCategoryRepository.findAll();
        if (categories.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "data not found");
        }

        List<ProductCategoryResponse> response = categories.stream()
                .map(category -> ProductCategoryResponse.builder()
                        .id(category.getId())
                        .name(category.getName()).build()
                ).toList();

        return response;
    }
}
