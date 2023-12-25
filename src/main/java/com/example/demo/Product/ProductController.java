package com.example.demo.Product;

import com.example.demo.Product.Model.Product;
import com.example.demo.Product.Model.ProductDTO;
import com.example.demo.Product.Model.UpdateProductCommand;
import com.example.demo.Product.commandhandlers.CreateProductCommandHandler;
import com.example.demo.Product.commandhandlers.DeleteProductCommandHandler;
import com.example.demo.Product.commandhandlers.UpdateProductCommandHandler;
import com.example.demo.Product.queryHandlers.GetAllProductsQueryHandler;
import com.example.demo.Product.queryHandlers.GetProductQueryHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/products")
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private GetAllProductsQueryHandler getAllProductsQueryHandler;

    @Autowired
    private GetProductQueryHandler getProductQueryHandler;

    @Autowired
    private CreateProductCommandHandler createProductCommandHandler;

    @Autowired
    private UpdateProductCommandHandler updateProductCommandHandler;

    @Autowired
    private DeleteProductCommandHandler deleteProductCommandHandler;

    @GetMapping
    public ResponseEntity<List<ProductDTO>> getAllProducts() {
        return getAllProductsQueryHandler.execute(null);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDTO> getAllProduct(@PathVariable String id) {
        return getProductQueryHandler.execute(id);
    }

    @PostMapping("/create")
    public ResponseEntity addProduct(@RequestBody Product product) {
        return createProductCommandHandler.execute(product);
    }

    @PutMapping("/{id}/update")
    public ResponseEntity updateProduct(@PathVariable String id, @RequestBody Product product) {
        UpdateProductCommand updateProductCommand = new UpdateProductCommand(id, product);
        return updateProductCommandHandler.execute(updateProductCommand);
    }

    @DeleteMapping("/{id}/delete")
    public ResponseEntity deleteProduct(@PathVariable String id) {
        return deleteProductCommandHandler.execute(id);
    }

}
