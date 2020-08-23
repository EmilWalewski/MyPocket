import { Component, OnInit, Input, Output, EventEmitter, OnDestroy } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { LoginData, LoginRequest } from 'src/app/models/loginModel/login-request';
import { AuthService } from 'src/app/services/auth/auth.service';
import { Subscription } from 'rxjs';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit, OnDestroy {

  loginForm: FormGroup;

  @Input()
  openDialog;

  @Output()
  closeDialogEvent = new EventEmitter<boolean>();

  validationError: boolean;
  errorMessage: string;

  private sub: Subscription;


  constructor(private formBuilder: FormBuilder, private auth: AuthService, private router: Router) {
    this.loginForm = this.createFormGroupWithBuilder(formBuilder);
  }


  ngOnDestroy(): void {
    this.sub.unsubscribe();
  }

  ngOnInit(): void {
  }

  createFormGroupWithBuilder(formBuilder: FormBuilder) {
    return formBuilder.group({
      loginData: formBuilder.group({
        username: '',
        password: ''
      })
    });
  }

  closeDialog() {
    this.validationError = false;
    this.loginForm.reset();
    this.closeDialogEvent.emit(false);
  }

  onSubmit() {

    const result: LoginRequest = Object.assign({}, this.loginForm.value);
    result.loginData = Object.assign({}, result.loginData);

    this.sub = this.auth.authenticate(result.loginData)
    .subscribe(response => {

      // set validation error flag to false
      this.validationError = response;

      this.closeDialog();

      // redirect to home
      this.router.navigate(['/mypocket']);
    },
    error => {
      this.validationError = true;
      this.errorMessage = error.error.message;
    });

  }

}
