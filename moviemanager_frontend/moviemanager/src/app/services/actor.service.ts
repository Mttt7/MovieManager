import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Actor } from '../models/actor.model';
import { of, switchMap } from 'rxjs';
import { Movie } from '../models/movie.model';
import { MovieService } from './movie.service';

@Injectable({
  providedIn: 'root'
})
export class ActorService {
  private actorUrl = 'http://localhost:8080/api/actors'


  constructor(private http: HttpClient, private movieService: MovieService) { }


  getAllActors() {
    return this.http.get<Actor[]>(this.actorUrl)
  }

  getActorById(id: number) {
    return this.http.get<Actor>(`${this.actorUrl}/${id}`)
  }

  getActorWithMovies(id: number) {
    return this.http.get<Actor>(`${this.actorUrl}/${id}`).pipe(
      switchMap(actor => {
        const movies = []
        return this.http.get<Movie[]>(`${this.actorUrl}/${id}/movies`).pipe(
          switchMap(movies => {
            return of({ ...actor, movies })
          })
        )

      })
    )
  }

  assignMovieToActor(actorId: number, movieId: number) {
    return this.http.patch(`${this.actorUrl}/${actorId}/${movieId}`, {})
  }

  removeMovieFromActor(actorId: number, movieId: number) {
    return this.http.delete(`${this.actorUrl}/${actorId}/${movieId}`)
  }

}
