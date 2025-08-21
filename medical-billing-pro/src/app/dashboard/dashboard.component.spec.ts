import { Component, OnInit } from '@angular/core';
import { AuthService, Purchase } from '../service/auth.service';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.scss']
})
export class DashboardComponent implements OnInit {
  purchaseList: Purchase[] = [];
  expandedRow: number | null = null;

  constructor(private authService: AuthService) {}

  ngOnInit(): void {
    this.loadPurchaseDetails();
  }

  loadPurchaseDetails(): void {
    this.authService.getPurchaseDetailsMock().subscribe((data: Purchase[]) => {
      this.purchaseList = data;
    });
  }

  toggleExpand(index: number): void {
    this.expandedRow = this.expandedRow === index ? null : index;
  }

  getGstAmount(price: number, gst: number): number {
    return price * (gst / 100);
  }

  getAmount(quantity: number, price: number): number {
    return quantity * price;
  }
}
