package ProductControllerTest;

import com.example.demo.Exceptions.ProductNotValidException;
import com.example.demo.NoBsSpringBootApplication;
import com.example.demo.Product.Model.Product;
import com.example.demo.Product.ProductRepository;
import com.example.demo.Product.commandhandlers.CreateProductCommandHandler;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest(classes = NoBsSpringBootApplication.class)
public class CreateProductCommandHandlerTest {

    @InjectMocks
    private CreateProductCommandHandler createProductCommandHandler;

    @Mock
    private ProductRepository productRepository;

    @Test
    public void createProductCommandHandler_validProduct_returnsSuccess() {

        // Given, When, Then
        // Arrange, Act, Assert

        // Given
        // Arrange
        Product product = new Product();
        product.setId("test-product");
        product.setPrice(9.99);
        product.setName("Test Product");
        product.setDescription("This description is for testing");
        product.setQuantity(10);

        // When
        // Act
        ResponseEntity response = createProductCommandHandler.execute(product);

        // Then
        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void createProductCommandHandler_invalidPrice_throwsInvalidPriceException() {

        // Given
        Product product = new Product();
        product.setId("test-product");
        product.setPrice(-9.99);
        product.setName("Test Product");
        product.setDescription("This description is for testing");
        product.setQuantity(10);

        // When / Then 1
        ProductNotValidException exception = assertThrows(ProductNotValidException.class, () -> createProductCommandHandler.execute(product));

        // Then 2
        assertEquals("Price should be more than 0 !", exception.getSimpleResponse().getMessage());
    }
}
