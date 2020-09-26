import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IEnvoi } from 'app/shared/model/envoi.model';

@Component({
  selector: 'jhi-envoi-detail',
  templateUrl: './envoi-detail.component.html',
})
export class EnvoiDetailComponent implements OnInit {
  envoi: IEnvoi | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ envoi }) => (this.envoi = envoi));
  }

  previousState(): void {
    window.history.back();
  }
}
