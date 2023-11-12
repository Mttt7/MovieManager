import { Component } from '@angular/core';
import { Director } from '../models/director.model';
import { DirectorService } from '../services/director.service';

@Component({
  selector: 'app-directors',
  templateUrl: './directors.component.html',
  styleUrl: '../list.scss'
})
export class DirectorsComponent {

  directors: Director[] = []

  constructor(private directorService: DirectorService) { }

  ngOnInit(): void {
    this.loadAllDirectors()
  }
  loadAllDirectors() {
    this.directorService.getDirectors().subscribe(data => {
      this.directors = data as Director[]
    })
  }

}
