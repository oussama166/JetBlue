import { Component, EventEmitter, Input, Output } from '@angular/core';
import {
  FormGroup,
  FormGroupDirective,
  ReactiveFormsModule
} from '@angular/forms';
import { signUpData } from "../../../../../utils/types";
import { InputComponent } from '../../../../Features/input/input.component';

@Component({
  selector: 'app-basic',
  standalone: true,
  imports: [InputComponent, ReactiveFormsModule],
  templateUrl: './basic.component.html',
  styleUrl: './basic.component.scss',
})
export class BasicComponent {
  basic!: FormGroup;
  @Input() isDone: boolean = false;
  step: number = 1;
  @Output() stepCounter = new EventEmitter<number>();
  @Input() show!: boolean;
  @Input() staticData!:signUpData;


  constructor(private rootDirectiveForm: FormGroupDirective) {
  }

  ngOnInit() {
    this.basic = this.rootDirectiveForm.form.get('basic') as FormGroup;

  }

  validateForm($event: MouseEvent) {
    this.isDone = this.basic.status == "VALID"
    if (this.isDone) {
      this.stepCounter.emit(this.step + 1);
      window.scrollTo({ top: 0, behavior: 'smooth' });
    }
  }


}
