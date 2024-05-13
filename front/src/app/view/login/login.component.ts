import { Component } from '@angular/core';
import { FormsModule, FormControl, ReactiveFormsModule } from '@angular/forms';
import { AuthService } from '../../service/auth.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [
    // Require FormsModule to prevent form to be send
    FormsModule,
    ReactiveFormsModule
  ],
  templateUrl: './login.component.html',
  styleUrl: './login.component.css'
})
export class LoginComponent {

  email = new FormControl("")
  password = new FormControl("")

  loading = false

  constructor(private authService: AuthService, private router: Router) { }

  public async login() {

    let email = this.email.value
    let password = this.password.value

    if ((email === null || email === "") || (password === null || password === "")) {
      return
    }

    this.loading = true

    let isConnectionOk = await this.authService.login(email, password)

    if(isConnectionOk){
      this.router.navigate(["search"])
    }else{
      console.log("Impossible de se connecter...")
    this.loading = false
    }

  }





}
