import { Component } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { DirectorService } from '../../services/director.service';
import { Director } from '../../models/director.model';
import { CommonModule } from '@angular/common';
import { RouterModule, Routes } from '@angular/router';
import { MatSnackBar } from '@angular/material/snack-bar';
import { ActorService } from '../../services/actor.service';
import { Movie } from '../../models/movie.model';
import { MovieService } from '../../services/movie.service';
import { DialogService } from '../../services/dialog.service';

@Component({
  selector: 'app-director',
  templateUrl: './director.component.html',
  styleUrls: ['../../person.scss', './director.component.scss']
})
export class DirectorComponent {

  selectedMovieId: number = -1
  movies: Movie[]
  id: number = 0
  director: Director = null
  constructor(private directorService: DirectorService, private router: Router,
    private movieService: MovieService,
    private route: ActivatedRoute, private _snackBar: MatSnackBar, private dialogService: DialogService) { }



  ngOnInit(): void {
    this.id = this.route.snapshot.params['id']
    this.loadDirector()
  }
  loadDirector() {
    this.directorService.getDirectorById(this.id).subscribe(data => {
      this.director = data as Director
      this.loadAllMovies()
    }
    )
  }


  loadAllMovies() {
    this.movieService.getAllMovies().subscribe(data => {
      let movies = data as Movie[]
      this.movies = movies.filter(movie => {
        return !this.director.movies.find(m => m.id === movie.id)
      }) as Movie[]
    })
  }


  addMovie() {
    if (this.selectedMovieId === -1) return
    this.directorService.assignMovieToDirector(this.id, this.selectedMovieId).subscribe(data => {
      this.loadDirector()
    })
    this.selectedMovieId = -1
  }

  deleteMovie(movieId: number) {
    this.directorService.removeMovieFromDirector(this.id, movieId).subscribe(() => {
      this.loadDirector()
    })

    let snackBarRef = this._snackBar.open('Movie Deleted', 'Undo', {
      duration: 3000
    })
    snackBarRef.onAction().subscribe(() => {
      this.directorService.assignMovieToDirector(this.id, movieId).subscribe(data => {
        console.log(data)
        this.loadDirector()
      })
    });
  }

  deleteDirector() {
    this.dialogService.openDialog().subscribe(result => {
      if (result === 'delete') {
        this.directorService.deleteDirector(this.id).subscribe(() => {
          this._snackBar.open('Director Deleted', 'OK', {
            duration: 3000
          })
          this.router.navigate(['/directors'])
        })

      }
    })
  }



  getAge() {
    if (this.director && this.director.birthDate) {
      const today = new Date()
      const birthDate = new Date(this.director.birthDate)
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
