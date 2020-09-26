import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IEnvoi } from 'app/shared/model/envoi.model';
import { EnvoiService } from './envoi.service';

@Component({
  templateUrl: './envoi-delete-dialog.component.html',
})
export class EnvoiDeleteDialogComponent {
  envoi?: IEnvoi;

  constructor(protected envoiService: EnvoiService, public activeModal: NgbActiveModal, protected eventManager: JhiEventManager) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.envoiService.delete(id).subscribe(() => {
      this.eventManager.broadcast('envoiListModification');
      this.activeModal.close();
    });
  }
}
