import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { AuthService } from '../services/auth.service';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { MessageService } from '../services/message.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {
  loginForm: FormGroup;
  showPassword = false;
  errorMessage = '';
  isLoading = false;

  constructor(
    private fb: FormBuilder,
    private authService: AuthService,
    private router: Router,
    private messageService: MessageService // ✅ Inject message service
  ) {
    this.loginForm = this.fb.group({
      username: ['', Validators.required],
      password: ['', Validators.required]
    });
  }

  ngOnInit(): void {
    if (localStorage.getItem('token')) {
      this.router.navigate(['/dashboard']);
    }
  }

  togglePassword(): void {
    this.showPassword = !this.showPassword;
  }

  onSubmit(): void {
    if (this.isLoading) return;
    if (this.loginForm.invalid) {
      this.errorMessage = 'Please enter both username and password.';
      return;
    }

    this.errorMessage = '';
    this.isLoading = true;

    const credentials = this.loginForm.value;
    console.log('🔐 Sending Login Request:', credentials);


    // Call the login method from AuthService
    this.authService.login(credentials).subscribe({
      next: (response: HttpResponse<any>) => {
        this.isLoading = false;
        console.log('✅ Login Response:', response);
        this.onLoginSuccess(response.body);
      },
      error: (error: HttpErrorResponse) => {
        this.isLoading = false;
        console.error('❌ Login Error:', error);
        this.messageService.showError(error.error?.msg || 'Server error. Please try again.');
        this.errorMessage = error.error?.msg || 'Server error. Please try again.';
      }
    });
  }

  onLoginSuccess(responseBody: any): void {
    if (responseBody?.success === true && responseBody?.message?.toLowerCase() === 'success') {
      if (responseBody.token) {
        localStorage.setItem('token', responseBody.token);
      }

      this.messageService.showSuccess('Login successful!');
      this.router.navigate(['/dashboard']);
    } else {
      this.messageService.showError(responseBody?.message || 'Login failed. Please try again.');
      this.errorMessage = responseBody?.message || 'Login failed. Please try again.';
    }
  }
}