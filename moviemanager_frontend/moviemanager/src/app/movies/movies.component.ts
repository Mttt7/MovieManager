import { Component } from '@angular/core';
import { MovieService } from '../services/movie.service';
import { Movie } from '../models/movie.model';
import { FormBuilder, FormGroup, FormControl, Validators } from '@angular/forms';



@Component({
  selector: 'app-movies',
  templateUrl: './movies.component.html',
  styleUrls: ['../list.scss', './movies.component.scss']
})
export class MoviesComponent {


  movies: Movie[] = []
  myForm: FormGroup

  constructor(private movieService: MovieService, private fb: FormBuilder) { }

  ngOnInit(): void {
    this.loadAllMovies()
    this.myForm = this.fb.group({
      title: new FormControl('', [Validators.required]),
      description: '',
      productionYear: new FormControl('', control => {
        const value = control.value;
        return (value >= 1850 && value <= 2050) ? null : { 'rangeError': true }
      }),
    })
  }
  loadAllMovies() {
    this.movieService.getAllMovies().subscribe(
      data => this.movies = data)
  }

  onMovieAdded() {
    const title = this.myForm.get('title').value
    let description = this.myForm.get('description').value
    const productionYear = this.myForm.get('productionYear').value

    if (description == '') {
      description = 'No description'
    }

    const movie = { title, description, productionYear }
    console.log(movie)
    this.movieService.addMovie(movie).subscribe(data => {
      console.log(data)
      this.loadAllMovies()
    })


  }
}

