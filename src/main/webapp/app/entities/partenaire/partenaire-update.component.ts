import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';

import { IPartenaire, Partenaire } from 'app/shared/model/partenaire.model';
import { PartenaireService } from './partenaire.service';
import { IPersonne } from 'app/shared/model/personne.model';
import { PersonneService } from 'app/entities/personne/personne.service';

@Component({
  selector: 'jhi-partenaire-update',
  templateUrl: './partenaire-update.component.html',
})
export class PartenaireUpdateComponent implements OnInit {
  isSaving = false;
  personnes: IPersonne[] = [];

  editForm = this.fb.group({
    id: [],
    numero: [],
    personne: [],
  });

  constructor(
    protected partenaireService: PartenaireService,
    protected personneService: PersonneService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ partenaire }) => {
      this.updateForm(partenaire);

      this.personneService
        .query({ filter: 'partenaire-is-null' })
        .pipe(
          map((res: HttpResponse<IPersonne[]>) => {
            return res.body || [];
          })
        )
        .subscribe((resBody: IPersonne[]) => {
          if (!partenaire.personne || !partenaire.personne.id) {
            this.personnes = resBody;
          } else {
            this.personneService
              .find(partenaire.personne.id)
              .pipe(
                map((subRes: HttpResponse<IPersonne>) => {
                  return subRes.body ? [subRes.body].concat(resBody) : resBody;
                })
              )
              .subscribe((concatRes: IPersonne[]) => (this.personnes = concatRes));
          }
        });
    });
  }

  updateForm(partenaire: IPartenaire): void {
    this.editForm.patchValue({
      id: partenaire.id,
      numero: partenaire.numero,
      personne: partenaire.personne,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const partenaire = this.createFromForm();
    if (partenaire.id !== undefined) {
      this.subscribeToSaveResponse(this.partenaireService.update(partenaire));
    } else {
      this.subscribeToSaveResponse(this.partenaireService.create(partenaire));
    }
  }

  private createFromForm(): IPartenaire {
    return {
      ...new Partenaire(),
      id: this.editForm.get(['id'])!.value,
      numero: this.editForm.get(['numero'])!.value,
      personne: this.editForm.get(['personne'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IPartenaire>>): void {
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

  trackById(index: number, item: IPersonne): any {
    return item.id;
  }
}
