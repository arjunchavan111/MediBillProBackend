import { Component, OnInit } from '@angular/core';

import { MatDialog } from '@angular/material/dialog';
import { AuthService } from '../../services/auth.service';
import { Purchase } from '../../models/purchase.model';
import { CreateBillComponent } from '../../pages/create-bill/create-bill.component';

import { ApiResponse } from '../../models/apiResponse.model';

@Component({
  selector: 'app-billing',
  templateUrl: './billing.component.html',
  styleUrls: ['./billing.component.scss']
})
export class BillingComponent implements OnInit {

  purchases: Purchase[] = [];
  totalOrders = 0;
  pendingOrders = 0;
  deliveredOrders = 0;
  totalValue = 0;
  selectedIds: number[] = []; 
 originalPurchases: Purchase[] = [];
 searchTerm = '';

  constructor(
    private authService: AuthService,
    private dialog: MatDialog
  ) {}

  ngOnInit(): void {
    this.loadPurchaseDetails();
  }

  loadPurchaseDetails() {
    this.authService.getPurchaseDetails()
      .subscribe((res: ApiResponse<Purchase[]>) => {
        this.purchases = res.data.map(p => ({ ...p, selected: false }));
         this.originalPurchases = res.data;
        this.calculateStats();
      });
  }

  calculateStats() {
    this.totalOrders = this.purchases.length;
    this.pendingOrders = this.purchases.filter(p => p.status === 'Pending').length;
    this.deliveredOrders = this.purchases.filter(p => p.status === 'Delivered' || p.status === 'Completed').length;
    this.totalValue = this.purchases.reduce((sum, p) => sum + p.totalAmount, 0);
  }

 openAddPurchaseDialog(): void {
  const dialogRef = this.dialog.open(CreateBillComponent, {
    width: '800px',
    disableClose: true
  });

  dialogRef.afterClosed().subscribe((newPurchase: Purchase | undefined) => {
    if (newPurchase) {
      this.authService.addPurchaseDetails(newPurchase).subscribe(() => {
        this.loadPurchaseDetails(); // Refresh after adding
      });
    }
  });
}


  // ✅ Handle row checkbox
  toggleSelection(id: number, event: Event): void {
    const checked = (event.target as HTMLInputElement).checked;
    if (checked) {
      this.selectedIds.push(id);
    } else {
      this.selectedIds = this.selectedIds.filter(item => item !== id);
    }
  }

  // ✅ Handle "Select All"
  toggleSelectAll(event: Event): void {
    const checked = (event.target as HTMLInputElement).checked;
    this.selectedIds = checked ? this.purchases.map(p => p.purchaseId) : [];
    this.purchases.forEach(p => (p as any).selected = checked);
  }

  // ✅ Send selected IDs to backend
  sendSelected(): void {
    if (this.selectedIds.length === 0) {
      alert('No purchase selected!');
      return;
    }
    this.authService.sendSelectedPurchaseIds(this.selectedIds).subscribe(
      res => {
        console.log('IDs sent successfully', res);
        this.loadPurchaseDetails(); 
      },
      err => {
        console.error('Error sending IDs', err);
      }
    );
  }
  filterPurchases() {
  const term = this.searchTerm.toLowerCase();
  this.purchases = this.originalPurchases.filter(purchase =>
    purchase.supplierName.toLowerCase().includes(term) ||
    purchase.supplierAddress.toLowerCase().includes(term) ||
    purchase.mobileNumber.toLowerCase().includes(term) ||
    (purchase.purchaseId + '').toLowerCase().includes(term) ||
    purchase.status.toLowerCase().includes(term)
  );

}
}