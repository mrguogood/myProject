package guo.controller;

import guo.entity.Cart;
import guo.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class CartController {

    @Autowired
    private CartService cartService;

    // 查询购物车
    @RequestMapping("/cart/manage/query")
    @ResponseBody
    public List<Cart> queryCart(String userId){

        return cartService.queryCart(userId);
    }
}
