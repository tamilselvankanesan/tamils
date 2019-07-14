import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { LoginService } from '../login/login.service';
import { PersonService } from '../user/service/person.service';

@Component({
    selector: 'app-linkedin',
    template:
        `
        <h3>
            Redirecting ...
        </h3>
    `
})
export class LinkedInCallBack implements OnInit {
    constructor(private router: Router, private route: ActivatedRoute, private loginService: LoginService, private personService: PersonService) {
    }

    ngOnInit() {
        const code = this.route.snapshot.queryParamMap.get("code");
        const state = this.route.snapshot.queryParamMap.get("state");
        let error = this.route.snapshot.queryParamMap.get("error");
        const originalState = sessionStorage.getItem('linkedin_state');
        if (code != null && code !== '' && originalState === state) {
            this.loginService.validateLinkedInToken(code).subscribe(
                data => {
                    this.personService.getPersonByUserId(data.userId);
                    // sessionStorage.setItem('aj_user_data', JSON.stringify(data));
                    this.router.navigate(['/dashboard']); // navigate to home page
                },
                error => {
                    console.log('error occurred....');
                    this.router.navigate(['/login']);
                });
        }
        if (error) {
            console.log('authorization error from linkedIn ');
            this.router.navigate(['/login']);
        }
    }
}