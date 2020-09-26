import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';

import { IClient, Client } from 'app/shared/model/client.model';
import { ClientService } from './client.service';
import { IPersonne } from 'app/shared/model/personne.model';
import { PersonneService } from 'app/entities/personne/personne.service';

@Component({
  selector: 'jhi-client-update',
  templateUrl: './client-update.component.html',
})
export class ClientUpdateComponent implements OnInit {
  isSaving = false;
  personnes: IPersonne[] = [];

  editForm = this.fb.group({
    id: [],
    numero: [],
    personne: [],
  });

  constructor(
    protected clientService: ClientService,
    protected personneService: PersonneService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ client }) => {
      this.updateForm(client);

      this.personneService
        .query({ filter: 'client-is-null' })
        .pipe(
          map((res: HttpResponse<IPersonne[]>) => {
            return res.body || [];
          })
        )
        .subscribe((resBody: IPersonne[]) => {
          if (!client.personne || !client.personne.id) {
            this.personnes = resBody;
          } else {
            this.personneService
              .find(client.personne.id)
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

  updateForm(client: IClient): void {
    this.editForm.patchValue({
      id: client.id,
      numero: client.numero,
      personne: client.personne,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const client = this.createFromForm();
    if (client.id !== undefined) {
      this.subscribeToSaveResponse(this.clientService.update(client));
    } else {
      this.subscribeToSaveResponse(this.clientService.create(client));
    }
  }

  private createFromForm(): IClient {
    return {
      ...new Client(),
      id: this.editForm.get(['id'])!.value,
      numero: this.editForm.get(['numero'])!.value,
      personne: this.editForm.get(['personne'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IClient>>): void {
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
