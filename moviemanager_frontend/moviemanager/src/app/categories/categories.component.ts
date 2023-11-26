import { Component } from '@angular/core';
import { CategoryService } from '../services/category.service';
import { Category } from '../models/category.model';
import { Movie } from '../models/movie.model';

@Component({
  selector: 'app-categories',
  templateUrl: './categories.component.html',
  styleUrl: './categories.component.scss'
})
export class CategoriesComponent {


  categories: Category[] = []
  selectedChips = "Action";
  movies: Movie[] = []

  constructor(private categoryService: CategoryService) { }

  ngOnInit(): void {
    this.categoryService.getAllCategories().subscribe(data => {
      this.categories = data as Category[]
    })
  }


  showMovies(categoryId: number) {
    this.categoryService.getCategoryWithMoviesById(categoryId).subscribe(data => {
      let category = data as Category
      this.movies = category.movies
    })
  }

}
