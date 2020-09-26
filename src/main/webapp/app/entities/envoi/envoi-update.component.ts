import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';

import { IEnvoi, Envoi } from 'app/shared/model/envoi.model';
import { EnvoiService } from './envoi.service';
import { IColi } from 'app/shared/model/coli.model';
import { ColiService } from 'app/entities/coli/coli.service';
import { IClient } from 'app/shared/model/client.model';
import { ClientService } from 'app/entities/client/client.service';
import { IDestinataire } from 'app/shared/model/destinataire.model';
import { DestinataireService } from 'app/entities/destinataire/destinataire.service';

type SelectableEntity = IColi | IClient | IDestinataire;

@Component({
  selector: 'jhi-envoi-update',
  templateUrl: './envoi-update.component.html',
})
export class EnvoiUpdateComponent implements OnInit {
  isSaving = false;
  colis: IColi[] = [];
  clients: IClient[] = [];
  destinataires: IDestinataire[] = [];
  dateCreationDp: any;

  editForm = this.fb.group({
    id: [],
    dateCreation: [],
    statut: [],
    reference: [],
    rapportQuai: [],
    rapportLivraisaon: [],
    coli: [],
    client: [],
    destinataire: [],
  });

  constructor(
    protected envoiService: EnvoiService,
    protected coliService: ColiService,
    protected clientService: ClientService,
    protected destinataireService: DestinataireService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ envoi }) => {
      this.updateForm(envoi);

      this.coliService
        .query({ filter: 'envoi-is-null' })
        .pipe(
          map((res: HttpResponse<IColi[]>) => {
            return res.body || [];
          })
        )
        .subscribe((resBody: IColi[]) => {
          if (!envoi.coli || !envoi.coli.id) {
            this.colis = resBody;
          } else {
            this.coliService
              .find(envoi.coli.id)
              .pipe(
                map((subRes: HttpResponse<IColi>) => {
                  return subRes.body ? [subRes.body].concat(resBody) : resBody;
                })
              )
              .subscribe((concatRes: IColi[]) => (this.colis = concatRes));
          }
        });

      this.clientService
        .query({ filter: 'envoi-is-null' })
        .pipe(
          map((res: HttpResponse<IClient[]>) => {
            return res.body || [];
          })
        )
        .subscribe((resBody: IClient[]) => {
          if (!envoi.client || !envoi.client.id) {
            this.clients = resBody;
          } else {
            this.clientService
              .find(envoi.client.id)
              .pipe(
                map((subRes: HttpResponse<IClient>) => {
                  return subRes.body ? [subRes.body].concat(resBody) : resBody;
                })
              )
              .subscribe((concatRes: IClient[]) => (this.clients = concatRes));
          }
        });

      this.destinataireService
        .query({ filter: 'envoi-is-null' })
        .pipe(
          map((res: HttpResponse<IDestinataire[]>) => {
            return res.body || [];
          })
        )
        .subscribe((resBody: IDestinataire[]) => {
          if (!envoi.destinataire || !envoi.destinataire.id) {
            this.destinataires = resBody;
          } else {
            this.destinataireService
              .find(envoi.destinataire.id)
              .pipe(
                map((subRes: HttpResponse<IDestinataire>) => {
                  return subRes.body ? [subRes.body].concat(resBody) : resBody;
                })
              )
              .subscribe((concatRes: IDestinataire[]) => (this.destinataires = concatRes));
          }
        });
    });
  }

  updateForm(envoi: IEnvoi): void {
    this.editForm.patchValue({
      id: envoi.id,
      dateCreation: envoi.dateCreation,
      statut: envoi.statut,
      reference: envoi.reference,
      rapportQuai: envoi.rapportQuai,
      rapportLivraisaon: envoi.rapportLivraisaon,
      coli: envoi.coli,
      client: envoi.client,
      destinataire: envoi.destinataire,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const envoi = this.createFromForm();
    if (envoi.id !== undefined) {
      this.subscribeToSaveResponse(this.envoiService.update(envoi));
    } else {
      this.subscribeToSaveResponse(this.envoiService.create(envoi));
    }
  }

  private createFromForm(): IEnvoi {
    return {
      ...new Envoi(),
      id: this.editForm.get(['id'])!.value,
      dateCreation: this.editForm.get(['dateCreation'])!.value,
      statut: this.editForm.get(['statut'])!.value,
      reference: this.editForm.get(['reference'])!.value,
      rapportQuai: this.editForm.get(['rapportQuai'])!.value,
      rapportLivraisaon: this.editForm.get(['rapportLivraisaon'])!.value,
      coli: this.editForm.get(['coli'])!.value,
      client: this.editForm.get(['client'])!.value,
      destinataire: this.editForm.get(['destinataire'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IEnvoi>>): void {
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
