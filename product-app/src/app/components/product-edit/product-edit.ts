import { Component, ChangeDetectorRef } from '@angular/core';
import { FormBuilder, FormGroup, ReactiveFormsModule } from '@angular/forms';
import { ActivatedRoute, Router, RouterLink } from '@angular/router';
import { CommonModule } from '@angular/common';
import { ProductService } from '../../services/product.service';
import { Product } from '../../services/product.model';

@Component({
  selector: 'app-product-edit',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule, RouterLink],
  templateUrl: './product-edit.html',
  styleUrl: './product-edit.css'
})
export class ProductEdit {

  id!: number;
  form!: FormGroup;

  constructor(
    private fb: FormBuilder,
    private productService: ProductService,
    private route: ActivatedRoute,
    private router: Router,
    private cdr: ChangeDetectorRef
  ) {
    this.form = this.fb.group({
      name: [''],
      price: [0],
      description: ['']
    });

    this.id = Number(this.route.snapshot.paramMap.get('id'));

    this.productService.getById(this.id).subscribe({
      next: (res) => {
        this.form.patchValue(res);
        queueMicrotask(() => this.cdr.detectChanges());
      }
    });
  }

  save() {
    const product = this.form.value as Product;

    console.log("Saving product:", product);
    console.log("ID =", this.id);

    this.productService.update(this.id, product).subscribe({
      next: () => {
        console.log("Update OK");
        this.router.navigate(['/']);
        queueMicrotask(() => this.cdr.detectChanges());
      },
      error: (err) => {
        console.error("Update error:", err);
      }
    });
  }


}
