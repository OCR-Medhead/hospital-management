import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable, firstValueFrom } from 'rxjs';

const  API_HOST_URI = "http://localhost:8888/"


@Injectable({
  providedIn: 'root'
})
export class AuthService {

  constructor(private client: HttpClient) { }

  async login(email: string, password: string): Promise<boolean>{

    let isAuthValid = await firstValueFrom(this.client.post(API_HOST_URI + "user/login", { 
      email,
      password
    }, {
      headers: new HttpHeaders({
        "Access-Control-Allow-Origin": "*"       
      }),
      responseType: 'text'
    })
    ).then(rep => {
      this.saveTokenInStorage(rep)
      return true
    })
    .catch(_ => false)
    
    return isAuthValid

  }

  private saveTokenInStorage(token: string){
    localStorage.setItem("token", token)
  }

  public loadTokenInStorage(): string | null {
    return localStorage.getItem("token") 
  }


}
