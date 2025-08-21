export interface PurchaseProduct {
  id: number;
  productName: string;
  quantity: number;
  costPrice: number;
  sellingPrice: number;
  gst: number;
}

export interface Purchase {
  purchaseId: number;
  supplierName: string;
  supplierAddress: string;
  mobileNumber: string;
  date: string;
  mrp: number;
  sellingPrice: number;
  totalAmount: number;
  status: string;
  products?: PurchaseProduct[]; 
}
