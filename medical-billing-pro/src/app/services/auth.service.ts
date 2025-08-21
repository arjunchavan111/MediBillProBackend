import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs'; // ✅ Added 'of' here
import { environment } from '../../environments/environment';
import { Purchase } from '../models/purchase.model';
import { ApiResponse } from '../models/apiResponse.model';


// ✅ Corrected import path and filename for purchaseDetails
import { purchaseDetails } from '../models/purchaseDetails';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  private readonly baseUrl = environment.apiBaseUrl; // http://localhost:8081/api

  constructor(private http: HttpClient) {}

  /**
   * Add new admin
   */
  addAdmin(adminData: { name: string; email: string; role: string }): Observable<HttpResponse<any>> {
    return this.http.post<any>(`${this.baseUrl}/admin/add`, adminData, {
      observe: 'response'
    });
  }

  login(credentials: { username: string; password: string }) {
    return this.http.post<any>(
      `${this.baseUrl}/admin/login`,
      credentials,
      { observe: 'response' }
    );
  }

  getAdminDetails(): Observable<any> {
    return this.http.get<any>(`${this.baseUrl}/admin/getadmindetails`);
  }

  // Purchase Page
  getPurchaseDetails(): Observable<any> {
    return this.http.get(`${this.baseUrl}/purchases/getPurchaseDetails`);
  }

  addPurchaseDetails(data: any): Observable<any> {
    return this.http.post(`${this.baseUrl}/purchases/addPurchaseDetails`, data);
  }

  sendSelectedPurchaseIds(data: any): Observable<any> {
    return this.http.post(`${this.baseUrl}/purchases/deleteOrder`, data);
  }

  // ✅ Mock data for testing
  getPurchaseDetailsMock(): Observable<purchaseDetails[]> {
    const mockData: purchaseDetails[] = [
      {
        purchaseHSN: 'HSN001',
        productName: 'Laptop',
        companyName: 'TechCorp',
        unit: 'pcs',
        btchNo: 'B123',
        expiryDate: '2026-08-10',
        quantity: 2,
        scheme: 'No Scheme',
        MRP: 100,
        buyingPrice: 80,
        sellingPrice: 95,
        discount: 3,
        GST: 12
      },
      {
        purchaseHSN: 'HSN002',
        productName: 'Mobile',
        companyName: 'PhoneMax',
        unit: 'pcs',
        btchNo: 'B456',
        expiryDate: '2027-08-09',
        quantity: 5,
        scheme: 'Buy 4 Get 1',
        MRP: 150,
        buyingPrice: 120,
        sellingPrice: 140,
        discount: 5,
        GST: 18
      }
    ];

    return of(mockData);
  }
}
