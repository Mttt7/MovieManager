import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

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
    ActorsComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    RouterModule.forRoot(routes)
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
