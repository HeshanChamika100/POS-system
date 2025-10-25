import React from 'react'
import CustomerCard from './CustomerCard'

const customers=[
   {id:1, fullName:"John Doe", email:"john@example.com", phone:"123-456-7890", loyaltyPoints:150, totalOrders: 5, totalSpent: 250.75, averageOrderValue: 50.15},
   {id:2, fullName:"Jane Smith", email:"janesmith@example.com", phone:"987-654-3210", loyaltyPoints:200, totalOrders: 3, totalSpent: 150.00, averageOrderValue: 50.00},
   {id:3, fullName:"Alice Johnson", email:"alicejohnson@example.com", phone:"555-555-5555", loyaltyPoints:300, totalOrders: 8, totalSpent: 500.50, averageOrderValue: 62.56},
   {id:4, fullName:"Bob Brown", email:"bobbrown@example.com", phone:"444-444-4444", loyaltyPoints:400, totalOrders: 2, totalSpent: 100.00, averageOrderValue: 50.00},
]

const CustomerList = ({ setSelectedCustomer }) => {
  return (
    <div className='flex-1 overflow-auto'>
      <div className="divide-y">
         {customers.map((customer)=>(
            <CustomerCard className="cursor-pointer" setSelectedCustomer={setSelectedCustomer} key={customer.id} customer={customer} />
         ))}
      </div>
    </div>
  )
}

export default CustomerList