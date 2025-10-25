import { Avatar, AvatarFallback, AvatarImage } from '@/components/ui/avatar'
import { Button } from '@/components/ui/button'
import { Menu } from 'lucide-react'
import React from 'react'

const POSHeader = () => {
  return (
    <div className='bg-white border-b border-gray-200 px-6 py-4'>
      <div className="flex items-center justify-between">
         <div>
            <Button>
               <Menu size={20} />
            </Button>
         </div>
         <div className='text-center'>
            <h1 className='text-xl font-bold text-gray-800'>POS Terminal</h1>
            <p className='text-xs text-gray-500'>Create new Order</p>
         </div>
         <div className='flex items-center space-x-2'>
            <Avatar className='h-8 w-8'>
        <AvatarImage src="https://cdn.pixabay.com/photo/2016/05/17/22/16/baby-1399332_1280.jpg" alt="user" />
        <AvatarFallback>US</AvatarFallback>
      </Avatar>
         </div>
      </div>
    </div>
  )
}

export default POSHeader