import { Component, OnInit } from '@angular/core';

interface SidebarItem {
  name: string;
  icon: string;
  route: string;
}

@Component({
  selector: 'app-dashboard-layout',
  templateUrl: './dashboard-layout.component.html',
  styleUrls: ['./dashboard-layout.component.scss']
})
export class DashboardLayoutComponent implements OnInit {

  sidebarItems: SidebarItem[] = [
    { name: 'Dashboard', icon: '📊', route: '/dashboard' },
    { name: 'Customers', icon: '👥', route: '/customers' },
    { name: 'Bills', icon: '📋', route: '/billing' },
    { name: 'Products', icon: '🏷️', route: '/products' },
    { name: 'Purchases', icon: '🛒', route: '/purchases' },
    { name: 'Payments', icon: '💳', route: '/payments' },
    { name: 'Reports', icon: '📈', route: '/reports' },
    { name: 'Settings', icon: '⚙️', route: '/settings' }
  ];

  constructor() { }

  ngOnInit(): void {}

}
