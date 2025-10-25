import { Badge } from "@/components/ui/badge";
import { Card, CardContent } from "@/components/ui/card";
import React from "react";
import { usePOS } from "../../../context/usePOS";
import { ShoppingCart } from "lucide-react";

const ProductCard = ({ product }) => {
  const { addToCart } = usePOS();

  return (
    <Card
      onClick={() => addToCart(product)}
      className="cursor-pointer hover:shadow-xl transition-all duration-300 transform hover:scale-105 overflow-hidden border border-gray-200 rounded-xl p-0"
    >
      <CardContent className="p-0">
        {/* Product Image */}
        <div className="relative overflow-hidden bg-gray-100 aspect-square w-full">
          <img
            className="h-full w-full object-cover transition-transform duration-300 hover:scale-110"
            src={product.image}
            alt={product.name}
          />
          {/* Add to Cart Button Overlay */}
          <div className="absolute inset-0 bg-black hover:bg-black/40 transition-all duration-300 flex items-center justify-center opacity-0 hover:opacity-100">
            <div className="bg-green-600 hover:bg-green-700 text-white p-2 rounded-full transition-all">
              <ShoppingCart size={24} />
            </div>
          </div>
        </div>

        {/* Product Info */}
        <div className="p-4">
          <h3 className="font-bold text-gray-900 text-sm line-clamp-2 mb-1">
            {product.name}
          </h3>
          <p className="text-xs text-gray-500 mb-3">{product.sku}</p>

          {/* Price and Category */}
          <div className="flex items-center justify-between gap-2">
            <span className="font-bold text-green-600 text-lg">
              ${product.sellingPrice}
            </span>
            <Badge 
              variant="secondary" className="text-xs" 
            >
               {product.category}
            </Badge>
          </div>
        </div>
      </CardContent>
    </Card>
  );
};

export default ProductCard;
