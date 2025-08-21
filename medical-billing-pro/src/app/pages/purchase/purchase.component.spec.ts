import { ComponentFixture, TestBed } from '@angular/core/testing';
import { PurchaseComponent } from './purchase.component';
import { AuthService } from '../../services/auth.service';
import { PdfGeneratorService } from '../../services/pdf-generator.service';
import { MatDialog } from '@angular/material/dialog';
import { of } from 'rxjs';
import { NO_ERRORS_SCHEMA } from '@angular/core';

describe('PurchaseComponent', () => {
  let component: PurchaseComponent;
  let fixture: ComponentFixture<PurchaseComponent>;

  // ✅ Mock services
  const mockAuthService = {
    getPurchaseDetails: () => of([]),
    addPurchaseDetails: () => of({}),
    sendSelectedPurchaseIds: () => of({})
  };

  const mockPdfGeneratorService = {
    generateInvoice: jasmine.createSpy('generateInvoice')
  };

  const mockMatDialog = {
    open: () => ({
      afterClosed: () => of(undefined)
    })
  };

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [PurchaseComponent],
      providers: [
        { provide: AuthService, useValue: mockAuthService },
        { provide: PdfGeneratorService, useValue: mockPdfGeneratorService },
        { provide: MatDialog, useValue: mockMatDialog }
      ],
      schemas: [NO_ERRORS_SCHEMA] // ✅ Ignores unknown elements like <mat-icon>
    }).compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(PurchaseComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
