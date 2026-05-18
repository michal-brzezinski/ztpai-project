import { Component, ChangeDetectorRef } from '@angular/core';
import { FormBuilder, ReactiveFormsModule, FormGroup } from '@angular/forms';
import { HttpClient } from '@angular/common/http';
import { Router, RouterLink } from '@angular/router';

@Component({
  selector: 'app-register',
  standalone: true,
  imports: [ReactiveFormsModule, RouterLink],
  templateUrl: './register.html',
  styleUrls: ['./register.css']
})
export class Register {

  error = '';
  success = '';
  form: FormGroup;

  constructor(
    private fb: FormBuilder,
    private http: HttpClient,
    private router: Router,
    private cdr: ChangeDetectorRef   // 🔥 DODANE
  ) {
    this.form = this.fb.group({
      username: [''],
      password: [''],
      email: [''],
      role: ['USER']
    });
  }

  register() {
    this.error = '';
    this.success = '';

    this.http.post('http://localhost:8080/api/auth/register', this.form.value)
      .subscribe({
        next: (res: any) => {
          this.success = res?.message || 'Rejestracja udana!';

          // Wymuszenie odświeżenia widoku w Angular 17 (zoneless)
          queueMicrotask(() => this.cdr.detectChanges());

          setTimeout(() => {
            this.router.navigate(['/login']);
          }, 1200);
        },
        error: (err) => {
          this.error = err?.error?.message || 'Błąd rejestracji';

          // KLUCZOWE — bez tego Angular nie odświeży widoku
          queueMicrotask(() => this.cdr.detectChanges());
        }
      });
  }
}
