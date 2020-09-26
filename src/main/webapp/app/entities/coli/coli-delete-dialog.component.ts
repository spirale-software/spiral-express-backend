import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IColi } from 'app/shared/model/coli.model';
import { ColiService } from './coli.service';

@Component({
  templateUrl: './coli-delete-dialog.component.html',
})
export class ColiDeleteDialogComponent {
  coli?: IColi;

  constructor(protected coliService: ColiService, public activeModal: NgbActiveModal, protected eventManager: JhiEventManager) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.coliService.delete(id).subscribe(() => {
      this.eventManager.broadcast('coliListModification');
      this.activeModal.close();
    });
  }
}
