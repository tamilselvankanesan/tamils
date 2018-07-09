import {Directive, forwardRef} from '@angular/core';
import {NG_VALIDATORS, FormControl} from '@angular/forms';

@Directive({
  selector: '[passwordValidator][ngModel]',
  providers: [{
    provide: NG_VALIDATORS, multi: true, useExisting: forwardRef(() => PasswordValidator)
  }]
})
export class PasswordValidator {

  validate(c: FormControl) {
    console.log(c.value);
    return null;
  }

}
