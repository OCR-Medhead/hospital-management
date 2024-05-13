import { Component, EventEmitter, OnInit, Output } from '@angular/core';
import { HospitalService } from '../../service/hospital.service';
import { HospitalSearchComponent } from './autocomplete/autocomplete.component';
import { Hospital } from '../../interface/hospital';
import { Specialization } from '../../interface/specialization';
import { CommonModule } from '@angular/common';
import { Observable } from 'rxjs';

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

  hospitals: string[] = []
  specializations: string[] = []

  speChoose: string = ""
  hospitalChoose: string = ""

  hopistalFound?: string;

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
                this.hospitals = rep.map(x => x.name).slice(0, 3)
              },
              error: (err) => {
                console.log("Impossible de récupérer les hopitaux")
                console.log(err)
              },
            })


      } catch (ex) {
        console.log("Impossible de récupérer les hopitaux et spécializations disponible")
        console.log(ex)
      }
    })()
  }

  onSearchByHospital(hospital: string) {
    this.hopistalFound = undefined
    if (hospital.length > 0) {
      hospital = hospital[0].toUpperCase() + hospital.slice(1)
      this.hospitalService.getHospitalsAround(hospital)
        .subscribe(
          {
            next: (rep) => {
              this.hospitals = rep.map(x => x.name)
            },
            error: (err) => {
              console.log("Impossible de récupérer les hopitaux")
              console.log(err)
            },
          })
    }
    // console.log("hospital founded")
    // console.log(this.hospitals)
    this.hospitalChoose = hospital


  }

  onSelectSpe(spe: string) {
    this.hopistalFound = undefined
    this.speChoose = spe
  }

  searchHospital() {
    if (this.speChoose && this.hospitalChoose) {
      if (this.specializations.includes(this.speChoose) && this.hospitals.includes(this.hospitalChoose)) {


        this.hospitalService.getHospitalByName(this.hospitalChoose)
          .subscribe(
            {
              next: (rep) => {
                let speInHospital = rep.specializations.map(x => x.name)
                if (speInHospital.includes(this.speChoose)) {
                  this.hopistalFound = this.hospitalChoose
                } else {
                  this.hopistalFound = ""
                }
              },
              error: (err) => {
                console.log(err)
              },
            })


      }
    }

  }



}
