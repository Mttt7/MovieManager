import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Movie } from '../models/movie.model';
import { Observable, of, switchMap } from 'rxjs';
import { Director } from '../models/director.model';
import { Actor } from '../models/actor.model';

@Injectable({
  providedIn: 'root'
})
export class MovieService {
  private movieUrl = 'http://localhost:8080/api/movies'
  private directorUrl = 'http://localhost:8080/api/directors';

  constructor(private http: HttpClient) { }

  getAllMovies(): Observable<Movie[]> {
    return this.http.get<Movie[]>(this.movieUrl)
  }

  getMovieById(id: number): Observable<Movie> {
    const url = `${this.movieUrl}/${id}`
    return this.http.get<Movie>(url).pipe(
      switchMap(movie => {
        if (!movie.directorId) { //directorId... blad?
          return of({ ...movie, director: null })
        }
        const directorUrl = `${this.directorUrl}/${movie.directorId}`
        const actors = []
        return this.http.get<Director>(directorUrl).pipe(
          switchMap(director => {
            return of({ ...movie, director, actors })
          })
        )
      }
      )
    )
  }

  getActorsByMovieId(id: number) {
    return this.http.get<Actor[]>(`${this.movieUrl}/${id}/actors`)
  }

  addMovie(movie: Movie) {
    return this.http.post(this.movieUrl, movie)
  }

  deleteMovie(id: number) {
    return this.http.delete(`${this.movieUrl}/${id}`)
  }


}
