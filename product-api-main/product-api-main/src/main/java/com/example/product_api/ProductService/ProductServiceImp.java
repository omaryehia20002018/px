package com.example.product_api.ProductService;

import com.example.product_api.Mapper.ProductMapper;
import com.example.product_api.dto.ProductDto;
import com.example.product_api.entity.Product;
import com.example.product_api.repository.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImp implements ProductService{
    //@Autowired
    private final ProductMapper productMapper;

    private final ProductRepo productRepo;
    @Autowired
    public ProductServiceImp(ProductMapper productMapper, ProductRepo productRepo) {
        this.productMapper = productMapper;
        this.productRepo = productRepo;
    }

    @Override
    public ProductDto addProduct(ProductDto productDto) {
        Product product=productMapper.toProduct(productDto);
        productRepo.save(product);

        return productDto;
    }

    @Override
    public ProductDto getProductbyid(int id) {
        Product product=productRepo.findById(id).get();
        ProductDto productDto=productMapper.toProductDto(product);
        return productDto;
    }

    @Override
    public void deleteproductbyid(int id) {
         productRepo.deleteById(id);
    }

    @Override
    public boolean isAvailable(int id) {
        if (!productRepo.findById(id).isPresent()){
            return false;
        }


        return true;
    }

    @Override
    public ProductDto updateProduct(ProductDto productDto) {
        Product product=productMapper.toProduct(productDto);
        productRepo.save(product);
        return productDto;
    }

    @Override
    public List<ProductDto> findProductByname(String name) {
        //List<Product> products=productRepo.findByName(name);

        return productRepo.findAll().stream().map(productMapper::toProductDto).collect(Collectors.toList());
    }

    @Override
    public Page<ProductDto> getAllProducts(int page,int size) {
        Pageable pageable= PageRequest.of(page,size);

        Page<Product> products= productRepo.findAll(pageable);
        List<ProductDto> productDtos=products.stream().map(productMapper::toProductDto).collect(Collectors.toList());

        int start = (int) pageable.getOffset();
        int end = Math.min((start + pageable.getPageSize()), productDtos.size());

        // Create a sublist for the current page
        List<ProductDto> paginatedList = productDtos.subList(start, end);
        return new PageImpl<>(paginatedList, pageable, productDtos.size());
    }
}
