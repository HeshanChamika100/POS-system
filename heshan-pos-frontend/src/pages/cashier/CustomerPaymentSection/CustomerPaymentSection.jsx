import React from 'react';
import { User, Tag } from 'lucide-react';
import { usePOS } from '../../../context/usePOS';

const CustomerPaymentSection = () => {
  const { 
    discountPercentage, 
    setDiscountPercentage,
    discountType,
    setDiscountType,
    orderNote,
    setOrderNote,
    total,
    discountAmount,
  } = usePOS();

  const handleDiscountChange = (e) => {
    setDiscountPercentage(Number(e.target.value));
  };

  const handleNoteChange = (e) => {
    setOrderNote(e.target.value);
  };

  const handleProcessPayment = () => {
    console.log('Processing payment:', {
      total,
      discountAmount,
      finalAmount: total - discountAmount,
      orderNote,
    });
  };

  const handleHoldOrder = () => {
    console.log('Holding order');
  };

  const finalAmount = total - discountAmount;

  return (
    <div className='w-80 flex flex-col bg-white border-l border-gray-200'>
      {/* Header */}
      <div className='px-6 py-3 border-b border-gray-200 bg-gray-50'>
        <div className='flex items-center space-x-2'>
          <User size={18} />
          <h3 className='text-lg font-semibold text-gray-800'>Customer</h3>
        </div>
      </div>

      {/* Content */}
      <div className='flex-1 overflow-y-auto px-6 py-4'>
        {/* Customer Section */}
        <div className='mb-6'>
          <button className='w-full bg-white hover:bg-gray-50 border border-gray-300 rounded-lg py-2 px-4 text-gray-700 font-medium transition text-sm'>
            Select Customer
          </button>
        </div>

        {/* Discount Section */}
        <div className='mb-6'>
          <h4 className='text-sm font-semibold text-gray-700 mb-3 flex items-center space-x-2'>
            <Tag size={16} />
            <span>Discount</span>
          </h4>
          <div className='flex space-x-2 mb-2'>
            <button
              onClick={() => setDiscountType('percent')}
              className={`flex-1 rounded-lg py-2 px-3 font-semibold text-sm transition ${
                discountType === 'percent'
                  ? 'bg-green-600 hover:bg-green-700 text-white'
                  : 'bg-gray-200 hover:bg-gray-300 text-gray-700'
              }`}
            >
              %
            </button>
            <button
              onClick={() => setDiscountType('fixed')}
              className={`flex-1 rounded-lg py-2 px-3 font-semibold text-sm transition ${
                discountType === 'fixed'
                  ? 'bg-green-600 hover:bg-green-700 text-white'
                  : 'bg-gray-200 hover:bg-gray-300 text-gray-700'
              }`}
            >
              $
            </button>
          </div>
          <input
            type='number'
            value={discountPercentage}
            onChange={handleDiscountChange}
            placeholder='0'
            className='w-full border border-gray-300 rounded-lg py-2 px-3 text-center text-lg focus:outline-none focus:ring-2 focus:ring-green-500'
          />
        </div>

        {/* Note Section */}
        <div className='mb-6'>
          <h4 className='text-sm font-semibold text-gray-700 mb-3'>Note</h4>
          <textarea
            value={orderNote}
            onChange={handleNoteChange}
            placeholder='Enter note...'
            className='w-full border border-gray-300 rounded-lg py-2 px-3 text-xs focus:outline-none focus:ring-2 focus:ring-green-500 resize-none'
            rows='3'
          ></textarea>
        </div>
      </div>

      {/* Footer - Total and Buttons */}
      <div className='border-t border-gray-200 px-6 py-4 bg-gray-50'>
        {/* Total Display */}
        <div className='mb-4 text-center'>
          <p className='text-xs text-gray-600 mb-1'>Total Amount</p>
          <p className='text-3xl font-bold text-green-600'>${finalAmount.toFixed(0)}</p>
        </div>

        {/* Process Payment Button */}
        <button
          onClick={handleProcessPayment}
          className='w-full bg-green-600 hover:bg-green-700 text-white font-bold py-3 rounded-lg transition mb-3'
        >
          Process Payment
        </button>

        {/* Hold Order Button */}
        <button
          onClick={handleHoldOrder}
          className='w-full bg-gray-200 hover:bg-gray-300 text-gray-800 font-bold py-2 rounded-lg transition text-sm'
        >
          Hold Order
        </button>
      </div>
    </div>
  );
};

export default CustomerPaymentSection;