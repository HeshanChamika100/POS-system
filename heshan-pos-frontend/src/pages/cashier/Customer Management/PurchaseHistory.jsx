import { Badge } from "@/components/ui/badge";
import { Card, CardContent, CardHeader, CardTitle } from "@/components/ui/card";
import { CalendarIcon } from "lucide-react";

const orders = [
  { id: 1, createdAt: "2024-10-01", totalAmount: 150.0, status: "Completed", paymentType: "Credit Card", items: [
      { id: 1, product: { name: "Product A" }, quantity: 2, price: 50.0 },
      { id: 2, product: { name: "Product B" }, quantity: 1, price: 50.0 },
  ]},
];

const PurchaseHistory = () => {
  return (
    <div className="p-4 h-full">
      <Card className="shadow-lg">
        <CardHeader className="pb-3 border-gray-200 bg-white">
          <CardTitle className="text-2xl font-bold text-gray-900">
            Purchase History
          </CardTitle>
        </CardHeader>
        <CardContent className="p-4">
          <div className="space-y-3 max-h-[calc(100vh-250px)] overflow-y-auto">
            {orders.map((order) => (
              <div
                key={order.id}
                className="group bg-white border border-gray-200 rounded-xl p-4 hover:shadow-lg hover:border-gray-300 transition-all duration-200 cursor-pointer hover:bg-accent"
              >
                <div className="flex justify-between items-start mb-4">
                  <div className="flex-1">
                    <h3 className="font-bold text-lg text-gray-900 mb-2">
                      Order #{order.id}
                    </h3>
                    <div className="flex items-center gap-2 text-gray-600">
                      <CalendarIcon className="h-4 w-4 text-blue-500" />
                      <span className="text-sm font-medium">
                        {order.createdAt}
                      </span>
                    </div>
                  </div>
                  <div className="flex items-center gap-2">
                    <span className="text-2xl font-bold text-green-600">
                      $ {order.totalAmount.toFixed(2)}
                    </span>
                  </div>
                </div>

                <div className="flex justify-end">
                  <Badge>{order.status}</Badge>
                </div>

                <div className="text-sm text-muted-foreground mb-2">
                  Payment: {order.paymentType}
                </div>
                <div className="border-t pt-3">
                  <h4 className="text-sm font-medium mb-2">Items: </h4>
                  <div className="space-y-1">
                    {order.items && order.items.map((item) => (
                      <div key={item.id} className="flex justify-between">
                        <span className="text-muted-foreground">
                          {item.product.name}
                        </span>
                        <span>
                          {item.quantity || 1} * {item.price.toFixed(2)}
                        </span>
                      </div>
                    ))}
                  </div>
                </div>
              </div>
            ))}
          </div>
        </CardContent>
      </Card>
    </div>
  );
};

export default PurchaseHistory;
