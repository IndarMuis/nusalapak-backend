package com.nusalapak.service.impl;

import com.nusalapak.dto.request.ProductCreateRequest;
import com.nusalapak.dto.response.ProductCreateResponse;
import com.nusalapak.dto.response.ProductResponse;
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

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

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

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        Seller seller = sellerRepository.findSellerByAccountEmail(userDetails.getUsername())
                .orElseThrow(
                        () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "seller not found")
                );

        if (productRepository.existsProductBySellerIdAndName(seller.getId(), request.getName())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "product is already on the list");
        }

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

    @Override
    public List<ProductResponse> findAll() {

        List<Product> products = productRepository.findAll();

        return products.stream().map(product -> ProductResponse.builder()
                .id(product.getId())
                .name(product.getName())
                .price(formatter.priceToIDR(product.getPrice()))
                .description(product.getDescription())
                .category(product.getProductCategory().getName())
                .seller(product.getSeller().getName())
                .quantity(product.getQuantity()).build()).collect(Collectors.toList());
    }


}
