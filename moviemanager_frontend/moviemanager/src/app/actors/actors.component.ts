import { Component } from '@angular/core';
import { Actor } from '../models/actor.model';
import { ActorService } from '../services/actor.service';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-actors',
  templateUrl: './actors.component.html',
  styleUrl: '../list.scss'
})
export class ActorsComponent {

  actors: Actor[] = []
  myForm: FormGroup

  constructor(private actorService: ActorService, private fb: FormBuilder) { }

  ngOnInit(): void {
    this.loadAllActors()
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

  onActorAdded() {
    const firstName = this.myForm.get('firstName').value
    const lastName = this.myForm.get('lastName').value
    let description = this.myForm.get('description').value
    const birthDate = this.myForm.get('birthDate').value

    if (description == '') {
      description = 'No description'
    }

    const actor = { firstName, lastName, description, birthDate }
    console.log(actor)
    this.actorService.addActor(actor).subscribe(data => {
      console.log(data)
      this.loadAllActors()
    })

  }

  loadAllActors() {
    this.actorService.getAllActors().subscribe(data => {
      this.actors = data as Actor[]
    })
  }

}

