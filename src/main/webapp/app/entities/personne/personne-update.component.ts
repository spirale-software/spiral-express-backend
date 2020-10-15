import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';

import { IPersonne, Personne } from 'app/shared/model/personne.model';
import { PersonneService } from './personne.service';
import { IAdresse } from 'app/shared/model/adresse.model';
import { AdresseService } from 'app/entities/adresse/adresse.service';

@Component({
  selector: 'jhi-personne-update',
  templateUrl: './personne-update.component.html',
})
export class PersonneUpdateComponent implements OnInit {
  isSaving = false;
  adresses: IAdresse[] = [];

  editForm = this.fb.group({
    id: [],
    nom: [],
    prenom: [],
    telephone: [],
    email: [],
    adresse: [],
  });

  constructor(
    protected personneService: PersonneService,
    protected adresseService: AdresseService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ personne }) => {
      this.updateForm(personne);

      this.adresseService
        .query({ filter: 'personne-is-null' })
        .pipe(
          map((res: HttpResponse<IAdresse[]>) => {
            return res.body || [];
          })
        )
        .subscribe((resBody: IAdresse[]) => {
          if (!personne.adresse || !personne.adresse.id) {
            this.adresses = resBody;
          } else {
            this.adresseService
              .find(personne.adresse.id)
              .pipe(
                map((subRes: HttpResponse<IAdresse>) => {
                  return subRes.body ? [subRes.body].concat(resBody) : resBody;
                })
              )
              .subscribe((concatRes: IAdresse[]) => (this.adresses = concatRes));
          }
        });
    });
  }

  updateForm(personne: IPersonne): void {
    this.editForm.patchValue({
      id: personne.id,
      nom: personne.nom,
      prenom: personne.prenom,
      telephone: personne.telephone,
      email: personne.email,
      adresse: personne.adresse,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const personne = this.createFromForm();
    if (personne.id !== undefined) {
      this.subscribeToSaveResponse(this.personneService.update(personne));
    } else {
      this.subscribeToSaveResponse(this.personneService.create(personne));
    }
  }

  private createFromForm(): IPersonne {
    return {
      ...new Personne(),
      id: this.editForm.get(['id'])!.value,
      nom: this.editForm.get(['nom'])!.value,
      prenom: this.editForm.get(['prenom'])!.value,
      telephone: this.editForm.get(['telephone'])!.value,
      email: this.editForm.get(['email'])!.value,
      adresse: this.editForm.get(['adresse'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IPersonne>>): void {
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

  trackById(index: number, item: IAdresse): any {
    return item.id;
  }
}
