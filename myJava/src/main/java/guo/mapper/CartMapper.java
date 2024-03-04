package guo.mapper;


import guo.entity.Cart;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface CartMapper {

    // 查询购物车
    List<Cart> selectCart(String userId);

}
