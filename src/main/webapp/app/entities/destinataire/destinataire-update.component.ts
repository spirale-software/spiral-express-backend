import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';

import { IDestinataire, Destinataire } from 'app/shared/model/destinataire.model';
import { DestinataireService } from './destinataire.service';
import { IPersonne } from 'app/shared/model/personne.model';
import { PersonneService } from 'app/entities/personne/personne.service';
import { IClient } from 'app/shared/model/client.model';
import { ClientService } from 'app/entities/client/client.service';

type SelectableEntity = IPersonne | IClient;

@Component({
  selector: 'jhi-destinataire-update',
  templateUrl: './destinataire-update.component.html',
})
export class DestinataireUpdateComponent implements OnInit {
  isSaving = false;
  personnes: IPersonne[] = [];
  clients: IClient[] = [];

  editForm = this.fb.group({
    id: [],
    personne: [],
    client: [],
  });

  constructor(
    protected destinataireService: DestinataireService,
    protected personneService: PersonneService,
    protected clientService: ClientService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ destinataire }) => {
      this.updateForm(destinataire);

      this.personneService
        .query({ filter: 'destinataire-is-null' })
        .pipe(
          map((res: HttpResponse<IPersonne[]>) => {
            return res.body || [];
          })
        )
        .subscribe((resBody: IPersonne[]) => {
          if (!destinataire.personne || !destinataire.personne.id) {
            this.personnes = resBody;
          } else {
            this.personneService
              .find(destinataire.personne.id)
              .pipe(
                map((subRes: HttpResponse<IPersonne>) => {
                  return subRes.body ? [subRes.body].concat(resBody) : resBody;
                })
              )
              .subscribe((concatRes: IPersonne[]) => (this.personnes = concatRes));
          }
        });

      this.clientService.query().subscribe((res: HttpResponse<IClient[]>) => (this.clients = res.body || []));
    });
  }

  updateForm(destinataire: IDestinataire): void {
    this.editForm.patchValue({
      id: destinataire.id,
      personne: destinataire.personne,
      client: destinataire.client,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const destinataire = this.createFromForm();
    if (destinataire.id !== undefined) {
      this.subscribeToSaveResponse(this.destinataireService.update(destinataire));
    } else {
      this.subscribeToSaveResponse(this.destinataireService.create(destinataire));
    }
  }

  private createFromForm(): IDestinataire {
    return {
      ...new Destinataire(),
      id: this.editForm.get(['id'])!.value,
      personne: this.editForm.get(['personne'])!.value,
      client: this.editForm.get(['client'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IDestinataire>>): void {
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

  trackById(index: number, item: SelectableEntity): any {
    return item.id;
  }
}
