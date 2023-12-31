import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Category } from '../models/category.model';

@Injectable({
  providedIn: 'root'
})
export class CategoryService {

  private categoriesUrl = 'http://localhost:8080/api/categories'

  constructor(private http: HttpClient) { }

  getAllCategories(): Observable<Category[]> {
    return this.http.get<Category[]>(this.categoriesUrl)
  }

  getCategoryById(id: number): Observable<Category> {
    return this.http.get<Category>(`${this.categoriesUrl}/${id}`)
  }

  getCategoryWithMoviesById(id: number): Observable<Category> {
    return this.http.get<Category>(`${this.categoriesUrl}/${id}/movies`)
  }



}

