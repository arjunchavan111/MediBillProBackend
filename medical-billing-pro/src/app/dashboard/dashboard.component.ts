import { Component, OnInit } from '@angular/core';
import { AuthService } from '../services/auth.service'; // ✅ fixed path (change to 'services' if folder is plural)
import { purchaseDetails } from '../models/purchaseDetails'; // ✅ using your model interface

interface Bill {
  id: number;
  patientName: string;
  phoneNumber: string;
  amount: number;
  status: 'Paid' | 'Pending' | 'Overdue';
}

interface DashboardMetric {
  title: string;
  value: string;
  change: string;
  changeType: 'positive' | 'negative';
  icon: string;
}

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.scss']
})
export class DashboardComponent implements OnInit {
  metrics: DashboardMetric[] = [
    { title: 'Total Customers', value: '156', change: '+5% from last month', changeType: 'positive', icon: '👥' },
    { title: 'Pending Bills', value: '23', change: '-5% from last month', changeType: 'negative', icon: '📋' },
    { title: 'Monthly Revenue', value: '₹1,24,500', change: '+18% from last month', changeType: 'positive', icon: '💰' },
    { title: 'Collection Rate', value: '94.2%', change: '+2.1% from last month', changeType: 'positive', icon: '📈' }
  ];

  recentBills: Bill[] = [
    { id: 1, patientName: 'John Smith', phoneNumber: '9876543210', amount: 450.00, status: 'Paid' },
    { id: 2, patientName: 'Sarah Johnson', phoneNumber: '9876543211', amount: 320.00, status: 'Pending' },
    { id: 3, patientName: 'Mike Davis', phoneNumber: '9876543212', amount: 680.00, status: 'Overdue' }
  ];

  sidebarItems = [
    { name: 'Dashboard', icon: '📊', route: '/dashboard', active: true },
    { name: 'Customers', icon: '👥', route: '/customers', active: false },
    { name: 'Bills', icon: '📋', route: '/billing', active: false },
    { name: 'Products', icon: '🏷️', route: '/products', active: false },
    { name: 'Purchases', icon: '🛒', route: '/purchases', active: false },
    { name: 'Payments', icon: '💳', route: '/payments', active: false },
    { name: 'Reports', icon: '📈', route: '/reports', active: false },
    { name: 'Settings', icon: '⚙️', route: '/settings', active: false }
  ];

  quickActions = [
    { name: 'Create Bill', icon: '📄', primary: true },
    { name: 'View Customers', icon: '👥', primary: false },
    { name: 'Record Payment', icon: '💰', primary: false },
    { name: 'View Reports', icon: '📊', primary: false }
  ];

  purchaseList: purchaseDetails[] = [];
  expandedRow: number | null = null;

  constructor(private authService: AuthService) {}

  ngOnInit(): void {
    this.loadPurchaseDetails();
  }

  loadPurchaseDetails(): void {
    this.authService.getPurchaseDetailsMock().subscribe((data: purchaseDetails[]) => {
      this.purchaseList = data;
    });
  }

  toggleExpand(index: number): void {
    this.expandedRow = this.expandedRow === index ? null : index;
  }

  getStatusClass(status: string): string {
    switch (status) {
      case 'Paid': return 'status-paid';
      case 'Pending': return 'status-pending';
      case 'Overdue': return 'status-overdue';
      default: return '';
    }
  }

  getGstAmount(price: number, gst: number): number {
    return price * (gst / 100);
  }

  getAmount(quantity: number, price: number): number {
    return quantity * price;
  }
}
