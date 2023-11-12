import { Component } from '@angular/core';
import { MovieService } from '../services/movie.service';
import { Movie } from '../models/movie.model';

@Component({
  selector: 'app-movies',
  templateUrl: './movies.component.html',
  styleUrl: '../list.scss'
})
export class MoviesComponent {

  movies: Movie[] = []

  constructor(private movieService: MovieService) { }

  ngOnInit(): void {
    this.loadAllMovies()
  }
  loadAllMovies() {
    this.movieService.getAllMovies().subscribe(
      data => this.movies = data)
  }
}

