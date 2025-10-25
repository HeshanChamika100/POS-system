import React from "react";
import { User, Tag, Pause, CreditCard, NotepadText } from "lucide-react";
import { usePOS } from "../../../context/usePOS";
import { Button } from "@/components/ui/button";
import { Input } from "@/components/ui/input";

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
    console.log("Processing payment:", {
      total,
      discountAmount,
      finalAmount: total - discountAmount,
      orderNote,
    });
  };

  const handleHoldOrder = () => {
    console.log("Holding order");
  };

  const finalAmount = total - discountAmount;

  return (
    <div className="w-80 flex flex-col bg-white border-l border-gray-200 h-full">
      {/* Header */}
      <div className="px-6 py-3 border-b border-gray-200 bg-gray-50 shrink-0">
        <div className="flex items-center space-x-2">
          <User size={18} />
          <h3 className="text-lg font-semibold text-gray-800">Customer</h3>
        </div>
      </div>

      {/* Content */}
      <div className="flex-1 overflow-y-auto px-6 py-4">
        {/* Customer Section */}
        <div className="mb-6">
          <Button variant="outline" className="w-full">
            Select Customer
          </Button>
        </div>

        {/* Discount Section */}
        <div className="mb-6">
          <h4 className="text-md font-semibold text-gray-700 mb-3 flex items-center space-x-2">
            <Tag size={16} />
            <span>Discount</span>
          </h4>
          <div className="flex space-x-2 mb-2">
            <Button
              variant={`${discountType === "percent" ? "" : "outline"}`}
              onClick={() => setDiscountType("percent")}
              className="flex-1"
            >
              %
            </Button>
            <Button
              variant={`${discountType === "fixed" ? "" : "outline"}`}
              onClick={() => setDiscountType("fixed")}
              className="flex-1"
            >
              $
            </Button>
          </div>
          <Input
            type="number"
            value={discountPercentage}
            onChange={handleDiscountChange}
            placeholder="0"
            className="w-full border border-gray-300 rounded-lg py-2 px-3 text-center text-lg focus:outline-none focus:ring-2 focus:ring-green-500"
          />
        </div>

        {/* Note Section */}
        <div className="mb-6">
          <div className="flex items-center space-x-2 mb-3">
            <NotepadText size={16} />
            <h4 className="text-md font-semibold text-gray-700">Note</h4>
          </div>

          <textarea
            value={orderNote}
            onChange={handleNoteChange}
            placeholder="Enter note..."
            className="w-full border border-gray-300 rounded-lg py-2 px-3 text-xs focus:outline-none focus:ring-2 focus:ring-green-500 resize-none"
            rows="3"
          ></textarea>
        </div>
      </div>

      {/* Footer - Total and Buttons */}
      <div className="border-t border-gray-200 px-6 py-4 bg-gray-50 shrink-0">
        {/* Total Display */}
        <div className="mb-4 text-center">
          <p className="text-xs text-gray-600 mb-1">Total Amount</p>
          <p className="text-3xl font-bold text-green-600">
            ${finalAmount.toFixed(0)}
          </p>
        </div>

        {/* Process Payment Button */}
        <Button onClick={handleProcessPayment} className="w-full mb-3">
          <CreditCard />
          Process Payment
        </Button>

        {/* Hold Order Button */}
        <Button variant="outline" onClick={handleHoldOrder} className="w-full">
          <Pause />
          Hold Order
        </Button>
      </div>
    </div>
  );
};

export default CustomerPaymentSection;
