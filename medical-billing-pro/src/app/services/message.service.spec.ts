import { Injectable } from '@angular/core';
import Swal from 'sweetalert2';

@Injectable({
  providedIn: 'root'
})
export class MessageService {

  success(message: string, title: string = 'Success') {
    Swal.fire({
      icon: 'success',
      title: title,
      text: message,
      confirmButtonColor: '#3085d6'
    });
  }

  error(message: string, title: string = 'Error') {
    Swal.fire({
      icon: 'error',
      title: title,
      text: message,
      confirmButtonColor: '#d33'
    });
  }

  info(message: string, title: string = 'Info') {
    Swal.fire({
      icon: 'info',
      title: title,
      text: message,
      confirmButtonColor: '#3085d6'
    });
  }
}
