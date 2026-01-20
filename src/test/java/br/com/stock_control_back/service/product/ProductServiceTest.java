package br.com.stock_control_back.service.product;

import br.com.stock_control_back.dto.product.ProductRequestDTO;
import br.com.stock_control_back.dto.product.ProductResponseDTO;
import br.com.stock_control_back.entity.Product;
import br.com.stock_control_back.enums.ProductType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.ActiveProfiles;

import java.math.BigDecimal;
import java.util.List;

@SpringBootTest
@ActiveProfiles("test")
public class ProductServiceTest {

    @Autowired
    IProductService productService;

    @Test
    void saveProductAndFindProduct(){
        ProductRequestDTO product = createProductDTO("Alcatra com Maminha", "2592");
        productService.save(product);

        Product productGetted = productService.findByCode(product.code()).data();

        Assertions.assertNotNull(productGetted);
    }

    @Test
    void findProductWithFilter(){
        ProductRequestDTO p1 = createProductDTO("Miolo da Alcatra", "2592");
        ProductRequestDTO p2 = createProductDTO("Alcatra com Maminha", "8679");
        ProductRequestDTO p3 = createProductDTO("Patinho", "24990");

        productService.save(p1);
        productService.save(p2);
        productService.save(p3);

        List<ProductResponseDTO> products = productService.findAll(Pageable.unpaged(),
                "Alcatra",
                null,
                null).data();

        Assertions.assertEquals(2, products.size());
    }

    @Test
    void updateProduct(){
        ProductRequestDTO product = createProductDTO("Alcatra", "2592");
        productService.save(product);

        Long productId = productService.findByCode(product.code()).data().getId();

        ProductRequestDTO updatedProduct = createProductDTO("Miolo da Alcatra", "2592");

        this.productService.update(productId, updatedProduct);

        Product productUpdated = productService.findById(productId).data();

        Assertions.assertEquals("Miolo da Alcatra", productUpdated.getName());
    }

    private ProductRequestDTO createProductDTO(String name, String code){
        return new ProductRequestDTO(
                name,
                code,
                new BigDecimal("39.97"),
                ProductType.VACUO
        );
    }

    private ProductRequestDTO createProductDTO(String name, String code, ProductType productType){
        return new ProductRequestDTO(
                name,
                code,
                new BigDecimal("39.97"),
                productType
        );
    }
}
