import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HospitalSearchComponent } from './autocomplete.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';



describe('HospitalSearchComponent', () => {
  let component: HospitalSearchComponent;
  let fixture: ComponentFixture<HospitalSearchComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [HospitalSearchComponent, BrowserAnimationsModule]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(HospitalSearchComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
