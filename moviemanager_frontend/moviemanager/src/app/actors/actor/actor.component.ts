import { Component } from '@angular/core';
import { Actor } from '../../models/actor.model';
import { ActorService } from '../../services/actor.service';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-actor',
  templateUrl: './actor.component.html',
  styleUrl: '../../person.scss'
})
export class ActorComponent {


  id: number = 0
  actor: Actor = null

  constructor(private actorService: ActorService,
    private route: ActivatedRoute) { }

  ngOnInit(): void {
    this.id = this.route.snapshot.params['id']
    this.loadActor()
  }
  loadActor() {
    this.actorService.getActorWithMovies(this.id).subscribe(data => {
      this.actor = data as Actor
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


