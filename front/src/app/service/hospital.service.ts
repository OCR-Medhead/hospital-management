import { Injectable } from '@angular/core';
import { Hospital } from '../interface/hospital';
import { Specialization } from '../interface/specialization';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { AuthService } from './auth.service';
import { Observable } from 'rxjs';
import { HospitalSpecialization } from '../interface/hospitalSpecialization';

const API_HOST_URI = "http://localhost:8888/"

@Injectable({
  providedIn: 'root'
})

export class HospitalService {

  constructor(private client: HttpClient, private authService: AuthService) {
  }

  getHospitals(): Observable<Hospital[]> {
    return this.client.get<Hospital[]>(API_HOST_URI + "hospitals", {
      headers: new HttpHeaders({
        "Authorization": `Bearer ${this.authService.loadTokenInStorage()}`
      })
    })

  }

  getHospitalsAround(hospital: string): Observable<Hospital[]> {
    return this.client.get<Specialization[]>(API_HOST_URI + "hospitals/around/" + hospital, {
      headers: new HttpHeaders({
        "Authorization": `Bearer ${this.authService.loadTokenInStorage()}`
      })
    })
  }

  getSpecializations(): Observable<Specialization[]> {
    return this.client.get<Specialization[]>(API_HOST_URI + "specializations", {
      headers: new HttpHeaders({
        "Authorization": `Bearer ${this.authService.loadTokenInStorage()}`
      })
    })
  }

  getHospitalByName(hospitalName: string) : Observable<HospitalSpecialization>{
    return this.client.get<HospitalSpecialization>(API_HOST_URI + "hospital/" + hospitalName, {
      headers: new HttpHeaders({
        "Authorization": `Bearer ${this.authService.loadTokenInStorage()}`
      })
    })
  }

}
