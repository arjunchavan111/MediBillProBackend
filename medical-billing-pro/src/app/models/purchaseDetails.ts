export interface purchaseDetails {
  purchaseHSN: string;
  productName: string;
  companyName: string;
  unit: string;
  btchNo: string;
  expiryDate: string;
  quantity: number;
  scheme: string;
  MRP: number;
  buyingPrice: number;
  sellingPrice: number;
  discount: number; // % on buyingPrice
  GST: number; // % on buyingPrice
}
