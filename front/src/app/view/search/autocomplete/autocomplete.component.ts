import { Component, EventEmitter, Input, Output } from '@angular/core';
import { FormControl, FormsModule, ReactiveFormsModule } from '@angular/forms';
import { MatAutocomplete, MatAutocompleteModule } from '@angular/material/autocomplete';
import { MatInputModule } from '@angular/material/input';
import { MatFormFieldModule } from '@angular/material/form-field';
import { AsyncPipe } from '@angular/common';
import { Observable } from 'rxjs';
import { map, startWith } from 'rxjs/operators';

@Component({
  selector: 'autocomplete-search',
  standalone: true,
  imports: [
    MatAutocomplete, MatAutocompleteModule, MatInputModule, MatFormFieldModule, AsyncPipe, FormsModule, ReactiveFormsModule
  ],
  templateUrl: './autocomplete.component.html',
  styleUrl: './autocomplete.component.css'
})
export class HospitalSearchComponent {

  @Input() datas: string[] = []
  @Input() placeholder: string = ""
  @Input() label: string = ""
  @Input() type: string = ""

  @Output() handleSearchHospitalChange: EventEmitter<string> = new EventEmitter()

  wordSearch = new FormControl("");

  filteredOptions: Observable<string[]> = new Observable<string[]>;

ngOnChanges(){
  this.filteredOptions = this.wordSearch.valueChanges.pipe(
    startWith(''),
    map(value => this._filter(value || '')),
  );
}

  ngOnInit() {
    this.filteredOptions = this.wordSearch.valueChanges.pipe(
      startWith(''),
      map(value => this._filter(value || '')),
    );

    this.wordSearch.valueChanges.subscribe(value => {
      this.onChange(value ||"")
    })
  }

  private _filter(value: string): string[] {
    const filterValue = value.toLocaleLowerCase()
    if(this.datas !== undefined && Array.isArray(this.datas)){
      return this.datas.filter(o => o.toLocaleLowerCase().includes(filterValue))
    }else{
      return []
    }
  }

  onChange(value: string){
    this.handleSearchHospitalChange.emit(value ||"")
  }

}
