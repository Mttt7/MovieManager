import { Component } from '@angular/core';
import { Director } from '../models/director.model';
import { DirectorService } from '../services/director.service';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-directors',
  templateUrl: './directors.component.html',
  styleUrl: '../list.scss'
})
export class DirectorsComponent {


  directors: Director[] = []
  myForm: FormGroup

  constructor(private directorService: DirectorService, private fb: FormBuilder) { }

  ngOnInit(): void {
    this.loadAllDirectors()
    this.myForm = this.fb.group({
      firstName: new FormControl('', [Validators.required]),
      lastName: new FormControl('', [Validators.required]),
      description: '',
      birthDate: new FormControl('', control => {
        const value = control.value
        if (value && value instanceof Date) {
          const year = value.getFullYear();
          return (year >= 1788 && year <= 2020) ? null : { 'rangeError': true };
        }
        return null;
      }),
    })
  }
  loadAllDirectors() {
    this.directorService.getDirectors().subscribe(data => {
      this.directors = data as Director[]
    })
  }

  onDirectorAdded() {
    const firstName = this.myForm.get('firstName').value
    const lastName = this.myForm.get('lastName').value
    let description = this.myForm.get('description').value
    const birthDate = this.myForm.get('birthDate').value

    if (description == '') {
      description = 'No description'
    }

    const director = { firstName, lastName, description, birthDate }
    console.log(director)
    this.directorService.addDirector(director).subscribe(data => {
      console.log(data)
      this.loadAllDirectors()
      this.myForm.reset()
    })

  }
}



