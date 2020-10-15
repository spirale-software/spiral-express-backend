import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IAdresse, Adresse } from 'app/shared/model/adresse.model';
import { AdresseService } from './adresse.service';

@Component({
  selector: 'jhi-adresse-update',
  templateUrl: './adresse-update.component.html',
})
export class AdresseUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    pays: [],
    ville: [],
    codePostal: [],
    rue: [],
  });

  constructor(protected adresseService: AdresseService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ adresse }) => {
      this.updateForm(adresse);
    });
  }

  updateForm(adresse: IAdresse): void {
    this.editForm.patchValue({
      id: adresse.id,
      pays: adresse.pays,
      ville: adresse.ville,
      codePostal: adresse.codePostal,
      rue: adresse.rue,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const adresse = this.createFromForm();
    if (adresse.id !== undefined) {
      this.subscribeToSaveResponse(this.adresseService.update(adresse));
    } else {
      this.subscribeToSaveResponse(this.adresseService.create(adresse));
    }
  }

  private createFromForm(): IAdresse {
    return {
      ...new Adresse(),
      id: this.editForm.get(['id'])!.value,
      pays: this.editForm.get(['pays'])!.value,
      ville: this.editForm.get(['ville'])!.value,
      codePostal: this.editForm.get(['codePostal'])!.value,
      rue: this.editForm.get(['rue'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IAdresse>>): void {
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
