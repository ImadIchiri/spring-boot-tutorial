package ProductControllerTest;

import com.example.demo.Exceptions.ProductNotFoundException;
import com.example.demo.NoBsSpringBootApplication;
import com.example.demo.Product.Model.Product;
import com.example.demo.Product.Model.ProductDTO;
import com.example.demo.Product.ProductRepository;
import com.example.demo.Product.commandhandlers.CreateProductCommandHandler;
import com.example.demo.Product.queryHandlers.GetProductQueryHandler;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = NoBsSpringBootApplication.class)
public class GetProductQueryHandlerTest {

    @InjectMocks
    private GetProductQueryHandler getProductQueryHandler;

    @InjectMocks
    private CreateProductCommandHandler createProductCommandHandler;

    @Mock
    private ProductRepository productRepository;

    @Test
    public void getProductQueryHandler_validId_returnsProductDTO() {

        // Given
        Product product = new Product();
        product.setId("test-product");
        product.setPrice(9.99);
        product.setName("Test Product");
        product.setDescription("This description is for testing");
        product.setQuantity(10);

        ProductDTO expectedDTO = new ProductDTO(product);

        // And (Given)
        when(productRepository.findById(product.getId())).thenReturn(Optional.of(product));

        // When
        ResponseEntity<ProductDTO> actualResponse = getProductQueryHandler.execute(product.getId());

        // Then
        assertEquals(expectedDTO, actualResponse.getBody());
        assertEquals(HttpStatus.OK, actualResponse.getStatusCode());
    }

    @Test
    public void getProductQueryHandler_invalidId_throwsProductNotFoundException() {
        String idToLookFor = "111-222-333";

        // Given
        when(productRepository.findById(idToLookFor)).thenReturn(Optional.empty());

        // When / Then 1
        ProductNotFoundException exception = assertThrows(ProductNotFoundException.class, () -> getProductQueryHandler.execute(idToLookFor));

        // Then 2
        assertEquals("Product Not Found !", exception.getSimpleResponse().getMessage());
    }
}
