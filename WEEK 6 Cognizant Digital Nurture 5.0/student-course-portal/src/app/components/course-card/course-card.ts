import { Component, Input, Output, EventEmitter, OnChanges, SimpleChanges } from '@angular/core';

@Component({
  selector: 'app-course-card',
  imports: [],
  templateUrl: './course-card.html',
  styleUrl: './course-card.css',
})
export class CourseCard implements OnChanges {
  @Input() course!: { id: number, name: string, code: string, credits: number };
  @Output() enrollRequested = new EventEmitter<number>();

  ngOnChanges(changes: SimpleChanges): void {
    console.log('CourseCardComponent changes detected:', changes);
    // Log previous and current value of 'course' specifically
    if (changes['course']) {
      const prev = changes['course'].previousValue;
      const curr = changes['course'].currentValue;
      console.log(`Course changed in CourseCard (id: ${curr?.id}). Previous value:`, prev, 'Current value:', curr);
    }
  }

  onEnrollClick(): void {
    if (this.course) {
      this.enrollRequested.emit(this.course.id);
    }
  }
}
