import {Directive, forwardRef, Attribute} from '@angular/core';
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

  constructor( @Attribute('passwordValidator') public targetComponent: string, @Attribute('reverse') public reverse: string) {}

  validate(c: AbstractControl): {[key: string]: boolean | null} {

    if (c && c.parent.get(this.targetComponent)) {
      console.log('clear = ');
      c.setErrors({Match: true});
      c.parent.get(this.targetComponent).setErrors({Match: true});
      
      
      c.setErrors(null);
      c.parent.get(this.targetComponent).setErrors(null);

      if (c.value && c.parent.get(this.targetComponent).value) {

        console.log('inside ' + c.value);
        console.log('inside target ' + c.root.get(this.targetComponent).value);
        if (c.value !== c.parent.get(this.targetComponent).value) {
          console.log('doesnt match = :');

          c.setErrors({'Match': false});
          c.parent.get(this.targetComponent).setErrors(null);

          //        if (this.reverse) {
          //          console.log(' password comp = :');
          //          c.parent.get(this.targetComponent).setErrors(null);
          //          c.setErrors({Match: false});
          //        } else {
          //          console.log(' confirm password comp = :');
          //          c.parent.get(this.targetComponent).setErrors(null);
          //          c.parent.get('confirmPasswd').setErrors({Match: false});
          //        }
          return {'Match': false};
        }
        console.log(' password match ');
        c.setErrors(null);
        c.parent.get(this.targetComponent).setErrors(null);

      }


    }


    //    if (c.parent.get('confirmPasswd') && c.parent.get('passwd') && c.parent.get('confirmPasswd').value && c.parent.get('passwd').value) {
    //      let cp = c.parent.get('confirmPasswd').value;
    //      let p = c.parent.get('passwd').value;
    //      if (cp != p) {
    //        c.parent.get('confirmPasswd').setErrors({Match: true});
    //        console.log('doesnt match = :' + cp + ' :p: ' + p);
    //        return {'Match': false};
    //      }
    //      if (!PasswordValidator.strong(c)) {
    //        c.parent.get('confirmPasswd').setErrors({Strong: true});
    //        console.log('not a strong password = ');
    //        return {'Strong': false};
    //      }
    //    }
    return null;
  }
}
