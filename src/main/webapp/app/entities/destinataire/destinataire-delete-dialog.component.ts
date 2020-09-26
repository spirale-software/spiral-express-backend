import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IDestinataire } from 'app/shared/model/destinataire.model';
import { DestinataireService } from './destinataire.service';

@Component({
  templateUrl: './destinataire-delete-dialog.component.html',
})
export class DestinataireDeleteDialogComponent {
  destinataire?: IDestinataire;

  constructor(
    protected destinataireService: DestinataireService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.destinataireService.delete(id).subscribe(() => {
      this.eventManager.broadcast('destinataireListModification');
      this.activeModal.close();
    });
  }
}
