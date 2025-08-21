import { Injectable } from '@angular/core';
import Swal from 'sweetalert2';

@Injectable({
  providedIn: 'root'
})
export class MessageService {


  private defaultClasses = {
    popup: 'small-popup',
    title: 'small-popup-title',
    confirmButton: 'small-popup-btn'
  };

  showSuccess(message: string, title: string = 'Success') {
  Swal.fire({
    width: '240px',          // Corrected width
    padding: '10px',         // Correct spacing
    icon: 'success',
    title: title,
    text: message,
    confirmButtonColor: '#3085d6',
    customClass: this.defaultClasses // Controls font size via CSS
  });
}
  

  showError(message: string, title: string = 'Error') {
    Swal.fire({
      width: '240px',          // Corrected width
      padding: '10px',
      icon: 'error',
      title: title,
      text: message,
      confirmButtonColor: '#d33',
      customClass: this.defaultClasses
    });
  }

  showInfo(message: string, title: string = 'Info') {
    Swal.fire({
      width: '240px',          // Corrected width
      padding: '10px',
      icon: 'info',
      title: title,
      text: message,
      confirmButtonColor: '#3085d6',
      customClass: this.defaultClasses
    });
  }
}
