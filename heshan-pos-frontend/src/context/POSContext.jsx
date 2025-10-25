import React, { useState } from 'react';
import { POSContext } from './POSContextType';

const POSProvider = ({ children }) => {
  const [cartItems, setCartItems] = useState([]);
  const [selectedCustomer, setSelectedCustomer] = useState(null);
  const [discountPercentage, setDiscountPercentage] = useState(0);
  const [discountType, setDiscountType] = useState('percent'); // 'percent' or 'fixed'
  const [orderNote, setOrderNote] = useState('');
  const [searchTerm, setSearchTerm] = useState('');

  // Calculate totals
  const subtotal = cartItems.reduce((sum, item) => sum + (item.price * item.quantity), 0);
  const tax = subtotal * 0.1; // 10% tax
  let discountAmount = 0;
  if (discountType === 'percent') {
    discountAmount = (subtotal * discountPercentage) / 100;
  } else {
    discountAmount = discountPercentage;
  }
  const total = subtotal + tax - discountAmount;

  // Add item to cart
  const addToCart = (product) => {
    const existingItem = cartItems.find(item => item.id === product.id);
    if (existingItem) {
      setCartItems(
        cartItems.map(item =>
          item.id === product.id
            ? { ...item, quantity: item.quantity + 1 }
            : item
        )
      );
    } else {
      setCartItems([
        ...cartItems,
        {
          id: product.id,
          name: product.name,
          sku: product.sku,
          price: product.sellingPrice || product.price,
          quantity: 1,
          image: product.image,
        },
      ]);
    }
  };

  // Remove item from cart
  const removeFromCart = (itemId) => {
    setCartItems(cartItems.filter(item => item.id !== itemId));
  };

  // Update item quantity
  const updateQuantity = (itemId, quantity) => {
    if (quantity <= 0) {
      removeFromCart(itemId);
      return;
    }
    setCartItems(
      cartItems.map(item =>
        item.id === itemId ? { ...item, quantity } : item
      )
    );
  };

  // Clear cart
  const clearCart = () => {
    setCartItems([]);
    setSelectedCustomer(null);
    setDiscountPercentage(0);
    setOrderNote('');
  };

  const value = {
    // Cart state
    cartItems,
    addToCart,
    removeFromCart,
    updateQuantity,
    clearCart,

    // Customer state
    selectedCustomer,
    setSelectedCustomer,

    // Discount state
    discountPercentage,
    setDiscountPercentage,
    discountType,
    setDiscountType,

    // Order note
    orderNote,
    setOrderNote,

    // Search
    searchTerm,
    setSearchTerm,

    // Totals
    subtotal,
    tax,
    discountAmount,
    total,
  };

  return <POSContext.Provider value={value}>{children}</POSContext.Provider>;
};

export default POSProvider;
