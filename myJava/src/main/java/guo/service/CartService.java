package guo.service;

import guo.entity.Cart;
import guo.mapper.CartMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartService {
    @Autowired
    private CartMapper cartMapper;

    public List<Cart> queryCart(String userId) {

        return cartMapper.selectCart(userId);
    }
}
