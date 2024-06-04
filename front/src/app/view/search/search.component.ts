import { Component, EventEmitter, OnInit, Output } from '@angular/core';
import { HospitalService } from '../../service/hospital.service';
import { HospitalSearchComponent } from './autocomplete/autocomplete.component';
import { Hospital } from '../../interface/hospital';
import { Specialization } from '../../interface/specialization';
import { CommonModule } from '@angular/common';
import { Observable, catchError, tap } from 'rxjs';
import { HttpResponse } from '@angular/common/http';

@Component({
  selector: 'app-search',
  standalone: true,
  imports: [
    HospitalSearchComponent, CommonModule
  ],
  templateUrl: './search.component.html',
  styleUrl: './search.component.css'
})
export class SearchComponent implements OnInit {

  hospitals: Hospital[] = []
  specializations: string[] = []
  hospitalsName: string[] = []

  speChoose: string = ""
  hospitalChoose: string = ""

  hospitalFound?: string;
  hospitalFoundMessage?: string;

  displayAlert: Boolean = false;
  alertType: string = ""
  alertMessage: string = ""

  constructor(private hospitalService: HospitalService) { }

  ngOnInit() {
    (async () => {
      try {
        this.hospitalService.getSpecializations()
          .subscribe(
            {
              next: (rep) => {
                this.specializations = rep.map(x => x.name);
              },
              error: (err) => {
                console.log("Impossible de récupérer les spécialisations")
              },
            })

        this.hospitalService.getHospitals()
          .subscribe(
            {
              next: (rep) => {
                this.hospitals = rep
                this.hospitalsName = this.hospitals.map(x => x.name)
              },
              error: (err) => {
                console.log("Impossible de récupérer les hopitaux")
              },
            })


      } catch (ex) {
        console.log("Impossible de récupérer les hopitaux et spécializations disponible")
      }
    })()
  }

  onSearchByHospital(hospital: string) {
    this.hospitalFound = undefined
    if (hospital.length > 0) {
      hospital = hospital[0].toUpperCase() + hospital.slice(1)
      this.hospitalService.getHospitalsAround(hospital)
        .subscribe(
          {
            next: (rep) => {
              this.hospitals = rep
            },
            error: (err) => {
              console.log("Impossible de récupérer les hopitaux")
            },
          })
    }
    this.hospitalChoose = hospital
  }

  onSelectSpe(spe: string) {
    this.hospitalFound = undefined
    this.speChoose = spe
  }

  searchHospital() {
    if (this.hospitals.length > 0) {
      this.hospitalFound = this.hospitals[0].name
      this.hospitalFoundMessage = `Une place est disponible dans l'hopital de ${this.hospitalFound}`
    } else {
      this.hospitalFound = ""
      this.hospitalFoundMessage = "Impossible de trouver un hopital avec cette spécialisation ayant des disponiblités"
    }
  }

  reserverPlace() {
    let hospital = this.hospitals.find(x => x.name === this.hospitalFound)
    if (hospital) {
      this.hospitalService.reserverPlace(hospital.id)
        .subscribe(
          {
            next: (rep) => {
              if (rep.status === 200) {
                this.toggleAlert("success", "La place à été réservé")
              } else {
                this.toggleAlert("warning", "Impossible de réserver la place")
              }
            },
            error: (err) => {
              this.toggleAlert("warning", "Impossible de réserver la place")
            },
          })
    }
  }

  toggleAlert(type: string, message: string) {
    this.displayAlert = true;
    this.alertType = type
    this.alertMessage = message
    setTimeout(() => {
      this.displayAlert = false;
      this.alertMessage = ""
      this.alertType = ""
    }, 5000)
  }
}
