import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { ReactiveFormsModule, FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';

// Routing
import { AppRoutingModule } from './app-routing.module';




// Components
import { AppComponent } from './app.component';
import { LoginComponent } from './login/login.component';
import { DashboardComponent } from './dashboard/dashboard.component';
import { CustomerComponent } from './pages/customer/customer.component';
import { BillingComponent } from './pages/billing/billing.component';
import { ProductComponent } from './pages/product/product.component';
import { PurchaseComponent } from './pages/purchase/purchase.component';
import { PaymentComponent } from './pages/payment/payment.component';
import { ReportComponent } from './pages/report/report.component';
import { DashboardLayoutComponent } from './layout/dashboard-layout/dashboard-layout.component';
import { AddPurchaseDialogComponent } from './pages/addpurchase/add-purchase-dialog.component'; // Add dialog component

// Angular Material Modules
import { MatIconModule } from '@angular/material/icon';
import { MatButtonModule } from '@angular/material/button';
import { MatDialogModule } from '@angular/material/dialog';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatSelectModule } from '@angular/material/select';
import { MatDatepickerModule } from '@angular/material/datepicker';
import { MatNativeDateModule } from '@angular/material/core';
import { CreateBillComponent } from './pages/create-bill/create-bill.component';

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    DashboardComponent,
    CustomerComponent,
    BillingComponent,
    ProductComponent,
    PurchaseComponent,
    PaymentComponent,
    ReportComponent,
    DashboardLayoutComponent,
    AddPurchaseDialogComponent,
    CreateBillComponent // Declare dialog here
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    ReactiveFormsModule,
    FormsModule,
    HttpClientModule,
    BrowserAnimationsModule,

    // Angular Material
    MatIconModule,
    MatButtonModule,
    MatDialogModule,
    MatFormFieldModule,
    MatInputModule,
    MatSelectModule,
    MatDatepickerModule,
    MatNativeDateModule

  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule {}
