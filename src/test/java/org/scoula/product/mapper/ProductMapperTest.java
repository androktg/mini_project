package org.scoula.product.mapper;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.scoula.config.RootConfig;
import org.scoula.product.domain.ProductVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {RootConfig.class})
@Log4j2
class ProductMapperTest {

    @Autowired
    private ProductMapper mapper;

    @Test
    public void getList() {
        List<ProductVO> list = mapper.getList();
        for (ProductVO product : list) {
            log.info(product);
        }
    }
}