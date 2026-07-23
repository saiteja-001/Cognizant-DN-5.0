import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule, NgForm } from '@angular/forms';

@Component({
  selector: 'app-enrollment-form',
  imports: [CommonModule, FormsModule],
  templateUrl: './enrollment-form.html',
  styleUrl: './enrollment-form.css',
})
export class EnrollmentForm {
  submitted = false;

  onSubmit(form: NgForm): void {
    console.log('Form Submitted!');
    console.log('Form Value:', form.value);
    console.log('Form Validity:', form.valid);
    
    if (form.valid) {
      this.submitted = true;
    }
  }
}
