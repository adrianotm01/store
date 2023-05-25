package br.com.ecommerce.store.cart.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import br.com.ecommerce.store.cart.db.ItemRepository;
import br.com.ecommerce.store.cart.model.Cart;
import br.com.ecommerce.store.cart.model.Item;
import br.com.ecommerce.store.cart.rest.dto.CartResponseDTO;
import br.com.ecommerce.store.cart.rest.dto.ItemDTO;
import br.com.ecommerce.store.cart.rest.dto.ItemResponseDTO;
import br.com.ecommerce.store.exception.NotFoundException;
import br.com.ecommerce.store.product.model.Product;
import br.com.ecommerce.store.product.rest.dto.ProductDTO;
import br.com.ecommerce.store.product.service.ProductService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;

    private final ObjectMapper mapper;

    private final CartService cartService;

    private final ProductService productService;

    private static final int ID_DISCOUNT_2_PER_1 = 1;
    private static final int ID_DISCOUNT_3_PER_10 = 2;
    private static final int QUANTITY_ITEM_ON_CART_3_PER_10 = 3;
    private static final int PRICE_WHEN_QUANTITY_3 = 10;

    public void createItem(ItemDTO itemDTO) {
        Cart cart = new Cart();
        if (!(itemDTO.getCart() == null) && itemDTO.getCart().getId() != null) {
            cart = cartService.findCartById(itemDTO.getCart().getId()).orElseThrow();
        }
        Item item = mapper.convertValue(itemDTO, Item.class);
        verifyDiscount(itemDTO, item);
        cart.setTotalPrice(cart.getTotalPrice().add(item.getPrice()));
        item.setCart(cart);
        itemRepository.save(item);
    }

    private void verifyDiscount(ItemDTO itemDTO, Item item){
        ProductDTO product = productService.getProductById(itemDTO.getProduct().getId());
        if (product.getDiscount() == null)
            item.setPrice(calculatePriceItem(itemDTO.getQuantity(),product.getPrice()));
        else{
            if (product.getDiscount().getId() == ID_DISCOUNT_2_PER_1) {
                item.setPrice(BigDecimal.ONE.divide(BigDecimal.valueOf(item.getQuantity())).multiply(product.getPrice()));
            }else if(product.getDiscount().getId() == ID_DISCOUNT_3_PER_10) {
                if (item.getQuantity() == QUANTITY_ITEM_ON_CART_3_PER_10) {
                    item.setPrice(BigDecimal.valueOf(PRICE_WHEN_QUANTITY_3));
                }else{
                    item.setPrice(calculatePriceItem(itemDTO.getQuantity(),product.getPrice()));
                }
            }
        }
    }

    public CartResponseDTO getItemsByCart(Long id) {
        List<ItemResponseDTO> itemDTOList = itemRepository.findByCartId(id).stream().map(item ->
          mapper.convertValue(item, ItemResponseDTO.class)).collect(Collectors.toList());
        BigDecimal totalPrice = calculatePriceCart(itemDTOList);
        CartResponseDTO cartResponseDTO = new CartResponseDTO(id, totalPrice, itemDTOList);
        return cartResponseDTO;
    }

    public void updateItem(Long id, ItemDTO itemDTO) {
        Item item = itemRepository.findById(id).orElseThrow(()-> new NotFoundException("Item não encontrado"));
        item.setProduct(mapper.convertValue(itemDTO.getProduct(), Product.class));
        item.setQuantity(itemDTO.getQuantity());
        item.setCart(mapper.convertValue(itemDTO.getCart(), Cart.class));
        itemRepository.save(item);
    }

    private BigDecimal calculatePriceItem(Integer quantity, BigDecimal price){
        return BigDecimal.valueOf(quantity).multiply(price);
    }

    public BigDecimal calculatePriceCart(List<ItemResponseDTO> itemDTOList) {

        return itemDTOList.stream().map(itemDTO -> itemDTO.getPrice()).reduce(BigDecimal.ZERO,BigDecimal::add);
    }

    public void removeItem(Long id) {
        Item item = itemRepository.findById(id).orElseThrow(()-> new NotFoundException("Item não encontrado"));
        itemRepository.delete(item);
    }
}
