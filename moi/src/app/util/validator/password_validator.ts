import {Directive, forwardRef, Attribute} from '@angular/core';
import {NG_VALIDATORS, AbstractControl} from '@angular/forms';

@Directive({
  selector: '[passwordValidator][ngModel]',
  providers: [{
    provide: NG_VALIDATORS, multi: true, useExisting: forwardRef(() => PasswordValidator)
  }]
})
export class PasswordValidator {

  constructor( @Attribute('passwordValidator') public targetComponent: string, @Attribute('reverse') public reverse: string) {}

  validate(c: AbstractControl): {[key: string]: boolean | null} {

    // c is either password or confirmPasswordField
    let v = c.value;

    // if v is password then other is confirmPassword and viceversa
    let other = c.parent.get(this.targetComponent);

    if (other && v !== other.value && !this.reverse) {
      // when reverse is null or false then c will be password field
      return {DoesNotMatch: true};
    }

    if (other && v === other.value && this.reverse) {
      delete other.errors['DoesNotMatch']; // when the password matches then remove the errors
    }

    if (other && v !== other.value && this.reverse) {
      other.setErrors({DoesNotMatch: true}); // set error in the confirmPassword field
      return null;
    }

    return this.strong(c, other);
  }

  strong(c: AbstractControl, other: AbstractControl): {[key: string]: boolean} {

    let hasNumber = /\d/.test(c.value);
    let hasUpper = /[A-Z]/.test(c.value);
    let hasLower = /[a-z]/.test(c.value);
    if (!(hasNumber && hasUpper && hasLower)) {
      console.log('Not strong');
      if (other && this.reverse) {
        other.setErrors({NotStrong: true}); // set error in the confirmPassword field
      } else {
        return {NotStrong: true};
      }
    }
    return null;
  }

}
