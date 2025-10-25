import React from 'react'
import ProductsSection from './ProductsSection/ProductsSection'
import CartSection from './CartSection/CartSection'
import CustomerPaymentSection from './CustomerPaymentSection/CustomerPaymentSection'
import POSHeader from './Header/POSHeader'
import POSProvider from '../../context/POSContext'

const CreateOrder = () => {
  return (
    <POSProvider>
      <div className='h-screen flex flex-col bg-white'>
        <POSHeader />
        <div className='flex-1 flex overflow-hidden'>
          <ProductsSection />
          <CartSection />
          <CustomerPaymentSection />
        </div>
      </div>
    </POSProvider>
  )
}

export default CreateOrder