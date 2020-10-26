import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';

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

  editForm = this.fb.group({
    id: [],
    dateCreation: [],
    statut: [],
    reference: [],
    rapportQuai: [],
    rapportLivraisaon: [],
    montant: [],
    coli: [],
    expediteur: [],
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
      if (!envoi.id) {
        const today = moment().startOf('day');
        envoi.dateCreation = today;
      }

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

      this.clientService.query().subscribe((res: HttpResponse<IClient[]>) => (this.clients = res.body || []));

      this.destinataireService.query().subscribe((res: HttpResponse<IDestinataire[]>) => (this.destinataires = res.body || []));
    });
  }

  updateForm(envoi: IEnvoi): void {
    this.editForm.patchValue({
      id: envoi.id,
      dateCreation: envoi.dateCreation ? envoi.dateCreation.format(DATE_TIME_FORMAT) : null,
      statut: envoi.statut,
      reference: envoi.reference,
      rapportQuai: envoi.rapportQuai,
      rapportLivraisaon: envoi.rapportLivraisaon,
      montant: envoi.montant,
      coli: envoi.coli,
      expediteur: envoi.expediteur,
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
      dateCreation: this.editForm.get(['dateCreation'])!.value
        ? moment(this.editForm.get(['dateCreation'])!.value, DATE_TIME_FORMAT)
        : undefined,
      statut: this.editForm.get(['statut'])!.value,
      reference: this.editForm.get(['reference'])!.value,
      rapportQuai: this.editForm.get(['rapportQuai'])!.value,
      rapportLivraisaon: this.editForm.get(['rapportLivraisaon'])!.value,
      montant: this.editForm.get(['montant'])!.value,
      coli: this.editForm.get(['coli'])!.value,
      expediteur: this.editForm.get(['expediteur'])!.value,
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
