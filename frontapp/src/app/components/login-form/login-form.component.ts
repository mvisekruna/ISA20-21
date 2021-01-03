import { Component, OnInit, OnDestroy } from '@angular/core';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { User } from 'src/app/model/user';
import { AuthServiceService } from 'src/app/service/auth-service.service';
import { takeUntil } from 'rxjs/operators';
import { Subject } from 'rxjs';
import { UserServiceService } from 'src/app/service/user-service.service';
import { AppComponent } from 'src/app/app.component';

@Component({
  selector: 'app-login-form',
  templateUrl: './login-form.component.html',
  styleUrls: ['./login-form.component.css']
})
export class LoginFormComponent implements OnInit {

  user:  User;
  title: string;
  constructor( private route: ActivatedRoute, 
    private router: Router, 
    private appComponent: AppComponent,
    private authService: AuthServiceService,
    private userService: UserServiceService) {
      this.title='Log in';
      this.user = new User();
     }

  ngOnInit(): void {
  }

  onSubmit() {
    console.log('kliknuo');
   
    this.authService.login(this.user)
        .subscribe(data => {
         
            this.userService.getMyInfo(this.user.email).subscribe(
              data => {
                console.log(data);
                console.log(JSON.stringify(data.authorities[0]).includes("ROLE_USER"));
                if (JSON.stringify(data.authorities[0]).includes("ROLE_USER")) {
              localStorage.setItem("ROLE", "ROLE_USER");
              
              //this.router.navigate(["/user-profile"]);
              
            }
              localStorage.setItem("USERNAME", data.email);
            console.log(localStorage);
            console.log(localStorage.getItem("ROLE"));
           
            }
            );
          //  this.router.navigate([this.returnUrl]);
          },
          error => {
          console.log('Incorrect username or password.');
          
          //  this.notification = {msgType: 'error', msgBody: 'Incorrect username or password.'};
          });
  }
}
