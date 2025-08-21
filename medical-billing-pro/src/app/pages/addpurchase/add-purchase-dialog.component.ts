import { Component } from '@angular/core';
import { FormArray, FormBuilder, FormGroup, Validators, AbstractControl } from '@angular/forms';
import { MatDialogRef } from '@angular/material/dialog';

@Component({
  selector: 'app-add-purchase-dialog',
  templateUrl: './add-purchase-dialog.component.html',
  styleUrls: ['./add-purchase-dialog.component.scss']
})
export class AddPurchaseDialogComponent {
  purchaseForm: FormGroup;

  constructor(
    private fb: FormBuilder,
    private dialogRef: MatDialogRef<AddPurchaseDialogComponent>
  ) {
    this.purchaseForm = this.fb.group({
      supplierName: ['', Validators.required],
      supplierAddress: [''],
      mobileNumber: ['', Validators.required],
      date: [new Date(), Validators.required],
      products: this.fb.array([])
    });

    this.addProduct(); // Add one product row initially
  }

  // Getter to access products FormArray
  get products(): FormArray {
    return this.purchaseForm.get('products') as FormArray;
  }

  addProduct(): void {
    const productGroup = this.fb.group({
      productName: ['', Validators.required],
      quantity: [1, [Validators.required, Validators.min(1)]],
      costPrice: [0, [Validators.required, Validators.min(0)]],
      sellingPrice: [0, [Validators.required, Validators.min(0)]],
      gst: [0, [Validators.min(0), Validators.max(100)]]
    });
    this.products.push(productGroup);
  }

  removeProduct(index: number): void {
    this.products.removeAt(index);
  }

  calculateTotal(): number {
    let total = 0;
    this.products.controls.forEach(control => {
      const group = control as FormGroup;
      total += this.calculateProductTotal(group);
    });
    return total;
  }

  calculateTotalSubtotal(): number {
    let subtotal = 0;
    this.products.controls.forEach(control => {
      subtotal += this.calculateProductSubtotal(control as FormGroup);
    });
    return subtotal;
  }

  calculateTotalGST(): number {
    let totalGST = 0;
    this.products.controls.forEach(control => {
      totalGST += this.calculateProductGST(control as FormGroup);
    });
    return totalGST;
  }

  calculateProductSubtotal(productGroup: FormGroup): number {
    const quantity = productGroup.get('quantity')?.value || 0;
    const costPrice = productGroup.get('costPrice')?.value || 0;
    return quantity * costPrice;
  }

  calculateProductGST(productGroup: FormGroup): number {
    const gstPercent = productGroup.get('gst')?.value || 0;
    const subtotal = this.calculateProductSubtotal(productGroup);
    return (subtotal * gstPercent) / 100;
  }

  calculateProductTotal(productGroup: FormGroup): number {
    const subtotal = this.calculateProductSubtotal(productGroup);
    const gstAmount = this.calculateProductGST(productGroup);
    return subtotal + gstAmount;
  }

save(): void {
  if (this.purchaseForm.valid) {
    const formValue = this.purchaseForm.value;
    const totalAmount = this.calculateTotal();

    const purchase: any = {
      ...formValue,
      totalAmount,
      mrp: formValue.products.reduce((sum: number, p: any) => sum + (p.costPrice * p.quantity || 0), 0),
      sellingPrice: formValue.products.reduce((sum: number, p: any) => sum + (p.sellingPrice * p.quantity || 0), 0),
      status: 'Pending'
    };

    // Only add purchaseId if we are editing
    if (formValue.purchaseId) {
      purchase.purchaseId = formValue.purchaseId; // from DB
    }

    this.dialogRef.close(purchase);
  } else {
    this.purchaseForm.markAllAsTouched(); // Show validation errors
  }
}


  close(): void {
    this.dialogRef.close();
  }

  castToFormGroup(control: AbstractControl): FormGroup {
    return control as FormGroup;
  }
}
