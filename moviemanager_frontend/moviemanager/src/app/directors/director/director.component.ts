import { Component } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { DirectorService } from '../../services/director.service';
import { Director } from '../../models/director.model';
import { CommonModule } from '@angular/common';
import { RouterModule, Routes } from '@angular/router';

@Component({
  selector: 'app-director',
  templateUrl: './director.component.html',
  styleUrl: '../../person.scss'
})
export class DirectorComponent {
  id: number = 0
  director: Director = null
  constructor(private directorService: DirectorService,
    private route: ActivatedRoute) { }



  ngOnInit(): void {
    this.id = this.route.snapshot.params['id']
    this.loadDirector()
  }
  loadDirector() {
    this.directorService.getDirectorById(this.id).subscribe(data => {
      this.director = data as Director
    }
    )
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
