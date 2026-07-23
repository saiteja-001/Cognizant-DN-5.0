import { Directive, HostBinding, HostListener, Input } from '@angular/core';

@Directive({
  selector: '[appHighlight]',
})
export class Highlight {
  @Input() appHighlight: string = 'yellow';

  @HostBinding('style.backgroundColor') backgroundColor: string | null = null;

  @HostListener('mouseenter') onMouseEnter() {
    // If the input is passed as empty, fall back to yellow
    this.backgroundColor = this.appHighlight || 'yellow';
  }

  @HostListener('mouseleave') onMouseLeave() {
    this.backgroundColor = null;
  }
}
