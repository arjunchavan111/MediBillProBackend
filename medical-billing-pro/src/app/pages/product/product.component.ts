import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-product',
  templateUrl: './product.component.html',
  styleUrls: ['./product.component.scss']
})
export class ProductComponent implements OnInit {

  purchases = [
    {
      supplierName: 'ABC Traders',
      supplierAddress: 'Mumbai',
      mobileNumber: '9876543210',
      date: '2025-08-15',
      mrp: 1500,
      sellingPrice: 1400,
      totalAmount: 2800,
      status: 'Completed'
    },
    {
      supplierName: 'XYZ Supplies',
      supplierAddress: 'Pune',
      mobileNumber: '9123456780',
      date: '2025-08-14',
      mrp: 2000,
      sellingPrice: 1900,
      totalAmount: 3800,
      status: 'Pending'
    }
  ];

  constructor() {}

  ngOnInit(): void {}
}
