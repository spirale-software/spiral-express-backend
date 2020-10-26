import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';

import { IEntreprise, Entreprise } from 'app/shared/model/entreprise.model';
import { EntrepriseService } from './entreprise.service';

@Component({
  selector: 'jhi-entreprise-update',
  templateUrl: './entreprise-update.component.html',
})
export class EntrepriseUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    dateCreation: [],
    dateModification: [],
    nom: [],
    numero: [],
    actif: [],
  });

  constructor(protected entrepriseService: EntrepriseService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ entreprise }) => {
      if (!entreprise.id) {
        const today = moment().startOf('day');
        entreprise.dateCreation = today;
        entreprise.dateModification = today;
      }

      this.updateForm(entreprise);
    });
  }

  updateForm(entreprise: IEntreprise): void {
    this.editForm.patchValue({
      id: entreprise.id,
      dateCreation: entreprise.dateCreation ? entreprise.dateCreation.format(DATE_TIME_FORMAT) : null,
      dateModification: entreprise.dateModification ? entreprise.dateModification.format(DATE_TIME_FORMAT) : null,
      nom: entreprise.nom,
      numero: entreprise.numero,
      actif: entreprise.actif,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const entreprise = this.createFromForm();
    if (entreprise.id !== undefined) {
      this.subscribeToSaveResponse(this.entrepriseService.update(entreprise));
    } else {
      this.subscribeToSaveResponse(this.entrepriseService.create(entreprise));
    }
  }

  private createFromForm(): IEntreprise {
    return {
      ...new Entreprise(),
      id: this.editForm.get(['id'])!.value,
      dateCreation: this.editForm.get(['dateCreation'])!.value
        ? moment(this.editForm.get(['dateCreation'])!.value, DATE_TIME_FORMAT)
        : undefined,
      dateModification: this.editForm.get(['dateModification'])!.value
        ? moment(this.editForm.get(['dateModification'])!.value, DATE_TIME_FORMAT)
        : undefined,
      nom: this.editForm.get(['nom'])!.value,
      numero: this.editForm.get(['numero'])!.value,
      actif: this.editForm.get(['actif'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IEntreprise>>): void {
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
