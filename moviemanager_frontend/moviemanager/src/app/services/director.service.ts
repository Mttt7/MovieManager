import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Director } from '../models/director.model';

@Injectable({
  providedIn: 'root'
})
export class DirectorService {
  private apiUrl = 'http://localhost:8080/api/directors'


  constructor(private http: HttpClient) { }

  getDirectors() {
    return this.http.get<Director[]>(this.apiUrl)
  }

  getDirectorById(id: number) {
    return this.http.get<Director>(`${this.apiUrl}/${id}`)
  }

  addDirector(director: Director) {
    return this.http.post(this.apiUrl, director)
  }

  deleteDirector(id: number) {
    return this.http.delete(`${this.apiUrl}/${id}`)
  }

  assignMovieToDirector(directorId: number, movieId: number) {
    return this.http.patch(`${this.apiUrl}/${directorId}/${movieId}`, {})
  }

  removeMovieFromDirector(directorId: number, movieId: number) {
    return this.http.delete(`${this.apiUrl}/${directorId}/${movieId}`)
  }
}
