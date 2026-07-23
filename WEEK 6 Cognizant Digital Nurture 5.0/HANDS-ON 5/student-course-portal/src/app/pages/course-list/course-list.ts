import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { CourseCard } from '../../components/course-card/course-card';
import { Highlight } from '../../directives/highlight';

@Component({
  selector: 'app-course-list',
  imports: [CommonModule, CourseCard, Highlight],
  templateUrl: './course-list.html',
  styleUrl: './course-list.css',
})
export class CourseList implements OnInit {
  isLoading = true;
  selectedCourseId: number | null = null;

  courses = [
    { id: 101, name: 'Introduction to Angular', code: 'ANG-101', credits: 3, gradeStatus: 'passed' },
    { id: 102, name: 'Advanced TypeScript', code: 'TS-201', credits: 4, gradeStatus: 'pending' },
    { id: 103, name: 'Web Performance Optimization', code: 'WEB-301', credits: 3, gradeStatus: 'failed' },
    { id: 104, name: 'Database Management Systems', code: 'DBMS-101', credits: 4, gradeStatus: 'passed' },
    { id: 105, name: 'Cloud Computing & DevOps', code: 'CLOUD-202', credits: 3, gradeStatus: 'pending' }
  ];

  ngOnInit(): void {
    // Simulate API delay of 1.5 seconds to load courses
    setTimeout(() => {
      this.isLoading = false;
    }, 1500);
  }

  onEnroll(courseId: number): void {
    console.log('Enrolling in course: ' + courseId);
    this.selectedCourseId = courseId;
  }

  /*
   * Why trackBy improves performance:
   * By default, when an array changes, Angular discards all DOM elements rendering the list
   * and rebuilds them. By providing trackBy, Angular tracks items by their unique identifier (e.g. course.id).
   * If the array elements are reordered, added, or removed, Angular will only change/re-render the specific
   * DOM nodes that were modified, keeping the rest of the DOM intact, which reduces rendering performance overhead.
   */
  trackByCourseId(index: number, course: any): number {
    return course.id;
  }
}
