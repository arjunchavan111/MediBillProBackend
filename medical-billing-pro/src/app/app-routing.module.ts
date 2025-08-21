import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { LoginComponent } from './login/login.component';
import { DashboardLayoutComponent } from './layout/dashboard-layout/dashboard-layout.component';
import { DashboardComponent } from './dashboard/dashboard.component';
import { CustomerComponent } from './pages/customer/customer.component';
import { BillingComponent } from './pages/billing/billing.component';
import { ProductComponent } from './pages/product/product.component';
import { PurchaseComponent } from './pages/purchase/purchase.component';
import { PaymentComponent } from './pages/payment/payment.component';
import { ReportComponent } from './pages/report/report.component';

const routes: Routes = [
  { path: '', redirectTo: '/login', pathMatch: 'full' },
  { path: 'login', component: LoginComponent },

  {
    path: '',
    component: DashboardLayoutComponent, // Sidebar always here
    children: [
      { path: 'dashboard', component: DashboardComponent },
      { path: 'customers', component: CustomerComponent },
      { path: 'billing', component: BillingComponent },
      { path: 'products', component: ProductComponent },
      { path: 'purchases', component: PurchaseComponent },
      { path: 'payments', component: PaymentComponent },
      { path: 'reports', component: ReportComponent },
      { path: '', redirectTo: '/dashboard', pathMatch: 'full' }
    ]
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {}
