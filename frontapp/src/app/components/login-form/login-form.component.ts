import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-login-form',
  templateUrl: './login-form.component.html',
  styleUrls: ['./login-form.component.css']
})
export class LoginFormComponent implements OnInit {

  title: string;
  constructor( private route: ActivatedRoute, 
    private router: Router) {
      this.title='Log in';
     }

  ngOnInit(): void {
  }

  onSubmit() {}
}
