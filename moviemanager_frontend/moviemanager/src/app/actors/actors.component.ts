import { Component } from '@angular/core';
import { Actor } from '../models/actor.model';
import { ActorService } from '../services/actor.service';

@Component({
  selector: 'app-actors',
  templateUrl: './actors.component.html',
  styleUrl: '../list.scss'
})
export class ActorsComponent {
  actors: Actor[] = []

  constructor(private actorService: ActorService) { }

  ngOnInit(): void {
    this.loadAllActors()
  }

  loadAllActors() {
    this.actorService.getAllActors().subscribe(data => {
      this.actors = data as Actor[]
    })
  }

}

