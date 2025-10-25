import { Button } from "@/components/ui/button";
import { Input } from "@/components/ui/input";
import { Barcode } from "lucide-react";
import React from "react";
import ProductCard from "./ProductCard";
import { usePOS } from "../../../context/usePOS";

const products = [
  {
    id: 1,
    image:
      "https://cdn.pixabay.com/photo/2023/05/06/01/33/t-shirt-7973394_1280.jpg",
    name: "Product Name",
    sku: "SKU12345",
    sellingPrice: 100,
    category: "Category A",
  },
  {
    id: 2,
    image:
      "https://cdn.pixabay.com/photo/2023/05/06/01/33/t-shirt-7973394_1280.jpg",
    name: "Product Name",
    sku: "SKU12345",
    sellingPrice: 100,
    category: "Category A",
  },
  {
    id: 3,
    image:
      "https://cdn.pixabay.com/photo/2023/05/06/01/33/t-shirt-7973394_1280.jpg",
    name: "Product Name",
    sku: "SKU12345",
    sellingPrice: 100,
    category: "Category A",
  },
];

const ProductsSection = () => {
  const { searchTerm, setSearchTerm } = usePOS();

  const handleSearchChange = (e) => {
    setSearchTerm(e.target.value);
  };

  const filteredProducts = products.filter(
    (product) =>
      product.name.toLowerCase().includes(searchTerm.toLowerCase()) ||
      product.sku.toLowerCase().includes(searchTerm.toLowerCase())
  );

  return (
    <div className="w-1/3 flex flex-col bg-white border-r border-gray-200">
      <div className="p-4 border-b border-gray-200 bg-gray-50">
        <div>
          <Input
            className={"py-2 text-sm"}
            placeholder="Search products..."
            value={searchTerm}
            type={"text"}
            onChange={handleSearchChange}
          />
        </div>

        <div className="flex items-center justify-between pt-3">
          <span className="text-xs text-gray-600">{filteredProducts.length} product founds</span>
          <Button variant="outline" size="sm" className={"text-xs"}>
            <Barcode size={14} />
            scan
          </Button>
        </div>
      </div>

      <div className="grid grid-cols-3 gap-2 p-3 overflow-y-auto">
        {filteredProducts.map((product, index) => (
          <ProductCard product={product} key={index} />
        ))}
      </div>
    </div>
  );
};

export default ProductsSection;
