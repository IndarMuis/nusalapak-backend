package com.nusalapak.service.impl;

import com.nusalapak.dto.request.ProductCreateRequest;
import com.nusalapak.dto.response.ProductCreateResponse;
import com.nusalapak.entity.Product;
import com.nusalapak.entity.ProductCategory;
import com.nusalapak.entity.Seller;
import com.nusalapak.helper.Formatter;
import com.nusalapak.repository.ProductCategoryRepository;
import com.nusalapak.repository.ProductRepository;
import com.nusalapak.repository.SellerRepository;
import com.nusalapak.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final SellerRepository sellerRepository;
    private final ProductCategoryRepository productCategoryRepository;
    private final Formatter formatter;

    @Transactional
    @Override
    public ProductCreateResponse addProduct(ProductCreateRequest request) {
        System.out.println("PRODUCT SERVICE");
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        Seller seller = sellerRepository.findSellerByAccountEmail(userDetails.getUsername())
                .orElseThrow(
                        () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "seller not found")
                );

        ProductCategory productCategory = productCategoryRepository.findById(request.getCategoryId())
                .orElseThrow(
                        () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "category not found")
                );

        Product product = Product.builder()
                .name(request.getName())
                .price(request.getPrice())
                .amount(request.getAmount())
                .description(request.getDescription())
                .productCategory(productCategory)
                .seller(seller).build();

        productRepository.save(product);

        return ProductCreateResponse.builder()
                .name(product.getName())
                .description(product.getDescription())
                .price(formatter.priceToIDR(product.getPrice()))
                .category(productCategory.getName())
                .seller(seller.getName()).build();
    }
}
