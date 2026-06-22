package org.scoula.product.service;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.scoula.config.RootConfig;
import org.scoula.product.dto.ProductDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {RootConfig.class})
@Log4j2
class ProductServiceTest {

    @Autowired
    private ProductService service;

    @Test
    public void getList() {
        List<ProductDTO> list = service.getList();
        log.info("상품 수: " + list.size());
        list.forEach(p -> log.info(p));
    }

    @Test
    public void get() {
        log.info(service.get(1L));
    }
}