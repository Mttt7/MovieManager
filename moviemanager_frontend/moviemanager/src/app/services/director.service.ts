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

}
