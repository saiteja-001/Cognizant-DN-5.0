import { Component, OnInit, OnDestroy } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-home',
  imports: [FormsModule, CommonModule],
  templateUrl: './home.html',
  styleUrl: './home.css',
})
export class Home implements OnInit, OnDestroy {
  portalName = 'Student Course Portal';
  isPortalActive = true;
  message = '';
  searchTerm = '';

  /*
   * DIFFERENCE BETWEEN PROPERTY BINDING AND TWO-WAY BINDING:
   * 1. [property] (One-Way Binding, Component -> DOM):
   *    Binds a component property to a DOM element property. Changes in the component class propagate
   *    to the DOM, but user interactions/changes in the DOM do NOT update the component property.
   *
   * 2. [(ngModel)] (Two-Way Binding, DOM <=> Component):
   *    Establishes a bi-directional data flow. Changes in the component class update the DOM,
   *    and changes/inputs from the user in the DOM (like typing in a text field) immediately
   *    update the component class property.
   */

  ngOnInit(): void {
    console.log('HomeComponent initialised — courses loaded');
  }

  ngOnDestroy(): void {
    console.log('HomeComponent destroyed');
  }

  onEnrollClick(): void {
    this.message = 'Enrollment opened!';
  }
}
