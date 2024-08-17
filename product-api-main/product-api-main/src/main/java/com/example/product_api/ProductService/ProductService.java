package com.example.product_api.ProductService;

import com.example.product_api.dto.ProductDto;
import com.example.product_api.entity.Product;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ProductService {
    public ProductDto addProduct(ProductDto productDto);
    public ProductDto getProductbyid(int id);

    public void deleteproductbyid(int id);

    public boolean isAvailable(int id);

    public ProductDto updateProduct(ProductDto productDto);

    public List<ProductDto> findProductByname(String name);

    public Page<ProductDto> getAllProducts(int page,int size);


}
