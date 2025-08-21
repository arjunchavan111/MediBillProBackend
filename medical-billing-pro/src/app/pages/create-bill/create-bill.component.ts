import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, FormArray, Validators, AbstractControl } from '@angular/forms';
import { MatDialogRef } from '@angular/material/dialog';

@Component({
  selector: 'app-create-bill',
  templateUrl: './create-bill.component.html',
  styleUrls: ['./create-bill.component.scss']
})
export class CreateBillComponent implements OnInit {

  purchaseForm: FormGroup;

  constructor(
    private fb: FormBuilder,
    private dialogRef: MatDialogRef<CreateBillComponent>
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

  ngOnInit(): void {}

  // Getter for products array
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
    return this.products.controls.reduce((sum, control) => {
      return sum + this.calculateProductTotal(control as FormGroup);
    }, 0);
  }

  calculateTotalSubtotal(): number {
    return this.products.controls.reduce((sum, control) => {
      return sum + this.calculateProductSubtotal(control as FormGroup);
    }, 0);
  }

  calculateTotalGST(): number {
    return this.products.controls.reduce((sum, control) => {
      return sum + this.calculateProductGST(control as FormGroup);
    }, 0);
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
    return this.calculateProductSubtotal(productGroup) + this.calculateProductGST(productGroup);
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

      if (formValue.purchaseId) {
        purchase.purchaseId = formValue.purchaseId; // only if editing
      }

      this.dialogRef.close(purchase);
    } else {
      this.purchaseForm.markAllAsTouched();
    }
  }

  close(): void {
    this.dialogRef.close();
  }

  castToFormGroup(control: AbstractControl): FormGroup {
    return control as FormGroup;
  }
}
