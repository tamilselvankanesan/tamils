import {Directive, forwardRef} from '@angular/core';
import {NG_VALIDATORS, AbstractControl} from '@angular/forms';

@Directive({
  selector: '[passwordValidator][ngModel]',
  providers: [{
    provide: NG_VALIDATORS, multi: true, useExisting: forwardRef(() => PasswordValidator)
  }]
})
export class PasswordValidator {

  static strong(control: AbstractControl): boolean {
    console.log('check strong');
    let hasNumber = /\d/.test(control.value);
    let hasUpper = /[A-Z]/.test(control.value);
    let hasLower = /[a-z]/.test(control.value);
    return hasNumber && hasUpper && hasLower;
  }

  validate(c: AbstractControl): {[key: string]: boolean | null} {
    if (c.parent.get('confirmPasswd') && c.parent.get('passwd') && c.parent.get('confirmPasswd').value && c.parent.get('passwd').value) {
      let cp = c.parent.get('confirmPasswd').value;
      let p = c.parent.get('passwd').value;
      if (cp != p) {
        c.parent.get('confirmPasswd').setErrors({Match: true});
        console.log('doesnt match = :' + cp + ' :p: ' + p);
        return {'Match': false};
      }
      if (!PasswordValidator.strong(c)) {
        c.parent.get('confirmPasswd').setErrors({Strong: true});
        console.log('not a strong password = ');
        return {'Strong': false};
      }
    }
    return null;
  }
}
