import { Component, Input, Output, EventEmitter, OnChanges, SimpleChanges } from '@angular/core';
import { CommonModule } from '@angular/common';
import { CreditLabelPipe } from '../../pipes/credit-label-pipe';

@Component({
  selector: 'app-course-card',
  imports: [CommonModule, CreditLabelPipe],
  templateUrl: './course-card.html',
  styleUrl: './course-card.css',
})
export class CourseCard implements OnChanges {
  @Input() course!: { id: number, name: string, code: string, credits: number, gradeStatus?: string };
  @Input() isEnrolled = false;
  @Output() enrollRequested = new EventEmitter<number>();

  isExpanded = false;

  /*
   * Why getters keep templates clean:
   * Using getter methods for bindings (like ngClass or ngStyle) abstracts the conditional
   * logic away from the template HTML files and centralizes it inside the TypeScript component class.
   * This prevents templates from being cluttered with complex logical expressions (e.g. nested ternary or AND operations)
   * which are hard to read, hard to test, and prone to formatting errors, improving maintainability.
   */
  get cardClasses() {
    return {
      'card--enrolled': this.isEnrolled,
      'card--full': this.course ? this.course.credits >= 4 : false,
      'expanded': this.isExpanded
    };
  }

  getBorderColor(): string {
    switch (this.course?.gradeStatus) {
      case 'passed': return '#10b981'; // Green
      case 'failed': return '#ef4444'; // Red
      case 'pending': return '#94a3b8'; // Grey
      default: return '#cbd5e1';
    }
  }

  ngOnChanges(changes: SimpleChanges): void {
    console.log('CourseCardComponent changes detected:', changes);
    if (changes['course']) {
      const prev = changes['course'].previousValue;
      const curr = changes['course'].currentValue;
      console.log(`Course changed in CourseCard (id: ${curr?.id}). Previous value:`, prev, 'Current value:', curr);
    }
  }

  toggleDetails(): void {
    this.isExpanded = !this.isExpanded;
  }
}
