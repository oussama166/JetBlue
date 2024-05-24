import { Component, EventEmitter, Input, Output } from '@angular/core';
import {
  FormGroup,
  FormGroupDirective,
  ReactiveFormsModule,
} from '@angular/forms';
import { PaginatorModule } from 'primeng/paginator';
import { InputComponent } from '../../../../Features/input/input.component';

@Component({
  selector: 'app-personal',
  standalone: true,
  imports: [InputComponent, PaginatorModule, ReactiveFormsModule],
  templateUrl: './personal.component.html',
  styleUrl: './personal.component.scss',
})
export class PersonalComponent {
  personal!: FormGroup;
  @Input() isDone: boolean = false;
  @Input() hidden: boolean = true;
  step: number = 2;
  @Output() stepCounter = new EventEmitter<number>();
  @Input() show!: boolean;

  constructor(private rootDirectiveForm: FormGroupDirective) {}
  ngOnInit() {
    this.personal = this.rootDirectiveForm.form.get(
      'personalInfo'
    ) as FormGroup;
  }

  validateForm($event: MouseEvent) {
    this.isDone = this.personal.status == 'VALID';
    console.log(this.personal);
    console.log(this.isDone);
    if (this.isDone) {
      this.stepCounter.emit(this.step + 1);
      window.scrollTo({ top: 0, behavior: 'smooth' });
    }
  }

  getCountry() {
    return this.personal.get('country')?.value;
  }
}
