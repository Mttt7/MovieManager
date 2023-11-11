import { Component } from '@angular/core';
import { Actor } from '../../models/actor.model';
import { ActorService } from '../../services/actor.service';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-actor',
  templateUrl: './actor.component.html',
  styleUrl: './actor.component.scss'
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
}


