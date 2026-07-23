import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { CourseCard } from '../../components/course-card/course-card';

@Component({
  selector: 'app-course-list',
  imports: [CommonModule, CourseCard],
  templateUrl: './course-list.html',
  styleUrl: './course-list.css',
})
export class CourseList {
  selectedCourseId: number | null = null;

  courses = [
    { id: 101, name: 'Introduction to Angular', code: 'ANG-101', credits: 3 },
    { id: 102, name: 'Advanced TypeScript', code: 'TS-201', credits: 4 },
    { id: 103, name: 'Web Performance Optimization', code: 'WEB-301', credits: 3 },
    { id: 104, name: 'Database Management Systems', code: 'DBMS-101', credits: 4 },
    { id: 105, name: 'Cloud Computing & DevOps', code: 'CLOUD-202', credits: 3 }
  ];

  onEnroll(courseId: number): void {
    console.log('Enrolling in course: ' + courseId);
    this.selectedCourseId = courseId;
  }
}
