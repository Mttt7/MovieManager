import { Component } from '@angular/core';
import { Movie } from '../../models/movie.model';
import { MovieService } from '../../services/movie.service';
import { ActivatedRoute } from '@angular/router';
import { Actor } from '../../models/actor.model';
import { ActorService } from '../../services/actor.service';
import { MatSnackBar } from '@angular/material/snack-bar';

@Component({
  selector: 'app-movie',
  templateUrl: './movie.component.html',
  styleUrl: './movie.component.scss'
})
export class MovieComponent {



  id: number = 0
  movie: Movie = null
  actors: Actor[]
  selectedActorId: number

  constructor(private movieService: MovieService, private actorService: ActorService,
    private route: ActivatedRoute, private _snackBar: MatSnackBar) { }

  ngOnInit(): void {
    this.id = this.route.snapshot.params['id']
    this.loadMovie()
  }
  loadActorsssociatedToMovie() {
    this.movieService.getActorsByMovieId(this.id).subscribe(data => {
      this.movie.actors = data
      this.loadActors()
    })
  }
  loadActors() {
    this.actorService.getAllActors().subscribe(data => {
      let actors = data as Actor[]
      this.actors = actors.filter(actor => {
        return !this.movie.actors.find(a => a.id === actor.id)
      }) as Actor[]
    })

  }
  loadMovie() {
    this.movieService.getMovieById(this.id).subscribe(data => {
      this.movie = data as Movie
      this.loadActorsssociatedToMovie()

    })
  }

  addActor() {
    this.actorService.assignMovieToActor(this.selectedActorId, this.id).subscribe(data => {
      console.log(data)
      this.loadActorsssociatedToMovie()
    })
  }

  deleteActor(actorId: number) {
    this.actorService.removeMovieFromActor(actorId, this.id).subscribe(() => {
      this.loadActorsssociatedToMovie()
    })

    let snackBarRef = this._snackBar.open('Actor Deleted', 'Undo', {
      duration: 3000
    })
    snackBarRef.onAction().subscribe(() => {
      this.actorService.assignMovieToActor(actorId, this.id).subscribe(data => {
        console.log(data)
        this.loadActorsssociatedToMovie()
      })
    });
  }


  openSnackBar(message: string, action: string) {
    this._snackBar.open(message, action);
  }


}
