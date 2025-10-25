import React from 'react';
import { Trash2, Plus, Minus, ShoppingCart, Pause } from 'lucide-react';
import { usePOS } from '../../../context/usePOS';
import { Button } from '@/components/ui/button';

const CartSection = () => {
  const { cartItems, updateQuantity, removeFromCart, subtotal, tax, total } = usePOS();

  return (
    <div className='flex-1 flex flex-col bg-white border-l border-r border-gray-200'>
      {/* Header */}
      <div className='px-6 py-3 border-b border-gray-200 bg-gray-50'>
        <div className='flex items-center justify-between'>
          <div className='flex items-center space-x-2'>
            <ShoppingCart size={20} className='text-gray-700' />
            <h2 className='text-lg font-semibold text-gray-800'>Cart ({cartItems.length}) item</h2>
          </div>
          <div className='flex space-x-2'>
            <Button variant="outline">
              <Pause />
              Hold
            </Button>
            <Button variant="outline">
              <Trash2 />
              Clear
            </Button>
          </div>
        </div>
      </div>

      {/* Cart Items */}
      <div className='flex-1 overflow-y-auto px-4 py-4 space-y-3'>
        {cartItems.length === 0 ? (
          <div className='flex flex-col items-center justify-center h-full text-gray-400 gap-3'>
            <ShoppingCart size={100} />
            <p className='text-lg font-medium'>Cart is Empty</p>
            <p>Add products to start an order</p>
          </div>
        ) : (
          cartItems.map((item) => (
            <div key={item.id} style={{ borderLeft: '4px solid #22c55e' }} className='bg-white border border-gray-300 p-3 rounded'>
              <div className='flex justify-between items-start mb-2'>
                <div className='flex-1'>
                  <h3 className='text-sm font-semibold text-gray-800 line-clamp-2'>{item.name}</h3>
                  <p className='text-xs text-gray-500 mt-1'>{item.sku}</p>
                </div>
                <span className='text-sm font-bold text-green-600 ml-2'>${(item.price * item.quantity).toFixed(2)}</span>
              </div>

              {/* Quantity Controls */}
              <div className='flex items-center justify-between mt-3'>
                <div className='flex items-center space-x-2'>
                  <button
                    onClick={() => updateQuantity(item.id, item.quantity - 1)}
                    className='bg-white border border-gray-300 p-1 rounded hover:bg-gray-100'
                  >
                    <Minus size={14} className='text-gray-600' />
                  </button>
                  <span className='w-6 text-center text-sm font-semibold'>{item.quantity}</span>
                  <button
                    onClick={() => updateQuantity(item.id, item.quantity + 1)}
                    className='bg-white border border-gray-300 p-1 rounded hover:bg-gray-100'
                  >
                    <Plus size={14} className='text-gray-600' />
                  </button>
                </div>
                <div className='flex items-center space-x-2'>
                  <span className='text-xs font-medium text-gray-600'>${item.price}</span>
                  <button
                    onClick={() => removeFromCart(item.id)}
                    className='text-red-500 hover:text-red-700 p-1'
                  >
                    <Trash2 size={14} />
                  </button>
                </div>
              </div>
            </div>
          ))
        )}
      </div>

      {/* Summary Section */}
      <div className='border-t border-gray-200 px-6 py-3 bg-gray-50'>
        <div className='space-y-2'>
          <div className='flex justify-between text-sm text-gray-700'>
            <span>Subtotal</span>
            <span>${subtotal.toFixed(2)}</span>
          </div>
          <div className='flex justify-between text-sm text-gray-700'>
            <span>Tax</span>
            <span>${tax.toFixed(2)}</span>
          </div>
          <div className='border-t border-gray-300 pt-2 flex justify-between text-lg font-bold text-gray-800'>
            <span>Total</span>
            <span>${total.toFixed(2)}</span>
          </div>
        </div>
      </div>
    </div>
  );
};

export default CartSection;