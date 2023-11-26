import { Component } from '@angular/core';
import { Movie } from '../../models/movie.model';
import { MovieService } from '../../services/movie.service';
import { ActivatedRoute, Router } from '@angular/router';
import { Actor } from '../../models/actor.model';
import { ActorService } from '../../services/actor.service';
import { MatSnackBar } from '@angular/material/snack-bar';
import { DirectorService } from '../../services/director.service';
import { Director } from '../../models/director.model';
import { DialogService } from '../../services/dialog.service';

@Component({
  selector: 'app-movie',
  templateUrl: './movie.component.html',
  styleUrl: './movie.component.scss'
})
export class MovieComponent {

  id: number = 0
  movie: Movie = null
  actors: Actor[]
  directors: Director[]
  selectedActorId: number = -1
  directorAssigned: boolean = false
  selectedDirectorId: number = -1

  constructor(private movieService: MovieService, private actorService: ActorService,
    private directorService: DirectorService, private router: Router,
    private route: ActivatedRoute, private _snackBar: MatSnackBar, private dialogService: DialogService) { }

  ngOnInit(): void {
    this.id = this.route.snapshot.params['id']
    this.loadMovie()

  }
  isDirectorAssigned() {
    if (this.movie?.directorId != null) {
      this.directorAssigned = true
    } else {
      this.directorAssigned = false
    }
  }
  loadActorsAssociatedToMovie() {
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
      this.loadActorsAssociatedToMovie()
      this.isDirectorAssigned()
      this.loadDirectors()
    })
  }

  addActor() {
    if (this.selectedActorId === -1) return
    this.actorService.assignMovieToActor(this.selectedActorId, this.id).subscribe(data => {
      console.log(data)
      this.loadActorsAssociatedToMovie()
    })
    this.selectedActorId = -1
  }

  deleteActor(actorId: number) {
    this.actorService.removeMovieFromActor(actorId, this.id).subscribe(() => {
      this.loadActorsAssociatedToMovie()
    })

    let snackBarRef = this._snackBar.open('Actor Deleted', 'Undo', {
      duration: 3000
    })
    snackBarRef.onAction().subscribe(() => {
      this.actorService.assignMovieToActor(actorId, this.id).subscribe(data => {
        console.log(data)
        this.loadActorsAssociatedToMovie()
      })
    });
  }

  loadDirectors() {
    this.directorService.getDirectors().subscribe(data => {
      this.directors = data as Director[]
    })
  }

  addDirector() {
    if (this.directorAssigned || this.selectedDirectorId == -1) return
    this.directorService.assignMovieToDirector(this.selectedDirectorId, this.id).subscribe(data => {
      console.log(data)
      this.loadMovie()
      this.directorAssigned = true
    })
    this.selectedDirectorId = -1
  }

  deleteDirector(directorId: number) {
    this.isDirectorAssigned()
    if (!this.directorAssigned) return
    this.directorService.removeMovieFromDirector(directorId, this.id).subscribe(() => {
      this.loadMovie()
      this.directorAssigned = false
    })



    let snackBarRef = this._snackBar.open('Director Deleted', 'Undo', {
      duration: 3000
    })
    snackBarRef.onAction().subscribe(() => {
      this.directorService.assignMovieToDirector(directorId, this.id).subscribe(data => {
        console.log(data)
        this.loadMovie()
        this.directorAssigned = true

      })

    });



  }

  deleteMovie() {
    this.dialogService.openDialog().subscribe(result => {
      if (result === 'delete') {
        this.movieService.deleteMovie(this.id).subscribe(() => {
          this._snackBar.open('Movie Deleted', 'OK', {
            duration: 3000
          })
          this.router.navigate(['/movies'])
        })

      }
    })
  }



  openSnackBar(message: string, action: string) {
    this._snackBar.open(message, action);
  }


}
