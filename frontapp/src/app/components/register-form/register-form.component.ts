import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-register-form',
  templateUrl: './register-form.component.html',
  styleUrls: ['./register-form.component.css']
})
export class RegisterFormComponent implements OnInit {

  title: string;
  buttonName: string = 'Register now';
  div1:boolean = false;
  constructor() { 
    this.title='Register';
  }
  ngOnInit(): void {
  }

  div1Function(){
    this.div1=!this.div1;
    if (this.div1==true){
      this.buttonName='Not now';
    }
  }

  onSubmit() {}

}
