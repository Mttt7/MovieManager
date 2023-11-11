import { Component } from '@angular/core';
import { Movie } from '../../models/movie.model';
import { MovieService } from '../../services/movie.service';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-movie',
  templateUrl: './movie.component.html',
  styleUrl: './movie.component.scss'
})
export class MovieComponent {

  id: number = 0
  movie: Movie = null

  constructor(private movieService: MovieService,
    private route: ActivatedRoute) { }

  ngOnInit(): void {
    this.id = this.route.snapshot.params['id']
    this.loadMovie()
  }
  loadActors() {
    this.movieService.getActorsByMovieId(this.id).subscribe(data => {
      this.movie.actors = data
    })
  }
  loadMovie() {
    this.movieService.getMovieById(this.id).subscribe(data => {
      this.movie = data as Movie
      this.loadActors()
    })
  }

}
