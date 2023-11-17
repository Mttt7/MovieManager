import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { Form, FormsModule } from '@angular/forms';
import { ReactiveFormsModule } from '@angular/forms';
import { MatInputModule } from '@angular/material/input';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatButtonModule } from '@angular/material/button';
import { MatNativeDateModule } from '@angular/material/core';
import { MatDatepickerModule } from '@angular/material/datepicker';
import { FormControl } from '@angular/forms';
import { MatTreeModule } from '@angular/material/tree';
import { MatSelectModule } from '@angular/material/select';
import { MatDialogModule } from '@angular/material/dialog';



import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { MoviesComponent } from './movies/movies.component';
import { HttpClient, HttpClientModule } from '@angular/common/http';
import { RouterModule, Routes } from '@angular/router';
import { MovieComponent } from './movies/movie/movie.component';
import { DirectorComponent } from './directors/director/director.component';
import { CommonModule } from '@angular/common';
import { DirectorsComponent } from './directors/directors.component';
import { ActorsComponent } from './actors/actors.component';
import { ActorComponent } from './actors/actor/actor.component';
import { HeaderComponent } from './header/header.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { YesnoDialogComponent } from './yesno.dialog/yesno.dialog.component';

const routes: Routes = [
  { path: 'movies', component: MoviesComponent },
  { path: 'movies/:id', component: MovieComponent },
  { path: 'directors', component: DirectorsComponent },
  { path: 'directors/:id', component: DirectorComponent },
  { path: 'actors', component: ActorsComponent },
  { path: 'actors/:id', component: ActorComponent },
]

@NgModule({
  declarations: [
    AppComponent,
    MoviesComponent,
    MovieComponent,
    DirectorComponent,
    DirectorsComponent,
    ActorComponent,
    ActorsComponent,
    HeaderComponent,
    YesnoDialogComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    RouterModule.forRoot(routes),
    FormsModule,
    ReactiveFormsModule,
    BrowserAnimationsModule,
    MatInputModule,
    MatFormFieldModule,
    MatButtonModule,
    MatNativeDateModule,
    MatDatepickerModule,
    MatSelectModule,
    MatDialogModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
