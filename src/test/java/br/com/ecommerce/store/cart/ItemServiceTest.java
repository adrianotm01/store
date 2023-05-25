package br.com.ecommerce.store.cart;

import static br.com.ecommerce.store.utils.ProductMocks.mockProduct;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyMap;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import br.com.ecommerce.store.cart.db.ItemRepository;
import br.com.ecommerce.store.cart.model.Cart;
import br.com.ecommerce.store.cart.model.Item;
import br.com.ecommerce.store.cart.rest.dto.CartRequestDTO;
import br.com.ecommerce.store.cart.rest.dto.ItemDTO;
import br.com.ecommerce.store.cart.rest.dto.ProductItemDTO;
import br.com.ecommerce.store.cart.service.CartService;
import br.com.ecommerce.store.cart.service.ItemService;
import br.com.ecommerce.store.discount.rest.dto.DiscountDTO;
import br.com.ecommerce.store.product.model.Product;
import br.com.ecommerce.store.product.rest.dto.ProductDTO;
import br.com.ecommerce.store.product.service.ProductService;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ItemServiceTest {

    @InjectMocks
    ItemService itemService;

    @Mock
    CartService cartService;

    @Mock
    ProductService productService;

    @Mock
    private ObjectMapper mapper;

    @Captor
    ArgumentCaptor<Item> itemArgumentCaptor;

    @Mock
    private ItemRepository itemRepository;

    @BeforeEach
    public void setUpMocks() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void shouldCreateItemOnCart() {
        Item item = new Item(null, null, mockProduct(), 2, null);
        ItemDTO itemDTO = new ItemDTO();
        itemDTO.setQuantity(2);
        itemDTO.setProduct(new ProductItemDTO(1L, new DiscountDTO(1L, null)));
        when(productService.getProductById(any())).thenReturn(new ProductDTO(BigDecimal.TEN, "Notebook test", null));
        when(mapper.convertValue(any(), eq(Item.class))).thenReturn(item);
        itemService.createItem(itemDTO);
        verify(itemRepository).save(itemArgumentCaptor.capture());
        Item itemActual = itemArgumentCaptor.getValue();
        assertEquals(2, itemActual.getQuantity());
        assertEquals(BigDecimal.valueOf(20), itemActual.getPrice());
    }

    @Test
    public void shouldCreateItemOnCartExistent() {
        Cart mockCart = new Cart(1L, BigDecimal.TEN);
        Item item = new Item(null, mockCart, mockProduct(), 2, null);
        ItemDTO itemDTO = new ItemDTO();
        itemDTO.setQuantity(2);
        itemDTO.setProduct(new ProductItemDTO(2L, null));
        itemDTO.setCart(new CartRequestDTO(1L, BigDecimal.TEN));
        when(productService.getProductById(any())).thenReturn(new ProductDTO(BigDecimal.valueOf(15), "Notebook test", null));
        when(cartService.findCartById(1L)).thenReturn(Optional.of(mockCart));
        when(mapper.convertValue(any(), eq(Item.class))).thenReturn(item);
        itemService.createItem(itemDTO);
        verify(itemRepository).save(itemArgumentCaptor.capture());
        Item itemActual = itemArgumentCaptor.getValue();
        assertEquals(BigDecimal.valueOf(30), itemActual.getPrice());
        assertEquals(1L, itemActual.getProduct().getId());
        assertEquals(1L, itemActual.getCart().getId());
        assertEquals(BigDecimal.valueOf(40), itemActual.getCart().getTotalPrice());
    }


    @Test
    public void shouldCreateItemAndCalculateWithDiscount1(){
        Cart mockCart = new Cart(1L, BigDecimal.TEN);
        Item item = new Item(null, mockCart, mockProduct(), 2, null);
        ItemDTO itemDTO = new ItemDTO();
        itemDTO.setQuantity(2);
        itemDTO.setProduct(new ProductItemDTO(1L, null));
        itemDTO.setCart(new CartRequestDTO(1L, BigDecimal.TEN));
        when(productService.getProductById(any())).thenReturn(new ProductDTO(BigDecimal.valueOf(15), "Notebook test", null));
        when(cartService.findCartById(1L)).thenReturn(Optional.of(mockCart));
        when(mapper.convertValue(any(), eq(Item.class))).thenReturn(item);
        itemService.createItem(itemDTO);
        verify(itemRepository).save(itemArgumentCaptor.capture());
        Item itemActual = itemArgumentCaptor.getValue();
        assertEquals(BigDecimal.valueOf(30), itemActual.getPrice());
        assertEquals(1L, itemActual.getProduct().getId());
        assertEquals(1L, itemActual.getCart().getId());
        assertEquals(BigDecimal.valueOf(40), itemActual.getCart().getTotalPrice());
    }
}
