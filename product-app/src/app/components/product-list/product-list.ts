import { Component, OnInit, ChangeDetectorRef } from '@angular/core';
import { CommonModule, CurrencyPipe } from '@angular/common';
import {Router, RouterLink} from '@angular/router';

import { ProductService } from '../../services/product.service';
import { Product } from '../../services/product.model';
import { AuthService } from '../../services/auth.service';

@Component({
  selector: 'app-product-list',
  standalone: true,
  imports: [CommonModule, CurrencyPipe, RouterLink],
  templateUrl: './product-list.html',
  styleUrl: './product-list.css',
})
export class ProductList implements OnInit {

  products: Product[] = [];

  constructor(
    private productService: ProductService,
    private cdr: ChangeDetectorRef,
    private router: Router,
    protected auth: AuthService
  ) {}

  ngOnInit(): void {

    console.log('ProductListComponent LOADED');

    this.productService.getAll().subscribe({
      next: (data) => {

        console.log('Products from backend:', data);

        this.products = data;

        this.cdr.detectChanges();
      },

      error: (err) => console.error('Error:', err)
    });
  }

  logout(): void {

    this.auth.logout();

    this.router.navigate(['/login']);
  }

  trackById(index: number, product: Product) {
    return product.id;
  }

  delete(id: number) {
    if (!confirm('Na pewno usunąć produkt?')) return;

    this.productService.delete(id).subscribe({
      next: () => {
        this.products = this.products.filter(p => p.id !== id);
        queueMicrotask(() => this.cdr.detectChanges());
      },
      error: (err) => console.error(err)
    });
  }

}
