import { Routes } from '@angular/router';
import { Login } from './components/login/login';
import { Register } from './components/register/register';
import { ProductList } from './components/product-list/product-list';
import { ProductDetails } from './components/product-details/product-details';
import { ProductCreate } from './components/product-create/product-create';
import { ProductEdit } from './components/product-edit/product-edit';
import { authGuard } from './services/auth.guard';
import { adminGuard } from './services/admin.guard';

export const routes: Routes = [
  { path: '', component: ProductList, canActivate: [authGuard] },
  { path: 'products/create', component: ProductCreate, canActivate: [authGuard, adminGuard] },
  { path: 'products/:id/edit', component: ProductEdit, canActivate: [authGuard, adminGuard] },
  { path: 'products/:id', component: ProductDetails, canActivate: [authGuard] },
  { path: 'login', component: Login },
  { path: 'register', component: Register },
  { path: '**', redirectTo: '' }
];

