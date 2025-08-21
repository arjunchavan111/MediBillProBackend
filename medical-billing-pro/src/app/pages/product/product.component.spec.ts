import { Component, Input } from '@angular/core';

export interface Product {
  hsn: string;
  name: string;
  mfg: string;
  unit: string;
  batch: string;
  exp: string;
  qty: number;
  mrp: number;
  rate: number;
  disc: number;
  gstPercent?: number;
  gst: number;
  amount: number;
}

@Component({
  selector: '[app-product]', // used as <tr app-product>
  templateUrl: './product.component.html',
  styleUrls: ['./product.component.scss']
})
export class ProductComponent {
  @Input() product!: Product;
}
