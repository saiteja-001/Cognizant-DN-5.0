import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormBuilder, FormGroup, FormArray, FormControl, Validators, AbstractControl, ValidationErrors, ReactiveFormsModule } from '@angular/forms';

// Custom Synchronous Validator
export function noCourseCode(control: AbstractControl): ValidationErrors | null {
  const val = control.value;
  if (val && val.toString().toUpperCase().startsWith('XX')) {
    return { noCourseCode: true };
  }
  return null;
}

// Custom Asynchronous Validator
export function simulateEmailCheck(control: AbstractControl): Promise<ValidationErrors | null> {
  return new Promise((resolve) => {
    setTimeout(() => {
      const email = control.value;
      if (email && email.toString().toLowerCase().includes('test@')) {
        resolve({ emailTaken: true });
      } else {
        resolve(null);
      }
    }, 800);
  });
}

@Component({
  selector: 'app-reactive-enrollment-form',
  imports: [CommonModule, ReactiveFormsModule],
  templateUrl: './reactive-enrollment-form.html',
  styleUrl: './reactive-enrollment-form.css',
})
export class ReactiveEnrollmentForm implements OnInit {
  enrollForm!: FormGroup;
  submitted = false;

  constructor(private fb: FormBuilder) {}

  ngOnInit(): void {
    this.enrollForm = this.fb.group({
      studentName: ['', [Validators.required, Validators.minLength(3)]],
      studentEmail: this.fb.control('', [Validators.required, Validators.email], [simulateEmailCheck]),
      courseId: ['', [Validators.required, noCourseCode]],
      preferredSemester: ['Odd', Validators.required],
      agreeToTerms: [false, Validators.requiredTrue],
      additionalCourses: this.fb.array([])
    });
  }

  /*
   * Why this getter is better than casting in template:
   * HTML templates in Angular do not support TypeScript type casting syntax (like 'as FormArray').
   * Defining a typed getter property 'additionalCourses' in the TypeScript component class abstracts
   * the casting logic away from the template, providing strong typing, compilation safety,
   * cleaner template markup, and IDE autocomplete assistance.
   */
  get additionalCourses(): FormArray {
    return this.enrollForm.get('additionalCourses') as FormArray;
  }

  // Helper method to cast AbstractControl to FormControl in templates
  getAsFormControl(control: AbstractControl): FormControl {
    return control as FormControl;
  }

  addCourse(): void {
    this.additionalCourses.push(this.fb.control('', Validators.required));
  }

  removeCourse(index: number): void {
    this.additionalCourses.removeAt(index);
  }

  onSubmit(): void {
    /*
     * DIFFERENCE BETWEEN value AND getRawValue():
     * 1. enrollForm.value: Excludes the values of any form controls that are disabled.
     * 2. enrollForm.getRawValue(): Returns the values of all form controls in the group,
     *    including disabled controls. This is critical when you need to submit disabled
     *    (read-only) input fields to a backend server.
     */
    console.log('Form Submitted!');
    console.log('Form .value:', this.enrollForm.value);
    console.log('Form .getRawValue():', this.enrollForm.getRawValue());
    console.log('Form validity status:', this.enrollForm.valid);

    if (this.enrollForm.valid) {
      this.submitted = true;
    }
  }

  resetForm(): void {
    this.enrollForm.reset({
      preferredSemester: 'Odd',
      agreeToTerms: false
    });
    this.additionalCourses.clear();
    this.submitted = false;
  }
}
