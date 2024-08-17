package com.example.product_api.controller;

import com.example.product_api.ProductService.ProductService;
import com.example.product_api.dto.ProductDto;
import com.example.product_api.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductRestController {

  @Autowired
  ProductService productService ;
  //@Autowired

  @CrossOrigin(origins = "http://localhost:4200")
  @GetMapping("/products")
  public ResponseEntity <Page<ProductDto>> getProducts(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "5") int size){

    return new ResponseEntity<>(productService.getAllProducts(page,size),HttpStatus.OK);
  }

  @GetMapping("/products/{id}")
  public ResponseEntity <ProductDto> getProduct(@PathVariable("id") int id){



    return new ResponseEntity<>(productService.getProductbyid(id),HttpStatus.OK) ;
  }

  @GetMapping("/getproductbyname/{name}")
  public ResponseEntity <List<ProductDto>> getProductbyname(@PathVariable("name") String name){

    //List<Product> products=productRepo.findByName(name);
    //productRepo.findById(product.getId()).orElseThrow(()->new NoSuchElementException("no product with this name"));
    //System.out.println(productRepo.findByName(name));
    return new ResponseEntity<>(productService.findProductByname(name),HttpStatus.OK);
  }

  @GetMapping("/products/{id}/isavailable")
  public ResponseEntity <Boolean> isavailable(@PathVariable("id") int id){

    return new ResponseEntity<>(productService.isAvailable(id),HttpStatus.OK);

  }

  @PostMapping("/products")
  public ResponseEntity <ProductDto> createProduct(@RequestBody ProductDto productdto){
    return new ResponseEntity<>(productService.addProduct(productdto),HttpStatus.OK);
  }


  @PutMapping("/products")
  public ResponseEntity <ProductDto> updateProduct(@RequestBody ProductDto productdto){

    return new ResponseEntity<>(productService.updateProduct(productdto),HttpStatus.OK);
  }

  @DeleteMapping("/products/{id}")
  public ResponseEntity <String> deleteProduct(@PathVariable("id") int id){
    productService.deleteproductbyid(id);
    return  new ResponseEntity<>("product deleted successfully",HttpStatus.OK);
  }


}
