import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IColi, Coli } from 'app/shared/model/coli.model';
import { ColiService } from './coli.service';

@Component({
  selector: 'jhi-coli-update',
  templateUrl: './coli-update.component.html',
})
export class ColiUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    description: [],
    longueur: [],
    largeur: [],
    hauteur: [],
    poids: [],
  });

  constructor(protected coliService: ColiService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ coli }) => {
      this.updateForm(coli);
    });
  }

  updateForm(coli: IColi): void {
    this.editForm.patchValue({
      id: coli.id,
      description: coli.description,
      longueur: coli.longueur,
      largeur: coli.largeur,
      hauteur: coli.hauteur,
      poids: coli.poids,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const coli = this.createFromForm();
    if (coli.id !== undefined) {
      this.subscribeToSaveResponse(this.coliService.update(coli));
    } else {
      this.subscribeToSaveResponse(this.coliService.create(coli));
    }
  }

  private createFromForm(): IColi {
    return {
      ...new Coli(),
      id: this.editForm.get(['id'])!.value,
      description: this.editForm.get(['description'])!.value,
      longueur: this.editForm.get(['longueur'])!.value,
      largeur: this.editForm.get(['largeur'])!.value,
      hauteur: this.editForm.get(['hauteur'])!.value,
      poids: this.editForm.get(['poids'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IColi>>): void {
    result.subscribe(
      () => this.onSaveSuccess(),
      () => this.onSaveError()
    );
  }

  protected onSaveSuccess(): void {
    this.isSaving = false;
    this.previousState();
  }

  protected onSaveError(): void {
    this.isSaving = false;
  }
}
