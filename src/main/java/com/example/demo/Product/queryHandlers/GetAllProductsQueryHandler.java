package com.example.demo.Product.queryHandlers;

import com.example.demo.Product.Model.Product;
import com.example.demo.Product.Model.ProductDTO;
import com.example.demo.Product.ProductRepository;
import com.example.demo.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetAllProductsQueryHandler implements Query<Void, List<ProductDTO>> {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public ResponseEntity<List<ProductDTO>> execute(Void input) {
        List<ProductDTO> productDTOList =
                productRepository.findAll()
                        .stream().map(ProductDTO::new)
                        .toList();

        return ResponseEntity.ok(productDTOList);
    }
}
