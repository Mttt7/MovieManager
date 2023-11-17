import { Component } from '@angular/core';
import { Actor } from '../../models/actor.model';
import { ActorService } from '../../services/actor.service';
import { ActivatedRoute, Router } from '@angular/router';
import { Movie } from '../../models/movie.model';
import { MatSnackBar } from '@angular/material/snack-bar';
import { MovieService } from '../../services/movie.service';
import { DialogService } from '../../services/dialog.service';



@Component({
  selector: 'app-actor',
  templateUrl: './actor.component.html',
  styleUrls: ['../../person.scss', './actor.component.scss']
})
export class ActorComponent {

  id: number = 0
  actor: Actor = null

  movies: Movie[]
  selectedMovieId: number = -1


  constructor(private router: Router, private actorService: ActorService, private movieService: MovieService,
    private route: ActivatedRoute, private _snackBar: MatSnackBar, private dialogService: DialogService) { }

  ngOnInit(): void {
    this.id = this.route.snapshot.params['id']
    this.loadActor()
  }
  loadActor() {
    this.actorService.getActorWithMovies(this.id).subscribe(data => {
      this.actor = data as Actor
      this.loadAllMovies()
    })
  }
  loadAllMovies() {
    this.movieService.getAllMovies().subscribe(data => {
      let movies = data as Movie[]
      this.movies = movies.filter(movie => {
        return !this.actor.movies.find(m => m.id === movie.id)
      }) as Movie[]
    })
  }

  addMovie() {
    if (this.selectedMovieId === -1) return
    this.actorService.assignMovieToActor(this.id, this.selectedMovieId).subscribe(data => {
      this.loadActor()
    })
    this.selectedMovieId = -1
  }

  deleteMovie(movieId: number) {
    this.actorService.removeMovieFromActor(this.id, movieId).subscribe(() => {
      this.loadActor()
    })

    let snackBarRef = this._snackBar.open('Movie Deleted', 'Undo', {
      duration: 3000
    })
    snackBarRef.onAction().subscribe(() => {
      this.actorService.assignMovieToActor(this.id, movieId).subscribe(data => {
        console.log(data)
        this.loadActor()
      })
    });
  }

  deleteActor() {
    this.dialogService.openDialog().subscribe(result => {
      if (result === 'delete') {
        this.actorService.deleteActor(this.id).subscribe(() => {
          this._snackBar.open('Actor Deleted', 'OK', {
            duration: 3000
          })
          this.router.navigate(['/actors'])
        })

      }
    })

  }




  getAge() {
    if (this.actor && this.actor.birthDate) {
      const today = new Date()
      const birthDate = new Date(this.actor.birthDate)
      let age = today.getFullYear() - birthDate.getFullYear()
      const month = today.getMonth() - birthDate.getMonth()
      if (month < 0 || (month === 0 && today.getDate() < birthDate.getDate())) {
        age--
      }
      return age
    }
    return 0
  }
}


