import { Routes } from '@angular/router';
import { LoginComponent } from './view/login/login.component';
import { SearchComponent } from './view/search/search.component';

export const routes: Routes = [
    { path: "", component: LoginComponent},
    { path: "search", component: SearchComponent}
];
