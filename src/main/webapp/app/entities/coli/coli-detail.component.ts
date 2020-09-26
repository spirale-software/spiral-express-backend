import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IColi } from 'app/shared/model/coli.model';

@Component({
  selector: 'jhi-coli-detail',
  templateUrl: './coli-detail.component.html',
})
export class ColiDetailComponent implements OnInit {
  coli: IColi | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ coli }) => (this.coli = coli));
  }

  previousState(): void {
    window.history.back();
  }
}
